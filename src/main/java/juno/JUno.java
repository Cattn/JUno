package juno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;

public class JUno {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck = new Deck();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        clearScreen();
        System.out.println("Welcome to JUno!\n");

        boolean numPlayersCorrect = false;

        int numPlayers = 0;

        while (!numPlayersCorrect) {
            try {
                System.out.print("Enter number of players: ");
                numPlayers = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                numPlayers = 0;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                numPlayers = 0;
            }

            if (numPlayers >= 2 && numPlayers <= 10) {
                numPlayersCorrect = true;
            } else {
                System.out.println("Invalid number of players. Please enter a number between 2 and 10.");
                numPlayers = 0;
                numPlayersCorrect = false;
            }
        }

        clearScreen();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = input.nextLine();
            Player player = new Player(i);
            player.setPlayerName(name);
            players.add(player);
        }

        Game game = new Game(players);
        game.startGame(players);

        while (!game.isGameOver()) {
            Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
            boolean turnComplete = false;

            clearScreen();
            System.out.print("It is " + player.getPlayerName() + "'s turn. Press Enter when ready. ");
            input.nextLine();
            clearScreen();

            while (!turnComplete) {
                System.out.println("The top card is: " + game.getTopCard().toColoredString());

                System.out.println(player.getHand().toString());

                if (player.getCards().size() > 1) {
                    System.out.print(player.getPlayerName() + ", enter your move (1-" + player.getCards().size()
                            + ", 0 to draw a card): ");
                } else {
                    System.out.print(player.getPlayerName()
                            + ", enter your move (1 to play your last card, 0 to draw a card): ");
                }

                int move;
                try {
                    move = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine();
                    continue;
                } catch (NumberFormatException e) {
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
                        game.checkActionCard(card, input);
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

                game.getPlayers().remove(player);

                if (game.getPlayers().size() > 1) {
                    System.out.print("Continue with remaining players? (y/n): ");
                    String continueInput = input.nextLine().trim().toLowerCase();
                    if (!continueInput.equals("y") && !continueInput.equals("yes")) {
                        game.endGame();
                        break;
                    }
                } else {
                    System.out.println("No players left. Game over.");
                    game.endGame();
                    break;
                }

                game.normalizeCurrentPlayerIndex();
            } else {
                game.nextPlayer();
            }
        }

        input.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static String colorize(String text, String colorCode) {
        return colorCode + text + ConsoleColors.RESET;
    }
}
