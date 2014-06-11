package display;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import model.Card;
import model.Player;
import model.PlayerHand;
import action.AdvanceHandAction;

import com.google.common.eventbus.EventBus;

/**
 * GUI for a poker table
 * A game with up to 6 players (5x AI + the user) can be played at the table
 * Guava EventBus used to control exchanges between the model and view
 * @author dgibbs
 *
 */
public class PokerTable extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<JLabel> communityLabels= new ArrayList<JLabel>(5);
	private ArrayList<Card> communityCards = new ArrayList<Card>(5);
	
	public static final Dimension CARD_SIZE = new Dimension(70, 100);
	private HashMap<Player, OpponentPanel> panels = new HashMap<Player, OpponentPanel>();
	private JLabel lblUserCards;
	private EventBus bus;
	private JButton advanceGame;
	private JFrame window;
	private Player userPlayer;
	private JLabel userCardOne;
	private JLabel userCardTwo;
	
	/**
	 * Constructs the user interface for a poker table
	 * @param players to be seated at the table - first player is always taken to be user controlled
	 * @param bus EventBus where user actions should be posted
	 */
	public PokerTable(ArrayList<Player> players, EventBus bus) {
		this.bus = bus;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel avatarPanel = new JPanel();
		avatarPanel.setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		add(Box.createVerticalStrut(5));
		add(avatarPanel);
		avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.X_AXIS));
		avatarPanel.add(Box.createHorizontalGlue());
		
		JPanel tableContainer = new JPanel();
		
		tableContainer.setBackground(UIConstants.TABLE_COLOR);
		tableContainer.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		tableContainer.setPreferredSize(new Dimension(UIConstants.TABLE_WIDTH, 300));
		tableContainer.setLayout(new BoxLayout(tableContainer, BoxLayout.Y_AXIS));
		
		JPanel topTablePanel = new JPanel();
		topTablePanel.setLayout(new BoxLayout(topTablePanel, BoxLayout.X_AXIS));
		topTablePanel.setBackground(UIConstants.TABLE_COLOR);
		topTablePanel.add(Box.createHorizontalGlue());
		
		JPanel bottomTablePanel = new JPanel();
		bottomTablePanel.setBackground(UIConstants.TABLE_COLOR);
		bottomTablePanel.setLayout(new BoxLayout(bottomTablePanel, BoxLayout.X_AXIS));
		for (int i = 0; i < 5; ++i){
			JLabel communityCard = new JLabel();
			communityCard.setVisible(false);
			communityCard.setBackground(UIConstants.TABLE_COLOR);
			bottomTablePanel.add(communityCard);
			bottomTablePanel.add(Box.createHorizontalStrut(3));
			communityLabels.add(communityCard);			
		}
		
		topTablePanel.setBackground(UIConstants.TABLE_COLOR);
		
		tableContainer.add(topTablePanel);
		tableContainer.add(bottomTablePanel);
		add(tableContainer);
		
		for (int i = 1; i < players.size(); ++i){
			ImageIcon icon = Util.getScaledImage("/avatar/" + i + ".png", UIConstants.PLAYER_WIDTH, -1);
			JLabel toAdd = new JLabel(icon);
			avatarPanel.add(toAdd);
			Player player = players.get(i);
			OpponentPanel panel = new OpponentPanel(player);
			panels.put(player, panel);
			topTablePanel.add(panel);
			avatarPanel.add(Box.createHorizontalGlue());
			topTablePanel.add(Box.createHorizontalGlue());
		}
		
		JPanel userPanel = new JPanel();
		add(userPanel);
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
		userPanel.setPreferredSize(new Dimension(UIConstants.TABLE_WIDTH, 125));
		
		// Setup for the user panel;
		userPanel.add(Box.createHorizontalGlue());
		lblUserCards = new JLabel("Your cards: ");
		lblUserCards.setFont(new Font("Tahoma", Font.BOLD, 20));
		userPanel.add(lblUserCards);
		
		userPanel.add(Box.createHorizontalStrut(3));
		userPlayer = players.get(0);
		ArrayList<Card> playerOneCards = userPlayer.getCards();
		Card firstCard = playerOneCards.get(0);
		userCardOne = new JLabel(Util.getScaledImage(firstCard));
		userPanel.add(userCardOne);
		userPanel.add(Box.createHorizontalStrut(3));
		Card secondCard = playerOneCards.get(1);
		userCardTwo = new JLabel(Util.getScaledImage(secondCard));
		userPanel.add(userCardTwo);
		
		advanceGame = new JButton("Advance Game");
		advanceGame.addActionListener(new AdvanceGameAction());
		userPanel.add(Box.createHorizontalStrut(5));
		userPanel.add(advanceGame);

		userPanel.add(Box.createHorizontalGlue());
		
		// Initialize the frame
		window = new JFrame("Texas Holdem");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(this);
	}
	
	/**
	 * Opens this table so that it is visible to the user
	 */
	public void open(){
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	/**
	 * Listens for a community card action from the bus and displays the card on this table
	 * @param action containing the card to be displayed
	 */
	public void publishCommunityCard(Card card){
		JLabel label = communityLabels.get(communityCards.size());
		communityCards.add(card);
		label.setIcon(Util.getScaledImage(card));
		label.setVisible(true);
		label.invalidate();
		label.repaint();
	}
	
	/**
	 * Ends the current hand
	 * @param winners the winning PlayerHand combinations for this hand
	 */
	public void endHand(Vector<PlayerHand> winners){
		
		String winnersMessage = "Winner!";
		
		int cardIndex = -1;
		for (OpponentPanel panel : panels.values()){
			panel.showCards();
		}
		if (winners.size() > 1){
			winnersMessage = "Split pot";
		}
		
		for (PlayerHand winningHand : winners) {
			Player winner = winningHand.getPlayer();
			OpponentPanel panel = panels.get(winner);
			
			for (Card card : winningHand.getHand()){
				if ((cardIndex = communityCards.indexOf(card)) >= 0){
					communityLabels.get(cardIndex).setBorder(UIConstants.CARD_HIGHLIGHT);
				}
				int userIndex = userPlayer.getCards().indexOf(card);
				if (userIndex == 0){
					userCardOne.setBorder(UIConstants.CARD_HIGHLIGHT);
				}
				else if (userIndex == 1){
					userCardTwo.setBorder(UIConstants.CARD_HIGHLIGHT);
				}
			}
			if (panel == null){
				lblUserCards.setText(winnersMessage);
			}
			else {
			 panel.setWinner(winningHand, winnersMessage);
			}
		}
//		PlayerHand winningHand = winners.firstElement();
		
		advanceGame.setPreferredSize(advanceGame.getPreferredSize());
		advanceGame.setText("New Hand");
	}
	
	/**
	 * Resets the user interface so that a new hand can begin
	 */
	public void newHand(){
		for (OpponentPanel panel : panels.values()){
			panel.restore();
		}
		advanceGame.setText("Advance Game");
		for (JLabel cardLabel : communityLabels){
			cardLabel.setBorder(BorderFactory.createEmptyBorder());
			cardLabel.setVisible(false);
		}

		lblUserCards.setText("Your cards: ");
		communityCards.clear();
		ArrayList<Card> playerCards = userPlayer.getCards();
		Card firstCard = playerCards.get(0);
		userCardOne.setIcon(Util.getScaledImage(firstCard));
		userCardOne.setBorder(BorderFactory.createEmptyBorder());
		Card secondCard = playerCards.get(1);
		userCardTwo.setIcon(Util.getScaledImage(secondCard));
		userCardTwo.setBorder(BorderFactory.createEmptyBorder());
	}
	
	/**
	 * Swing action to be activated by user interaction
	 * Posts an AdvanceHandAction to the event bus which is a request that the hand be advanced
	 * @author dgibbs
	 *
	 */
	class AdvanceGameAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			bus.post(new AdvanceHandAction());
		}
	}
}
