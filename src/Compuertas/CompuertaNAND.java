package Compuertas;

import GUI.Painter;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaNAND extends Compuerta {

    public CompuertaNAND(Pane gridPane) {
        createNAND(gridPane);
    }

    private void  createNAND(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta2.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.setStartDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }

    @Override
    public void operar(){

        for(int i = 0; i<= outputs.size()-1; i++){
            Compuerta compuerta = outputs.getElement(i);
            compuerta.operar();
            inputs.add(compuerta.output.value);
            //compuerta.output.value = output.value;
        }

        output.value = (Boolean)inputs.getElement(0);
        for(int i=0; i<= inputs.size()-1; i++){
            System.out.println("entra operar inputs");
            boolean res = (Boolean)inputs.getElement(i);
            output.value = output.value && res;
        }
        output.value = !output.value;

    }
}
