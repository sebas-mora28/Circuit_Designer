package Compuertas;


import GUI.Main;
import ListaEnlazada.Nodo;
import Logica.DragAndDrop;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.LogicGatesCreator;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;


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

            for(int i=0; i<= outputs.size()-1; i++){
                System.out.println("Entra operar outputs");
                Compuerta compuerta = outputs.getElement(i);
                inputs.add(compuerta.output.value);

                /*
                if(!compuerta.input1Connected){
                    System.out.println("Entra");
                    compuerta.input1.value = output.value;

                }
                if(!compuerta.input2Connected){
                    System.out.println("Entra2");
                    compuerta.input2.value = output.value;
                }

                 */
            }

            output.value = (Boolean)inputs.getElement(0);
            for(int i=1; i<= inputs.size()-1; i++){
                System.out.println("entra operar inputs");
                boolean res = (Boolean)inputs.getElement(i);
                output.value = output.value && res;
            }



        System.out.println("-----------------------------------");
    }

    EventHandler<ContextMenuEvent> rightlickMenu = contextMenuEvent -> menu();




}
