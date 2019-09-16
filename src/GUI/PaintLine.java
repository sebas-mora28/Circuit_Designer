package GUI;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PaintLine {
    public Pane pane;
    private boolean flag = true;
    public static double posx, posy;
    public static double endPosX, endPosY;
    public static Line line;


    public void paintLine(){
        line = new Line();
        line.setStartX(posx);
        line.setStartX(posy);
        line.setStartY(endPosX);
        line.setStartY(endPosY);
        line.setStrokeWidth(5);
        line.setId("Linea");
    }



    public static void setStartLine( double startPosx, double startPosy){
            posx = startPosx;
            posy = startPosy;
    }

    public static void setLineEnd(double endX, double endY) {
        endPosX = endX;
        endPosY = endY;
    }

    public static void updateEnd(double posx, double posy){
        line.setEndX(posx);
        line.setEndY(posy);
    }

    public static void updateStart(double posx, double posy){
        line.setStartX(posx);
        line.setStartX(posy);
    }
}
