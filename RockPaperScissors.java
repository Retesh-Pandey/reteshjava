import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"Rock", "Paper", "Scissors"};
        System.out.println("🎮 Welcome to Rock-Paper-Scissors!");
        System.out.println("Enter your choice (Rock, Paper, Scissors). Type 'exit' to quit.");

        while (true) {
            System.out.print("Your move: ");
            String playerChoice = scanner.nextLine();

            if (playerChoice.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for playing! Goodbye 👋");
                break;
            }

            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];
            System.out.println("Computer chose: " + computerChoice);

            if (playerChoice.equalsIgnoreCase(computerChoice)) {
                System.out.println("It's a tie!");
            } else if (
                (playerChoice.equalsIgnoreCase("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equalsIgnoreCase("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equalsIgnoreCase("Scissors") && computerChoice.equals("Paper"))
            ) {
                System.out.println("🎉 You win!");
            } else {
                System.out.println("😢 You lose!");
            }
        }

        scanner.close();
    }
}
