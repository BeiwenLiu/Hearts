package sample;

import com.sun.javaws.progress.PreloaderPostEventListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ResourceBundle;

/**
 * Created by BLiu on 1/29/2016.
 */
public class ScoreController implements Initializable {

    public GridPane gridPane;
    public Pane scorePane;
    public TableView tableView;
    public TableColumn player1;
    public TableColumn player2;
    public TableColumn player3;
    public TableColumn player4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (!Main.enteredScoreController) {
//
//        }
        player1.setText(Main.players[0].getName());
        player2.setText(Main.players[1].getName());
        player3.setText(Main.players[2].getName());
        player4.setText(Main.players[3].getName());
        player1.setCellValueFactory(new PropertyValueFactory<DataHolder, Integer>("points1"));
        player2.setCellValueFactory(new PropertyValueFactory<DataHolder, Integer>("points2"));
        player3.setCellValueFactory(new PropertyValueFactory<DataHolder, Integer>("points3"));
        player4.setCellValueFactory(new PropertyValueFactory<DataHolder, Integer>("points4"));
        DataHolder dataHolder = new DataHolder();
        dataHolder.points1.setValue(Main.players[0].getPoints());
        dataHolder.points2.setValue(Main.players[1].getPoints());
        dataHolder.points3.setValue(Main.players[2].getPoints());
        dataHolder.points4.setValue(Main.players[3].getPoints());
        Main.data.add(dataHolder);
        tableView.setItems(Main.data);
    }


    public void goBack(Event event) throws IOException {
        Scene game = new Scene(FXMLLoader.load(getClass().getResource("Game.fxml")));
        Stage initializer = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        initializer.setScene(game);
        initializer.show();
    }
}
