package juno.application;

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> deck = new ArrayList<Card>();
    
    public Deck()
    {
        int index = 0;

        for(int i = 0; i < Card.cards.length; i++)
        {
            if(!Card.cards[i].equals("X"))
            {
                index = i;
                break;
            }
        }

        for(int i = index; i < Card.cards.length; i++)
        {
            deck.add(new Card());
        }
        
        Collections.shuffle(deck);
    }
    
    public Card draw()
    {
		if (!deck.isEmpty())
		{
			return deck.remove(deck.size() - 1);
		}
		else
		{
			return null;
		}
	}
	
	public void dealCards(Player p)
	{
	    for(int i = 0; i < 6; i++)
	    {
    	    int randIndex = (int) (Math.random() * deck.size());
            String randomCard = deck.get(randIndex).toString();
            deck.remove(deck.get(randIndex));
            
            Card c = new Card(randomCard);
            
            p.getCards().add(c);
	    }
    }
    
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public void add(Card c)
    {
        deck.add(c);
    }
    
    public void remove(Card c)
    {
        deck.remove(find(c.toString()));
    }
    
    public Card find(String card)
    {
        for(Card c : deck)
        {
            if(c.toString().equals(card))
            {
                return c;
            }
        }
        
        return null;
    }

	public int size()
	{
		return deck.size();
	}
	
	public static int findIndex(String[] arr, String text) 
    {
        int len = arr.length; 
        int i = 0; 

        while(i < len)
        {
            if(arr[i].equals(text))
            {
                return i; 
            }
            else
            {
                i += 1; 
            }
        }
        return -1; 
    }
}