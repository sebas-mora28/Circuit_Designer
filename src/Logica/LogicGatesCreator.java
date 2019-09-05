package Logica;


import Compuertas.*;
import GUI.Painter;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class LogicGatesCreator {
    private Group logicGateGroup;
    private static GridPane gridPane;
    private static double posX, posY, newPosX, newPosY, translationX, translationY, newTranslationX, newTranslationY;


    /**
     * Constructor
     */
    public LogicGatesCreator() {}



    /**
     * Método que crea la compuerta lógica cada vez que se presiona el botón
     * @param gridPane
     * @param logicGateType
     */
    public Compuerta createLogicGates(GridPane gridPane, LogicGateType logicGateType){
        if(logicGateType == LogicGateType.AND){
            return new CompuertaAND(gridPane);
        }else if(logicGateType == LogicGateType.NAND){
            return new CompuertaNAND(gridPane);
        }else if(logicGateType == logicGateType.OR){
            return new CompuertaOR(gridPane);
        }else if(logicGateType == LogicGateType.NORD){
            return new CompuertaNORD(gridPane);
        }else if(logicGateType == LogicGateType.NOT){
            return new CompuertaNOT(gridPane);
        }else if(logicGateType == LogicGateType.XOR){
            return new CompuertaXOR(gridPane);
        }else{
            return new CompuertaXNOR(gridPane);
        }


        /*
        setGridPane(gridPane);
        Group logicGateGroup = new Group();
        setLogicGateGroup(logicGateGroup);
        Image image = new Image(name);
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.setOnMousePressed(MousePressed);
        logicGateGroup.setOnMouseDragged(MousedDragged);
        logicGateGroup.setOnMouseReleased(MousedRelease);
        logicGateGroup.getChildren().add(logicGate);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
         */



    }


    EventHandler<MouseEvent> MousePressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((Group)(mouseEvent.getSource())).getTranslateX();
            translationY = ((Group)(mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> MousedDragged = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            newTranslationX = translationX + newPosX;
            newTranslationY = translationY + newPosY;
            ((Group)(mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((Group)(mouseEvent.getSource())).setTranslateY(newTranslationY);

        }};



     EventHandler<MouseEvent> MousedRelease = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(VerifyCoordsInUse(gridPane, (Group)(mouseEvent.getSource()))){
                ((Group)(mouseEvent.getSource())).setTranslateX(posX);
                ((Group)(mouseEvent.getSource())).setTranslateY(posY);
            }
        }
    };


    /**
     * Verifica si la nueva posición de la compuerta está siendo ocupada por otra para evitar que se sobrepongan
     * @param gridPane Se utilizada para comparar todos las compuertas que se encuntran en la pantalla
     * @param newlogicGateGroup Compuerta que se cambió a una nueva posición
     * @return true en caso de que la posición este siendo ocupada, false en caso contrario
     */
    public boolean VerifyCoordsInUse(GridPane gridPane, Group newlogicGateGroup){
        for(Node nodo : gridPane.getChildren()){
            if(!newlogicGateGroup.equals(nodo) && newlogicGateGroup.getBoundsInParent().intersects(nodo.getBoundsInParent())){
                return true;
            }
        }
        return false;
    }

    /**
     * Crea los labels correspondientes a las salidas de las compuertas
     * @param gridPane
     */


    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public Group getLogicGateGroup() {
        return logicGateGroup;
    }

    public void setLogicGateGroup(Group logicGateGroup) {
        this.logicGateGroup = logicGateGroup;
    }


    public enum LogicGateType{
        AND,
        NAND,
        NORD,
        NOT,
        OR,
        XOR,
        XNOR;

    }

}
