package ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import main.HandRanking;
import model.Card;

import org.junit.Test;

/**
 * Test suite to ensure that hands within a given category are properly ranked against one another
 * Accomplished by testing each possible kicker scenario for a hand
 * @author dgibbs
 *
 */
public class InterCategorySpread {
	
	@Test
	public void highCard(){
		// test the highest card
		ArrayList<Card> highCard = new ArrayList<Card>();
		highCard.add(new Card(Card.Suit.SPADE, 14));
		highCard.add(new Card(Card.Suit.HEART, 3));
		highCard.add(new Card(Card.Suit.DIAMOND, 4));
		highCard.add(new Card(Card.Suit.SPADE, 5));
		highCard.add(new Card(Card.Suit.CLUB, 6));
		
		ArrayList<Card> lowCard = new ArrayList<Card>();
		lowCard.add(new Card(Card.Suit.SPADE, 13));
		lowCard.add(new Card(Card.Suit.HEART, 12));
		lowCard.add(new Card(Card.Suit.DIAMOND, 11));
		lowCard.add(new Card(Card.Suit.SPADE, 10));
		lowCard.add(new Card(Card.Suit.CLUB, 8));
		
		assertTrue(HandRanking.rankHand(highCard) > HandRanking.rankHand(lowCard));
		
		//test the first kicker
		highCard.clear();
		lowCard.clear();
		
		highCard.add(new Card(Card.Suit.SPADE, 14));
		highCard.add(new Card(Card.Suit.HEART, 13));
		highCard.add(new Card(Card.Suit.DIAMOND, 4));
		highCard.add(new Card(Card.Suit.SPADE, 5));
		highCard.add(new Card(Card.Suit.CLUB, 6));
		
		lowCard.add(new Card(Card.Suit.SPADE, 14));
		lowCard.add(new Card(Card.Suit.HEART, 12));
		lowCard.add(new Card(Card.Suit.DIAMOND, 11));
		lowCard.add(new Card(Card.Suit.SPADE, 10));
		lowCard.add(new Card(Card.Suit.CLUB, 9));
		
		assertTrue(HandRanking.rankHand(highCard) > HandRanking.rankHand(lowCard));
		
		//test the second kicker
		highCard.clear();
		lowCard.clear();
		
		highCard.add(new Card(Card.Suit.SPADE, 14));
		highCard.add(new Card(Card.Suit.HEART, 13));
		highCard.add(new Card(Card.Suit.DIAMOND, 12));
		highCard.add(new Card(Card.Suit.SPADE, 2));
		highCard.add(new Card(Card.Suit.CLUB, 3));
		
		lowCard.add(new Card(Card.Suit.SPADE, 14));
		lowCard.add(new Card(Card.Suit.HEART, 13));
		lowCard.add(new Card(Card.Suit.DIAMOND, 11));
		lowCard.add(new Card(Card.Suit.SPADE, 10));
		lowCard.add(new Card(Card.Suit.CLUB, 9));
		
		assertTrue(HandRanking.rankHand(highCard) > HandRanking.rankHand(lowCard));
		
		//test the third kicker
		highCard.clear();
		lowCard.clear();
		
		highCard.add(new Card(Card.Suit.SPADE, 14));
		highCard.add(new Card(Card.Suit.HEART, 13));
		highCard.add(new Card(Card.Suit.DIAMOND, 12));
		highCard.add(new Card(Card.Suit.SPADE, 11));
		highCard.add(new Card(Card.Suit.CLUB, 2));
		
		lowCard.add(new Card(Card.Suit.SPADE, 14));
		lowCard.add(new Card(Card.Suit.HEART, 13));
		lowCard.add(new Card(Card.Suit.DIAMOND, 12));
		lowCard.add(new Card(Card.Suit.SPADE, 10));
		lowCard.add(new Card(Card.Suit.CLUB, 9));
		
		assertTrue(HandRanking.rankHand(highCard) > HandRanking.rankHand(lowCard));
		
		//test a the fourth kicker
		highCard.clear();
		lowCard.clear();
		
		highCard.add(new Card(Card.Suit.SPADE, 14));
		highCard.add(new Card(Card.Suit.HEART, 13));
		highCard.add(new Card(Card.Suit.DIAMOND, 12));
		highCard.add(new Card(Card.Suit.SPADE, 11));
		highCard.add(new Card(Card.Suit.CLUB, 9));
		
		lowCard.add(new Card(Card.Suit.SPADE, 14));
		lowCard.add(new Card(Card.Suit.HEART, 13));
		lowCard.add(new Card(Card.Suit.DIAMOND, 12));
		lowCard.add(new Card(Card.Suit.SPADE, 11));
		lowCard.add(new Card(Card.Suit.CLUB, 8));
		
		assertTrue(HandRanking.rankHand(highCard) > HandRanking.rankHand(lowCard));
	}
	
