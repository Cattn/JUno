package juno;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
	private ArrayList<Card> playerCards = new ArrayList<Card>();

	public Hand() {

	}

	public void add(Card c) {
		playerCards.add(c);
	}

	public void remove(Card c) {
		playerCards.remove(c);
	}

	public Card find(String card) {
		for (Card c : playerCards) {
			if (c.toString().equals(card)) {
				return c;
			}
		}

		return null;
	}

	public int size() {
		return playerCards.size();
	}

	public ArrayList<Card> getCards() {
		return playerCards;
	}

	public ArrayList<String> sort() {
		ArrayList<String> sorted = new ArrayList<String>();

		for (int i = 0; i < size(); i++) {
			sorted.add(playerCards.get(i).toString());
		}

		Collections.sort(sorted);

		return sorted;
	}

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