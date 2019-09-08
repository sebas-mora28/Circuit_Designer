package Logica;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.awt.*;

public class LogicGatesConection {

    public LogicGatesConection(Pane pane){
        Button button = new Button();
        button.setText("Conectar");
        button.setLayoutX(70);
        button.setLayoutY(10);
        pane.getChildren().add(button);
    }
}
