package sample;
/**
 * @author Beiwen Liu
 * @version 1.8
 * 1.0 Notes: Implemented Basic structure of sample class (main screen)
 * player/card/deck/game controller/player configuration/ basic ui /Single Player
 * and Multiplayer functionality -> configuring players depending on input.
 *
 * 1.1 Notes: Player configuration choice box / Toggle features /Added instances of rules (q-breaks, jack of diamonds, etc.)
 * deck shuffling / Pointer Reference (to the player who plays first)
 *
 * 1.2 Notes: Drag and Drop functionality for Image View/ Introduction to
 * SceneBuilder and CSS association
 *
 * 1.3 Notes: Assign association of imageview to specific cards
 * and changing the URL image corresponding to Card value / Created separate
 * ImageCardContainer.java class to hold ImageView and its corresponding Card.
 * Created a GameRegulator Class to regulate rules and handle deck (distibute
 * cards to players and shuffling, etc.)
 * Realized having an ImageCardContainer class was redundant. (at least for
 * referencing the card. Useful though because of array)
 * Successfully Associated Instance of ImageView with correct url, as well as
 * the corresponding player and the player's hands.
 * Start method should only be invoked if you are creating scene manually I think.
 * imageView can be easily added onto a border pane.
 *
 * 1.4: Add imageView to border pane and centering along area. Add full imageList
 * that contains all 52 cards. Added label for each player from 1-4 and their
 * corresponding imageViews. Updated label to show name of player.
 * Centered components of border pane
 * GameRegulator will increment player round.
 *
 * 1.5 GameRegulator changes: Accept card method where it will add cards to a container
 * within Game Regulator class. Created inner class CardIndexContainer that keeps track of the
 * Card and who plays it. Determine the amount of points allotted to the player that plays
 * the highest card. Made method that determines who plays next, depending on the selection of the cards.
 * GameController: Added functionality that only allows the player to play their cards
 * during their turn only.
 *
 * Created separate method for starting round (This will enable drag and drop on the image views)
 * Created separate regulateround method to regulate the enabling and disabling of imageviews
 * depending on the pointer.
 *
 *1.6 Fixed Adjustments with enabling imageView.
 * Fixed player's inventory of cards that corresponds to the correect imageView.
 *
 * 1.7 Implement end of each round control. Giving first play to the highest card on the table.
 * Clearing border pane after every round. Fixed Index out of bounds for adding a player's card
 * to the gameRegulator class using MOD. Successfully regulated rounds using game class by
 * calculating the highest card based on the first suit played per round.
 *
 * Trying to implement timer so that the table will clear 1-2 seconds after the last
 * card has been played so that players can see and analyze cards before it disappears.
 * Currently using multi-threading with a Timer1 class, but failed due to
 * discrepancy in thread type.
 *
 * Decided to use Platform.runlater because it uses JavaFX thread. Purely used to update GUI.
 *
 * Created private method that disables all the imageViews so that no one
 * can play a card before the Table clears. Separated "roundRegulate to both conditional
 * branches so that the one with round == 4, can disable all imageviews, and after 2 seconds
 * it will re-enable them depending on who played the highest suit.
 *
 * Bug: Sometimes it would clear the table after 3 cards have been played. Fixed it by
 * setting the delay or 2 / 1 seconds and then running the command once.
 *
 * 1.8 Configure Score -> both GUI and system tracking.
 * Added method in player that adds to existing points.
 * At the end of each round (where all 52 cards are played), the gameController
 * will open a new screen that represents the score of the players.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.labs.util.event.MouseControlUtil;

import java.io.IOException;
import java.util.Timer;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Created by Beiwen Liu on 1/23/2016.
 */
