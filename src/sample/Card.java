package sample;

/**
 * Created by user on 1/23/2016.
 */
public class Card {
    private String suit;
    private String value;
    private int pointValue;

    public Card(String value, String suit, int pointValue) {
        this.suit = suit;
        this.value = value;
        this.pointValue = pointValue;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getPointValue() {
        return pointValue;
    }

    @Override
    public String toString() {
        return value + "Of" + suit;
    }
}