	@Test
	public void pair(){
		// test the pair itself
		ArrayList<Card> highPair = new ArrayList<Card>();
		highPair.add(new Card(Card.Suit.SPADE, 14));
		highPair.add(new Card(Card.Suit.HEART, 14));
		highPair.add(new Card(Card.Suit.DIAMOND, 2));
		highPair.add(new Card(Card.Suit.SPADE, 3));
		highPair.add(new Card(Card.Suit.CLUB, 4));
		
		ArrayList<Card> lowPair = new ArrayList<Card>();
		lowPair.add(new Card(Card.Suit.SPADE, 13));
		lowPair.add(new Card(Card.Suit.HEART, 13));
		lowPair.add(new Card(Card.Suit.DIAMOND, 12));
		lowPair.add(new Card(Card.Suit.SPADE, 11));
		lowPair.add(new Card(Card.Suit.CLUB, 10));
		
		assertTrue(HandRanking.rankHand(highPair) > HandRanking.rankHand(lowPair));
		
		//test the first kicker
		highPair.clear();
		lowPair.clear();
		
		highPair.add(new Card(Card.Suit.SPADE, 14));
		highPair.add(new Card(Card.Suit.HEART, 14));
		highPair.add(new Card(Card.Suit.DIAMOND, 13));
		highPair.add(new Card(Card.Suit.SPADE, 2));
		highPair.add(new Card(Card.Suit.CLUB, 3));
		
		lowPair.add(new Card(Card.Suit.SPADE, 14));
		lowPair.add(new Card(Card.Suit.HEART, 14));
		lowPair.add(new Card(Card.Suit.DIAMOND, 12));
		lowPair.add(new Card(Card.Suit.SPADE, 11));
		lowPair.add(new Card(Card.Suit.CLUB, 10));
		
		assertTrue(HandRanking.rankHand(highPair) > HandRanking.rankHand(lowPair));
		
		//test the second kicker
		highPair.clear();
		lowPair.clear();
		
		highPair.add(new Card(Card.Suit.SPADE, 14));
		highPair.add(new Card(Card.Suit.HEART, 14));
		highPair.add(new Card(Card.Suit.DIAMOND, 13));
		highPair.add(new Card(Card.Suit.SPADE, 12));
		highPair.add(new Card(Card.Suit.CLUB, 2));
		
		lowPair.add(new Card(Card.Suit.SPADE, 14));
		lowPair.add(new Card(Card.Suit.HEART, 14));
		lowPair.add(new Card(Card.Suit.DIAMOND, 13));
		lowPair.add(new Card(Card.Suit.SPADE, 11));
		lowPair.add(new Card(Card.Suit.CLUB, 10));
		
		assertTrue(HandRanking.rankHand(highPair) > HandRanking.rankHand(lowPair));
		
		//test the third kicker
		highPair.clear();
		lowPair.clear();
		
		highPair.add(new Card(Card.Suit.SPADE, 14));
		highPair.add(new Card(Card.Suit.HEART, 14));
		highPair.add(new Card(Card.Suit.DIAMOND, 13));
		highPair.add(new Card(Card.Suit.SPADE, 12));
		highPair.add(new Card(Card.Suit.CLUB, 11));
		
		lowPair.add(new Card(Card.Suit.SPADE, 14));
		lowPair.add(new Card(Card.Suit.HEART, 14));
		lowPair.add(new Card(Card.Suit.DIAMOND, 13));
		lowPair.add(new Card(Card.Suit.SPADE, 12));
		lowPair.add(new Card(Card.Suit.CLUB, 10));
		
		assertTrue(HandRanking.rankHand(highPair) > HandRanking.rankHand(lowPair));	
	}
	
