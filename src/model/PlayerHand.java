package model;

import java.util.ArrayList;

/**
 * Models the grouping of a player and their cards
 * @author dgibbs
 *
 */
public class PlayerHand {
	private Player player;
	private ArrayList<Card> hand;
	
	public PlayerHand(Player player, ArrayList<Card> hand){
		this.player = player;
		this.hand = hand;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
}
