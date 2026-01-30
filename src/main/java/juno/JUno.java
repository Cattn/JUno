package juno;

import java.util.ArrayList;
import java.util.Scanner;

public class JUno {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck = new Deck();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        clearScreen();
        System.out.println("Welcome to JUno!\n");

        boolean numPlayersCorrect = false;

        System.out.print("Enter number of players: ");
        int numPlayers = input.nextInt();
        input.nextLine();

        while (!numPlayersCorrect) {
            if (numPlayers < 2 || numPlayers > 10) {
                clearScreen();
                System.out.println("Invalid number of players. Please enter a number between 2 and 10.");
                System.out.print("Enter number of players: ");
                numPlayers = input.nextInt();
                input.nextLine();
            } else {
                numPlayersCorrect = true;
                break;
            }
        }
        
        clearScreen();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = input.next();
            Player player = new Player(i);
            player.setPlayerName(name);
            players.add(player);
        }

        Game game = new Game(players);
        game.startGame(players);

        while (!game.isGameOver()) {
            for (int i = 0; i < game.getPlayers().size(); i++) {
                Player player = game.getPlayers().get(i);
                boolean turnComplete = false;

                clearScreen();
                System.out.print("It is " + player.getPlayerName() + "'s turn. Press Enter when ready. ");
                input.nextLine();
                clearScreen();

                while (!turnComplete) {
                    System.out.println("The top card is: " + game.getTopCard().toColoredString());

                    System.out.println(player.getHand().toString());

                    System.out.print(player.getPlayerName() + ", enter your move (1-" + player.getCards().size()
                            + ", 0 to draw a card): ");
                    int move;
                    try {
                        move = input.nextInt();
                        input.nextLine();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        input.nextLine();
                        continue;
                    } catch (java.lang.NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        input.nextLine();
                        continue;
                    }

                    if (move == 0) {
                        game.drawCard(player);
                        turnComplete = true;
                    } else if (move > 0 && move <= player.getCards().size()) {
                        Card card = player.getCards().get(move - 1);
                        if (game.isValidMove(card)) {
                            player.getCards().remove(card);
                            game.setTopCard(card);
                            game.nextPlayer();
                            turnComplete = true;
                        } else {
                            System.out.println("Invalid move. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid move. Please try again.");
                    }
                }

                if (player.hasWon()) {
                    System.out.println(player.getPlayerName() + " has won!");

                    if (game.getPlayers().size() <= 1) {
                        game.endGame();
                        break;
                    }

                    System.out.print("Continue with remaining players? (y/n): ");
                    String continueInput = input.nextLine().trim().toLowerCase();
                    if (!continueInput.equals("y") && !continueInput.equals("yes")) {
                        game.endGame();
                        break;
                    }

                    game.getPlayers().remove(i);
                    i--;

                    if (game.getPlayers().size() <= 1) {
                        game.endGame();
                        break;
                    }
                }
            }
        }
        input.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static String colorize(String text, String colorCode)
    {
        return colorCode + text + ConsoleColors.RESET;
    }
}
