package Logica;


import Compuertas.*;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class LogicGatesCreator {
    public static LinkedList<Compuerta> LogicGatesList = new LinkedList<>();
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
    public void createLogicGates(GridPane gridPane, LogicGateType logicGateType) {
        if (logicGateType == LogicGateType.AND) {
            Compuerta logicGateAND = new CompuertaAND(gridPane);
            LogicGatesList.add(logicGateAND);
            logicGateAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NAND) {
            Compuerta logicGateNAND = new CompuertaNAND(gridPane);
            LogicGatesList.add(logicGateNAND);
            logicGateNAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == logicGateType.OR) {
            Compuerta logicGateOR = new CompuertaOR(gridPane);
            LogicGatesList.add(logicGateOR);
            logicGateOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NORD) {
            Compuerta logicGateNORD = new CompuertaNORD(gridPane);
            LogicGatesList.add(logicGateNORD);
            logicGateNORD.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NOT) {
            Compuerta logicGateNOT = new CompuertaNOT(gridPane);
            LogicGatesList.add(logicGateNOT);
            logicGateNOT.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.XOR) {
            Compuerta logicGateXOR = new CompuertaXOR(gridPane);
            LogicGatesList.add(new CompuertaXOR(gridPane));
            logicGateXOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else {
            Compuerta logicGateXNOR = new CompuertaXNOR(gridPane);
            LogicGatesList.add(new CompuertaXNOR(gridPane));
            logicGateXNOR.logicGateGroup.setUserData(LogicGatesList.getLast());

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
