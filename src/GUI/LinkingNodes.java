package GUI;

import ListaEnlazada.Nodo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;




public class LinkingNodes extends Line {
    private boolean alreadyCreated = true;
    private Circle circle;
    private Group logicGateGroup;
    private GridPane gridPane;
    private Line line = new Line();
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();

    public void PaintLine(GridPane gridPane, Group group) {
        if (alreadyCreated) {
            alreadyCreated = true;

            //Setter del gridpane y del rectagulo para seguir usando a lo largo de toda la clase
            setCircle(circle);
            setGridPane(gridPane);
            setLogicGateGroup(group);

            //Asignación de las funciones del mouse

            //Coordenadas de la linea
            line.setStartX(circle.getCenterX());
            line.setStartY(circle.getCenterY());
            line.endXProperty().bind(mouseX);
            line.endXProperty().bind(mouseY);
            line.setStroke(Color.rgb(25, 25, 25));
            line.setStrokeWidth(2);

            // Se agrega al gridpane
            gridPane.getChildren().add(line);
        } else {
            return;
        }
    }


    /**
     * Método que regresa a la posición original la compuerta en caso de que la nueva posición esté ocupada
     */

    private void BackToOriginNode() {
        line.endXProperty().unbind();
        System.out.println("Pasa");
        line.endYProperty().unbind();
        gridPane.getChildren().addAll(line);
        line = null;
    }


    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Group getLogicGateGroup() {
        return logicGateGroup;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void setLogicGateGroup(Group logicGateGroup) {
        this.logicGateGroup = logicGateGroup;
    }
}

