package Compuertas;

import GUI.Painter;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


/**
 * Clase de la compuerta XNOR
 */
public class CompuertaXNOR extends Compuerta {


    /**
     * Constructor
     * @param gridPane
     */
    public CompuertaXNOR(Pane gridPane) {
        createXNOR(gridPane);

    }

    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */
    private void  createXNOR(Pane gridPane){
        logicGateGroup = new Group();
        Image image = new Image("Compuerta7.png");
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

        for (int i = 0; i <= outputs.size()-1; i++) {
            System.out.println(i);
            Compuerta compuerta = outputs.getElement(i);
            compuerta.operar();
            inputs.add(compuerta.output.value);
            //compuerta.output.value = output.value;
        }


        output.value = (Boolean)inputs.getElement(0);
        for (int i = 1; i <= inputs.size() - 1; i++) {
            System.out.println("entra operar inputs");
            boolean res = (Boolean) inputs.getElement(i);
            if(output.value != res){
                output.value = false;
            }else{
                output.value = true;
            }
        }


    }
}
