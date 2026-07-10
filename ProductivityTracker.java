import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String name;
    private int duration; // in minutes
    private boolean completed;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public String toString() {
        return name + " (" + duration + " min) - " + (completed ? "Done ✅" : "Pending ⏳");
    }
}

public class ProductivityTracker {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Productivity Tracker ---");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. Show Total Time Spent");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter duration (minutes): ");
                    int duration = sc.nextInt();
                    tasks.add(new Task(name, duration));
                    System.out.println("Task added!");
                    break;

                case 2:
                    System.out.print("Enter task number to mark completed: ");
                    int index = sc.nextInt();
                    if (index > 0 && index <= tasks.size()) {
                        tasks.get(index - 1).markCompleted();
                        System.out.println("Task marked as completed!");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 3:
                    System.out.println("\nYour Tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    break;

                case 4:
                    int totalTime = 0;
                    for (Task t : tasks) {
                        if (t.isCompleted()) {
                            totalTime += t.getDuration();
                        }
                    }
                    System.out.println("Total productive time: " + totalTime + " minutes");
                    break;

                case 5:
                    System.out.println("Exiting... Stay productive!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
