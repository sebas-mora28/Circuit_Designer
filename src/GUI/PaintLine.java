package GUI;

import Compuertas.Compuerta;
import Logica.LogicGateConexion;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

public class PaintLine {
    private static  boolean flag = true;
    private double startposx, startposy;
    private Line line1 = new Line(), line2 = new Line(), line3 = new Line();
    private Pane pane;
    private Random random = new Random();


    public PaintLine(Compuerta compuerta, Pane pane, double startposx, double startposy){
        this.pane = pane;
        pane.setOnMouseMoved(MovingLine);

        this.startposx = startposx;
        this.startposy = startposy;

        createLines(compuerta);
    }


    /**
     * Método que crea las líneas para conectar las compuertas
     * @param compuerta Compuerta para obtener la posición en X y Y para conectar la línea de la compuerta
     */

    private void createLines(Compuerta compuerta){
        int r = random.nextInt(255);
        int v = random.nextInt(255);
        int g = random.nextInt(255);

        line1 = new Line();
        line1.startXProperty().bind(compuerta.lineOutputPosX);
        line1.startYProperty().bind(compuerta.lineOutputPosY);
        line1.setStroke(Color.rgb(r,v,g));
        line1.setStrokeWidth(3);
        line1.setId("Linea");
        pane.getChildren().add(line1);
        /*
        Line[] lines = new Line[3];
        for(int i=0; i<=2; i++) {
            Line line = new Line();
            lines[i] = line;
            line.setStroke(Color.rgb(r, v, g));
            line.setId("Linea");
            pane.getChildren().add(line);
        }

         */
        //setLines(lines[0], lines[1], lines[2]);

    }




    /**
     * Método que remueve las líneas que se crearon en caso de que la acción hecha por el usuario sea inváloda
     */

    public void removeLines(){
        for(int y=0; y<=1; y++) {
            for (int i = 0; i <= pane.getChildren().size()-1; i++) {
                Node nodo = pane.getChildren().get(i);
                if (nodo.equals(line1)) {
                    System.out.println("Se elimina 1");
                    pane.getChildren().remove(nodo);
                }
                if (nodo.equals(line2)) {
                    System.out.println("Se elimina 2");
                    pane.getChildren().remove(nodo);
                }
                if (nodo.equals(line3)) {
                    System.out.println("Se elimina 3");
                    pane.getChildren().remove(nodo);
                }
            }
        }
    }


    public void setLine3EndPositionsInput1(Compuerta compuerta){
        line1.endXProperty().bind(compuerta.lineInput1PosX);
        line1.endYProperty().bind(compuerta.lineInput1PosY);
    }

    public void setLine3EndPositionInput2(Compuerta compuerta){
        line1.endXProperty().bind(compuerta.lineInput2PosX);
        line1.endYProperty().bind(compuerta.lineInput2PosY);
    }

    EventHandler<MouseEvent> MovingLine = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(LogicGateConexion.conectingOutput) {
                double endPosX = mouseEvent.getSceneX()- 10;
                double endPosY = mouseEvent.getSceneY() -5;

                double newPosx = (startposx + endPosX) / 2;

                starLine(endPosX, endPosY, newPosx, endPosY, endPosX, endPosY);
                //starLine(newPosx, startposy, newPosx, endPosY, endPosX, endPosY);
            }

        }
    };


    /**
     * Método que actualiza la líneas para las conexión de las compuertas
     * @param posx1
     * @param posy1
     * @param posx2
     * @param posy2
     * @param posx3
     * @param posy3
     */

    private void starLine(double posx1, double posy1, double posx2, double posy2, double posx3, double posy3) {
        line1.setEndX(posx1);
        line1.setEndY(posy1);
        /*
        line2.setStartX(posx1);
        line2.setStartY(posy1);
        line2.setEndX(posx2);
        line2.setEndY(posy2);
        line3.setStartX(posx2);
        line3.setStartY(posy2);
        line3.setEndX(posx3);
        line3.setEndY(posy3);

         */
    }


    public void updatePosition(double posx1, double posy1, double posx2, double posy2){
        line1.setEndX(posx1);
        line1.setEndY(posy1);
        line2.setStartX(posx1);
        line2.setStartY(posy1);
        line2.setEndX(posx2);
        line2.setEndY(posy2);
        line3.setStartX(posx2);

    }

    private void setLines(Line line1, Line line2, Line line3){
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }



}