	@Test
	public void twoPair(){
		// test the larger pair
		ArrayList<Card> highTwoPair = new ArrayList<Card>();
		highTwoPair.add(new Card(Card.Suit.SPADE, 14));
		highTwoPair.add(new Card(Card.Suit.HEART, 14));
		highTwoPair.add(new Card(Card.Suit.DIAMOND, 2));
		highTwoPair.add(new Card(Card.Suit.SPADE, 2));
		highTwoPair.add(new Card(Card.Suit.CLUB, 3));
		
		ArrayList<Card> lowTwoPair = new ArrayList<Card>();
		lowTwoPair.add(new Card(Card.Suit.SPADE, 13));
		lowTwoPair.add(new Card(Card.Suit.HEART, 13));
		lowTwoPair.add(new Card(Card.Suit.DIAMOND, 12));
		lowTwoPair.add(new Card(Card.Suit.SPADE, 12));
		lowTwoPair.add(new Card(Card.Suit.CLUB, 14));
		
		assertTrue(HandRanking.rankHand(highTwoPair) > HandRanking.rankHand(lowTwoPair));
		
		//test the smaller pair
		highTwoPair.clear();
		lowTwoPair.clear();
		
		highTwoPair.add(new Card(Card.Suit.SPADE, 14));
		highTwoPair.add(new Card(Card.Suit.HEART, 14));
		highTwoPair.add(new Card(Card.Suit.DIAMOND, 13));
		highTwoPair.add(new Card(Card.Suit.SPADE, 13));
		highTwoPair.add(new Card(Card.Suit.CLUB, 2));
		
		lowTwoPair.add(new Card(Card.Suit.SPADE, 14));
		lowTwoPair.add(new Card(Card.Suit.HEART, 14));
		lowTwoPair.add(new Card(Card.Suit.DIAMOND, 12));
		lowTwoPair.add(new Card(Card.Suit.SPADE, 12));
		lowTwoPair.add(new Card(Card.Suit.CLUB, 13));
		
		assertTrue(HandRanking.rankHand(highTwoPair) > HandRanking.rankHand(lowTwoPair));
		
		//test the kicker
		highTwoPair.clear();
		lowTwoPair.clear();
		
		highTwoPair.add(new Card(Card.Suit.SPADE, 14));
		highTwoPair.add(new Card(Card.Suit.HEART, 14));
		highTwoPair.add(new Card(Card.Suit.DIAMOND, 13));
		highTwoPair.add(new Card(Card.Suit.SPADE, 13));
		highTwoPair.add(new Card(Card.Suit.CLUB, 12));
		
		lowTwoPair.add(new Card(Card.Suit.SPADE, 14));
		lowTwoPair.add(new Card(Card.Suit.HEART, 14));
		lowTwoPair.add(new Card(Card.Suit.DIAMOND, 13));
		lowTwoPair.add(new Card(Card.Suit.SPADE, 13));
		lowTwoPair.add(new Card(Card.Suit.CLUB, 11));
		
		assertTrue(HandRanking.rankHand(highTwoPair) > HandRanking.rankHand(lowTwoPair));
	}
	
	@Test
	public void trips(){
		// test the three of a kind itself
		ArrayList<Card> highTrips = new ArrayList<Card>();
		highTrips.add(new Card(Card.Suit.SPADE, 14));
		highTrips.add(new Card(Card.Suit.HEART, 14));
		highTrips.add(new Card(Card.Suit.DIAMOND, 14));
		highTrips.add(new Card(Card.Suit.SPADE, 2));
		highTrips.add(new Card(Card.Suit.CLUB, 3));
		
		ArrayList<Card> lowTrips = new ArrayList<Card>();
		lowTrips.add(new Card(Card.Suit.SPADE, 13));
		lowTrips.add(new Card(Card.Suit.HEART, 13));
		lowTrips.add(new Card(Card.Suit.DIAMOND, 13));
		lowTrips.add(new Card(Card.Suit.SPADE, 12));
		lowTrips.add(new Card(Card.Suit.CLUB, 14));
		
		assertTrue(HandRanking.rankHand(highTrips) > HandRanking.rankHand(lowTrips));
		
		//test the first kicker
		highTrips.clear();
		lowTrips.clear();
		
		highTrips.add(new Card(Card.Suit.SPADE, 14));
		highTrips.add(new Card(Card.Suit.HEART, 14));
		highTrips.add(new Card(Card.Suit.DIAMOND, 14));
		highTrips.add(new Card(Card.Suit.SPADE, 13));
		highTrips.add(new Card(Card.Suit.CLUB, 2));
		
		lowTrips.add(new Card(Card.Suit.SPADE, 14));
		lowTrips.add(new Card(Card.Suit.HEART, 14));
		lowTrips.add(new Card(Card.Suit.DIAMOND, 14));
		lowTrips.add(new Card(Card.Suit.SPADE, 12));
		lowTrips.add(new Card(Card.Suit.CLUB, 11));
		
		assertTrue(HandRanking.rankHand(highTrips) > HandRanking.rankHand(lowTrips));
		
		//test the second kicker
		highTrips.clear();
		lowTrips.clear();
		
		highTrips.add(new Card(Card.Suit.SPADE, 14));
		highTrips.add(new Card(Card.Suit.HEART, 14));
		highTrips.add(new Card(Card.Suit.DIAMOND, 14));
		highTrips.add(new Card(Card.Suit.SPADE, 13));
		highTrips.add(new Card(Card.Suit.CLUB, 12));
		
		lowTrips.add(new Card(Card.Suit.SPADE, 14));
		lowTrips.add(new Card(Card.Suit.HEART, 14));
		lowTrips.add(new Card(Card.Suit.DIAMOND, 14));
		lowTrips.add(new Card(Card.Suit.SPADE, 13));
		lowTrips.add(new Card(Card.Suit.CLUB, 11));
		
		assertTrue(HandRanking.rankHand(highTrips) > HandRanking.rankHand(lowTrips));
	}
	
