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

	public Hand getHand() {
		return hand;
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

	public void printCards() {
		System.out.println(getCards());
	}
}