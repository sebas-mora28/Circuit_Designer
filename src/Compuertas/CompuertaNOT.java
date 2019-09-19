package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaNOT extends Compuerta{


    public CompuertaNOT(Pane gridPane) {
        createNOT(gridPane);

    }

    private void  createNOT(Pane gridPane){
        logicGateGroup = new Group();
        input2Connected = true;
        Image image = new Image("Compuerta5.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.setStartDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradaSalidaNOT(logicGateGroup);
        Painter.enumeration(logicGateGroup);


    }

    @Override
    public void operar() {
        output.value = !((Compuerta) outputs.getElement(0)).output.value;


    }
}
