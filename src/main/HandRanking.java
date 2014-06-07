package main;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import model.Card;
import model.Card.Suit;

/**
 * Utility class which ranks the strength of 5 card poker hands
 * @author dgibbs
 *
 */
public class HandRanking {
	
	private static int PAIR = 2;
	private static int TRIPLE = 3;
	private static int QUAD = 4;
	private static int SPREAD = 15;
	private static int HAND_SIZE = 5;
	
	/**
	 * Assesses the strength of a given hand based on its cards, assigning a numeric value
	 * The value returned is comparable to the value for any other hand in order to determine a winner
	 * @param hand to be valued
	 * @return a value identifying the strength of the given hand
	 */
	public static long rankHand(ArrayList<Card> hand){
		if (hand.size() != HAND_SIZE){
			throw new GameException("Hand should be 5 cards : " + hand);
		}

		long rank = -1;
		long[] pairSet;
		long highCard;
		ArrayList<Long> excludes = new ArrayList<Long>();

		sortHand(hand);

		if ( isStraightFlush(hand) ){
			highCard = straightHigh(hand);
			rank = 10000000000000l * highCard;
			return rank;
		}
		if ( ( rank = hasMultiple(hand, -1, QUAD) ) > 0 ){
			excludes.add(rank);
			rank = 1000000000000l * rank + highCard(hand, excludes);
			return rank;
		}
		pairSet = isFullHouse(hand);
		if ( pairSet[0] > 0 ){
			rank = 100000000000l * pairSet[0] + pairSet[1];
			return rank;
		}
		if ( isFlush(hand) ){
			highCard = highCard(hand, excludes);
			excludes.add(highCard);
			rank = (10000000000l * highCard) + kickerValues(hand, excludes, HAND_SIZE - 1);
			return rank;
		}
		if ( isStraight(hand) ){
			rank = 1000000000l * straightHigh(hand);
			return rank;
		}
		if ( ( rank = hasMultiple(hand, -1, TRIPLE) ) > 0 ){
			excludes.add(rank);
			rank *= 100000000l;
			return rank + kickerValues(hand, excludes, HAND_SIZE - 3);
		}
		pairSet = isTwoPair(hand);
		if ( pairSet[0] > 0 ){
			excludes.add(pairSet[0]);
			excludes.add(pairSet[1]);
			rank =  (10000000l * pairSet[0]) + (pairSet[1] * SPREAD) + highCard(hand, excludes);
			return rank;
		}
		if ( ( rank = hasMultiple(hand, -1, PAIR) ) > 0 ){
			excludes.add(rank);
			rank *= 1000000l;
			return rank + kickerValues(hand, excludes, HAND_SIZE - 2);
		}
		else {
			rank = kickerValues(hand, excludes, HAND_SIZE);
			return rank;
		}
	}

	/**
	 * Determines whether the given hand is a straight flush
	 * @param hand to be searched for a straight flush
	 * @return true if the hand is a straight flush, false otherwise
	 */
	private static boolean isStraightFlush(ArrayList<Card> hand){
		if (isFlush(hand) && isStraight(hand)){
			return true;
		}
		return false;
	}

	/**
	 * Determines whether a hand is a full house
	 * @param hand to be searched for a full house
	 * @return a length 2 array containing the numeric values of the fullhouse in the form [TRIPLET, PAIR] 
	 * 			<br> [-1, -1] if the hand is not a full house
	 */
	private static long[] isFullHouse(ArrayList<Card> hand){
		long[] house = {-1, -1};
		long triplet = hasMultiple(hand, -1, TRIPLE);
		if (triplet > 0){
			long secondPair = hasMultiple(hand, triplet, PAIR);
			if (secondPair > 0){
				house[0] = triplet;
				house[1] = secondPair;
			}
		}
		return house;
	}

