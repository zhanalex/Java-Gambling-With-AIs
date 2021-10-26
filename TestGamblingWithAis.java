import java.util.List;
import java.util.Scanner;

public class TestGamblingWithAis {
    public static void runGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Specify how many cards you want to be distributed to each player. It has to be less than 18: ");
        int numOfCards = Integer.parseInt(sc.nextLine());
        GamblingWithAIs game = new GamblingWithAIs();
        game.GamblingWithAIS();
        game.distributeCards(numOfCards);
        while (true) {
            for (List<String> player : game.players) {
                if (player == game.you) {
                    System.out.println("You have " + game.getYourLength() + " card(s). You cards are " + game.getYourCards());
                    if (game.player1_status = true) {
                        System.out.println("Player1 has " + game.getPlayer1() + " cards");
                    }
                    if (game.player2_status = true) {
                        System.out.println("Player2 has " + game.getPlayer2() + " cards");
                    }
                    Scanner input = new Scanner(System.in);
                    System.out.println("Specify a card to place it in the middle of the table. Anything else will mean that you want to draw a card: ");
                    // takes input from the keyboard
                    String userResponse = input.nextLine();
                    if (game.you.contains(userResponse)) {
                        game.putCardOnTable(game.you, userResponse);
                    } else {
                        game.drawCard(game.you);
                    }
                } else {
                    if (player == game.player1 && game.player1_status == false) {
                        continue;
                    }
                    if (player == game.player2 && game.player2_status == false) {
                        continue;
                    }
                    String action = game.aiDecision(player);
                    if (player.equals(game.player1)) {
                        System.out.println("Player1 has" + action);
                    }
                    if (player.equals(game.player2)) {
                        System.out.println("Player2 has" + action);
                    }
                }
                if (game.you.isEmpty()) {
                    System.out.println("You have lost all your cards. You lose.");
                    System.exit(0);
                }
                if (game.player1.isEmpty()) {
                    System.out.println("Player1 has lost and is no longer in the game");
                    game.player1_status = false;
                }
                if (game.player2.isEmpty()) {
                    System.out.println("Player2 has lost and is no longer in the game");
                    game.player2_status = false;
                }
                if (game.player2.isEmpty() && game.player1.isEmpty()) {
                    System.out.println("You win!");
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String args[]) {
        runGame();
    }
}