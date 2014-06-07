package main;

import java.util.ArrayList;

import model.Card;
import model.Player;
import action.AdvanceHandAction;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import display.PokerTable;

/**
 * A game in which the user is participating, involving a user interface
 * This class acts as the controller, using an event bus to communicate
 * @author dgibbs
 *
 */
public class UserTournament extends Tournament{
		
	private EventBus bus;
	private PokerTable table;
	
	/**
	 * Constructs a new tournament with the user as one of the players
	 * @param opponents
	 */
	public UserTournament(ArrayList<Player> opponents) {
		super(opponents);
		this.bus = new EventBus();
		bus.register(this);
		dealHand();
		this.table = new PokerTable(getPlayers(), bus);
	}
	
	/**
	 * Opens the table interface
	 */
	public void start(){
		table.open();
	}
	
	@Subscribe
	/**
	 * Listens for events from the table and responds accordingly
	 * @param evt action received from the table
	 */
	public void handleTableAction(AdvanceHandAction evt){
		performHandAction();		
	}
	
	@Override
	/**
	 * Flips a single card from the deck, and notifies the table
	 */
	protected Card flipCard(){
		Card turned = super.flipCard();
		table.publishCommunityCard(turned);
		return turned;
	}
	
	@Override
	/**
	 * Communicates to the table that the hand is over
	 */
	protected void endHand(){
		table.endHand(checkHandWinner());
		super.endHand();
	}
	
	@Override
	/**
	 * Communicates to the table that a new hand should be started
	 */
	protected void newHand(){
		super.newHand();
		table.newHand();
	}
}