	@Test
	public void straight(){
		// test a basic scenario
		ArrayList<Card> highStraight = new ArrayList<Card>();
		highStraight.add(new Card(Card.Suit.SPADE, 14));
		highStraight.add(new Card(Card.Suit.HEART, 13));
		highStraight.add(new Card(Card.Suit.DIAMOND, 12));
		highStraight.add(new Card(Card.Suit.SPADE, 11));
		highStraight.add(new Card(Card.Suit.CLUB, 10));
		
		ArrayList<Card> lowStraight = new ArrayList<Card>();
		lowStraight.add(new Card(Card.Suit.SPADE, 13));
		lowStraight.add(new Card(Card.Suit.HEART, 12));
		lowStraight.add(new Card(Card.Suit.DIAMOND, 11));
		lowStraight.add(new Card(Card.Suit.SPADE, 10));
		lowStraight.add(new Card(Card.Suit.CLUB, 9));
		
		assertTrue(HandRanking.rankHand(highStraight) > HandRanking.rankHand(lowStraight));	
		
		//test interesting A,2,3,4,5 scenario
		highStraight.clear();
		lowStraight.clear();
		
		highStraight.add(new Card(Card.Suit.SPADE, 2));
		highStraight.add(new Card(Card.Suit.HEART, 3));
		highStraight.add(new Card(Card.Suit.DIAMOND, 4));
		highStraight.add(new Card(Card.Suit.SPADE, 5));
		highStraight.add(new Card(Card.Suit.CLUB, 6));
		
		lowStraight.add(new Card(Card.Suit.SPADE, 14));
		lowStraight.add(new Card(Card.Suit.HEART, 2));
		lowStraight.add(new Card(Card.Suit.DIAMOND, 3));
		lowStraight.add(new Card(Card.Suit.SPADE, 4));
		lowStraight.add(new Card(Card.Suit.CLUB, 5));
		
		assertTrue(HandRanking.rankHand(highStraight) > HandRanking.rankHand(lowStraight));		
	}
	
