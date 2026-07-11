import java.util.*;
import java.util.concurrent.*;

// Singleton for chatbot engine
class ChatEngine {
    private static ChatEngine instance;
    private ExecutorService executor;

    private ChatEngine() {
        executor = Executors.newFixedThreadPool(5);
    }

    public static ChatEngine getInstance() {
        if (instance == null) {
            instance = new ChatEngine();
        }
        return instance;
    }

    public void processMessage(String user, String message) {
        executor.submit(() -> {
            String response = NLPProcessor.generateResponse(message);
            Database.saveConversation(user, message, response);
            System.out.println("Bot to " + user + ": " + response);
        });
    }
}

// Factory for NLP responses
class NLPProcessor {
    public static String generateResponse(String input) {
        if (input.toLowerCase().contains("hello")) {
            return "Hi there! How can I help?";
        } else if (input.toLowerCase().contains("java")) {
            return "Java is powerful for enterprise apps!";
        }
        return "Interesting... tell me more.";
    }
}

// Simple database simulation
class Database {
    private static List<String> logs = new ArrayList<>();

    public static void saveConversation(String user, String input, String output) {
        logs.add(user + " said: " + input + " | Bot replied: " + output);
    }

    public static void showLogs() {
        System.out.println("=== Conversation Logs ===");
        logs.forEach(System.out::println);
    }
}

// Main driver
public class EliteChatBot {
    public static void main(String[] args) {
        ChatEngine bot = ChatEngine.getInstance();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String user = sc.nextLine();

        while (true) {
            System.out.print(user + ": ");
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;
            bot.processMessage(user, msg);
        }

        Database.showLogs();
        sc.close();
    }
}
