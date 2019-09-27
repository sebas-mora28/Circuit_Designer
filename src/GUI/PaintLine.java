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

        Line[] lines = new Line[3];

        for(int i=0; i<=2; i++){
            Line line = new Line();
            lines[i] = line;
            line.setStroke(Color.rgb(r,v,g));
            line.setStrokeWidth(3);
            line.setId("Linea");
            pane.getChildren().add(line);
        }

        setLine1(lines[0], lines[1], lines[2]);
        line1.startXProperty().bind(compuerta.lineOutputPosX);
        line1.startYProperty().bind(compuerta.lineOutputPosY);

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
                if(nodo.equals(line2)){
                    System.out.println("Se elimina 2");
                    pane.getChildren().remove(nodo);
                }
                if(nodo.equals(line3)){
                    System.out.println("Se elimina 3");
                    pane.getChildren().remove(nodo);
                }
            }
        }
    }



    EventHandler<MouseEvent> MovingLine = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(LogicGateConexion.conectingOutput) {
                double endPosX = mouseEvent.getSceneX()- 10;
                double endPosY = mouseEvent.getSceneY() -5;

                double newPosx = (startposx + endPosX) / 2;


                updateLine(newPosx, startposy, newPosx, endPosY, endPosX, endPosY);
            }

        }
    };


    public void updateLine(double posx1, double posy1, double posx2, double posy2, double posx3, double posy3) {
        line1.setEndX(posx1);
        line1.setEndY(posy1);
        line2.setStartX(posx1);
        line2.setStartY(posy1);
        line2.setEndX(posx2);
        line2.setEndY(posy2);
        line3.setStartX(posx2);
        line3.setStartY(posy2);
        line3.setEndX(posx3);
        line3.setEndY(posy3);

    }

    public void updateLine() {
        line1.setEndX((line1.getStartX() +line3.getEndX() )/2);
        line1.setEndY(line1.getStartY());
        line2.setStartX((line1.getStartX() +line3.getEndX() )/2);
        line2.setStartY(line1.getStartY());
        line2.setEndX((line1.getStartX() +line3.getEndX() )/2);
        line2.setEndY(line3.getStartY());
        line3.setStartX((line1.getStartX() +line3.getEndX() )/2);
        line3.setStartY(line3.getEndY());


    }




    /**
     * Este método permite enlazar las propiedades de la línea con la posición respectiva de la entrada 1 de la compuerta
     * @param compuerta
     */
    public void setLine3EndPositionsInput1(Compuerta compuerta){
        line3.endXProperty().bind(compuerta.lineInput1PosX);
        line3.endYProperty().bind(compuerta.lineInput1PosY);
    }

    /**
     * Este método permite enlazar las propiedades de la línea con la posición respectiva de la entrada 2 de la compuerta
     * @param compuerta
     */

    public void setLine3EndPositionInput2(Compuerta compuerta){
        line3.endXProperty().bind(compuerta.lineInput2PosX);
        line3.endYProperty().bind(compuerta.lineInput2PosY);
    }


    public void setLine1(Line line1, Line line2, Line line3) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }


}


