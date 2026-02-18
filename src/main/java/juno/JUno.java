package juno;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;

public class JUno {
    public static ArrayList<Player> players = new ArrayList<Player>();
    public static Deck deck;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        clearScreen();
        System.out.println("Welcome to JUno!\n");

        boolean numPlayersCorrect = false;

        int numPlayers = 0;

        while (!numPlayersCorrect) {
            System.out.print("Enter number of players: ");
            String playerInput = input.nextLine().trim();
            
            if (playerInput.isEmpty()) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            try {
                numPlayers = Integer.parseInt(playerInput);
                if (numPlayers >= 2 && numPlayers <= 10) {
                    numPlayersCorrect = true;
                } else {
                    System.out.println("Invalid number of players. Please enter a number between 2 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        clearScreen();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = input.nextLine();
            if(name.trim().isEmpty()) {
                name = "Player " + i;
            }
            Player player = new Player(i);
            player.setPlayerName(name);
            players.add(player);
        }

        Game game = new Game(players);
        deck = new Deck(game);
        game.startGame(players);

        while (!game.isGameOver()) {
            Player player = game.getPlayers().get(game.getCurrentPlayerIndex());
            boolean turnComplete = false;

            clearScreen();
            System.out.print("It is " + player.getPlayerName() + "'s turn. Press Enter when ready. ");
            input.nextLine();
            clearScreen();

            while (!turnComplete) {
                if (!hasPlayableCard(game, player)) {
                    Card lastDrawn = null;
                    while (lastDrawn == null || !game.isValidMove(lastDrawn)) {
                        System.out.println("No playable cards. Drawing a card...");
                        lastDrawn = game.drawCard(player);
                    }

                    System.out.println("Drew a playable card: " + lastDrawn.toColoredString());
                    player.remove(lastDrawn);
                    game.checkActionCard(lastDrawn, input);
                    System.out.println(player.handToString());
                    if (!lastDrawn.isWild() && !lastDrawn.isPlusFour()) {
                        System.out.print("Press Enter to continue...");
                        input.nextLine();
                    }
                    turnComplete = true;
                    continue;
                }

                System.out.println("The top card is: " + game.getTopCard().toColoredString());

                System.out.println(player.handToString());

                if (player.getCards().size() > 1) {
                    System.out.print(player.getPlayerName() + ", enter your move (1-" + player.getCards().size()
                            + ", 0 to draw a card): ");
                } else {
                    System.out.print(player.getPlayerName()
                            + ", enter your move (1 to play your last card, 0 to draw a card): ");
                }

                String move;
                try {
                    move = input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine();
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine();
                    continue;
                }

                if (move.equals("0")) {
                    game.drawCard(player);
                    turnComplete = true;
                } else {
                    Card card = player.find(move.trim().toUpperCase());
                    if (card != null) {
                        if (game.isValidMove(card)) {
                            player.remove(card);
                            game.checkActionCard(card, input);
                            turnComplete = true;
                        } else {
                            System.out.println("Invalid move. Please try again.");
                        }
                    } else {
                        try {
                            int idx = Integer.parseInt(move);
                            if (idx > 0 && idx <= player.getCards().size()) {
                                Card cardByIdx = player.getCards().get(idx - 1);
                                if (game.isValidMove(cardByIdx)) {
                                    player.remove(cardByIdx);
                                    game.checkActionCard(cardByIdx, input);
                                    turnComplete = true;
                                } else {
                                    System.out.println("Invalid move. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid move. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid move. Please try again.");
                        }
                    }
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
        String osName = System.getProperty("os.name").toLowerCase();
        try {
            if (osName.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.print("\n".repeat(50));
        }
    }

    public static String colorize(String text, String colorCode) {
        return colorCode + text + ConsoleColors.RESET;
    }

    private static boolean hasPlayableCard(Game g, Player p) {
        for (Card card : p.getCards()) {
            if (g.isValidMove(card)) {
                return true;
            }
        }
        return false;
    }
}
