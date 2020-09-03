package Compuertas;

import GUI.Painter;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * Clase que de la compuerta lógica OR
 */
public class CompuertaOR extends Compuerta {


    /**
     * Constructor
     * @param gridPane
     */
    public CompuertaOR(Pane gridPane) {
        createOR(gridPane);

    }

    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */

    private void  createOR(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Resources/Compuerta3.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.setStartDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }


    /**
     * Método heredado de la clase padre compuerta el cual se encarga se evaluar entradas de la compuerta y asignarle
     * valor a la salida según el comport
     */

    @Override
    public void operar() {

        System.out.println("Entra a operar las compuertas de entrada del OR");
        for(int i = 0; i<= outputs.size()-1; i++){
            Compuerta compuerta = outputs.getElement(i);
            compuerta.operar();
            System.out.println(compuerta.output.value);
            inputs.add(compuerta.output.value);
            //compuerta.output.value = output.value;
        }
        output.value = (Boolean)inputs.getElement(0);
        for(int i=1; i<= inputs.size()-1; i++){
            boolean res = (Boolean)inputs.getElement(i);
            output.value = output.value || res;
            System.out.println("OR" + output.value);
        }

    }
}
