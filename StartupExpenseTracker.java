import java.util.*;

class Expense {
    String category;
    double amount;
    String description;

    Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return category + " | $" + amount + " | " + description;
    }
}

public class StartupExpenseTracker {
    static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Startup Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Expenses by Category");
            System.out.println("4. Calculate Total Expenses");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addExpense(sc);
                case 2 -> viewExpenses();
                case 3 -> viewByCategory(sc);
                case 4 -> calculateTotal();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    static void addExpense(Scanner sc) {
        System.out.print("Enter category (e.g., Marketing, Tech, HR): ");
        String category = sc.nextLine();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.print("Enter description: ");
        String description = sc.nextLine();

        expenses.add(new Expense(category, amount, description));
        System.out.println("Expense added successfully!");
    }

    static void viewExpenses() {
        System.out.println("All Expenses:");
        expenses.forEach(System.out::println);
    }

    static void viewByCategory(Scanner sc) {
        System.out.print("Enter category to filter: ");
        String category = sc.nextLine();
        System.out.println("Expenses in " + category + ":");
        expenses.stream()
                .filter(e -> e.category.equalsIgnoreCase(category))
                .forEach(System.out::println);
    }

    static void calculateTotal() {
        double total = expenses.stream().mapToDouble(e -> e.amount).sum();
        System.out.println("Total Expenses: $" + total);
    }
}
