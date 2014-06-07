package display;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Card;
import model.Player;
import model.PlayerHand;

/**
 * Panel to represent a computer player and their cards
 * @author dgibbs
 */
public class OpponentPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private JLabel message;
	private JLabel[] cards = new JLabel[2];
	private ImageIcon cardBack;
	
	public OpponentPanel (Player player){
		this.player = player;
		setBackground(UIConstants.TABLE_COLOR);
		JPanel cardPanel = new JPanel();
		cardPanel.setBackground(UIConstants.TABLE_COLOR);
		
		setPreferredSize(new Dimension(UIConstants.PLAYER_WIDTH, 150));
		
		cardBack = new ImageIcon(PokerTable.class.getResource("/cards/Red_Back.png"));
		Image image = cardBack.getImage();
		cardBack = new ImageIcon(image.getScaledInstance(70, -1, Image.SCALE_SMOOTH));
		cards[0] = new JLabel(cardBack);
		cards[1] = new JLabel(cardBack);
		cardPanel.add(cards[0]);
		cardPanel.add(cards[1]);
		message = new JLabel();
		message.setVisible(false);
		message.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		add(message);
		add(cardPanel);
		
	}
	
	/**
	 * Adjusts this panel to indicate that the player has won
	 * @param playerHand the winning hand
	 */
	public void setWinner(PlayerHand playerHand, String messageText) {
		message.setText(messageText);
		message.setVisible(true);
		message.invalidate();
		message.repaint();
		ArrayList<Card> playerCards = player.getCards();
		ArrayList<Card> winningHand = playerHand.getHand();
		for (int i = 0; i < 2; ++i){
			if (winningHand.contains(playerCards.get(i))){
				cards[i].setBorder(UIConstants.CARD_HIGHLIGHT);
			}
		}
	}
	
	/**
	 * Restores the interface for a new hand
	 */
	public void restore(){
		message.setText("");
		message.setVisible(false);
		message.invalidate();
		message.repaint();
		for (int i = 0; i < 2; ++i){
			cards[i].setBorder(BorderFactory.createEmptyBorder());
			cards[i].setIcon(cardBack);
			cards[i].repaint();
		}
	}
	
	/**
	 * Turns the cards in this position over to be seen
	 */
	public void showCards() {
		for (int i = 0; i < 2; ++i){
			cards[i].setIcon(Util.getScaledImage(player.getCards().get(i)));
			cards[i].repaint();
		}
	}

}
