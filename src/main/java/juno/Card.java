package juno;

/**
 * Represents a single Uno card with color, value, and optional user-chosen color for wild cards.
 * Card values are encoded as strings (e.g., "R5", "GS", "W", "P4"):
 * - First character: Color (G=Green, R=Red, B=Blue, Y=Yellow)
 * - Second character: Value (0-9, R=Reverse, S=Skip, P=Plus Two)
 * - Special cards: "W" for Wild, "P4" for Plus Four Wild
 */
public class Card {
    private int card;
    private String chosenColor;

    public static String[] cards = {
            "G0", "R0", "B0", "Y0", "G1", "R1", "B1", "Y1", "G1", "R1", "B1", "Y1", "G2", "R2", "B2", "Y2", "G2", "R2",
            "B2", "Y2", "G3", "R3", "B3", "Y3", "G3", "R3", "B3", "Y3", "G4", "R4", "B4", "Y4", "G4", "R4", "B4", "Y4",
            "G5", "R5", "B5", "Y5", "G5", "R5", "B5", "Y5", "G6", "R6", "B6", "Y6", "G6", "R6", "B6", "Y6", "G7", "R7",
            "B7", "Y7", "G7", "R7", "B7", "Y7", "G8", "R8", "B8", "Y8", "G8", "R8", "B8", "Y8", "G9", "R9", "B9", "Y9",
            "G9", "R9", "B9", "Y9", "W", "W", "W", "W", "GR", "RR", "BR", "YR", "GR", "RR", "BR", "YR", "GS", "RS",
            "BS", "YS", "GS", "RS", "BS", "YS", "P4", "P4", "P4", "P4", "GP", "RP", "BP", "YP", "GP", "RP", "BP", "YP",
    };

    /**
     * Creates a random card by selecting a random index from the cards array.
     */
    public Card() {
        card = (int) (Math.random() * cards.length);
        chosenColor = null;
    }

    /**
     * Creates a card with a specific value.
     *
     * @param c The card string (e.g., "R5", "GS", "W", "P4")
     */
    public Card(String c) {
        card = findIndex(c);
        chosenColor = null;
    }

    /**
     * Finds the index of a card string in the cards array.
     *
     * @param c The card string to find
     * @return The index of the card, or -1 if not found
     */
    public int findIndex(String c) {
        for (int i = 0; i < cards.length; i++) {
            if (c.equals(cards[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the color of this card.
     * For wild cards with a chosen color, returns the chosen color.
     * Otherwise, returns the first character of the card string.
     *
     * @return The color character (G, R, B, Y) or the chosen color for wild cards
     */
    public String getColor() {
        String cardStr = cards[card];
        if (isWild() && chosenColor != null) {
            return chosenColor;
        }
        return cardStr.substring(0, 1);
    }

    /**
     * Gets the numeric value of this card.
     * Maps special cards to values: Reverse=10, Skip=11, Plus Two=12, Wild=13, Plus Four=14.
     * Number cards return their face value (0-9).
     *
     * @return The numeric value of the card
     */
    public int getValue() {
        String cardStr = cards[card];
        if (cardStr.startsWith("W"))
            return 13;
        if (cardStr.startsWith("P"))
            return 14;
        String suffix = cardStr.substring(1);
        switch (suffix) {
            case "R":
                return 10;
            case "S":
                return 11;
            case "P":
                return 12;
            default:
                return Integer.parseInt(suffix);
        }
    }

    /**
     * Checks if this card is an action card (Reverse, Skip, Plus Two, Wild, or Plus Four).
     *
     * @return true if the card value is 10 or higher, false otherwise
     */
    public boolean isActionCard() {
        int v = getValue();
        return v >= 10 && v <= 14;
    }

    /**
     * Checks if this card is a wild card (standard Wild or Plus Four).
     *
     * @return true if this is a wild card, false otherwise
     */
    public boolean isWild() {
        String cardStr = cards[card];
        return cardStr.equals("W") || cardStr.equals("P4");
    }

    /**
     * Checks if this card is a Plus Four wild card.
     *
     * @return true if this is a Plus Four card, false otherwise
     */
    public boolean isPlusFour() {
        return cards[card].equals("P4");
    }

    /**
     * Sets the chosen color for a wild card.
     * Only affects wild cards; this method has no effect on regular cards.
     *
     * @param color The color to set (G, R, B, or Y)
     */
    public void setColor(String color) {
        if (isWild()) {
            chosenColor = color;
        }
    }

    /**
     * Clears the chosen color for a wild card.
     */
    public void clearChosenColor() {
        chosenColor = null;
    }

    /**
     * Returns the string representation of this card.
     *
     * @return The card string (e.g., "R5", "GS", "W", "P4")
     */
    public String toString() {
        if (card < 0 || card >= cards.length) {
            return "";
        }
        return cards[card];
    }

    /**
     * Returns the display string for this card, accounting for user-chosen colors on wild cards.
     *
     * @return The display string (e.g., "R5", "WG" for wild with green, "PY" for plus four with yellow)
     */
    public String toDisplayString() {
        if (card < 0 || card >= cards.length) {
            return "";
        }
        if (isWild() && chosenColor != null) {
            if (isPlusFour()) {
                return "P" + chosenColor;
            }
            return "W" + chosenColor;
        }
        return cards[card];
    }

    /**
     * Returns a colored console representation of this card.
     * The card text is colorized based on the card's color using ANSI escape codes.
     *
     * @return The colored card string for console output
     */
    public String toColoredString() {
        if (card < 0 || card >= cards.length) {
            return "";
        }
        String cardStr = toDisplayString();
        String color = getColor();
        String colorCode;

        switch (color) {
            case "G":
                colorCode = ConsoleColors.GREEN;
                break;
            case "R":
                colorCode = ConsoleColors.RED;
                break;
            case "B":
                colorCode = ConsoleColors.BLUE;
                break;
            case "Y":
                colorCode = ConsoleColors.YELLOW;
                break;
            default:
                colorCode = ConsoleColors.WHITE;
                break;
        }

        return ConsoleColors.colorize(cardStr, colorCode);
    }
}