	/**
	 * Determines whether the given hand is a flush
	 * @param hand to be searched for a flush
	 * @return true if the hand is a flush, false otherwise
	 */
	private static boolean isFlush(ArrayList<Card> hand){
		Enum<Suit> suit = hand.get(0).getSuit();
		for (Card card : hand){
			if (card.getSuit() != suit){
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines whether the given hand is a straight
	 * @param hand to be searched for a straight
	 * @return true if the hand is a straight, false otherwise
	 */
	private static boolean isStraight(ArrayList<Card> hand){
		int lowCard = hand.get(0).getValue();
		int highCard = hand.get(4).getValue();
		int checkCount = hand.size();
		//Check A,2,3,4,5 edge case
		if (lowCard == 2 && highCard == 14){
			checkCount = 4;
		}
		for (int i = 1, current = lowCard; i < checkCount; ++i){
			if (hand.get(i).getValue() != current + 1)
				return false;
			current += 1;
		}
		return true;
	}

	/**
	 * Determines whether a hand contains two pairs
	 * @param hand to be searched for two pairs
	 * @return a length 2 array containing the numeric values of both pairs: [HIGH, LOW]
	 * 			<br> [-1, -1] if the hand does not contain two pairs
	 */
	private static long[] isTwoPair(ArrayList<Card> hand){
		long[] pairs = {-1, -1};
		long lowPair = hasMultiple(hand, -1, PAIR);
		if (lowPair > 0){
			long highPair = hasMultiple(hand, lowPair, PAIR);
			if (highPair > 0 ){
				pairs[0] = highPair;
				pairs[1] = lowPair;
			}
		}
		return pairs;
	}

	/**
	 * Generic function to determine if a hand has more than one of any numeric value
	 * A value can be excluded from the search using the exclusion parameter
	 * @param hand to be searched for common numeric card values
	 * @param exclusion	value to be excluded from the search
	 * @param multiplicity one of Util.PAIR, Util.TRIPLE, Util.QUAD indicating how many common values to look for
	 * @return the numeric value satisfying the search criteria, 
	 * 			<br> -1 if no value in the hand has the given multiplicity
	 */
	private static long hasMultiple(ArrayList<Card> hand, long exclusion, int multiplicity){
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (Card card : hand){
			int value = card.getValue();
			Integer count = counts.get(value);
			if (count == null){
				counts.put(value, 1);
				count = 1;
			}
			else {
				counts.put(value, ++count);
			}
			if (count.intValue() == multiplicity){
				if (exclusion < 0 || value != exclusion){
					return value;
				}
			}
		}
		return -1;
	}

	/**
	 * Returns the highest card in a given hand excluding the specified values
	 * @param hand to be searched for the highest value
	 * @param exclude numeric card values to be excluded from the search
	 * @return the numeric value for the highest card in hand
	 */
	private static long highCard(ArrayList<Card> hand, ArrayList<Long> exclude){
		for (int i = 4; i >= 0; --i) {
			boolean keep = true;
			Card card = hand.get(i);
			for (long excluded : exclude){
				if (card.getValue() == excluded){
					keep = false;
					break;
				}
			}
			if (keep) return card.getValue();	
		}
		System.err.println("WARNING : searched for high card, and found none");
		System.err.println("Hand: " + hand + " , excludes: " + exclude);
		return -1;
	}
	
	/**
	 * Determines the value for a specified number of kickers in the hand
	 * @param hand to have kicker cards values
	 * @param excludes values to be excluded as kickers. eg. for a pair, the pair value should be excluded
	 * @param kickerCount the number of kickers to include in the valuation
	 * @return a numeric value for the hand's kicker cards
	 */
	private static long kickerValues(ArrayList<Card> hand, ArrayList<Long> excludes, int kickerCount){
		long value = 0;
		for (int i = (HAND_SIZE - kickerCount); i < HAND_SIZE; ++i){
			long highCard = highCard(hand, excludes);
			excludes.add(highCard);
			double multiplier = Math.pow(SPREAD, HAND_SIZE - (i + 1));
			value +=  multiplier * highCard;
		}
		return value;
	}

	/**
	 * Sorts a given hand by numeric value
	 * @param hand to be sorted
	 */
	private static void sortHand(ArrayList<Card> hand){
		Collections.sort(hand, new Comparator<Card>(){

			@Override
			public int compare(Card one, Card two) {

				return one.getValue() - two.getValue();

			}});
	}
	
	/**
	 * Returns the high card for a hand which is known to be a straight
	 * @param hand which is known to be a straight
	 * @return numeric value for the hand's high card 
	 */
	private static long straightHigh(ArrayList<Card> hand){
		int last = hand.get(4).getValue();
		int previous = hand.get(3).getValue();
		if ( last == 14 && previous != 13){
			return previous;
		}
		return last;
	}
}
