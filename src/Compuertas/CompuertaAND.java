package Compuertas;


import Logica.DragAndDrop;
import GUI.Painter;
import Logica.LogicGatesCreator;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Clase de la compuerta lógica AND
 * @author Sebastian Mora
 */
public class CompuertaAND extends Compuerta{


    /**
     * Constructor
     * @param gridPane Pane donde se agregan los componentes gráficos de las compuerta
     */
    public CompuertaAND(Pane gridPane){
        try {
            createAND(gridPane);
        }catch (Exception err){
            err.getMessage();
        }
    }
    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */

    public void createAND(Pane gridPane) throws FileNotFoundException {
        logicGateGroup = new Group();
        logicGateGroup.setId("CompuertaGrupo");
        LogicGatesCreator.LogicGatesList.showData();
        Image image = new Image("Compuerta1.png");
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
            for(int i = 0; i<= outputs.size()-1; i++){
                Compuerta compuerta = outputs.getElement(i);
                compuerta.operar();
                inputs.add(compuerta.output.value);

            };
            output.value = (Boolean)inputs.getElement(0);
            for(int i=1; i<= inputs.size()-1; i++){
                boolean res = (Boolean)inputs.getElement(i);
                output.value = output.value && res;
                System.out.println(output.value);
            }

    }


}
