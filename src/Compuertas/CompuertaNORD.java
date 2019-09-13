package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaNORD extends Compuerta {
    LinkedList<Boolean> inputs = new LinkedList<>();
    boolean output;


    public CompuertaNORD(Pane gridPane) {
        createNORD(gridPane);

    }

    private void  createNORD(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta4.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }

    @Override
    public void operar() {
        Boolean res = !(input1.value && input2.value);

    }
}
