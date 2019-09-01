package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class LinkingNodes extends Line {

    private Line line;
    private Node sourceNode;


    public void PrintLine(GridPane gridPane, double startX, double startY, double endX, double endY){
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.rgb(25,25,25));
        line.setStrokeWidth(2);
        gridPane.getChildren().add(line);
    }

}
