package model;

/**
 * Models an individual card from a standard deck
 * @author dgibbs
 *
 */
public class Card {
	
	public static enum Suit {
		CLUB,DIAMOND,HEART,SPADE
	}
	
	private Enum<Suit> suit;
	private int value;
	
	public Card(Enum<Suit> suit, int value){
		if (value < 2 || value > 14){
			throw new CardException("Invalid value " + value + " for new Card (Must be 1-13)");
		}
		this.setSuit(suit);
		this.setValue(value);
	}
	
	@Override
	public String toString(){
		char suitType = suit.toString().charAt(0);
		String valueString;
		switch(value){
		case 11:
			valueString = "J";
			break;
		case 12:
			valueString = "Q";
			break;
		case 13:
			valueString = "K";
			break;
		case 14:
			valueString = "A";
			break;
		default: 
			valueString = "" + value;
		}
		return valueString + "_" + suitType;
	}
	
	public Enum<Suit> getSuit() {
		return suit;
	}

	public void setSuit(Enum<Suit> suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	class CardException extends RuntimeException {

		private static final long serialVersionUID = 1L;
		
		public CardException(String message){
			super(message);
		}
	}
}
