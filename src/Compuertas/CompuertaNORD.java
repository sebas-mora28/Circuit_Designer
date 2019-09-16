package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaNORD extends Compuerta {

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
        if(input1.value != null && input2.value != null){
            output.value = !(input1.value && input2.value);

            for(int i=0; i<= outputs.size(); i++){
                Compuerta compuerta = outputs.getElement(i);
                compuerta.output.value = output.value;
            }

        }
    }
}
