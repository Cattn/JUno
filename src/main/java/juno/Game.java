package juno;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Integer currentPlayerIndex;
    private boolean isReversed;
    private Card topCard;
    private boolean isOver;

    public Game(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.isReversed = false;
        this.isOver = false;
    }

    public void startGame(ArrayList<Player> players) {
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

    public Card drawCard(Player player) {
        Card drawn = JUno.deck.draw();
        if (drawn != null) {
            player.getCards().add(drawn);
        }
        return drawn;
    }

    public void drawTwoCards(Player player) {
        drawCard(player);
        drawCard(player);
    }

    public void drawFourCards(Player player) {
        drawCard(player);
        drawCard(player);
        drawCard(player);
        drawCard(player);
    }

    public void nextPlayer() {
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

    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public void normalizeCurrentPlayerIndex() {
        if (this.players.isEmpty()) {
            return;
        }
        if (this.currentPlayerIndex >= this.players.size()) {
            this.currentPlayerIndex = 0;
        } else if (this.currentPlayerIndex < 0) {
            this.currentPlayerIndex = this.players.size() - 1;
        }
    }

    public boolean isValidMove(Card card) {
        if (card.isWild()) {
            return true;
        }
        return card.getColor().equals(topCard.getColor()) || card.getValue() == topCard.getValue();
    }

    public void setTopCard(Card card) {
        this.topCard = card;
    }

    public void reverse() {
        this.isReversed = !this.isReversed;
        this.currentPlayerIndex = (this.currentPlayerIndex - 1) % this.players.size();
    }

    public void skipTurn() {
        this.nextPlayer();
    }

    public void checkActionCard(Card c, Scanner s) {
        String cardStr = c.toString();
        if (c.isWild()) {
            if (c.isPlusFour()) {
                this.nextPlayer();
                this.drawFourCards(this.players.get(this.currentPlayerIndex));
                System.out.println(this.players.get(this.currentPlayerIndex).getPlayerName()
                        + " draws 4 cards and skips their turn!");
            }
            c.setColor(promptForColor(s));
        } else if (cardStr.endsWith("R")) {
            this.reverse();
        } else if (cardStr.endsWith("S")) {
            this.skipTurn();
        } else if (cardStr.endsWith("P")) {
            this.nextPlayer();
            this.drawTwoCards(this.players.get(this.currentPlayerIndex));
            System.out.println(
                    this.players.get(this.currentPlayerIndex).getPlayerName() + " draws 2 cards and skips their turn!");
        }
        this.setTopCard(c);
    }

    private String promptForColor(Scanner s) {
        String color;
        while (true) {
            System.out.print("Choose a color (R, G, B, Y): ");
            color = s.nextLine().trim().toUpperCase();
            if (color.matches("[RGBY]"))
                break;
            System.out.println("Invalid color.");
        }
        return color;
    }

    public boolean isGameOver() {
        return isOver || players.size() <= 1;
    }

    public void endGame() {
        this.isOver = true;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Card getTopCard() {
        return topCard;
    }
}
