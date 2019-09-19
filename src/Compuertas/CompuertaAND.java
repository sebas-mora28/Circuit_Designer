package Compuertas;


import Logica.DragAndDrop;
import GUI.Painter;
import Logica.LogicGatesCreator;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class CompuertaAND extends Compuerta{


    /**
     * Constructor
     * @param gridPane Pane donde se agregan los componentes gráficos de las compuerta
     */
    public CompuertaAND(Pane gridPane){
        createAND(gridPane);
    }
    /**
     * Método que crea los componentes gráficos de la compuerta y les asigna los diferentes EventHandler
     * @param gridPane
     */

    private void  createAND(Pane gridPane){
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
        logicGate.setOnContextMenuRequested(rightlickMenu);
    }


    /**
     * Método heredado de la clase padre Compuerta el cual se encarga se evaluar entradas de la compuerta y asignarle
     * valor a la salida según el comport
     */

    @Override
    public void operar() {

        System.out.println("Compuerta AND");
        System.out.println("Conexiones a otras compuertas");
            for(int i = 0; i<= outputs.size()-1; i++){
                Compuerta compuerta = outputs.getElement(i);
                compuerta.operar();
                System.out.println(compuerta.output.value);
                inputs.add(compuerta.output.value);

            }
            output.value = (Boolean)inputs.getElement(0);
            for(int i=1; i<= inputs.size()-1; i++){
                boolean res = (Boolean)inputs.getElement(i);
                output.value = output.value && res;
            }

        System.out.println("Salida de la compuerta AND: " + output.value);



        System.out.println("-----------------------------------");
        System.out.println();
        System.out.println("-----------------------------------");
    }

    EventHandler<ContextMenuEvent> rightlickMenu = contextMenuEvent -> menu();




}
