package sample;

/**
 * Created by user on 1/23/2016.
 */
public class Player {
    private String name;
    private String color;
    private int points;
    private Card[] hand;

    public Player(String name, String color, int points) {
        this.name = name;
        this.color = color;
        this.points = points;
    }

    public void dealHand() {

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

    @Override
    public String toString() {
        return name + " " + color;
    }
}
