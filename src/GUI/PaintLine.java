package GUI;

import Compuertas.Compuerta;
import Logica.LogicGateConexion;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

public class PaintLine {
    private double startposx, startposy;
    private Line line1 = new Line();
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

                starLine(endPosX, endPosY);
                //starLine(newPosx, startposy, newPosx, endPosY, endPosX, endPosY);
            }

        }
    };


    /**
     * Método que actualiza la líneas para las conexión de las compuertas
     * @param posx1
     * @param posy1
     */

    private void starLine(double posx1, double posy1) {
        line1.setEndX(posx1);
        line1.setEndY(posy1);

    }


    /**
     * Este método permite enlazar las propiedades de la línea con la posición respectiva de la entrada 1 de la compuerta
     * @param compuerta
     */
    public void setLine3EndPositionsInput1(Compuerta compuerta){
        line1.endXProperty().bind(compuerta.lineInput1PosX);
        line1.endYProperty().bind(compuerta.lineInput1PosY);
    }

    /**
     * Este método permite enlazar las propiedades de la línea con la posición respectiva de la entrada 2 de la compuerta
     * @param compuerta
     */

    public void setLine3EndPositionInput2(Compuerta compuerta){
        line1.endXProperty().bind(compuerta.lineInput2PosX);
        line1.endYProperty().bind(compuerta.lineInput2PosY);
    }
}


