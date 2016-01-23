package sample;

import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.xml.soap.Node;

import java.awt.*;
import javafx.event.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by user on 1/23/2016.
 */
public class PlayerConfigurationController implements Initializable{

    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
    @FXML
    private TextField name3;
    @FXML
    private TextField name4;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.setItems(FXCollections.observableArrayList());
        choiceBox.getItems().addAll("Red", "Blue", "Green", "Yellow");
        choiceBox1.setItems(FXCollections.observableArrayList());
        choiceBox1.getItems().addAll("Red", "Blue", "Green", "Yellow");
        choiceBox2.setItems(FXCollections.observableArrayList());
        choiceBox2.getItems().addAll("Red", "Blue", "Green", "Yellow");
        choiceBox3.setItems(FXCollections.observableArrayList());
        choiceBox3.getItems().addAll("Red", "Blue", "Green", "Yellow");
    }
    @FXML
    public void startGame(Event event) throws IOException {
        Main.players[0] = new Player (name1.getText(),choiceBox.getValue());
        Main.players[1] = new Player (name2.getText(),choiceBox1.getValue());
        Main.players[2] = new Player (name3.getText(),choiceBox2.getValue());
        Main.players[3] = new Player (name4.getText(),choiceBox3.getValue());

        Scene game = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
        Stage initializer = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        initializer.setScene(game);
        initializer.show();
    }






}
