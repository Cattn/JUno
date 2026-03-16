package juno;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the game deck containing all Uno cards.
 * Manages card drawing and deck replenishment when cards run low.
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Game game;

    /**
     * Creates and initializes a new deck with 3 copies of each card.
     * The deck is shuffled after initialization.
     *
     * @param game The game instance (used for deck replenishment logic)
     */
    public Deck(Game game) {
        this.game = game;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < Card.cards.length; j++) {
                deck.add(new Card(Card.cards[j]));
            }
        }

        Collections.shuffle(deck);
    }

    /**
     * Draws a card from the top of the deck.
     * If the deck becomes empty, it is replenished with new cards (excluding the top card).
     *
     * @return The drawn card, or null if replenishment fails
     */
    public Card draw() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else {
            for (int i = 0; i < Card.cards.length; i++) {
                if (!Card.cards[i].equals(game.getTopCard().toString())) {
                    this.add(new Card(Card.cards[i]));
                    break;
                }
            }
            if (deck.isEmpty()) {
                return null;
            }
            return deck.remove(0);
        }
    }

    /**
     * Deals 7 random cards to a player at the start of the game.
     *
     * @param p The player to receive cards
     */
    public void dealCards(Player p) {
        for (int i = 0; i < 7; i++) {
            int randIndex = (int) (Math.random() * deck.size());
            String randomCard = deck.get(randIndex).toString();
            deck.remove(deck.get(randIndex));

            Card c = new Card(randomCard);

            p.getCards().add(c);
        }
    }

    /**
     * Adds a card back to the deck.
     * Clears any color choice on wild cards before adding.
     *
     * @param c The card to add to the deck
     */
    public void add(Card c) {
        if (c != null) {
            c.clearChosenColor();
        }
        deck.add(c);
    }
}