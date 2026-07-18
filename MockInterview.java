import java.util.*;

class Question {
    String text;
    List<String> keywords;

    public Question(String text, List<String> keywords) {
        this.text = text;
        this.keywords = keywords;
    }

    public int evaluateAnswer(String answer) {
        int score = 0;
        for (String keyword : keywords) {
            if (answer.toLowerCase().contains(keyword.toLowerCase())) {
                score++;
            }
        }
        return score;
    }
}

public class MockInterview {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        System.out.println("=== AI-Powered Mock Interview ===");
        System.out.print("How many questions do you want to add? ");
        int n = Integer.parseInt(sc.nextLine());

        // User adds custom questions
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter Question " + (i+1) + ":");
            String qText = sc.nextLine();

            System.out.println("Enter expected keywords (comma separated):");
            String[] keys = sc.nextLine().split(",");
            List<String> keywords = new ArrayList<>();
            for (String k : keys) {
                keywords.add(k.trim());
            }

            questions.add(new Question(qText, keywords));
        }

        // Interview simulation
        System.out.println("\n=== Interview Begins ===");
        for (Question q : questions) {
            System.out.println("\nQ: " + q.text);
            String ans = sc.nextLine();
            int score = q.evaluateAnswer(ans);
            System.out.println("Feedback: You covered " + score + "/" + q.keywords.size() + " key points.");
        }

        System.out.println("\n=== Interview Finished ===");
        sc.close();
    }
}
