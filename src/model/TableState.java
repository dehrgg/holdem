package model;


/**
 * Represents the current state of a poker table
 * @author dgibbs
 *
 */
public class TableState {
	
	public static final int PRE_FLOP = 0;
	public static final int FLOP = 1;
	public static final int TURN = 2;
	public static final int RIVER = 3;
	public static final int HAND_ENDED = 4;
	
//	private ArrayList<Card> communityCards = new ArrayList<>();
//	private ArrayList<Player> players;
//	private long pot;
	private int handProgress = PRE_FLOP;
	
	/**
	 * Advances the current progress of the hand
	 */
	public void advance(){
		
		if (handProgress == HAND_ENDED){
			handProgress = PRE_FLOP;
		}
		else {
			handProgress += 1;
		}
	}
	
	public int getHandProgress(){
		return handProgress;
	}
}
