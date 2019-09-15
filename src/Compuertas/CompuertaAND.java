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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class CompuertaAND extends Compuerta{


    public CompuertaAND(Pane gridPane){
        createAND(gridPane);
    }


    private void  createAND(Pane gridPane){
        logicGateGroup = new Group();
        logicGateGroup.setId("CompuertaGrupo");
        LogicGatesCreator.LogicGatesList.showData();
        Image image = new Image("Compuerta1.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
        Painter.enumeration(logicGateGroup);
    }



    @Override
    public void operar() {
        if(input1.value != null && input2.value != null){
            output.value = input1.value && input2.value;
            if(inputs1.size() !=0){
                for(int i=0; i<= inputs1.size()-1; i++){
                    Compuerta compuerta = inputs1.getElement(i);
                    compuerta.input1.value = output.value;
                }
            }
            if(inputs2.size() !=0){
                for(int i=0; i<=inputs2.size()-1; i++){
                    Compuerta compuerta = (Compuerta)inputs2.getElement(i);
                    compuerta.input2.value = output.value;
                }
            }
        }
    }

}
