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

    public boolean isWildOrDrawCard()
    {
        int v = getValue();
        return v == 12 || v == 13 || v == 14;  // Draw 2, Wild, Wild Draw 4
    }

    public void setWildcardColor(String c, String color)
    {
        cards[findIndex(c)] = "W" + color;
    }

    public String toString() {
        return cards[card];
    }
}