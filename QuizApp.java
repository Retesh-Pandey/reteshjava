import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Questions
        String[] questions = {
            "Q1: What is the capital of India?\n1. Delhi\n2. Mumbai\n3. Kolkata\n4. Chennai",
            "Q2: Which language is primarily used for Android development?\n1. Python\n2. Java\n3. C++\n4. Ruby",
            "Q3: Who is known as the father of computers?\n1. Charles Babbage\n2. Alan Turing\n3. Bill Gates\n4. Steve Jobs"
        };

        // Correct answers (option numbers)
        int[] answers = {1, 2, 1};
        int score = 0;

        System.out.println("=== Welcome to the Quiz Application ===");
        System.out.println("Answer by entering the option number.\n");

        // Loop through questions
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            int userAnswer = sc.nextInt();

            if (userAnswer == answers[i]) {
                System.out.println("✅ Correct!\n");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer was option " + answers[i] + ".\n");
            }
        }

        // Final score
        System.out.println("=== Quiz Finished! ===");
        System.out.println("Your score: " + score + " out of " + questions.length);

        // Feedback
        if (score == questions.length) {
            System.out.println("🎉 Excellent! You nailed it!");
        } else if (score >= 2) {
            System.out.println("👍 Good job! Keep practicing.");
        } else {
            System.out.println("📖 Keep learning, you’ll get better!");
        }

        sc.close();
    }
}
