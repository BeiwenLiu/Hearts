package sample;

import java.util.Random;

/**
 * Created by user on 1/23/2016.
 */
public class Deck {
    private int index = 0;
    private Card[] deck = new Card[52];
    private String[] values = {"Ace","Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight","Nine", "Ten", "Jack","Queen", "King"};
    private String[] suits = {"Diamonds", "Clubs", "Spades", "Hearts"};
    private int[] pointValues = {0,1,13};
    public Deck() {
        int z = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (suits[i].equals("Hearts")) {
                    deck[z++] = new Card(values[j], suits[i], pointValues[1]);
                } else if (suits[i].equals("Spades") && values[j].equals("Queen")) {
                    deck[z++] = new Card(values[j], suits[i], pointValues[2]);
                } else {
                    deck[z++] = new Card(values[j], suits[i], pointValues[0]);
                }

            }
        }
    }

    public Card[] getCards() {
        return deck;
    }

    public void shuffle() {
        Random random = new Random();
        Card temp;
        int index = 0;
        for (int i = deck.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = deck[index];
            deck[index] = deck[i];
            deck[i] = temp;
        }
    }

    public Card dealCard() {
        return deck[index++];
    }

}
