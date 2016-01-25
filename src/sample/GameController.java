package sample;
/**
 * @author Beiwen Liu
 * @version 1.3
 * 1.0 Notes: Implemented Basic structure of sample class (main screen)
 * player/card/deck/game controller/player configuration/ basic ui /Single Player
 * and Multiplayer functionality -> configuring players depending on input.
 * 1.1 Notes: Player configuration choice box / Toggle features /Added instances of rules (q-breaks, jack of diamonds, etc.)
 * deck shuffling / Pointer Reference (to the player who plays first)
 * 1.2 Notes: Drag and Drop functionality for Image View/ Introduction to
 * SceneBuilder and CSS association
 * 1.3 Notes: / Assign association of imageview to specific cards
 * and changing the URL image corresponding to Card value / Created separate
 * ImageCardContainer.java class to hold ImageView and its corresponding Card.
 * Created a GameRegulator Class to regulate rules and handle deck (distibute
 * cards to players and shuffling, etc.)
 * Realized having an ImageCardContainer class was redundant. (at least for
 * referencing the card. Useful though because of array)
 * Successfully Associated Instance of ImageView with correct url, as well as
 * the corresponding player and the player's hands.
 */

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import jfxtras.labs.util.event.MouseControlUtil;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by user on 1/23/2016.
 */
public class GameController implements Initializable {
    private final String[] DEFAULT_NAMES = {"You","James", "Thomas", "Daniel"};
    private final String[] DEFAULT_COLORS = {"Red", "Green", "Blue", "Yellow"};
    public ImageCardContainer[] imageList = new ImageCardContainer[52];
    public Pane target;
    public Pane gameView;
    public ImageView card1;
    public ImageView card2;
    public ImageView card3;
    public ImageView card4;
    public ImageView card5;
    public ImageView card6;
    public ImageView card7;
    public ImageView card8;
    public ImageView card9;
    public ImageView card10;
    public ImageView card11;
    public ImageView card12;
    public ImageView card13;
    private Deck deck = new Deck();
    private Player[] players = Main.players;
    private final int HAND_SIZE = 13;
    private int pointer;
    private GameRegulator game = new GameRegulator();

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        if (Main.playType.equals("Single Player")) {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = new Player(DEFAULT_NAMES[i],DEFAULT_COLORS[i]);
            }
        }
        game.shuffleDeck(deck);

        //The following lines will simulate the passing of the cards
        //to each player from the top of the deck after it has been
        //shuffled.

        for (int i = 0; i < HAND_SIZE; i++) {
            for (int j = 0; j < players.length; j++) {
                Card temp = game.dealCard();
                if (temp.toString().equals("Two of Clubs")) {
                    pointer = j; // This pointer will indicate the index of the player that will start first.
                }
                players[j].receiveCard(temp);
            }
        }
        imageList[0] = new ImageCardContainer(card1);
        imageList[1] = new ImageCardContainer(card2);
        imageList[2] = new ImageCardContainer(card3);
        imageList[3] = new ImageCardContainer(card4);
        imageList[4] = new ImageCardContainer(card5);
        imageList[5] = new ImageCardContainer(card6);
        imageList[6] = new ImageCardContainer(card7);
        imageList[7] = new ImageCardContainer(card8);
        imageList[8] = new ImageCardContainer(card9);
        imageList[9] = new ImageCardContainer(card10);
        imageList[10] = new ImageCardContainer(card11);
        imageList[11] = new ImageCardContainer(card12);
        imageList[12] = new ImageCardContainer(card13);

        //Why isnt it committing

        for (int i = 0; i < 13; i++) {
            imageList[i].setCard(game.assignCard()); //For temporary use.
        }

        for (int i = 0; i < 13; i++) {
            imageList[i].getImageView().setImage(new Image(getClass().getResourceAsStream("/resources/"
            + players[0].returnHand().get(i).toString() + ".png")));
        }

        //new ImageView(new Image(getClass().getResourceAsStream("QueenOfClubs.jpg"),63.5, 88.9, true, true));  This is how
        //you do it manually.
        //imageList[1].setImage(new Image(getClass().getResourceAsStream("QueenOfClubs.jpg"))); //This will change the
        //image view depending on the type of card!! I FINALLY GOT IT TO WORK.
        //        player1.setText(players[0].returnHand().get(0).toString());

        card1.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = card1.startDragAndDrop(TransferMode.ANY);
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
                    target.getChildren().add(card1);
                    boolean success = true;
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
        card1.setOnDragDone(new EventHandler <DragEvent>() {
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

        //------------------------------------------------------------------------
        card2.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = card2.startDragAndDrop(TransferMode.ANY);
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
                target.getChildren().add(card2);
                boolean success = true;
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
        card2.setOnDragDone(new EventHandler <DragEvent>() {
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
