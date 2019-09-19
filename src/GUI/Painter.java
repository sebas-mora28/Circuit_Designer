package GUI;

import Compuertas.Compuerta;
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

import java.util.Random;


/**
 * Clase que crear inserta las imágenes y crea figuras
 */

public class Painter {
    private static Pane pane;
    private static Circle salida, entrada1, entrada2;
    private static Rectangle rectangleImage;
    private static double startposx, startposy, endPosX, endPosY;
    private static Random random = new Random();
    private static boolean flag = true;
    private static Line line1, line2, line3;;
    private static Line[] lines = new Line[3];

    /**
     *
     * @param pane
     */

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
        salida.setUserData(logicGateGroup);
        pane.setOnMouseMoved(MovingLine);
        pane.getChildren().addAll(salida);
        logicGateGroup.getChildren().add(salida);
    }


    /**
     * Este método crea la entrada y salida para la compuerta NOT
     * @param logicGateGroup grupo donde se agregan la entrada y salida
     */


    public static void crearEntradaSalidaNOT(Group logicGateGroup) {
        salida = new Circle(10);
        salida.setOnMouseEntered(mouseEvent -> salida.setCursor(Cursor.CROSSHAIR));
        salida.setOnMouseClicked(MouseClickOutput);
        salida.setLayoutX(105);
        salida.setLayoutY(95);
        salida.setId("Salida");
        salida.setOpacity(0.0);
        pane.setOnMouseMoved(MovingLine);
        pane.getChildren().addAll(salida);
        logicGateGroup.getChildren().add(salida);

        entrada1 = new Circle(10);
        entrada1.setOnMouseEntered(mouseEvent -> entrada1.setCursor(Cursor.CROSSHAIR));
        entrada1.setOnMouseClicked(MouseClickInput);
        entrada1.setLayoutX(15);
        entrada1.setLayoutY(100);
        entrada1.setId("Entrada1");
        entrada1.setUserData(logicGateGroup);
        logicGateGroup.getChildren().add(entrada1);
        entrada1.setOpacity(0.0);


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
            Compuerta compuerta = (Compuerta)((Group)circle.getUserData()).getUserData();
            if (circle.getId().equals("Salida")) {
                if (!LogicGateConexion.conectingOutput && !LogicGateConexion.selectingOutput) {
                    LogicGateConexion.conectingOutput = true;
                    LogicGateConexion.selectingOutput = true;
                    createLines(compuerta);
                    startposx = mouseEvent.getSceneX();
                    startposy = mouseEvent.getSceneY();
                }else{
                    removeLines();
                    LogicGateConexion.selectingOutput = false;
                    LogicGateConexion.conectingOutput= false;
                }
            }

            }

    };

    /**
     * Método que remueve las líneas que se crearon en caso de que la acción hecha por el usuario sea inváloda
     */

    public static void removeLines(){
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

    /**
     * Método que crea las líneas para conectar las compuertas
     * @param compuerta Compuerta para obtener la posición en X y Y para conectar la línea de la compuerta
     */

    private static void createLines(Compuerta compuerta){
        int r = random.nextInt(255);
        int v = random.nextInt(255);
        int g = random.nextInt(255);
        lines = new Line[3];
        for(int i=0; i<=2; i++) {
            Line line = new Line();
            line.setStroke(Color.rgb(r, v, g));
            line.setId("Linea");
            lines[i] = line;
            pane.getChildren().add(line);
        }
        setLines(lines[0], lines[1], lines[2]);
        line1.startXProperty().bind(compuerta.lineOutputPosX);
        line1.startYProperty().bind(compuerta.lineOutputPosY);
    }




    static  EventHandler<MouseEvent> MouseClickInput = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            Compuerta compuerta = (Compuerta)((Group)circle.getUserData()).getUserData();
            if (circle.getId().equals("Entrada1")) {
                if (LogicGateConexion.conectingOutput) {
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input1 = true;
                    line3.endXProperty().bind(compuerta.lineInput1PosX);
                    line3.endYProperty().bind(compuerta.lineInput1PosY);
                    return;
                }

                if (!LogicGateConexion.conectingOutput) {
                    if (flag) {
                        startposx = mouseEvent.getSceneX();
                        startposy = mouseEvent.getSceneY();
                        LogicGateConexion.conectingInput = true;
                        LogicGateConexion.selectingInput = true;
                        LogicGateConexion.input1 = true;
                        flag = false;
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
                    line3.endXProperty().bind(compuerta.lineInput2PosX);
                    line3.endYProperty().bind(compuerta.lineInput2PosY);
                    return;
                }
                if (!LogicGateConexion.conectingOutput) {

                    if (flag) {
                        LogicGateConexion.conectingInput = true;
                        LogicGateConexion.selectingInput = true;
                        LogicGateConexion.input2 = true;
                        startposx = mouseEvent.getSceneX();
                        startposy = mouseEvent.getSceneY();
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


    /**
     * Este método agrega los labels que corresponden a la numeración de las entradas y salidas de la compuerta
     * @param group
     */


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

    /**
     * Método que actualiza los labels de las entradas y salidas de la compuertas en caso de que se realice algún cambio
     * en el circuito.
     */

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
                if(node.getId().equals("Output") && SimulateCircuit.simulatingCircuit && compuerta.outputConnected){
                    Label labelOutput = (Label)node;
                    labelOutput.setText("");
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
                            labelInput1.setText(compuerta.input1.value.toString());
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
                            labelInput2.setText(compuerta.input2.value.toString());
                        }
                    }else{
                        labelInput2.setText("");

                    }
                }
            }
        }
    }


    static EventHandler<MouseEvent> MovingLine = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if(LogicGateConexion.conectingOutput) {
                endPosX = mouseEvent.getSceneX();
                endPosY = mouseEvent.getSceneY();

                double newPosx = (startposx + endPosX) / 2;

                starLine(newPosx, startposy, newPosx, endPosY, endPosX, endPosY);
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

    public static void starLine(double posx1, double posy1, double posx2, double posy2, double posx3, double posy3) {
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




    public static void setLines(Line line, Line line2, Line line3) {

        Painter.line1 = line;
        Painter.line2 = line2;
        Painter.line3 = line3;
    }

}
