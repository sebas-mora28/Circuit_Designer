package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import Logica.Grid;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class CompuertaNOT extends Compuerta{
    LinkedList<Boolean> inputs = new LinkedList<>();

    public CompuertaNOT(GridPane gridPane) {
        createNOT(gridPane);

    }

    private void  createNOT(GridPane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta5.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);

    }

    @Override
    public void operar() {
        output.value = !input1.value;


    }
}
