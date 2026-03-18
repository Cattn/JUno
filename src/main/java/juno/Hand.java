package juno;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a player's hand of cards in Uno.
 * Manages card collection including adding, removing, and searching for cards.
 */
public class Hand {
	private ArrayList<Card> playerCards = new ArrayList<Card>();

	/**
	 * Creates an empty hand.
	 */
	public Hand() {

	}

	/**
	 * Adds a card to the hand.
	 *
	 * @param c The card to add
	 */
	public void add(Card c) {
		playerCards.add(c);
	}

	/**
	 * Removes a card from the hand.
	 *
	 * @param c The card to remove
	 */
	public void remove(Card c) {
		playerCards.remove(c);
	}

	/**
	 * Searches for a card in the hand by its string representation.
	 *
	 * @param card The card string to find (e.g., "R5", "GS")
	 * @return The Card object if found, null otherwise
	 */
	public Card find(String card) {
		for (Card c : playerCards) {
			if (c.toString().equals(card)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * Gets the number of cards in the hand.
	 *
	 * @return The size of the hand
	 */
	public int size() {
		return playerCards.size();
	}

	/**
	 * Gets the list of cards in the hand.
	 *
	 * @return The list of cards
	 */
	public ArrayList<Card> getCards() {
		return playerCards;
	}

	/**
	 * Returns a sorted list of card strings in the hand.
	 * The original hand order is not modified.
	 *
	 * @return A new sorted list of card strings
	 */
	public ArrayList<String> sort() {
		ArrayList<String> sorted = new ArrayList<String>();

		for (int i = 0; i < size(); i++) {
			sorted.add(playerCards.get(i).toString());
		}

		Collections.sort(sorted);

		return sorted;
	}

	/**
	 * Returns a string representation of the hand with colored card symbols.
	 *
	 * @return A bracketed list of colored card strings
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < playerCards.size(); i++) {
			if (i > 0)
				sb.append(", ");
			sb.append(playerCards.get(i).toColoredString());
		}
		sb.append("]");
		return sb.toString();
	}
}