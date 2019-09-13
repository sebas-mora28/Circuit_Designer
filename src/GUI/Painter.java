package GUI;

import Compuertas.Compuerta;
import Logica.LogicGatesCreator;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


/**
 * Clase que crear inserta las imágenes y crea figuras
 */

public class Painter {
    private static Pane pane;
    private static Circle salida, entrada1, entrada2;
    private static Rectangle rectangleImage;
    private static boolean conectando;
    public static Line line = new Line();
    private static double posx, posy;


    public Painter(Pane pane) {
        Painter.pane = pane;
    }

    /**
     * Este método se encarga de insertar la imagen en un rectángulo
     *
     * @param image La imágen que se inserta en el rectángulo
     * @return rectangleImage El rectángulo con la imágen
     */

    public static Rectangle insertImage(Image image) {
        rectangleImage = new Rectangle(0, 0, 100, 100);
        rectangleImage.setStroke(Color.rgb(1, 1, 1, 0.0));
        rectangleImage.setFill(new ImagePattern(image));
        rectangleImage.setCursor(Cursor.OPEN_HAND);
        rectangleImage.setX(10);
        rectangleImage.setY(50);
        rectangleImage.setId("Compuerta");
        return rectangleImage;

    }

    /**
     * Facade para llamar a los métodos salida y entrada
     *
     * @param logicGateGroup grupo que corresponde a la compuerta lógica
     */


    public static void crearEntradasSalidas(Group logicGateGroup) {
        salida(logicGateGroup);
        entradas(logicGateGroup);

    }


    /**
     * Método que crea los círculos que se incluiran en el grupo de la compuerta que corresponderan a las salida de la compuerta
     *
     * @param logicGateGroup grupo que corresponde a la compuerta lógica
     */

    private static void salida(Group logicGateGroup) {
        salida = new Circle(10);
        salida.setOnMouseEntered(mouseEvent -> salida.setCursor(Cursor.CROSSHAIR));
        salida.setOnMouseClicked(MouseClickOutput);
        salida.setLayoutX(100);
        salida.setLayoutY(92);
        salida.setId("Salida");
        salida.setOpacity(0.0);
        logicGateGroup.getChildren().add(salida);

    }

    /**
     * Método que crea los círculos que se incluiran en el grupo de la compuerta que corresponderan a las salida de la compuerta
     *
     * @param logicGateGroup grupo que corresponde a la compuerta lógica
     */

    private static void entradas(Group logicGateGroup) {
        entrada1 = new Circle(10);
        entrada1.setOnMouseEntered(mouseEvent -> entrada1.setCursor(Cursor.CROSSHAIR));
        entrada1.setOnMouseClicked(MouseClickInput);
        entrada1.setLayoutX(25);
        entrada1.setLayoutY(110);
        entrada1.setId("Entrada1");
        entrada1.setOpacity(0.0);


        entrada2 = new Circle(10);
        entrada2.setOnMouseEntered(mouseEvent -> entrada2.setCursor(Cursor.CROSSHAIR));
        entrada2.setOnMouseClicked(MouseClickInput);
        entrada2.setLayoutX(25);
        entrada2.setLayoutY(80);
        entrada2.setId("Entrada2");
        entrada2.setOpacity(0.0);

        logicGateGroup.getChildren().addAll(entrada1, entrada2);
    }


    private static void paintLine(double x, double y){
        if(Main.conectingOutput && (!Main.input1 &!Main.input2)){
            System.out.println("Empieza a crear la linea");
            posx = x;
            posy = y;
            System.out.println(posx + " " + posy);
        }
        if(Main.input1 || Main.input2){
            System.out.println("Termina- la linea");
            Line line = new Line();
            line.setId("linea");
            line.setStrokeWidth(5);
            line.setStartX(posx);
            line.setStartY(posy);
            line.setEndX(x);
            line.setEndY(y);
            System.out.println(x + " " + y);
            pane.getChildren().addAll(line);

        }
    }


    static EventHandler<MouseEvent> MouseClickOutput = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            if (circle.getId().equals("Salida")){
                if(!Main.conectingInput) {
                    Main.conectingOutput = true;
                    Main.selectingOutput = true;
                    paintLine(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                }
            }

        }
    };
    static  EventHandler<MouseEvent> MouseClickInput = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            if(circle.getId().equals("Entrada1")){
                if(!Main.selectingOutput) {
                    Main.selectingNewGate = true;
                    Main.input1 = true;
                    paintLine(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                }
            }
            if(circle.getId().equals("Entrada2")){
                if(!Main.selectingOutput){
                    Main.selectingNewGate = true;
                    Main.input2 = true;
                    paintLine(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                }
            }

        }
    };
    public static void enumeration(Group group) {
        Label label = new Label();
        label.setLayoutX(80);
        label.setLayoutY(50);
        label.setId("label");

        Label labelOutput = new Label();
        labelOutput.setLayoutX(120);
        labelOutput.setLayoutY(80);
        labelOutput.setId("Output");

        group.getChildren().addAll(label,labelOutput);

    }

    public static void updateEnumeration(){
        for(int i=0; i<= LogicGatesCreator.LogicGatesList.size()-1;i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            ObservableList<Node> nodos =compuerta.logicGateGroup.getChildren();
            for(Node node : nodos){
                if(node.getId().equals("label")){
                    Label label = (Label)node;
                    label.setText("<"+ i + ">");
                }
                if(node.getId().equals("Output") && Main.simulatingCircuit){
                    Label labelOutput = (Label)node;
                    labelOutput.setText(compuerta.output.value.toString());
                }


            }
        }
    }
}
