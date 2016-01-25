package sample;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 1/23/2016.
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

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

//    public Card playCard() {
//
//    }

    public ArrayList<Card> returnHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name + " " + color;
    }
}
