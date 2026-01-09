package juno.application;

import java.util.ArrayList;
import java.util.Collections;

public class Hand
{
    private ArrayList<Card> playerCards = new ArrayList<Card>();
    
    public Hand()
    {
        
    }
    
    public void add(Card c)
    {
		playerCards.add(c);
	}
	
	public void add(ArrayList<Card> listOfCards)
	{
	    for(Card c : listOfCards)
	    {
	        playerCards.add(c);
	    }
	}

	public void remove(Card c)
	{
		playerCards.remove(find(c.toString()));
	}
	
	public Card find(String card)
    {
        for(Card c : playerCards)
        {
            if(c.toString().equals(card))
            {
                return c;
            }
        }
        
        return null;
    }

//	public Card find(String card)
//    {
//        for(Card c : playerCards)
//        {
//            if(c.getRank() == Deck.findIndex(Card.ranks, card.substring(0, card.length() - 1))
//				&& c.getSuit() == Deck.findIndex(Card.suits, card.substring(card.length() - 1)))
//            {
//                return c;
//            }
//        }
//        
//        return null;
//    }
    
    public int size()
    {
		return playerCards.size();
	}
	
	public Card getCard(int i)
	{
	    return playerCards.get(i);
	}
	
	public ArrayList<Card> getCards()
	{
		return playerCards;
	}
	
	public ArrayList<String> sort()
	{
	    ArrayList<String> sorted = new ArrayList<String>();
	    
	    for(int i = 0; i < size(); i++)
	    {
	        sorted.add(getCard(i).toString());
	    }
	    
	    Collections.sort(sorted);
	    
	    return sorted;
	}
}