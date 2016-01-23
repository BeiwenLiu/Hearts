package sample;

/**
 * Created by user on 1/23/2016.
 */
public class Player {
    private String name;
    private String color;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + color;
    }
}
