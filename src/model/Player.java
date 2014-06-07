package model;

import java.util.ArrayList;

import main.GameException;

/**
 * Represents a player in a poker game
 * @author dgibbs
 *
 */
public class Player {
	
	private String name;
	private ArrayList<Card> cards = new ArrayList<Card>(2);
	private long chipCount;
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
	}
	
//	public abstract long getAction(long currentBet);
	
	public void dealCard(Card card){
		if (cards.size() >= 2){
			throw new GameException("Attempted to deal too many cards to Player " + name);
		}
		cards.add(card);
	}
	
	public ArrayList<Card> foldCards() {
		ArrayList<Card> folded = new ArrayList<Card>(cards);
		cards = new ArrayList<Card>();
		return folded;
	}
	
	public boolean placeBet(long amount){
		if (amount <= chipCount){
			chipCount -= amount;
			return true;
		}
		return false;
	}
	
	public long getChipCount() {
		return chipCount;
	}
	public void setChipCount(long chipCount) {
		this.chipCount = chipCount;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