	@Test
	public void flush(){
		// test the highest card
		ArrayList<Card> highFlush = new ArrayList<Card>();
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 3));
		highFlush.add(new Card(Card.Suit.SPADE, 4));
		highFlush.add(new Card(Card.Suit.SPADE, 5));
		highFlush.add(new Card(Card.Suit.SPADE, 6));
		
		ArrayList<Card> lowFlush = new ArrayList<Card>();
		lowFlush.add(new Card(Card.Suit.HEART, 13));
		lowFlush.add(new Card(Card.Suit.HEART, 12));
		lowFlush.add(new Card(Card.Suit.HEART, 11));
		lowFlush.add(new Card(Card.Suit.HEART, 10));
		lowFlush.add(new Card(Card.Suit.HEART, 8));
		
		assertTrue(HandRanking.rankHand(highFlush) > HandRanking.rankHand(lowFlush));
		
		//test the first kicker
		highFlush.clear();
		lowFlush.clear();
		
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 13));
		highFlush.add(new Card(Card.Suit.SPADE, 4));
		highFlush.add(new Card(Card.Suit.SPADE, 5));
		highFlush.add(new Card(Card.Suit.SPADE, 6));
		
		lowFlush.add(new Card(Card.Suit.HEART, 14));
		lowFlush.add(new Card(Card.Suit.HEART, 12));
		lowFlush.add(new Card(Card.Suit.HEART, 11));
		lowFlush.add(new Card(Card.Suit.HEART, 10));
		lowFlush.add(new Card(Card.Suit.HEART, 9));
		
		assertTrue(HandRanking.rankHand(highFlush) > HandRanking.rankHand(lowFlush));
		
		//test the second kicker
		highFlush.clear();
		lowFlush.clear();
		
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 13));
		highFlush.add(new Card(Card.Suit.SPADE, 12));
		highFlush.add(new Card(Card.Suit.SPADE, 2));
		highFlush.add(new Card(Card.Suit.SPADE, 3));
		
		lowFlush.add(new Card(Card.Suit.HEART, 14));
		lowFlush.add(new Card(Card.Suit.HEART, 13));
		lowFlush.add(new Card(Card.Suit.HEART, 11));
		lowFlush.add(new Card(Card.Suit.HEART, 10));
		lowFlush.add(new Card(Card.Suit.HEART, 9));
		
		assertTrue(HandRanking.rankHand(highFlush) > HandRanking.rankHand(lowFlush));
		
		//test the third kicker
		highFlush.clear();
		lowFlush.clear();
		
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 13));
		highFlush.add(new Card(Card.Suit.SPADE, 12));
		highFlush.add(new Card(Card.Suit.SPADE, 11));
		highFlush.add(new Card(Card.Suit.SPADE, 2));
		
		lowFlush.add(new Card(Card.Suit.HEART, 14));
		lowFlush.add(new Card(Card.Suit.HEART, 13));
		lowFlush.add(new Card(Card.Suit.HEART, 12));
		lowFlush.add(new Card(Card.Suit.HEART, 10));
		lowFlush.add(new Card(Card.Suit.HEART, 9));
		
		assertTrue(HandRanking.rankHand(highFlush) > HandRanking.rankHand(lowFlush));
		
		//test a the fourth kicker
		highFlush.clear();
		lowFlush.clear();
		
		highFlush.add(new Card(Card.Suit.SPADE, 14));
		highFlush.add(new Card(Card.Suit.SPADE, 13));
		highFlush.add(new Card(Card.Suit.SPADE, 12));
		highFlush.add(new Card(Card.Suit.SPADE, 11));
		highFlush.add(new Card(Card.Suit.SPADE, 9));
		
		lowFlush.add(new Card(Card.Suit.HEART, 14));
		lowFlush.add(new Card(Card.Suit.HEART, 13));
		lowFlush.add(new Card(Card.Suit.HEART, 12));
		lowFlush.add(new Card(Card.Suit.HEART, 11));
		lowFlush.add(new Card(Card.Suit.HEART, 8));
		
		assertTrue(HandRanking.rankHand(highFlush) > HandRanking.rankHand(lowFlush));
	}
	
	@Test
	public void fullHouse(){
		//test the three of a kind
		ArrayList<Card> highFullHouse = new ArrayList<Card>();
		highFullHouse.add(new Card(Card.Suit.SPADE, 14));
		highFullHouse.add(new Card(Card.Suit.HEART, 14));
		highFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		highFullHouse.add(new Card(Card.Suit.SPADE, 2));
		highFullHouse.add(new Card(Card.Suit.CLUB, 2));
		
		ArrayList<Card> lowFullHouse = new ArrayList<Card>();
		lowFullHouse.add(new Card(Card.Suit.SPADE, 13));
		lowFullHouse.add(new Card(Card.Suit.HEART, 13));
		lowFullHouse.add(new Card(Card.Suit.DIAMOND, 13));
		lowFullHouse.add(new Card(Card.Suit.SPADE, 14));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 14));
		
		assertTrue(HandRanking.rankHand(highFullHouse) > HandRanking.rankHand(lowFullHouse));
		
		//test the pair
		highFullHouse.clear();
		lowFullHouse.clear();
		
		highFullHouse.add(new Card(Card.Suit.SPADE, 14));
		highFullHouse.add(new Card(Card.Suit.HEART, 14));
		highFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		highFullHouse.add(new Card(Card.Suit.SPADE, 13));
		highFullHouse.add(new Card(Card.Suit.CLUB, 13));
		
		lowFullHouse.add(new Card(Card.Suit.SPADE, 14));
		lowFullHouse.add(new Card(Card.Suit.HEART, 14));
		lowFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		lowFullHouse.add(new Card(Card.Suit.SPADE, 12));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 12));
		
		assertTrue(HandRanking.rankHand(highFullHouse) > HandRanking.rankHand(lowFullHouse));
	}
	
	@Test
	public void fours(){
		//test the four of a kind
		ArrayList<Card> highFullHouse = new ArrayList<Card>();
		highFullHouse.add(new Card(Card.Suit.SPADE, 14));
		highFullHouse.add(new Card(Card.Suit.HEART, 14));
		highFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		highFullHouse.add(new Card(Card.Suit.CLUB, 14));
		highFullHouse.add(new Card(Card.Suit.CLUB, 2));
		
		ArrayList<Card> lowFullHouse = new ArrayList<Card>();
		lowFullHouse.add(new Card(Card.Suit.SPADE, 13));
		lowFullHouse.add(new Card(Card.Suit.HEART, 13));
		lowFullHouse.add(new Card(Card.Suit.DIAMOND, 13));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 13));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 14));
		
		assertTrue(HandRanking.rankHand(highFullHouse) > HandRanking.rankHand(lowFullHouse));
		
		//test the kicker
		highFullHouse.clear();
		lowFullHouse.clear();
		
		highFullHouse.add(new Card(Card.Suit.SPADE, 14));
		highFullHouse.add(new Card(Card.Suit.HEART, 14));
		highFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		highFullHouse.add(new Card(Card.Suit.CLUB, 14));
		highFullHouse.add(new Card(Card.Suit.CLUB, 13));
		
		lowFullHouse.add(new Card(Card.Suit.SPADE, 14));
		lowFullHouse.add(new Card(Card.Suit.HEART, 14));
		lowFullHouse.add(new Card(Card.Suit.DIAMOND, 14));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 14));
		lowFullHouse.add(new Card(Card.Suit.CLUB, 12));
		
		assertTrue(HandRanking.rankHand(highFullHouse) > HandRanking.rankHand(lowFullHouse));
	}
	
	@Test
	public void straightFlush(){
		// test a basic scenario
		ArrayList<Card> highStraight = new ArrayList<Card>();
		highStraight.add(new Card(Card.Suit.SPADE, 14));
		highStraight.add(new Card(Card.Suit.SPADE, 13));
		highStraight.add(new Card(Card.Suit.SPADE, 12));
		highStraight.add(new Card(Card.Suit.SPADE, 11));
		highStraight.add(new Card(Card.Suit.SPADE, 10));
		
		ArrayList<Card> lowStraight = new ArrayList<Card>();
		lowStraight.add(new Card(Card.Suit.HEART, 13));
		lowStraight.add(new Card(Card.Suit.HEART, 12));
		lowStraight.add(new Card(Card.Suit.HEART, 11));
		lowStraight.add(new Card(Card.Suit.HEART, 10));
		lowStraight.add(new Card(Card.Suit.HEART, 9));
		
		assertTrue(HandRanking.rankHand(highStraight) > HandRanking.rankHand(lowStraight));	
		
		//test interesting A,2,3,4,5 scenario
		highStraight.clear();
		lowStraight.clear();
		
		highStraight.add(new Card(Card.Suit.SPADE, 2));
		highStraight.add(new Card(Card.Suit.SPADE, 3));
		highStraight.add(new Card(Card.Suit.SPADE, 4));
		highStraight.add(new Card(Card.Suit.SPADE, 5));
		highStraight.add(new Card(Card.Suit.SPADE, 6));
		
		lowStraight.add(new Card(Card.Suit.HEART, 14));
		lowStraight.add(new Card(Card.Suit.HEART, 2));
		lowStraight.add(new Card(Card.Suit.HEART, 3));
		lowStraight.add(new Card(Card.Suit.HEART, 4));
		lowStraight.add(new Card(Card.Suit.HEART, 5));
		
		assertTrue(HandRanking.rankHand(highStraight) > HandRanking.rankHand(lowStraight));
	}

}
