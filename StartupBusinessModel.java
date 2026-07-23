import java.util.*;

class Customer {
    String name;
    String email;

    Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

public class StartupBusinessModel {
    static List<Customer> customers = new ArrayList<>();
    static List<Product> products = new ArrayList<>();
    static double revenue = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Startup Business Model ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Sell Product");
            System.out.println("4. View Customers");
            System.out.println("5. View Products");
            System.out.println("6. View Revenue");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addCustomer(sc);
                case 2 -> addProduct(sc);
                case 3 -> sellProduct(sc);
                case 4 -> viewCustomers();
                case 5 -> viewProducts();
                case 6 -> System.out.println("Total Revenue: $" + revenue);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);

        sc.close();
    }

    static void addCustomer(Scanner sc) {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();
        System.out.print("Enter customer email: ");
        String email = sc.nextLine();
        customers.add(new Customer(name, email));
        System.out.println("Customer added successfully!");
    }

    static void addProduct(Scanner sc) {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter product price: ");
        double price = sc.nextDouble();
        products.add(new Product(name, price));
        System.out.println("Product added successfully!");
    }

    static void sellProduct(Scanner sc) {
        if (products.isEmpty() || customers.isEmpty()) {
            System.out.println("Add customers and products first!");
            return;
        }
        System.out.println("Select customer:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }
        int custChoice = sc.nextInt() - 1;

        System.out.println("Select product:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        int prodChoice = sc.nextInt() - 1;

        revenue += products.get(prodChoice).price;
        System.out.println("Sale recorded: " + customers.get(custChoice).name +
                " bought " + products.get(prodChoice).name);
    }

    static void viewCustomers() {
        System.out.println("Customers:");
        customers.forEach(System.out::println);
    }

    static void viewProducts() {
        System.out.println("Products:");
        products.forEach(System.out::println);
    }
}
