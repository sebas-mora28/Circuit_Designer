package GUI;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class LogicGatesCreator {
    private double posX, posY, newPosX, newPosY, translationX, translationY;


    public LogicGatesCreator(){}


    /**
     * Método que crea la compuerta lógica cada vez que se presiona el botón
     * @param gridPane
     * @param name
     */
    public void createLogicGates(GridPane gridPane, String name) {
        Image image = new Image(name);
        Rectangle logicGate = Painter.insertImage(image);
        Painter.paintRec(20, 20, gridPane);
        logicGate.setOnMousePressed(MousePressed);
        logicGate.setOnMouseDragged(MousedDragged);
        gridPane.getChildren().add(logicGate);
    }




    EventHandler<MouseEvent> MousePressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((Rectangle) (mouseEvent.getSource())).getTranslateX();
            translationY = ((Rectangle) (mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> MousedDragged = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            double newTranslationX = translationX + newPosX;
            double newTranslationY = translationY + newPosY;
            ((Rectangle) (mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((Rectangle) (mouseEvent.getSource())).setTranslateY(newTranslationY);
            Painter.translateRec(newTranslationX, newTranslationY);
        }
    };
}
