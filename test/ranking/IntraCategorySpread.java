package ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.HandRanking;
import model.Card;

import org.junit.Test;

/**
 * Test suite to ensure that hand categories are properly ranked against one another
 * Accomplished by ensuring that the lowest valued hand for any given category
 * beats the highest valued hand for the category directly below it
 * @author dgibbs
 *
 */
public class IntraCategorySpread {
	
	@Test
	public void highCardVsPair(){
		//Create the highest high card hand
		ArrayList<Card> highHighCard = new ArrayList<Card>();
		highHighCard.add(new Card(Card.Suit.SPADE, 14));
		highHighCard.add(new Card(Card.Suit.HEART, 13));
		highHighCard.add(new Card(Card.Suit.DIAMOND, 12));
		highHighCard.add(new Card(Card.Suit.SPADE, 11));
		highHighCard.add(new Card(Card.Suit.CLUB, 9));
		
		//Create the lowest single pair hand
		ArrayList<Card> lowPair = new ArrayList<Card>();
		lowPair.add(new Card(Card.Suit.SPADE, 2));
		lowPair.add(new Card(Card.Suit.HEART, 2));
		lowPair.add(new Card(Card.Suit.DIAMOND, 3));
		lowPair.add(new Card(Card.Suit.SPADE, 4));
		lowPair.add(new Card(Card.Suit.CLUB, 5));

		//Ensure lowest pair beats the highest high card
		assertTrue(HandRanking.rankHand(lowPair) > HandRanking.rankHand(highHighCard));
	}
	
	@Test
	public void pairVsTwoPair(){
		//Create highest single pair hand
		ArrayList<Card> highPair = new ArrayList<Card>();
		highPair.add(new Card(Card.Suit.SPADE, 14));
		highPair.add(new Card(Card.Suit.HEART, 14));
		highPair.add(new Card(Card.Suit.DIAMOND, 13));
		highPair.add(new Card(Card.Suit.SPADE, 12));
		highPair.add(new Card(Card.Suit.CLUB, 11));

		//Create lowest two pair hand
		ArrayList<Card> lowTwoPair = new ArrayList<Card>();
		lowTwoPair.add(new Card(Card.Suit.SPADE, 2));
		lowTwoPair.add(new Card(Card.Suit.HEART, 2));
		lowTwoPair.add(new Card(Card.Suit.DIAMOND, 3));
		lowTwoPair.add(new Card(Card.Suit.SPADE, 3));
		lowTwoPair.add(new Card(Card.Suit.CLUB, 4));

		//Ensure lowest two pair beats the highest single pair
		assertTrue(HandRanking.rankHand(lowTwoPair) > HandRanking.rankHand(highPair));
	}
	
	@Test
	public void twoPairVsTrip(){
		//Create highest two pair hand
		ArrayList<Card> highTwoPair = new ArrayList<Card>();
		highTwoPair.add(new Card(Card.Suit.SPADE, 14));
		highTwoPair.add(new Card(Card.Suit.HEART, 14));
		highTwoPair.add(new Card(Card.Suit.DIAMOND, 13));
		highTwoPair.add(new Card(Card.Suit.SPADE, 13));
		highTwoPair.add(new Card(Card.Suit.CLUB, 12));
		
		//Create lowest three of a kind hand
		ArrayList<Card> lowTrip = new ArrayList<Card>();
		lowTrip.add(new Card(Card.Suit.SPADE, 2));
		lowTrip.add(new Card(Card.Suit.HEART, 2));
		lowTrip.add(new Card(Card.Suit.DIAMOND, 2));
		lowTrip.add(new Card(Card.Suit.SPADE, 3));
		lowTrip.add(new Card(Card.Suit.CLUB, 4));

		//Ensure lowest three of a kind beats the highest two pair
		assertTrue(HandRanking.rankHand(lowTrip) > HandRanking.rankHand(highTwoPair));
	}
	
	@Test
	public void tripVsStraight(){
		//Create highest three of a kind hand
		ArrayList<Card> highTrip = new ArrayList<Card>();
		highTrip.add(new Card(Card.Suit.SPADE, 14));
		highTrip.add(new Card(Card.Suit.HEART, 14));
		highTrip.add(new Card(Card.Suit.DIAMOND, 14));
		highTrip.add(new Card(Card.Suit.SPADE, 13));
		highTrip.add(new Card(Card.Suit.CLUB, 12));
		
		//Create lowest straight hand
		ArrayList<Card> lowStraight = new ArrayList<Card>();
		lowStraight.add(new Card(Card.Suit.SPADE, 2));
		lowStraight.add(new Card(Card.Suit.HEART, 3));
		lowStraight.add(new Card(Card.Suit.DIAMOND, 4));
		lowStraight.add(new Card(Card.Suit.SPADE, 5));
		lowStraight.add(new Card(Card.Suit.CLUB, 14));

		//Ensure lowest straight beats the highest three of a kind
		assertTrue(HandRanking.rankHand(lowStraight) > HandRanking.rankHand(highTrip));
	}
	
