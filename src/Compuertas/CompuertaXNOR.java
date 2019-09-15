package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaXNOR extends Compuerta {
    LinkedList<Boolean> inputs = new LinkedList<>();


    public CompuertaXNOR(Pane gridPane) {
        createXNOR(gridPane);

    }

    private void  createXNOR(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta7.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }

    @Override
    public void operar() {
        if(input1.value != null && input2.value != null) {
            if ((input1.value == false && input2.value == true) || (input1.value == true && input2.value == false)) {
                output.value = true;
            } else {
                output.value = false;
            }
        }
        if(inputs1.size() != 0){
            for(int i=0; i <= inputs1.size()-1; i++){
                Compuerta compuerta = inputs1.getElement(i);
                compuerta.input1.value = output.value;
            }
        }
        if(inputs2.size() !=0){
            for(int i=0; i <= inputs2.size()-1; i++){
                Compuerta compuerta = inputs2.getElement(i);
                compuerta.input2.value = output.value;

            }
        }

    }
}
