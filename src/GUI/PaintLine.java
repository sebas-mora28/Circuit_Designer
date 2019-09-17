package GUI;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class PaintLine {
    private static  boolean flag = true;
    public double posx, posy;
    public double endPosX, endPosY;
    private static Line line = new Line(), line2 = new Line(), line3 = new Line();


    public static void startLine(Pane pane, double posx1, double posy1, double posx2, double posy2, double posx3, double posy3){
        line.setId("Linea");
        line2.setId("Linea");
        line3.setId("Linea");
        if (flag){
            pane.getChildren().addAll(line, line2, line3);
            flag = false;

        }
        positionLines(line, posx1, posx2, posx2, posy3);
        positionLines(line2, posx2, posy2, posx3, posx3);

    }





    private static void positionLines(Line line, double posStartX, double posStartY, double posEndX, double posEndY){
        line.setStartX(posStartX);
        line.setStartY(posStartY);
        line.setEndX(posEndX);
        line.setEndY(posEndY);


    }
}
