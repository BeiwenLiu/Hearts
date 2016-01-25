package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 1/23/2016.
 */
public class Deck {
    private int index = 0;
    private ArrayList<Card> deck = new ArrayList<>();
    private String[] values = {"Ace","Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight","Nine", "Ten", "Jack","Queen", "King"};
    private String[] suits = {"Diamonds", "Clubs", "Spades", "Hearts"};
    private int[] pointValues = {0,1,13};
    public Deck() {
        int z = 0;
        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (suits[i].equals("Hearts")) {
                    deck.add(new Card(values[j], suits[i], pointValues[1]));
                } else if (suits[i].equals("Spades") && values[j].equals("Queen")) {
                    deck.add(new Card(values[j], suits[i], pointValues[2]));
                } else {
                    deck.add(new Card(values[j], suits[i], pointValues[0]));
                }

            }
        }
    }

    public ArrayList<Card> getCards() {
        return deck;
    }

    public int getSize() {
         return deck.size();
    }



}
