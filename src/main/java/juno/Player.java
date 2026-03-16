package juno;

import java.util.ArrayList;

/**
 * Represents a player in the Uno game.
 * Maintains player identity, hand of cards, and game state.
 */
public class Player {
	private Hand hand;
	private int playerID;
	private String playerName;

	/**
	 * Creates a new player with the given player ID.
	 *
	 * @param playerID The unique identifier for this player
	 */
	public Player(int playerID) {
		hand = new Hand();
		this.playerID = playerID;
	}

	/**
	 * Sets the name for this player.
	 *
	 * @param playerName The name to assign to this player
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Gets the player's hand of cards.
	 *
	 * @return The list of cards in the player's hand
	 */
	public ArrayList<Card> getCards() {
		return hand.getCards();
	}

	/**
	 * Gets the player's unique ID.
	 *
	 * @return The player's ID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Gets the player's name.
	 *
	 * @return The player's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Checks if the player has won the game by checking if their hand is empty.
	 *
	 * @return true if the player has no cards, false otherwise
	 */
	public boolean hasWon() {
		return getCards().isEmpty();
	}

	/**
	 * Checks if the player has at least one card that can be legally played.
	 *
	 * @param g The game instance (used to check move validity)
	 * @return true if a playable card exists, false otherwise
	 */
	public boolean hasPlayableCard(Game g) {
        for (Card card : getCards()) {
            if (g.isValidMove(card)) {
                return true;
            }
        }
        return false;
    }

	/**
	 * Removes a card from the player's hand.
	 *
	 * @param c The card to remove
	 */
	public void remove(Card c) {
		hand.remove(c);
	}

	/**
	 * Searches for a card in the player's hand by card string.
	 *
	 * @param card The card string to find (e.g., "R5", "GS")
	 * @return The Card object if found, null otherwise
	 */
	public Card find(String card) {
		return hand.find(card);
	}

	/**
	 * Gets a string representation of the player's hand.
	 *
	 * @return The hand as a formatted string with colored cards
	 */
	public String handToString() {
		return hand.toString();
	}
}