package juno;

import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Integer currentPlayerIndex;
    private ActionManager action;
    private boolean isReversed;
    private Card topCard;

    public Game(ArrayList<Player> players)
    {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.isReversed = false;
    }

    public void startGame(ArrayList<Player> players)
    {
        this.players = players;
        for (Player player : players) {
            JUno.deck.dealCards(player);
        }
        this.action = new ActionManager(this);
        Card c;
        do {
            c = JUno.deck.draw();
            if (c == null)
                throw new IllegalStateException("Deck empty at game start");
            if (c.isWildOrDrawCard())
                JUno.deck.add(c);
            else {
                this.topCard = c;
                break;
            }
        } while (true);
    }

    public void nextPlayer()
    {
        if (this.isReversed) {
            this.currentPlayerIndex--;
        } else {
            this.currentPlayerIndex++;
        }
        if (this.currentPlayerIndex >= this.players.size()) {
            this.currentPlayerIndex = 0;
        } else if (this.currentPlayerIndex < 0) {
            this.currentPlayerIndex = this.players.size() - 1;
        }
    }

    public boolean isValidMove(Card card)
    {
        return card.getColor().equals(topCard.getColor()) || card.getValue() == topCard.getValue() || card.toString().equals(topCard.toString());
    }

    public void setTopCard(Card card)
    {
        this.topCard = card;
    }

    public void reverseDirection()
    {
        this.isReversed = !this.isReversed;
    }

    public ActionManager getAction()
    {
        return action;
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public Card getTopCard()
    {
        return topCard;
    }
}