public class GameController implements Initializable {
    private final String[] DEFAULT_NAMES = {"You", "James", "Thomas", "Daniel"};
    private final String[] DEFAULT_COLORS = {"Red", "Green", "Blue", "Yellow"};
    public Label player1;
    public Label player2;
    public Label player3;
    public Label player4;
    public Label score1;
    public Label score2;
    public Label score3;
    public Label score4;
    private ImageCardContainer[] imageList = new ImageCardContainer[52];
    public BorderPane target;
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
    public ImageView card14;
    public ImageView card15;
    public ImageView card16;
    public ImageView card17;
    public ImageView card18;
    public ImageView card19;
    public ImageView card20;
    public ImageView card21;
    public ImageView card22;
    public ImageView card23;
    public ImageView card24;
    public ImageView card25;
    public ImageView card26;
    public ImageView card27;
    public ImageView card28;
    public ImageView card29;
    public ImageView card30;
    public ImageView card31;
    public ImageView card32;
    public ImageView card33;
    public ImageView card34;
    public ImageView card35;
    public ImageView card36;
    public ImageView card37;
    public ImageView card38;
    public ImageView card39;
    public ImageView card40;
    public ImageView card41;
    public ImageView card42;
    public ImageView card43;
    public ImageView card44;
    public ImageView card45;
    public ImageView card46;
    public ImageView card47;
    public ImageView card48;
    public ImageView card49;
    public ImageView card50;
    public ImageView card51;
    public ImageView card52;
    private Timer1 timer = new Timer1();
    private Deck deck = new Deck();
    private Player[] players = Main.players;
    private final int HAND_SIZE = 13;
    private int pointer;
    private GameRegulator game = new GameRegulator();
    private int round = 0;
    private int seconds = 2;
    private int counter = 0;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        if (Main.playType.equals("Single Player")) {
            for (int i = 0; i < DEFAULT_NAMES.length; i++) {
                players[i] = new Player(DEFAULT_NAMES[i], DEFAULT_COLORS[i]);
            }
        }
        player1.setText(players[0].getName());
        player2.setText(players[1].getName());
        player3.setText(players[2].getName());
        player4.setText(players[3].getName());


        game.shuffleDeck(deck);

        //The following lines will simulate the passing of the cards
        //to each player from the top of the deck after it has been
        //shuffled.

        for (int i = 0; i < HAND_SIZE; i++) {
            for (int j = 0; j < players.length; j++) {
                Card temp = game.dealCard();
                if (temp.toString().equals("TwoOfClubs")) {
                    pointer = j; // This pointer will indicate the index of the player that will start first.
                }
                players[j].receiveCard(temp);
            }
        }

        System.out.println("Pointer: " + pointer);
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
        imageList[13] = new ImageCardContainer(card14);
        imageList[14] = new ImageCardContainer(card15);
        imageList[15] = new ImageCardContainer(card16);
        imageList[16] = new ImageCardContainer(card17);
        imageList[17] = new ImageCardContainer(card18);
        imageList[18] = new ImageCardContainer(card19);
        imageList[19] = new ImageCardContainer(card20);
        imageList[20] = new ImageCardContainer(card21);
        imageList[21] = new ImageCardContainer(card22);
        imageList[22] = new ImageCardContainer(card23);
        imageList[23] = new ImageCardContainer(card24);
        imageList[24] = new ImageCardContainer(card25);
        imageList[25] = new ImageCardContainer(card26);
        imageList[26] = new ImageCardContainer(card27);
        imageList[27] = new ImageCardContainer(card28);
        imageList[28] = new ImageCardContainer(card29);
        imageList[29] = new ImageCardContainer(card30);
        imageList[30] = new ImageCardContainer(card31);
        imageList[31] = new ImageCardContainer(card32);
        imageList[32] = new ImageCardContainer(card33);
        imageList[33] = new ImageCardContainer(card34);
        imageList[34] = new ImageCardContainer(card35);
        imageList[35] = new ImageCardContainer(card36);
        imageList[36] = new ImageCardContainer(card37);
        imageList[37] = new ImageCardContainer(card38);
        imageList[38] = new ImageCardContainer(card39);
        imageList[39] = new ImageCardContainer(card40);
        imageList[40] = new ImageCardContainer(card41);
        imageList[41] = new ImageCardContainer(card42);
        imageList[42] = new ImageCardContainer(card43);
        imageList[43] = new ImageCardContainer(card44);
        imageList[44] = new ImageCardContainer(card45);
        imageList[45] = new ImageCardContainer(card46);
        imageList[46] = new ImageCardContainer(card47);
        imageList[47] = new ImageCardContainer(card48);
        imageList[48] = new ImageCardContainer(card49);
        imageList[49] = new ImageCardContainer(card50);
        imageList[50] = new ImageCardContainer(card51);
        imageList[51] = new ImageCardContainer(card52);

