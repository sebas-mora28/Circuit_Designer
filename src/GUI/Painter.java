package GUI;

import Compuertas.Compuerta;
import LinkedList.LinkedList;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Clase que crear inserta las imágenes y crea figuras
 */

public class Painter {
    private static Pane pane;
    private static Circle salida, entrada1, entrada2;
    private static Rectangle rectangleImage;
    private static boolean flag = true;
    public static PaintLine paintLine;
    public static LinkedList<PaintLine> linesList = new LinkedList<PaintLine>();
    private static int index =0;

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
        salida = new Circle(12);
        salida.setOnMouseEntered(mouseEvent -> salida.setCursor(Cursor.CROSSHAIR));
        salida.setOnMouseClicked(MouseClickOutput);
        salida.setLayoutX(100);
        salida.setLayoutY(92);
        salida.setId("Salida");
        salida.setOpacity(0.0);
        salida.setUserData(logicGateGroup);
        pane.getChildren().addAll(salida);
        logicGateGroup.getChildren().add(salida);
    }


    /**
     * Este método crea la entrada y salida para la compuerta NOT
     * @param logicGateGroup grupo donde se agregan la entrada y salida
     */


    public static void crearEntradaSalidaNOT(Group logicGateGroup) {
        salida = new Circle(12);
        salida.setOnMouseEntered(mouseEvent -> salida.setCursor(Cursor.CROSSHAIR));
        salida.setOnMouseClicked(MouseClickOutput);
        salida.setLayoutX(105);
        salida.setLayoutY(95);
        salida.setId("Salida");
        salida.setOpacity(0.0);
        salida.setUserData(logicGateGroup);
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
        entrada1.setLayoutX(20);
        entrada1.setLayoutY(80);
        entrada1.setId("Entrada1");
        entrada1.setUserData(logicGateGroup);
        entrada1.setOpacity(0.0);


        entrada2 = new Circle(10);
        entrada2.setOnMouseEntered(mouseEvent -> entrada2.setCursor(Cursor.CROSSHAIR));
        entrada2.setOnMouseClicked(MouseClickInput);
        entrada2.setLayoutX(20);
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
            Compuerta compuerta = (Compuerta) ((Group) circle.getUserData()).getUserData();
            if (!compuerta.outputConnected) {
                if (circle.getId().equals("Salida")) {
                    if (!LogicGateConexion.conectingOutput && !LogicGateConexion.selectingOutput) {
                        LogicGateConexion.conectingOutput = true;
                        LogicGateConexion.selectingOutput = true;
                        paintLine = new PaintLine(compuerta, pane, mouseEvent.getSceneX(), mouseEvent.getSceneY());
                        compuerta.listLines.add(paintLine);
                    } else {
                        paintLine.removeLines();
                        LogicGateConexion.selectingOutput = false;
                        LogicGateConexion.conectingOutput = false;
                    }
                }

            }else{
                System.out.println("La salida de la compuerta ya se encuentra conectada");
            }
        }

    };


    static  EventHandler<MouseEvent> MouseClickInput = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Circle circle = (Circle) mouseEvent.getSource();
            Compuerta compuerta = (Compuerta)((Group)circle.getUserData()).getUserData();
            if (circle.getId().equals("Entrada1")) {
                if (LogicGateConexion.conectingOutput) {
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input1 = true;
                    LogicGateConexion.logicGateTo = compuerta;
                    paintLine.setLine3EndPositionsInput1(compuerta);
                    return;
                }


            }
            if (circle.getId().equals("Entrada2")) {
                if (LogicGateConexion.conectingOutput) {
                    LogicGateConexion.selectingNewGate = true;
                    LogicGateConexion.input2 = true;
                    LogicGateConexion.logicGateTo = compuerta;
                    paintLine.setLine3EndPositionInput2(compuerta);
                    return;
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
        labeInput1.setLayoutY(50);
        labeInput1.setId("Input1");

        Label labeInput2 = new Label("<0>");
        labeInput2.setLayoutX(10);
        labeInput2.setLayoutY(125);
        labeInput2.setId("Input2");


        group.getChildren().addAll(label,labelOutput, labeInput1, labeInput2);

    }

    /**
     * Método que actualiza los labels de las entradas y salidas de la compuertas en caso de que se realice algún cambio
     * en el circuito.
     */

    public static void updateEnumeration(){
        for(int i=0; i<= LogicGatesCreator.LogicGatesList.size()-1;i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            ObservableList<Node> nodos =compuerta.logicGateGroup.getChildren();
            for(Node node : nodos){
                if(node.getId().equals("label")){
                    Label label = (Label)node;
                    label.setText("i<"+ i + ">");
                }
                updateInputsLabel(node, compuerta);
                updateOutputsLabel(node, compuerta);

            }
        }
        index =0;
    }

    /**
     * Método que actualiza los labels de la salida de la compuerta
     * @param node Nodo que corresponde al label
     * @param compuerta Compuerta de donde se obtiene el valor de la salida
     */

    private static void updateOutputsLabel(Node node, Compuerta compuerta) {
        if (node.getId().equals("Output") && SimulateCircuit.simulatingCircuit && compuerta.outputConnected) {
            Label labelOutput = (Label) node;
            labelOutput.setText("");
        }
        if (node.getId().equals("Output") && SimulateCircuit.simulatingCircuit && !compuerta.outputConnected) {
            Label labelOutput = (Label) node;
            labelOutput.setText(compuerta.output.value.toString());
        }
    }

    /**
     * Método que actualiza los labels de salida de cada compuerta
     * @param node Nodo que corresponde al label salida
     * @param compuerta Compuerta de donde se obtiene el valor de las entradas
     */

    private static void updateInputsLabel(Node node, Compuerta compuerta){
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
