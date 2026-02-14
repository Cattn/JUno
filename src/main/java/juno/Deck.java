package juno;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Game game;

    public Deck(Game game) {
        this.game = game;
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < Card.cards.length; j++) {
                deck.add(new Card(Card.cards[j]));
            }
        }

        Collections.shuffle(deck);
    }

    public Card draw() {
        if (!deck.isEmpty()) {
            return deck.remove(0);
        } else {
            for (int i = 0; i < Card.cards.length; i++) {
                if (!Card.cards[i].equals(game.getTopCard().toString())) {
                    deck.add(new Card(Card.cards[i]));
                    break;
                }
            }
            if (deck.isEmpty()) {
                return null;
            }
            return deck.remove(0);
        }
    }

    public void dealCards(Player p) {
        for (int i = 0; i < 7; i++) {
            int randIndex = (int) (Math.random() * deck.size());
            String randomCard = deck.get(randIndex).toString();
            deck.remove(deck.get(randIndex));

            Card c = new Card(randomCard);

            p.getCards().add(c);
        }
    }

    public void add(Card c) {
        if (c != null) {
            c.clearChosenColor();
        }
        deck.add(c);
    }
}