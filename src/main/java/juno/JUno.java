package juno;
import java.util.ArrayList;

public class JUno
{
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck = new Deck();

    public static void main(String[] args)
    {        
        Player player1 = new Player(1);
        player1.setPlayerName("Logan");
        players.add(player1);
        Player player2 = new Player(2);
        player2.setPlayerName("Daniel");
        players.add(player2);
        Player player3 = new Player(3);
        player3.setPlayerName("Player 3");
        players.add(player3);
        Player player4 = new Player(4);
        player4.setPlayerName("Player 4");
        players.add(player4);
        
        Game game = new Game(players);
        game.startGame(players);

        for (Player player : game.getPlayers())
        {
            System.out.println(player.getPlayerName() + " has " + player.getCards().size() + " cards.");
            player.printCards();
        }
    }
}
