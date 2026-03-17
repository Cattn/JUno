package juno;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Manages the game state and rules for a game of Uno.
 * Tracks players, whose turn it is, the current top card, and game progression.
 * Enforces Uno rules including valid moves, action card effects, and turn order.
 */
public class Game {

    private ArrayList<Player> players;
    private Integer currentPlayerIndex;
    private boolean isReversed;
    private Card topCard;
    private boolean isOver;

    /**
     * Creates a new game instance with the given players.
     *
     * @param players The list of players participating in the game
     */
    public Game(ArrayList<Player> players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.isReversed = false;
        this.isOver = false;
    }

    /**
     * Initializes the game by dealing 7 cards to each player and drawing the initial top card.
     * Ensures the initial top card is not an action card.
     *
     * @param players The list of players in the game
     * @throws IllegalStateException if the deck becomes empty during initialization
     */
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

    /**
     * Draws a single card from the deck and adds it to the player's hand.
     *
     * @param player The player drawing a card
     * @return The drawn card, or null if the deck is empty
     */
    public Card drawCard(Player player) {
        Card drawn = JUno.deck.draw();
        if (drawn != null) {
            player.getCards().add(drawn);
        }
        return drawn;
    }

    /**
     * Draws two cards from the deck and adds them to the player's hand.
     *
     * @param player The player drawing cards
     */
    public void drawTwoCards(Player player) {
        drawCard(player);
        drawCard(player);
    }

    /**
     * Draws four cards from the deck and adds them to the player's hand.
     *
     * @param player The player drawing cards
     */
    public void drawFourCards(Player player) {
        drawCard(player);
        drawCard(player);
        drawCard(player);
        drawCard(player);
    }

    /**
     * Advances to the next player in turn order.
     * If the game is reversed, moves backward; otherwise moves forward.
     * Wraps around at the start and end of the player list.
     */
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

    /**
     * Gets the index of the current player.
     *
     * @return The index of the current player in the players list
     */
    public int getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    /**
     * Adjusts the current player index to ensure it remains within valid bounds.
     * Called when the player list is modified (e.g., when a player wins).
     */
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

    /**
     * Determines if a card can be legally played on the current top card.
     * A move is valid if: the card is wild, or it matches the top card's color or value.
     *
     * @param card The card to validate
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(Card card) {
        if (card == null || topCard == null) {
            return false;
        }
        if (card.isWild()) {
            return true;
        }
        return Objects.equals(card.getColor(), topCard.getColor()) || card.getValue() == topCard.getValue();
    }

    /**
     * Sets the top card of the discard pile.
     *
     * @param card The card to set as the top card
     */
    public void setTopCard(Card card) {
        this.topCard = card;
    }

    /**
     * Toggles the game direction (forward or backward).
     * In 2-player games, reverse acts like a skip (advances to next player).
     */
    public void reverse() {
        this.isReversed = !this.isReversed;
        if (this.players.size() == 2) {
            this.nextPlayer();
        }
    }

    /**
     * Skips the next player's turn.
     */
    public void skipTurn() {
        this.nextPlayer();
    }

    /**
     * Processes the effects of an action card.
     * Handles Reverse, Skip, Plus Two, Wild, and Plus Four cards.
     * Prompts for color selection on wild cards.
     *
     * @param c The action card to process
     * @param s Scanner for user input (color selection for wild cards)
     */
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

    /**
     * Prompts the player to choose a color for a wild card.
     * Validates input and repeats until a valid color is selected.
     *
     * @param s Scanner for reading user input
     * @return The chosen color (R, G, B, or Y)
     */
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

    /**
     * Checks if the game has ended.
     * The game is over if there is only one player remaining or the players decide to end the game after a player has won.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return isOver || players.size() <= 1;
    }

    /**
     * Marks the game as over.
     */
    public void endGame() {
        this.isOver = true;
    }

    /**
     * Gets the list of players in the game.
     *
     * @return The list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the current top card of the discard pile.
     *
     * @return The top card
     */
    public Card getTopCard() {
        return topCard;
    }
}
