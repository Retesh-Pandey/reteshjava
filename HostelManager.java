import java.util.*;

public class HostelManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Double> expenses = new HashMap<>();
        List<String> chores = new ArrayList<>();

        System.out.println("🏠 Hostel Expense & Chore Manager");
        System.out.println("Enter number of students:");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] students = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i+1) + ": ");
            students[i] = sc.nextLine();
            expenses.put(students[i], 0.0);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Assign Chore");
            System.out.println("4. View Chores");
            System.out.println("5. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    if (expenses.containsKey(name)) {
                        System.out.print("Enter expense amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        expenses.put(name, expenses.get(name) + amt);
                        System.out.println("Expense added!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Expenses ---");
                    double total = 0;
                    for (String s : students) {
                        System.out.println(s + " spent: ₹" + expenses.get(s));
                        total += expenses.get(s);
                    }
                    System.out.println("Total expenses: ₹" + total);
                    System.out.println("Each should pay: ₹" + (total/n));
                    break;

                case 3:
                    System.out.print("Enter chore description: ");
                    String chore = sc.nextLine();
                    Random rand = new Random();
                    String assigned = students[rand.nextInt(n)];
                    chores.add(chore + " -> " + assigned);
                    System.out.println("Chore assigned to " + assigned);
                    break;

                case 4:
                    System.out.println("\n--- Chores ---");
                    for (String c : chores) {
                        System.out.println(c);
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
