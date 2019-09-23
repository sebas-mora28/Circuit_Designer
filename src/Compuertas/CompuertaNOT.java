package Compuertas;

import GUI.Painter;
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
        logicGateGroup.setId("CompuertaGroup");
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
        for(int i=0; i<= outputs.size()-1; i++){
            System.out.println(i);
            Compuerta compuerta = outputs.getElement(i);
            compuerta.operar();
            inputs.add(compuerta.output.value);
        }

        output.value = (Boolean)inputs.getElement(0);
        output.value = !output.value;


    }
}
