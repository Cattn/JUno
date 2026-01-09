package juno.application;

import java.util.ArrayList;

public class Player
{
	private Hand hand;
	private int playerID;
	
	public Player(int playerID)
	{
	    hand = new Hand();
	    this.playerID = playerID;
	}
	
	public ArrayList<Card> getCards()
	{
	   return hand.getCards();
	}
	
	public Hand getHand()
	{
	    return hand;
	}
	
	public int getPlayerID()
	{
	    return playerID;
	}
	
	public boolean hasWon()
	{
	    return getCards().isEmpty();
	}

    public void printCards()
	{
	    System.out.println(getCards());
	}
}