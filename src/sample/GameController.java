package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by user on 1/23/2016.
 */
public class GameController implements Initializable {
    private final String[] DEFAULT_NAMES = {"You","James", "Thomas", "Daniel"};
    private final String[] DEFAULT_COLORS = {"Red", "Green", "Blue", "Yellow"};
    private Deck deck;
    private Player[] players = new Player[4];

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        deck = new Deck();
        Card[] newDeck = deck.getCards();

        for (int i = 0; i < newDeck.length; i++) {
            System.out.println(newDeck[i]);
        }

        if (Main.playType.equals("Single Player") || Main.players.length == 0) {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = new Player(DEFAULT_NAMES[i],DEFAULT_COLORS[i]);
            }
        } else {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = Main.players[i];
            }
        }
    }
}
