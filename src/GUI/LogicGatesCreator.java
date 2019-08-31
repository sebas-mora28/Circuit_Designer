package GUI;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class LogicGatesCreator {
    private double posX, posY, newPosX, newPosY, translationX, translationY;


    public LogicGatesCreator(){

    }

    public void createLogicGates(GridPane gridPane, String name) {
        Image image = new Image(name);
        ImageView imageViewAND = new ImageView(image);
        imageViewAND.setFitWidth(90);
        imageViewAND.setFitHeight(85);
        imageViewAND.setCursor(Cursor.HAND);
        Painter.paintRec(posX, posY, gridPane);
        imageViewAND.setOnMousePressed(MousePressed);
        imageViewAND.setOnMouseDragged(MousedDragged);
        gridPane.getChildren().add(imageViewAND);

    }


    EventHandler<MouseEvent> MousePressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((ImageView) (mouseEvent.getSource())).getTranslateX();
            translationY = ((ImageView) (mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> MousedDragged = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            double newTranslationX = translationX + newPosX;
            double newTranslationY = translationY + newPosY;
            ((ImageView) (mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((ImageView) (mouseEvent.getSource())).setTranslateY(newTranslationY);
            Painter.translateRec(newTranslationX, newTranslationY);
        }
    };
}
