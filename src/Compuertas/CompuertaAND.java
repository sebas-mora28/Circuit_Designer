package Compuertas;


import ListaEnlazada.Nodo;
import Logica.DragAndDrop;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.LogicGatesCreator;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


public class CompuertaAND extends Compuerta{
    public static LinkedList<Boolean> inputs = new LinkedList<>();


    public CompuertaAND(GridPane gridPane){
        createAND(gridPane);
    }


    private void  createAND(GridPane gridPane){
        logicGateGroup = new Group();
        LogicGatesCreator.LogicGatesList.showData();
        Image image = new Image("Compuerta1.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);
    }


    @Override
    public void operar() {
        if(input1.value != null && input1.value != null){
            Boolean res = input1.value && input2.value;
            output.value = res;
        }
    }

}
