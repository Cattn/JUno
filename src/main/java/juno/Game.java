package juno;

import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Integer currentPlayerIndex;
    private ActionManager action;
    private boolean isReversed;

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
}
