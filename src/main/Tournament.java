package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Stack;
import java.util.Vector;

import model.Card;
import model.Card.Suit;
import model.Player;
import model.PlayerHand;
import model.TableState;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Represents a series of poker hands to be played by a group of players
 * Eventual goal is to have simulated tournaments with betting, chips, and AI
 * @author dgibbs
 *
 */
public abstract class Tournament {
	
	private TableState state;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Stack<Card> deck = new Stack<Card>();
	private Stack<Card> discarded = new Stack<Card>();
	private Stack<Card> visible = new Stack<Card>();
	
//	private long currentBet = 0;
	private int dealerIndex = 0;
	
	public Tournament(ArrayList<Player> players, long startingChips){
		setPlayers(players);
		this.state = new TableState();
	}
	
	public Tournament(ArrayList<Player> players){
		this(players, 100000l);
	}
	
	/**
	 * Returns the deck of cards to its initial state
	 */
	private void resetDeck() {
		deck.clear();
		for (Suit suit : Card.Suit.values()){
			for (int i = 2; i <= 14; ++i){
				deck.add(new Card(suit, i));
			}
		}
		discarded.clear();
		visible.clear();
	}
	
	/**
	 * Shuffles the deck using a pre-defined seed as well as the current time
	 */
	private void shuffle() {
		if (deck.size() != 52){
			throw new GameException("Attempted to shuffle partial deck of size = " + deck.size());
		}
		long seedOne = 19237643987l;
		Random random = new Random(seedOne);
		long seedTwo = new Date().getTime();
		
		Collections.shuffle(deck, random);
		Collections.shuffle(deck, new Random(seedTwo));
		Collections.shuffle(deck, random);
	}
	
	/**
	 * Adds a player to the tournament
	 * @param player to be added
	 */
	public void addPlayer(Player player){
		players.add(player);
	}
	
	/**
	 * Deals two cards to each player
	 */
	public void deal() {
		int numberOfPlayers = players.size();
		for (int cardCount = 0; cardCount < 2; ++cardCount){
			for (int i = 1; i <= numberOfPlayers; ++i){
				int index = (i + dealerIndex) % numberOfPlayers;
				players.get(index).dealCard(deck.pop());
			}
		}		
	}
	
	/**
	 * Currently unused
	 * @param playerIndex
	 */
	public void runBettingRound(int playerIndex) {
		int numberOfPlayers = players.size();
		for (int i = playerIndex; i <= numberOfPlayers; ++i){
//			int index = (i + dealerIndex) % numberOfPlayers;
//			players.get(index).getAction(currentBet);
		}
	}
	
	/**
	 * Deals a new hand
	 */
	protected void dealHand() {
		resetDeck();
		shuffle();
		deal();
	}
	
	/**
	 * Discards the top card of the deck
	 */
	private void burnCard(){
		discarded.push(deck.pop());
	}
	
	/**
	 * Flips community cards
	 * @param quantity to be flipped
	 */
	private void flipCards(int quantity){
		burnCard();
		for (int i=0; i < quantity; ++i){
			Card turned = flipCard();
			visible.push(turned);
		}
	}
	
	/**
	 * Performs an action based on the current progress of the hand
	 */
	public void performHandAction(){
		state.advance();
		int progress = state.getHandProgress();
		switch(progress){
		case TableState.PRE_FLOP:
			newHand();
			break;
		case TableState.FLOP:
			flipCards(3);
			break;
		case TableState.HAND_ENDED:
			endHand();
			break;
		default: 
			flipCards(1);
			break;
		}
	}
	
	/**
	 * Determines the winner(s) of the current hand
	 * @return player(s) and the cards they used to win the current hand
	 */
	protected Vector<PlayerHand> checkHandWinner() {
		HashMap<PlayerHand, Long> ranks = new HashMap<PlayerHand, Long>();
		ArrayList<Card> allSeven = new ArrayList<Card>();
		for (Player player : players){
			allSeven.clear();
			allSeven.addAll(visible);
			allSeven.addAll(player.getCards());
			ICombinatoricsVector<Card> originalVector = Factory.createVector(allSeven);
			Generator<Card> gen = Factory.createSimpleCombinationGenerator(originalVector, 5);
			long bestRank = 0;
			ArrayList<Card> bestHand = null;
			for (ICombinatoricsVector<Card> combination : gen) {
				ArrayList<Card> tempHand = new ArrayList<Card>(combination.getVector());
				long tempRank = HandRanking.rankHand(tempHand);
				if (tempRank > bestRank){
					bestRank = tempRank;
					bestHand = tempHand;
				}
			}
			ranks.put(new PlayerHand(player, bestHand), bestRank);
		}
		Vector<PlayerHand> winners = new Vector<PlayerHand>();
		long highHand = 0;
		for (Entry<PlayerHand,Long> entry : ranks.entrySet()){
			long playerScore = entry.getValue();
			if (playerScore > highHand){
				winners.clear();
				winners.add(entry.getKey());
				highHand = playerScore;
			}
			else if (playerScore == highHand){
				winners.add(entry.getKey());
			}
		}
		return winners;
	}
	
	/**
	 * Ends the current hand
	 */
	protected void endHand(){
		for (Player player : players){
			player.foldCards();
		}
	}
	
	/**
	 * Deals a new hand
	 */
	protected void newHand(){
		dealHand();
	}
	
	protected Card flipCard(){
		Card turned = deck.pop();
		return turned;
	}
	
	public TableState getState(){
		return state;
	}
	
	public void setState(TableState s){
		this.state = s;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
}
