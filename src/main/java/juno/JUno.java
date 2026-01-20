package juno;
import java.util.ArrayList;
import java.util.Scanner;

public class JUno
{
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck = new Deck();

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to JUno!\n");

        System.out.print("Enter number of players: ");
        int numPlayers = input.nextInt();

        if(numPlayers < 2 || numPlayers > 4)
        {
            System.out.println("Invalid number of players. Please enter a number between 2 and 4.");
            input.close();
            return;
        }
        else
        {
            for(int i = 1; i <= numPlayers; i++)
            {
                System.out.print("Enter name for Player " + i + ": ");
                String name = input.next();
                Player player = new Player(i);
                player.setPlayerName(name);
                players.add(player);
            }
            
            Game game = new Game(players);
            game.startGame(players);

            for (Player player : game.getPlayers())
            {
                System.out.println(player.getPlayerName() + " has " + player.getCards().size() + " cards.");
                player.printCards();
            }
        }

        input.close();
    }
}
