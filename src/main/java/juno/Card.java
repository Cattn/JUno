package juno;

public class Card {
    private int card;

    public static String[] cards = { "G0", "R0", "B0", "Y0", "G1", "R1", "B1", "Y1", "G1", "R1", "B1", "Y1", "G2", "R2",
            "B2", "Y2", "G2", "R2", "B2", "Y2", "G3", "R3", "B3", "Y3", "G3", "R3", "B3", "Y3", "G4", "R4", "B4", "Y4",
            "G4", "R4", "B4", "Y4", "G5", "R5", "B5", "Y5", "G5", "R5", "B5", "Y5", "G6", "R6", "B6", "Y6", "G6", "R6",
            "B6", "Y6", "G7", "R7", "B7", "Y7", "G7", "R7", "B7", "Y7", "G8", "R8", "B8", "Y8", "G8", "R8", "B8", "Y8",
            "G9", "R9", "B9", "Y9", "G9", "R9", "B9", "Y9", "W", "W", "W", "W", "GR", "RR", "BR", "YR", "GS", "RS", "BS", "YS",
            "P4", "P4", "P4", "P4", "GP", "RP", "BP", "YP",
    };

    public Card() {
        card = (int) (Math.random() * cards.length);
    }

    public Card(String c) {
        card = findIndex(c);
    }

    public int findIndex(String c) {
        for (int i = 0; i < cards.length; i++) {
            if (c.equals(cards[i])) {
                return i;
            }
        }

        return -1;
    }

    public String getCard(int i) {
        return cards[i];
    }

    public String getColor()
    {
        return cards[card].substring(0, 1);
    }

    public int getValue()
    {
        String cardStr = cards[card];
        if (cardStr.equals("W") || cardStr.startsWith("W"))
            return 13;  // Wild
        if (cardStr.equals("P4"))
            return 14;  // Wild Draw 4
        String suffix = cardStr.substring(1);
        switch (suffix) {
            case "R": return 10;  // Reverse
            case "S": return 11;  // Skip
            case "P": return 12;  // Draw 2
            default:  return Integer.parseInt(suffix);  // 0–9
        }
    }

    public boolean isActionCard()
    {
        int v = getValue();
        return v >= 10 && v <= 14; // Reverse, Skip, Draw 2, Wild, Wild Draw 4
    }

    public void setWildcardColor(String c, String color)
    {
        cards[findIndex(c)] = "W" + color;
    }

    public void setPlusFourColor(String c, String color)
    {
        cards[findIndex(c)] = "P4" + color;
    }

    public String toString() {
        if (card < 0 || card >= cards.length) {
            return "";
        }
        return cards[card];
    }

    public String toColoredString() {
        if (card < 0 || card >= cards.length) {
            return "";
        }
        String cardStr = cards[card];
        String colorCode;
        
        if (cardStr.equals("W") || cardStr.startsWith("W") || cardStr.equals("P4") || cardStr.startsWith("P4")) {
            colorCode = ConsoleColors.WHITE;
        } else {
            String color = getColor();
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
        }
        
        return JUno.colorize(cardStr, colorCode);
    }
}