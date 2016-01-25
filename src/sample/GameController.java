package sample;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jfxtras.labs.util.event.MouseControlUtil;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by user on 1/23/2016.
 */
public class GameController implements Initializable {
    private final String[] DEFAULT_NAMES = {"You","James", "Thomas", "Daniel"};
    private final String[] DEFAULT_COLORS = {"Red", "Green", "Blue", "Yellow"};
    public ImageView player1;
    public Pane target;
    private Deck deck = new Deck();
    private Player[] players = Main.players;
    private final int HAND_SIZE = 13;
    private int pointer;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        deck.shuffle();
        if (Main.playType.equals("Single Player")) {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = new Player(DEFAULT_NAMES[i],DEFAULT_COLORS[i]);
            }
        }

        for (int i = 0; i < HAND_SIZE; i++) {
            for (int j = 0; j < players.length; j++) {
                Card temp = deck.dealCard();
                if (temp.toString().equals("Two of Clubs")) {
                    pointer = j;
                }
                players[j].dealHand(temp);
            }
        }

//        player1.setText(players[0].returnHand().get(0).toString());

        player1.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = player1.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString("HEY");
                db.setContent(content);

                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");

                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
//                    target.setFill(Color.GREEN);
                }

                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */


                event.consume();
            }
        });

        target.setOnDragDropped(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {

                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                    target.getChildren().add(player1);
                    boolean success = true;
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

        player1.setOnDragDone(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture ended */
                System.out.println("onDragDone");
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
//                    player1.setText("");
                }

                event.consume();
            }
        });

//        MouseControlUtil.makeDraggable(player1); //MouseControlUtil makes an item draggable.

    }
}
