package GUI;

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


public class LinkingNodes extends Line {
    private boolean alreadyCreated = true;
    private Circle circle;
    private Group logicGateGroup;
    private Line line = new Line();
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();


    public LinkingNodes() { }


    public void PaintLine(Group group, GridPane gridPane) {
            //Coordenadas de la linea;
        setLogicGateGroup(group);
        line.setStartX(getOutput(group).getLayoutX());
        line.setStartY(getOutput(group).getLayoutY());
        line.endXProperty().bind(mouseX);
        line.endXProperty().bind(mouseY);
        line.setStroke(Color.rgb(25, 25, 25));
        line.setStrokeWidth(10);
        line.setOnMouseClicked(MouseMoved);
        line.setOnMouseMoved(MouseDragged);

            // Se agrega al gridpane
        gridPane.getChildren().add(line);
    }

    EventHandler<MouseEvent> MouseMoved = mouseEvent -> {
        mouseX.set(mouseEvent.getScreenX());
        mouseY.set(mouseEvent.getScreenY());
    };

    EventHandler<MouseEvent> MouseDragged = mouseEvent -> {
        mouseX.set(mouseEvent.getScreenX());
        mouseY.set(mouseEvent.getSceneY());

    };

    EventHandler<MouseEvent> MouseReleased = mouseEvent -> {
        BackToOriginNode();
    };




    /**
     * Método que regresa a la posición original la compuerta en caso de que la nueva posición esté ocupada
     */
    private void BackToOriginNode() {
        line.endXProperty().unbind();
        System.out.println("Pasa");
        line.endYProperty().unbind();
        logicGateGroup.getChildren().addAll(line);
        line = null;
    }


    /**
     * Método que obtiene el círculo de salida de la compuerta lógica
     * @param group Grupo de donde se desea obtener la salida
     * @return salida Se retorna la salida de la compuerta lógica
     */

    private Circle getOutput(Group group) {
        Circle temp = null;
        for (Node nodes : group.getChildren()) {
            if(nodes.getId().equals("Salida")){
                System.out.println(nodes.getId());
                temp = (Circle)(nodes);
            }
        }
        return temp;
    }

    public void setLogicGateGroup(Group logicGateGroup) {
        this.logicGateGroup = logicGateGroup;
    }
}
