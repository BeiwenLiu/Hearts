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
    private Deck deck = new Deck();
    private Player[] players = Main.players;
    private final int HAND_SIZE = 13;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        deck.shuffle();
        if (Main.playType.equals("Single Player")) {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = new Player(DEFAULT_NAMES[i],DEFAULT_COLORS[i]);
            }
        }

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < HAND_SIZE; j++) {
                players[i].dealHand(deck.dealCard());
            }
        }




    }
}
