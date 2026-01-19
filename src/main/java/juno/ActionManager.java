package juno;

public class ActionManager
{
    private static Game game;
    
    public ActionManager(Game g)
    {
        game = g;
    }

    public static void drawTwoCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
    }

    public static void drawFourCards(Player player)
    {
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
        player.getCards().add(JUno.deck.draw());
    }

    public static void skipTurn(Player player)
    {
        game.nextPlayer();
    }

    public static void reverse()
    {
        game.reverseDirection();
    }
}
