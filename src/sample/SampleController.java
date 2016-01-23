package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.soap.Node;
import java.io.IOException;

public class SampleController {

    private Stage scene1;

    @FXML
    public void startNext(Event event) throws IOException {
        String temp = event.getSource().toString();
        String[] tokens = temp.split("'"); //This will take out the single player or multiplayer string
        Main.playType = tokens[1]; //This will assign single player or multiplayer to Main's playType
        switch (Main.playType) {

            case ("Single Player"): //if Single Player, then we will go straight to the game
                Scene game = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
                Stage initializer = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                initializer.setScene(game);
                initializer.show();
                break;
            case ("Multiplayer"): //if Multiplayer, we'll have a player configuration screen
                Scene player = new Scene(FXMLLoader.load(getClass().getResource("PlayerConfiguration.fxml")));
                Stage t = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                t.setScene(player);
                t.show();
                break;

        }
    }
}

