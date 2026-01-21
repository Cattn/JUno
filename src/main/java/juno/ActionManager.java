package juno;

public class ActionManager
{
    private static Game game;
    
    public ActionManager(Game g)
    {
        game = g;
    }

    public void drawCard(Player player)
    {
        player.getCards().add(JUno.deck.draw());
    }

    public void drawTwoCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
    }

    public void drawFourCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
    }

    public void skipTurn(Player player)
    {
        game.nextPlayer();
    }

    public void reverse()
    {
        game.reverseDirection();
    }
}
