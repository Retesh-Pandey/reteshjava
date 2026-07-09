import java.util.Random;
import java.util.Scanner;

public class DiceGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("🎲 Welcome to the Dice Rolling Game!");
        System.out.println("You and the computer will roll a dice. Highest number wins!");

        System.out.print("Press Enter to roll your dice...");
        scanner.nextLine();

        int playerRoll = random.nextInt(6) + 1; // 1 to 6
        int computerRoll = random.nextInt(6) + 1;

        System.out.println("You rolled: " + playerRoll);
        System.out.println("Computer rolled: " + computerRoll);

        if (playerRoll > computerRoll) {
            System.out.println("🎉 You win!");
        } else if (playerRoll < computerRoll) {
            System.out.println("😢 You lose!");
        } else {
            System.out.println("🤝 It's a tie!");
        }

        scanner.close();
    }
}
