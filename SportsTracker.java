import java.util.*;

class Player {
    String name;
    int age;
    String sport;
    int matchesPlayed;
    int score;

    Player(String name, int age, String sport) {
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.matchesPlayed = 0;
        this.score = 0;
    }

    void updateStats(int matchScore) {
        matchesPlayed++;
        score += matchScore;
    }

    void showPerformance() {
        System.out.println("Player: " + name);
        System.out.println("Sport: " + sport);
        System.out.println("Matches Played: " + matchesPlayed);
        System.out.println("Total Score: " + score);
        System.out.println("Average Performance: " + (matchesPlayed > 0 ? score / matchesPlayed : 0));
    }
}

public class SportsTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Player> players = new ArrayList<>();

        System.out.println("Welcome to Smart Sports Performance Tracker!");
        while (true) {
            System.out.println("\n1. Add Player\n2. Update Stats\n3. Show Performance\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Age: ");
                int age = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Sport: ");
                String sport = sc.nextLine();
                players.add(new Player(name, age, sport));
            } else if (choice == 2) {
                System.out.print("Enter Player Name: ");
                String name = sc.nextLine();
                for (Player p : players) {
                    if (p.name.equalsIgnoreCase(name)) {
                        System.out.print("Enter Match Score: ");
                        int score = sc.nextInt();
                        p.updateStats(score);
                        break;
                    }
                }
            } else if (choice == 3) {
                for (Player p : players) {
                    p.showPerformance();
                    System.out.println("-------------------");
                }
            } else {
                break;
            }
        }
        sc.close();
    }
}
