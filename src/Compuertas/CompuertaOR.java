package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import Logica.Grid;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class CompuertaOR extends Compuerta {
    LinkedList<Boolean> inputs = new LinkedList<>();


    public CompuertaOR(GridPane gridPane) {
        createOR(gridPane);

    }
    private void  createOR(GridPane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta3.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);

    }

    @Override
    public void operar() {
        Boolean res = input1.value || input2.value;
        output.value = res;

    }
}
