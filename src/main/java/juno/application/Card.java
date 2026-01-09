package juno.application;

public class Card
{
    private int card;
    
    //private int color;
    
    public static String[] cards = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "skip", "reverse"};
    
    public static String[] colors = {"blue", "red", "yellow", "green"};
    
    public Card()
    {
        card = (int) (Math.random() * cards.length);
    }
    
    public Card(String c)
    {
    	card = findIndex(c);
    }
    
    public int findIndex(String c)
    {
    	for(int i = 0; i < cards.length; i++)
    	{
    		if(c.equals(cards[i]))
    		{
    			return i;
    		}
    	}
    	
    	return -1;
    }

    public String getCard(int i)
    {
        return cards[i];
    }
    
    public String toString()
    {
        return cards[card];
    }
}