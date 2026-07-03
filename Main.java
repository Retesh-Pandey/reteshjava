import java.util.*;

class Student {
    private int id;
    private String name;
    private String course;

    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCourse() { return course; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Course: " + course;
    }
}

class StudentManagement {
    private Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student s) {
        students.put(s.getId(), s);
        System.out.println("Student added successfully!");
    }

    public void removeStudent(int id) {
        if (students.containsKey(id)) {
            students.remove(id);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            students.values().forEach(System.out::println);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. View Students");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();
                    sm.addStudent(new Student(id, name, course));
                    break;
                case 2:
                    System.out.print("Enter ID to remove: ");
                    int removeId = sc.nextInt();
                    sm.removeStudent(removeId);
                    break;
                case 3:
                    sm.viewStudents();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
