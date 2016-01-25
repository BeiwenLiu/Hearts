package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.labs.scene.layout.ScalableContentPane;
import jfxtras.labs.util.event.MouseControlUtil;
import javafx.scene.input.MouseEvent;

/**
 * Created by BLiu on 1/25/2016.
 */
public class Testing extends Application {
    final Text source = new Text(50,100, "DRAG ME");
    final Text target = new Text(300,100, "DROP HERE");


    @Override
    public void start(Stage primaryStage) {
        ScalableContentPane scaledPane = new ScalableContentPane();

        Pane root = scaledPane.getContentPane();
        Scene scene = new Scene(scaledPane, 400, 400);

        Shape shape = new Rectangle(50, 50);
        shape.setStroke(Color.WHITE);
        root.getChildren().add(shape);

        MouseControlUtil.makeDraggable(shape);

        primaryStage.setTitle("MakeNodesDraggable");
        primaryStage.setScene(scene);
        primaryStage.show();
//        source.setOnDragDetected(new EventHandler<MouseEvent>()) {
//            public void handle(MouseEvent event) {
//                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
//                ClipboardContent content = new ClipboardContent();
//                content.putString(source.getText());
//
//                event.comsume;
//            }
//        }

    }
}
