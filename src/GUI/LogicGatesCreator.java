package GUI;

import ListaEnlazada.LinkedList;
import javafx.collections.ModifiableObservableListBase;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.awt.*;


public class LogicGatesCreator {
    private GridPane gridPane;
    private double posX, posY, newPosX, newPosY, translationX, translationY, newTranslationX, newTranslationY;



    public LogicGatesCreator() {}

    /**
     * Método que crea la compuerta lógica cada vez que se presiona el botón
     *
     * @param gridPane
     * @param name
     */
    public void createLogicGates(GridPane gridPane, String name) {
        setGridPane(gridPane);
        Image image = new Image(name);
        Rectangle logicGate = Painter.insertImage(image);
        Painter.paintRec(20, 20, gridPane);
        logicGate.setOnMouseClicked(PaintLine);
        logicGate.setOnMousePressed(MousePressed);
        logicGate.setOnMouseDragged(MousedDragged);
        logicGate.setOnMouseReleased(MousedRelease);
        gridPane.getChildren().add(logicGate);
    }


    EventHandler<MouseEvent> MousePressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("Mouse Pressed");
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((Rectangle) (mouseEvent.getSource())).getTranslateX();
            translationY = ((Rectangle) (mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> MousedDragged = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("Mouse Dragged");
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            newTranslationX = translationX + newPosX;
            newTranslationY = translationY + newPosY;
            ((Rectangle) (mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((Rectangle) (mouseEvent.getSource())).setTranslateY(newTranslationY);
        }};

    EventHandler<MouseEvent> MousedRelease = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("Mouse Release");
            if(VerifyCoordsInUse(gridPane, (Rectangle)(mouseEvent.getSource()))){
                ((Rectangle) (mouseEvent.getSource())).setTranslateX(posX);
                ((Rectangle) (mouseEvent.getSource())).setTranslateY(posY);
            }
        }
    };


    EventHandler<MouseEvent> PaintLine = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.out.println("Entra");
            LinkingNodes line = new LinkingNodes();
            line.PrintLine(gridPane, ((Rectangle)(mouseEvent.getSource())).getX(),((Rectangle)(mouseEvent.getSource())).getX(), mouseEvent.getSceneX(), mouseEvent.getSceneY());

        }
    };



    public boolean VerifyCoordsInUse(GridPane gridPane, Rectangle newRectangle){
        for(Node nodo : gridPane.getChildren()){
            System.out.println(nodo);
            if(!newRectangle.equals(nodo) && newRectangle.getBoundsInParent().intersects(nodo.getBoundsInParent())){
                return true;
            }
        }
        return false;
    }


    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }
}

