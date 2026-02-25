package juno;

import java.util.ArrayList;

public class Player {
	private Hand hand;
	private int playerID;
	private String playerName;

	public Player(int playerID) {
		hand = new Hand();
		this.playerID = playerID;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public ArrayList<Card> getCards() {
		return hand.getCards();
	}

	public int getPlayerID() {
		return playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean hasWon() {
		return getCards().isEmpty();
	}

	public boolean hasPlayableCard(Game g) {
        for (Card card : getCards()) {
            if (g.isValidMove(card)) {
                return true;
            }
        }
        return false;
    }

	public void remove(Card c) {
		hand.remove(c);
	}

	public Card find(String card) {
		return hand.find(card);
	}

	public String handToString() {
		return hand.toString();
	}
}