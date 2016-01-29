package sample;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Beiwen Liu on 1/23/2016.
 */
public class Player {
    private String name;
    private String color;
    private int points;
    private ArrayList<Card> hand = new ArrayList<>();

    public Player(String name, String color, int points) {
        this.name = name;
        this.color = color;
        this.points = points;
    }

    public void receiveCard(Card card) {
        hand.add(card);
    }

    public Player(String name, String color) {
        this(name, color, 0);
    }

    public void reArrangeCards() {
        ArrayList<Card> clubs = new ArrayList<>();
        ArrayList<Card> diamonds = new ArrayList<>();
        ArrayList<Card> spades = new ArrayList<>();
        ArrayList<Card> hearts = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getSuit().equals("Hearts")) {
                hearts.add(hand.get(i));
            } else if (hand.get(i).getSuit().equals("Clubs")) {
                clubs.add(hand.get(i));
            } else if (hand.get(i).getSuit().equals("Diamonds")) {
                diamonds.add(hand.get(i));
            } else if (hand.get(i).getSuit().equals("Spades")) {
                spades.add(hand.get(i));
            }
        }

        hand.clear();

        clubs = sort(clubs);
        diamonds = sort(diamonds);
        spades = sort(spades);
        hearts = sort(hearts);

        for (int i = 0; i < clubs.size(); i++) {
            hand.add(clubs.get(i));
        }
        for (int i = 0; i < diamonds.size(); i++) {
            hand.add(diamonds.get(i));
        }
        for (int i = 0; i < spades.size(); i++) {
            hand.add(spades.get(i));
        }
        for (int i = 0; i < hearts.size(); i++) {
            hand.add(hearts.get(i));
        }


    }

    private ArrayList<Card> sort(ArrayList<Card> list) {
        Card min = null;
        if (list.size() != 0) {
            min = list.get(0);
        } else {
            return null;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getCardValue() < list.get(i).getCardValue()) {
                    Card temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }

    public ArrayList<Card> returnHand() {
        return hand;
    }

    public void setPoints(int points) {
        this.points = this.points + points;
    }

    @Override
    public String toString() {
        return name + " " + color;
    }
}