        int index = 0;
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < 13; j++) {
                imageList[index].getImageView().setImage(new Image(getClass().getResourceAsStream("/resources/"
                        + players[i].returnHand().get(j).toString() + ".png"))); //This assigns all the images to the imageView
                imageList[index].setCard(players[i].returnHand().get(j));
                index++;
            }
        }
        startMatch();
    }

    //        for (int round = 0; round < HAND_SIZE; round++) {
//            for (int playerRound = 0; playerRound < players.length; playerRound++) {
//                if (pointer == 0) {
//            }
//        }
    //new ImageView(new Image(getClass().getResourceAsStream("QueenOfClubs.jpg"),63.5, 88.9, true, true));  This is how
    //you do it manually.
    //imageList[1].setImage(new Image(getClass().getResourceAsStream("QueenOfClubs.jpg"))); //This will change the
    //image view depending on the type of card!! I FINALLY GOT IT TO WORK.
    //        player1.setText(players[0].returnHand().get(0).toString());
    public void startMatch() {
        roundRegulate(pointer);
        imageList[0].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[0].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[0].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[0].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[1].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[1].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[1].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[1].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[2].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[2].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[2].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[2].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[3].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[3].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[3].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[3].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[4].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[4].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[4].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[4].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[5].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[5].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[5].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[5].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[6].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[6].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[6].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[6].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[7].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[7].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[7].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[7].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[8].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[8].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[8].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[8].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[9].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[9].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[9].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[9].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[10].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[10].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[10].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[10].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[11].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[11].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[11].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[11].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[12].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[12].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[12].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[12].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[13].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[13].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[13].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[13].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[14].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[14].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[14].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[14].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[15].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[15].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[15].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[15].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[16].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[16].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[16].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[16].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[17].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[17].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[17].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[17].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[18].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[18].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[18].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[18].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[19].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[19].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[19].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[19].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[20].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[20].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[20].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[20].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[21].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[21].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[21].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[21].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[22].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[22].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[22].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[22].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[23].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[23].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[23].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[23].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[24].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[24].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[24].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[24].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[25].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[25].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[25].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[25].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[26].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[26].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[26].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[26].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[27].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[27].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[27].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[27].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[28].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[28].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[28].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[28].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[29].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[29].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[29].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[29].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[30].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[30].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[30].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[30].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[31].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[31].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[31].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[31].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[32].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[32].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[32].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[32].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[33].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[33].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[33].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[33].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[34].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[34].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[34].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[34].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[35].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[35].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[35].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[35].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[36].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[36].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[36].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[36].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[37].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[37].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[37].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[37].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[38].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[38].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[38].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[38].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[39].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[39].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[39].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[39].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[40].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[40].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[40].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[40].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[41].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[41].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[41].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[41].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[42].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[42].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[42].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[42].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[43].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[43].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[43].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[43].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[44].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[44].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[44].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[44].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[45].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[45].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[45].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[45].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[46].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[46].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[46].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[46].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[47].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[47].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[47].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[47].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[48].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[48].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[48].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[48].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[49].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[49].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[49].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[49].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[50].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[50].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[50].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[50].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        imageList[51].getImageView().setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");
                /* allow any transfer mode */
                Dragboard db = imageList[51].getImageView().startDragAndDrop(TransferMode.ANY);
                /* put a string on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(imageList[51].getCard().toString());
                db.setContent(content);

                event.consume();
            }
        });
        imageList[51].getImageView().setOnDragDone(new EventHandler<DragEvent>() {
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
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
//                System.out.println("onDragOver");
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
        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
//                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
//                    target.setFill(Color.GREEN);
                }
                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                event.consume();
            }
        });
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                /* data dropped */
//                System.out.println("onDragDropped");
                boolean success = false;
                counter++;
                /* if there is a string data on dragboard, read it and use it */
                Dragboard dragboard = event.getDragboard();
                for (int i = 0; i < imageList.length; i++) {
                    if (dragboard.getString().equals(imageList[i].getCard().toString())) {
                        if (i < 13) {
                            target.setBottom(imageList[i].getImageView());
                            BorderPane.setAlignment(imageList[i].getImageView(), Pos.CENTER);
                        } else if (i >= 13 && i < 26) {
                            target.setLeft(imageList[i].getImageView());
                            BorderPane.setAlignment(imageList[i].getImageView(), Pos.CENTER);
                        } else if (i >= 26 && i < 39) {
                            target.setTop(imageList[i].getImageView());
                            BorderPane.setAlignment(imageList[i].getImageView(), Pos.CENTER);
                        } else {
                            target.setRight(imageList[i].getImageView());
                            BorderPane.setAlignment(imageList[i].getImageView(), Pos.CENTER);
                        }
                        success = true;
                        round++;
                        int temp = i % 13;
                        System.out.println("GAME SCREEN: " + players[pointer].returnHand().get(temp).getCardValue());
                        game.acceptCard(players[pointer].returnHand().get(temp), pointer);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
                if (round == 4) {
                    round = 0;
                    disableGUI();
                    //Need to clear stage
                    updateTable();
                    pointer = game.computeHighestPlayer();
                    players[pointer].setPoints(game.getPoints());
                } else {
                    pointer = game.incrementPlayerRound(pointer);
                    System.out.println("Pointer value: " + pointer);
                    roundRegulate(pointer);
                }

                if (counter == 52) {
                    Scene game = null;
                    try {
                        game = new Scene(FXMLLoader.load(getClass().getResource("PlayerConfiguration.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage initializer = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    initializer.setScene(game);
                    initializer.show();
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
            }
        });
    }

    private void disableGUI() {
        for (int i = 0; i <imageList.length; i++) {
            imageList[i].getImageView().setDisable(true);
        }
    }


    public void roundRegulate(int pointer) {
        for (int j = 0; j < imageList.length; j++) {
            imageList[j].getImageView().setDisable(true);
        }
        if (pointer == 0) {
            for (int i = 0; i < 13; i++) {
                imageList[i].getImageView().setDisable(false);
            }
        } else if (pointer == 1) {
            for (int i = 13; i < 26; i++) {
                imageList[i].getImageView().setDisable(false);
            }
        } else if (pointer == 2) {
            for (int i = 26; i < 39; i++) {
                imageList[i].getImageView().setDisable(false);
            }
        } else if (pointer == 3) {
            for (int i = 39; i < 52; i++ ){
                imageList[i].getImageView().setDisable(false);
            }
        }
    }
//        MouseControlUtil.makeDraggable(player1); //MouseControlUtil makes an item draggable.

    private void updateTable(){

        final ScheduledExecutorService scheduler
                = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(
                new Runnable(){

                    int counter = 0;

                    @Override
                    public void run() {
                        counter++;
                        if(counter<=1){

                            Platform.runLater(new Runnable(){
                                @Override
                                public void run() {
                                    System.out.println(counter);
                                    target.setBottom(null);
                                    target.setTop(null);
                                    target.setLeft(null);
                                    target.setRight(null);
                                    roundRegulate(pointer);
                                    score1.setText(Integer.toString(players[0].getPoints()));
                                    score2.setText(Integer.toString(players[1].getPoints()));
                                    score3.setText(Integer.toString(players[2].getPoints()));
                                    score4.setText(Integer.toString(players[3].getPoints()));
                                    System.out.println("Executed");
                                    System.out.println("New Pointer: " + pointer);
                                    System.out.println("---------------------------------------------");
                                }
                            });
                        }

                    }
                },
                1, //After this amount of seconds, run() will be executed.
                1, //Not sure what this does.
                TimeUnit.SECONDS);
    }




}


