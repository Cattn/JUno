package juno;

import java.util.ArrayList;

public class Game {

    private ArrayList<Player> players;
    private Integer currentPlayerIndex;
    private boolean isReversed;
    private Card topCard;   
    private boolean isOver;

    public Game(ArrayList<Player> players)
    {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.isReversed = false;
        this.isOver = false;
    }

    public void startGame(ArrayList<Player> players)
    {
        this.players = players;
        this.isOver = false;
        for (Player player : players) {
            JUno.deck.dealCards(player);
        }
        Card c;
        do {
            c = JUno.deck.draw();
            if (c == null)
                throw new IllegalStateException("Deck empty at game start");
            if (c.isActionCard())
                JUno.deck.add(c);
            else {
                this.topCard = c;
                break;
            }
        } while (true);
    }
    
    public void drawCard(Player player)
    {
        player.getCards().add(JUno.deck.draw());
    }

    public void drawTwoCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
    }

    public void drawFourCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
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
        String cardStr = card.toString();
        if (cardStr.equals("W") || cardStr.startsWith("W") || cardStr.equals("P4") || cardStr.startsWith("P4")) {
            return true;
        }
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

    public void skipTurn()
    {
        this.nextPlayer();
    }

    public void reverse()
    {
        this.reverseDirection();
    }

    public boolean isGameOver()
    {
        return isOver || players.size() <= 1;
    }

    public void endGame()
    {
        this.isOver = true;
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
