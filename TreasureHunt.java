import java.util.Scanner;

public class TreasureHunt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🏝️ Welcome to the Treasure Hunt!");
        System.out.println("You are standing at the entrance of a dark cave.");
        System.out.println("Do you want to go LEFT or RIGHT?");

        String choice1 = scanner.nextLine();

        if (choice1.equalsIgnoreCase("LEFT")) {
            System.out.println("You find a sleeping dragon! 🐉");
            System.out.println("Do you want to FIGHT or RUN?");
            String choice2 = scanner.nextLine();

            if (choice2.equalsIgnoreCase("FIGHT")) {
                System.out.println("The dragon wakes up and defeats you. Game Over!");
            } else {
                System.out.println("You run away safely and find a hidden treasure chest! 🎉 You win!");
            }
        } else if (choice1.equalsIgnoreCase("RIGHT")) {
            System.out.println("You walk into a room full of traps! ⚔️");
            System.out.println("Do you want to JUMP or CROUCH?");
            String choice2 = scanner.nextLine();

            if (choice2.equalsIgnoreCase("JUMP")) {
                System.out.println("You jump over the trap and find the treasure! 🏆 You win!");
            } else {
                System.out.println("You crouch but get caught in the trap. Game Over!");
            }
        } else {
            System.out.println("Invalid choice. The cave collapses. Game Over!");
        }

        scanner.close();
    }
}
