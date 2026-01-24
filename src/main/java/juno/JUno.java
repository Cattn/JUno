package juno;
import java.util.ArrayList;
import java.util.Scanner;

public class JUno
{
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck = new Deck();
    //public static Card topCard;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        clearScreen();
        System.out.println("Welcome to JUno!\n");

        System.out.print("Enter number of players: ");
        int numPlayers = input.nextInt();

        if(numPlayers < 2 || numPlayers > 10)
        {
            System.out.println("Invalid number of players. Please enter a number between 2 and 10.");
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

            //clearScreen();

            for (Player player : game.getPlayers())
            {
                clearScreen();

                clearScreen();
                System.out.print("It is " + player.getPlayerName() + "'s turn. Press Enter when ready. ");
                input.nextLine();
                clearScreen();

                System.out.println("The top card is: " + game.getTopCard().toString());

                //System.out.print(player.getPlayerName() + ", you have the following cards: ");
                System.out.println(player.getCards());

                System.out.print(player.getPlayerName() + ", enter your move (1-" + player.getCards().size() + ", 0 to draw a card): ");
                int move = input.nextInt();
                if(move == 0)
                {
                    game.getAction().drawCard(player);
                }
                else if(move > 0 && move <= player.getCards().size())
                {
                    Card card = player.getCards().get(move - 1);
                    if(game.isValidMove(card))
                    {
                        player.getCards().remove(card);
                        game.setTopCard(card);
                        game.nextPlayer();
                    }
                    else
                    {
                        System.out.println("Invalid move. Please try again.");
                    }
                }
            }
        }

        input.close();
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
    }
}
