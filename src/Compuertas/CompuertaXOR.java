package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CompuertaXOR extends Compuerta {
    double posX, posY;
    LinkedList<Boolean> inputs = new LinkedList<>();



    public CompuertaXOR(Pane gridPane) {
        createXOR(gridPane);

    }

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

        for(int i=0; i<= outputs.size(); i++){
            Compuerta compuerta = outputs.getElement(i);
            compuerta.inputs.add(compuerta.output.value);
            //compuerta.output.value = output.value;
        }

        output.value = (Boolean) inputs.getElement(0);
        for(int i=1; i<= inputs.size()-1; i++){
            System.out.println("entra operar inputs");
            boolean res = (Boolean)inputs.getElement(i);
            if(output.value != res){
                output.value = true;
            }else{
                output.value = false;
            }
        }





    }
}

