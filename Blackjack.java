import java.util.*;

class Card {
    String suit;
    String rank;
    int value;

    Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    List<Card> cards = new ArrayList<>();
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    int[] values = {2,3,4,5,6,7,8,9,10,10,10,10,11};

    Deck() {
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards.add(new Card(suits[i], ranks[j], values[j]));
            }
        }
        Collections.shuffle(cards);
    }

    Card draw() {
        return cards.remove(0);
    }
}

public class Blackjack {
    public static int handValue(List<Card> hand) {
        int value = 0;
        int aces = 0;
        for (Card c : hand) {
            value += c.value;
            if (c.rank.equals("Ace")) aces++;
        }
        while (value > 21 && aces > 0) {
            value -= 10; // Ace counts as 1 instead of 11
            aces--;
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();

        List<Card> playerHand = new ArrayList<>();
        List<Card> dealerHand = new ArrayList<>();

        // Initial deal
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());

        System.out.println("Welcome to Blackjack!");
        System.out.println("Your hand: " + playerHand + " (Value: " + handValue(playerHand) + ")");
        System.out.println("Dealer shows: " + dealerHand.get(0));

        // Player turn
        while (true) {
            System.out.print("Hit or Stand? ");
            String choice = sc.nextLine().toLowerCase();
            if (choice.equals("hit")) {
                playerHand.add(deck.draw());
                System.out.println("You drew: " + playerHand.get(playerHand.size()-1));
                System.out.println("Your hand: " + playerHand + " (Value: " + handValue(playerHand) + ")");
                if (handValue(playerHand) > 21) {
                    System.out.println("Bust! You lose.");
                    return;
                }
            } else if (choice.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        // Dealer turn
        System.out.println("Dealer's hand: " + dealerHand + " (Value: " + handValue(dealerHand) + ")");
        while (handValue(dealerHand) < 17) {
            dealerHand.add(deck.draw());
            System.out.println("Dealer draws: " + dealerHand.get(dealerHand.size()-1));
        }
        System.out.println("Dealer's final hand: " + dealerHand + " (Value: " + handValue(dealerHand) + ")");

        // Result
        int playerValue = handValue(playerHand);
        int dealerValue = handValue(dealerHand);

        if (dealerValue > 21 || playerValue > dealerValue) {
            System.out.println("You win!");
        } else if (playerValue == dealerValue) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }

        sc.close();
    }
}
