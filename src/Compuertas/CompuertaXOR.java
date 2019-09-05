package Compuertas;

import GUI.Painter;
import Logica.DragAndDrop;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class CompuertaXOR extends Compuerta {


    public CompuertaXOR(GridPane gridPane) {
        createXOR(gridPane);

    }

    private void  createXOR(GridPane gridPane){
        Group logicGateGroup = new Group();
        Image image = new Image("Compuerta6.png");
        Rectangle logicGate = Painter.insertImage(image);
        logicGateGroup.getChildren().add(logicGate);
        DragAndDrop.SetDragAndDrop(gridPane, logicGateGroup);
        gridPane.getChildren().add(logicGateGroup);
        Painter.crearEntradasSalidas(logicGateGroup);

    }

    @Override
    public void operar() {

    }
}

