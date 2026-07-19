import java.util.*;

public class VocabularyQuiz {
    static Scanner sc = new Scanner(System.in);

    static class Word {
        String word;
        String meaning;

        Word(String word, String meaning) {
            this.word = word;
            this.meaning = meaning;
        }
    }

    public static void main(String[] args) {
        List<Word> words = new ArrayList<>();
        System.out.println("📘 Welcome to Vocabulary Quiz!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Word");
            System.out.println("2. Take Quiz");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter word: ");
                String word = sc.nextLine();
                System.out.print("Enter meaning: ");
                String meaning = sc.nextLine();
                words.add(new Word(word, meaning));
                System.out.println("✅ Word added!");
            } 
            else if (choice == 2) {
                if (words.isEmpty()) {
                    System.out.println("No words added yet. Add some first!");
                } else {
                    Random rand = new Random();
                    Word randomWord = words.get(rand.nextInt(words.size()));
                    System.out.println("\n❓ What is the meaning of: " + randomWord.word);
                    String answer = sc.nextLine();

                    if (answer.equalsIgnoreCase(randomWord.meaning)) {
                        System.out.println("🎉 Correct!");
                    } else {
                        System.out.println("❌ Wrong. The correct meaning is: " + randomWord.meaning);
                    }
                }
            } 
            else if (choice == 3) {
                System.out.println("Goodbye! Keep learning 📚");
                break;
            } 
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
