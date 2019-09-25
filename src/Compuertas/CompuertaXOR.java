package Compuertas;

import GUI.Painter;
import LinkedList.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * Clase de la compuerta lógica XOR
 */
public class CompuertaXOR extends Compuerta {

    /**
     * Constructor
     * @param gridPane
     */
    public CompuertaXOR(Pane gridPane) {
        createXOR(gridPane);

    }

    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */

    private void  createXOR(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta6.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.setStartDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);

    }

    @Override
    public void operar() {

        for(int i = 0; i<= outputs.size()-1; i++){
            Compuerta compuerta = outputs.getElement(i);
            compuerta.operar();
            inputs.add(compuerta.output.value);
        }

        System.out.println("El valor de outputValue es " + output.value);
        output.value = (Boolean)inputs.getElement(0);
        for(int i=1; i<= inputs.size()-1; i++){
            if(output.value != inputs.getElement(i)){
                output.value = true;
            }else{
                output.value = false;
            }
        }





    }
}

