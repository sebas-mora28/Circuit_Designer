package Compuertas;

import GUI.Painter;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * Clase para crear el objeto de la compuerta lógica NOT
 */
public class CompuertaNOT extends Compuerta{


    /**
     * Constructuctor
     * @param gridPane
     */
    public CompuertaNOT(Pane gridPane) {
        createNOT(gridPane);

    }

    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */


    private void  createNOT(Pane gridPane){
        logicGateGroup = new Group();
        logicGateGroup.setId("CompuertaGroup");
        input2Connected = true;
        Image image = new Image("Resources/Compuerta5.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.setStartDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradaSalidaNOT(logicGateGroup);
        Painter.enumeration(logicGateGroup);


    }

    /**
     * Método heredado de la clase padre compuerta el cual se encarga se evaluar entradas de la compuerta y asignarle
     * valor a la salida según el comport
     */

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
