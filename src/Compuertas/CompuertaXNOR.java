package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import Logica.Grid;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class CompuertaXNOR extends Compuerta {
    LinkedList<Boolean> inputs = new LinkedList<>();


    public CompuertaXNOR(GridPane gridPane) {
        createXNOR(gridPane);

    }

    private void  createXNOR(GridPane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta7.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);

    }

    @Override
    public void operar() {
        if((input1.value == false && input2.value == true) || (input1.value == true && input2.value == false)){
            output.value = true;
        }else{
            output.value = false;
        }

    }
}
