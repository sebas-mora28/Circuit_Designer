package GUI;

import Compuertas.Compuerta;
import ListaEnlazada.LinkedList;
import Logica.DragAndDrop;
import Logica.LogicGateConexion;
import Logica.LogicGatesCreator;
import Logica.SimulateCircuit;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.util.Random;


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
    private static Random random = new Random();
    private static boolean flag = true;
    private static Line line, line2, line3;;

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
        pane.setOnMouseMoved(line2Drag);
        pane.getChildren().addAll(salida);
        logicGateGroup.getChildren().add(salida);

    }


    /**
     * Método que crea los círculos que se incluirán en el grupo de la compuerta que corresponderan a las salida de la compuerta
     *
     * @param logicGateGroup grupo que corresponde a la compuerta lógica
     */

    private static void entradas(Group logicGateGroup) {
        entrada1 = new Circle(10);
        entrada1.setOnMouseEntered(mouseEvent -> entrada1.setCursor(Cursor.CROSSHAIR));
        entrada1.setOnMouseClicked(MouseClickInput);
        entrada1.setLayoutX(25);
        entrada1.setLayoutY(80);
        entrada1.setId("Entrada1");
        entrada1.setUserData(logicGateGroup);
        entrada1.setOpacity(0.0);


        entrada2 = new Circle(10);
        entrada2.setOnMouseEntered(mouseEvent -> entrada2.setCursor(Cursor.CROSSHAIR));
        entrada2.setOnMouseClicked(MouseClickInput);
        entrada2.setLayoutX(25);
        entrada2.setLayoutY(110);
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
                LogicGateConexion.conectingOutput = true;
                LogicGateConexion.selectingOutput = true;
                createLines();
                startposx =  mouseEvent.getSceneX();
                startposy = mouseEvent.getSceneY();
                }
            }

    };

    public static void createLines(){
        int r = random.nextInt(255);
        int v = random.nextInt(255);
        int g = random.nextInt(255);
        Line[] lines = new Line[3];

        for(int i=0; i<=2; i++){
            Line line = new Line();
            line.setFill(Color.rgb(r,v,g));
            line.setId("Linea");
            lines[i] = line;
            pane.getChildren().add(line);
        }
        setLine(lines[0], lines[1], lines[2]);
    }

    static  EventHandler<MouseEvent> MouseClickInput = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            if (circle.getId().equals("Entrada1")) {
                if (LogicGateConexion.conectingOutput) {
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input1 = true;
                    return;
                }


                if (!LogicGateConexion.conectingOutput) {
                    if(flag) {
                        LogicGateConexion.conectingInput = true;
                        LogicGateConexion.selectingInput = true;
                        LogicGateConexion.input1 = true;
                        Line line = new Line(startposx, startposy, mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        line.setStroke(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                        line.setStrokeWidth(5);
                        line.setId("Linea");
                    }
                }

                if (LogicGateConexion.selected) {
                    LogicGateConexion.selectingInput = false;
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input1Selected = true;
                    flag = true;
                    }
            }
            if (circle.getId().equals("Entrada2")) {
                if (LogicGateConexion.conectingOutput) {
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input2 = true;
                    return;
                }
                if (!LogicGateConexion.conectingOutput) {

                    if(flag) {
                        LogicGateConexion.conectingInput = true;
                        LogicGateConexion.selectingInput = true;
                        LogicGateConexion.input2 = true;
                        flag = false;
                    }
                }
                if (LogicGateConexion.selected) {
                    LogicGateConexion.selectingInput = false;
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input2Selected = true;
                    flag = true;
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
        labelOutput.setLayoutX(110);
        labelOutput.setLayoutY(85);
        labelOutput.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        labelOutput.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY)));
        labelOutput.setId("Output");

        Label labeInput1 = new Label("<0>");
        labeInput1.setLayoutX(10);
        labeInput1.setLayoutY(55);
        labeInput1.setId("Input1");

        Label labeInput2 = new Label("<0>");
        labeInput2.setLayoutX(10);
        labeInput2.setLayoutY(120);
        labeInput2.setId("Input2");


        group.getChildren().addAll(label,labelOutput, labeInput1, labeInput2);

    }

    public static void updateEnumeration(){
        int index = 0;
        for(int i=0; i<= LogicGatesCreator.LogicGatesList.size()-1;i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            ObservableList<Node> nodos =compuerta.logicGateGroup.getChildren();
            for(Node node : nodos){
                if(node.getId().equals("label")){
                    Label label = (Label)node;
                    label.setText("i<"+ i + ">");
                }
                if(node.getId().equals("Output") && SimulateCircuit.simulatingCircuit && !compuerta.outputConnected){
                    Label labelOutput = (Label)node;
                    labelOutput.setText(compuerta.output.value.toString());
                }
                if(node.getId().equals("Input1")){
                    Label labelInput1 = (Label)node;
                    if(!compuerta.input1Connected){
                        labelInput1.setText("i<"  + index + ">");
                        index +=1;
                        if(SimulateCircuit.simulatingCircuit){
                            //labelInput1.setText(compuerta.input1.value.toString());
                        }
                    }else{
                        labelInput1.setText("");

                    }
                }
                if(node.getId().equals("Input2")){
                    Label labelInput2 = (Label)node;
                    if(!compuerta.input2Connected){
                        labelInput2.setText("i<" + index +">");
                        index +=1;
                        if(SimulateCircuit.simulatingCircuit){
                            //labelInput2.setText(compuerta.input2.value.toString());
                        }
                    }else{
                        labelInput2.setText("");

                    }
                }
            }
        }
    }


    static EventHandler<MouseEvent> line2Drag = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(LogicGateConexion.conectingOutput) {
                endPosX = mouseEvent.getSceneX();
                endPosY = mouseEvent.getSceneY();

                double newPosx = (startposx + endPosX) / 2;

                startLine(newPosx, startposy, newPosx, endPosY, endPosX, endPosY);
            }

        }
    };


    public static void startLine(double posx1, double posy1, double posx2, double posy2, double posx3, double posy3){
        line.setStartX(startposx);
        line.setStartY(startposy);
        line.setEndX(posx1);
        line.setEndY(posy1);
        line2.setStartX(posx1);
        line2.setStartY(posy1);
        line2.setEndX(posx2);
        line2.setEndY(posy2);
        line3.setStartX(posx2);
        line3.setStartY(posy2);
        line3.setEndX(posx3);
        line3.setEndY(posy3);

    }


    public static void setLine(Line line, Line line2, Line line3) {
        Painter.line = line;
        Painter.line2 = line2;
        Painter.line3 = line3;
    }
}
