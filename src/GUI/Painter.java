package GUI;

import Compuertas.Compuerta;
import ListaEnlazada.LinkedList;
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

import java.awt.*;


/**
 * Clase que crear inserta las imágenes y crea figuras
 */

public class Painter {
    private static Pane pane;
    private static Circle salida, entrada1, entrada2;
    private static Rectangle rectangleImage;
    private static boolean conectando;
    private static Circle current;
    private static double startposx, startposy, endPosX, endPosY;


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
        salida.setUserData(logicGateGroup.getUserData());
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
        entrada1.setUserData(logicGateGroup);
        entrada1.setOpacity(0.0);


        entrada2 = new Circle(10);
        entrada2.setOnMouseEntered(mouseEvent -> entrada2.setCursor(Cursor.CROSSHAIR));
        entrada2.setOnMouseClicked(MouseClickInput);
        entrada2.setLayoutX(25);
        entrada2.setLayoutY(80);
        entrada2.setId("Entrada2");
        entrada2.setUserData(logicGateGroup);
        entrada2.setOpacity(0.0);

        logicGateGroup.getChildren().addAll(entrada1, entrada2);
    }


    static EventHandler<MouseEvent> MouseClickOutput = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            if (circle.getId().equals("Salida")){
                Main.conectingOutput = true;
                Main.selectingOutput = true;
                current = circle;
                startposx = mouseEvent.getSceneX();
                startposy = mouseEvent.getSceneY();
                }
            }

    };
    static  EventHandler<MouseEvent> MouseClickInput = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            if (circle.getId().equals("Entrada1")) {
                if (Main.conectingOutput) {
                    Main.selectingNewGate = true;
                    Main.input1 = true;
                    return;
                    //paintLine(pane, startposx, startposy, mouseEvent.getSceneX(), mouseEvent.getSceneY(),  current, circle)
                }
                if (!Main.conectingOutput) {
                    Main.conectingInput = true;
                    Main.selectingInput = true;
                    Main.input1Selected = true;

                    if (Main.selected) {
                        Main.selectingInput = false;
                        Main.input1 = true;
                        Main.selectingNewGate = true;
                    }
                }
            }
            if (circle.getId().equals("Entrada2")) {
                if (Main.conectingOutput) {
                    Main.input2Selected = true;
                    Main.selectingNewGate = true;
                    Main.input2 = true;
                    return;
                    //paintLine(pane, startposx, startposy, mouseEvent.getSceneX(), mouseEvent.getSceneY(), current, circle);
                }
                if (!Main.conectingOutput) {
                    Main.conectingInput = true;
                    Main.selectingInput = true;
                }
                if (Main.selected) {
                    System.out.println("Selected activado");
                    Main.selectingInput = false;
                    Main.input2 = true;
                    Main.selectingNewGate = true;
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

    public static Line paintLine(Pane pane, double startposx, double startposy, double endPosX, double endPosY, Circle compuerta1, Circle compuerta2){
        Line line = new Line();
        line.setStartX(startposx);
        line.setStartY(startposy);
        line.setEndX(endPosX);
        line.setEndY(endPosY);
        line.setId("Linea");
        pane.getChildren().addAll(line);
        Compuerta Newcompuerta = (Compuerta) ((Group)compuerta1.getUserData()).getUserData();
        Compuerta Newcompuerta2 = (Compuerta) ((Group)compuerta2.getUserData()).getUserData();
        Newcompuerta.lines.add(line);
        Newcompuerta2.lines.add(line);
        return line;


    }
}
