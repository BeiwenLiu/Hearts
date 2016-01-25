package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 1/23/2016.
 */
public class GameRegulator {
    private Player player;
    private ArrayList<Card> deck;
    private int index;
    private int imageIndex; //This is only used temporarily to assign
    //the corresponding card indicies to the corresponding imageView.

    public GameRegulator() {
        System.out.println("Welcome");
    }

    public void shuffleDeck(Deck deck1) {
        deck = deck1.getCards();
        Random random = new Random();
        Card temp;
        index = 0;
        int index1 = 0;
        for (int i = deck.size() - 1; i > 0; i--) {
            index1 = random.nextInt(i + 1);
            temp = deck.get(index1);
            deck.set(index1, deck.get(i));
            deck.set(i,temp);
        }
    }

    public Card dealCard() {
        return deck.get(index++);
    }

    public Card assignCard() {
        return deck.get(imageIndex++);
    }
}
