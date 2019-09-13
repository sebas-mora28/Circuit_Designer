package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaXOR extends Compuerta {
    double posX, posY;
    LinkedList<Boolean> inputs = new LinkedList<>();



    public CompuertaXOR(Pane gridPane) {
        createXOR(gridPane);

    }

    private void  createXOR(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta6.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }

    @Override
    public void operar() {
        if((input1.value == false && input2.value == true) || (input1.value == true && input2.value == false)){
            output.value = false;
        }else{
            output.value = true;
        }

    }
}

