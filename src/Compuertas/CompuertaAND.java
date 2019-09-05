package Compuertas;


import Logica.DragAndDrop;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;


public class CompuertaAND extends Compuerta{
    private boolean input1, input2;
    LinkedList<Boolean> inputs = new LinkedList<>();
    boolean outputs;


    public CompuertaAND(GridPane gridPane){
        createAND(gridPane);
    }


    private void  createAND(GridPane gridPane){
        Group logicGateGroup = new Group();
        Image image = new Image("Compuerta1.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);

    }

    public void setInputs1(boolean input1){
        this.input1 = input1;
    }

    public  void setInput2(boolean input2){
        this.input2 = input2;
    }


    @Override
    public void operar() {
        outputs = (boolean)(inputs.getElement(0) && inputs.getElement(1));

    }


}