	@Test
	public void straightVsFlush(){
		//Create highest straight hand
		ArrayList<Card> highStraight = new ArrayList<Card>();
		highStraight.add(new Card(Card.Suit.SPADE, 14));
		highStraight.add(new Card(Card.Suit.HEART, 13));
		highStraight.add(new Card(Card.Suit.DIAMOND, 12));
		highStraight.add(new Card(Card.Suit.SPADE, 11));
		highStraight.add(new Card(Card.Suit.CLUB, 10));
		
		//Create lowest flush hand
		ArrayList<Card> lowFlush = new ArrayList<Card>();
		lowFlush.add(new Card(Card.Suit.SPADE, 2));
		lowFlush.add(new Card(Card.Suit.SPADE, 4));
		lowFlush.add(new Card(Card.Suit.SPADE, 5));
		lowFlush.add(new Card(Card.Suit.SPADE, 6));
		lowFlush.add(new Card(Card.Suit.SPADE, 7));

		//Ensure lowest flush beats the highest straight
		assertTrue(HandRanking.rankHand(lowFlush) > HandRanking.rankHand(highStraight));
	}
	
	@Test
	public void flushVsFullHouse(){
		//Create highest flush hand
		ArrayList<Card> highFlush = new ArrayList<Card>();
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 13));
		highFlush.add(new Card(Card.Suit.SPADE, 12));
		highFlush.add(new Card(Card.Suit.SPADE, 11));
		highFlush.add(new Card(Card.Suit.SPADE, 9));
		
		//Create lowest full house hand
		ArrayList<Card> lowFullHouse = new ArrayList<Card>();
		lowFullHouse.add(new Card(Card.Suit.SPADE, 2));
		lowFullHouse.add(new Card(Card.Suit.HEART, 2));
		lowFullHouse.add(new Card(Card.Suit.DIAMOND, 2));
		lowFullHouse.add(new Card(Card.Suit.SPADE, 3));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 3));

		//Ensure lowest three of a kind beats the highest two pair
		assertTrue(HandRanking.rankHand(lowFullHouse) > HandRanking.rankHand(highFlush));
	}
	
	@Test
	public void fullHouseVsFour(){
		//Create highest full house hand
		ArrayList<Card> highFullHouse = new ArrayList<Card>();
		highFullHouse.add(new Card(Card.Suit.SPADE, 14));
		highFullHouse.add(new Card(Card.Suit.HEART, 14));
		highFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		highFullHouse.add(new Card(Card.Suit.SPADE, 13));
		highFullHouse.add(new Card(Card.Suit.CLUB, 13));
		
		//Create lowest four of a kind hand
		ArrayList<Card> lowFour = new ArrayList<Card>();
		lowFour.add(new Card(Card.Suit.SPADE, 2));
		lowFour.add(new Card(Card.Suit.HEART, 2));
		lowFour.add(new Card(Card.Suit.DIAMOND, 2));
		lowFour.add(new Card(Card.Suit.SPADE, 3));
		lowFour.add(new Card(Card.Suit.CLUB, 2));

		//Ensure lowest four of a kind beats the highest full house
		assertTrue(HandRanking.rankHand(lowFour) > HandRanking.rankHand(highFullHouse));
	}
	
	@Test
	public void fourVsStraightFlush(){
		//Create highest four of a kind hand
		ArrayList<Card> highFour = new ArrayList<Card>();
		highFour.add(new Card(Card.Suit.SPADE, 14));
		highFour.add(new Card(Card.Suit.HEART, 14));
		highFour.add(new Card(Card.Suit.DIAMOND, 14));
		highFour.add(new Card(Card.Suit.SPADE, 14));
		highFour.add(new Card(Card.Suit.CLUB, 13));
		
		//Create lowest straight flush hand
		ArrayList<Card> lowStraightFlush = new ArrayList<Card>();
		lowStraightFlush.add(new Card(Card.Suit.SPADE, 14));
		lowStraightFlush.add(new Card(Card.Suit.SPADE, 2));
		lowStraightFlush.add(new Card(Card.Suit.SPADE, 3));
		lowStraightFlush.add(new Card(Card.Suit.SPADE, 4));
		lowStraightFlush.add(new Card(Card.Suit.SPADE, 5));

		//Ensure lowest straight flush beats the highest four of a kind
		assertTrue(HandRanking.rankHand(lowStraightFlush) > HandRanking.rankHand(highFour));
	}
}
