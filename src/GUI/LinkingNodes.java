package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class LinkingNodes implements EventHandler<MouseEvent> {

    private Line line;
    private Node sourceNode;

    @Override
    public void handle(MouseEvent mouseEvent) {
        if(line==null){
            sourceNode = (Node) mouseEvent.getSource();
        }else{
            line = new Line();
            Bounds bounds = sourceNode.getBoundsInParent();

            line.setStartX(bounds.getMinX());
            line.setStartY(bounds.getMinY());

            line.setEndX(line.getStartX());
            line.setEndY(line.getStartY());
        }
    }
}
