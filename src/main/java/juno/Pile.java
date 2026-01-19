package juno;

import java.util.ArrayList;

public class Pile
{
    private ArrayList<Card> pile = new ArrayList<Card>();

    public Pile()
    {

    }
    
    public void addCard(Card card)
    {
        if (pile.isEmpty()) {
            pile.add(card);
        } else {
            if (canAddCard(card)) {
                pile.add(card);
            }
            else {
                throw new IllegalArgumentException("Cannot add card to pile: invalid move.");
            }
        }
    }
    
    public boolean canAddCard(Card card)
    {
        Card topCard = pile.get(pile.size() - 1);
        if (topCard.getValue() == card.getValue() || topCard.getColor() == card.getColor() || topCard.getColor().startsWith("W"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
