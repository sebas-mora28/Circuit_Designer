package Logica;


import Compuertas.Compuerta;
import ListaEnlazada.LinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimulateCircuit extends Application {
    private Pane root = new Pane();
    private TextField textField;
    private Label labelLogicGate, labelEntrada;
    private LinkedList<Compuerta> entries = new LinkedList<>();
    private LinkedList<TextField> textFieldList = new LinkedList<>();


    @Override
    public void start(Stage stage) throws Exception {

        root.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));


        Label labelTitle = new Label();
        labelTitle.setText("Ingrese las entradas");
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        labelTitle.setLayoutX(200);
        labelTitle.setLayoutY(30);
        root.getChildren().addAll(labelTitle);

        Button buttonRun = new Button();
        buttonRun.setText("Run");
        buttonRun.setLayoutX(600);
        buttonRun.setLayoutY(650);
        root.getChildren().addAll(buttonRun);

        labelLogicGate = new Label();
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        labelTitle.setLayoutX(225);
        labelTitle.setLayoutY(320);
        root.getChildren().addAll(labelLogicGate);


        labelEntrada = new Label();
        labelTitle.setText("Ingrese las entradas");
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        labelTitle.setLayoutX(200);
        labelTitle.setLayoutY(20);
        root.getChildren().addAll(labelEntrada);

        run();
        askEntries();


        stage.setScene(new Scene(root, 700, 700));
        stage.setTitle("Ingresar las entradas ");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

    }

    public void run(){
            System.out.println(LogicGatesCreator.LogicGatesList.size());
            if (LogicGatesCreator.LogicGatesList.size() == 0) {
                throw new NullPointerException();
            }
            for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
                Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
                if (compuerta.input1Connected) {
                    entries.add(compuerta.input1);
                    //TODO: Pedir la entrada1 de la compuerta
                }

                if (compuerta.input2Connected) {
                    entries.add(compuerta.input2);
                    //TODO: Pedir la entrada2 de la compuerta
                }
            }
    }

    public void askEntries(){
        int posx = 50;
        int posy = 100;
        for(int i=0; i<=entries.size()-1; i++){
            System.out.println("Se crean los ");
            TextField textField = new TextField();
            textFieldList.add(textField);
            textField.setLayoutX(posx);
            textField.setLayoutY(posy);
            root.getChildren().addAll(textField);
            posy += 80;
        }
    }

    public void asignInputs() {
        for (int i = 0; i <= textFieldList.size() - 1; i++) {
            TextField entry = textFieldList.getElement(i);
            Compuerta compuerta = entries.getElement(i);
            if (entry.getText().equals("true")) {
                if (compuerta.input1Connected) {
                    compuerta.input1.value = true;
                }
                if (compuerta.input2Connected) {
                    compuerta.input2.value = true;
                }
            }
            if (entry.getText().equals("false")) {
                if (compuerta.input1Connected) {
                    compuerta.input1.value = false;
                }
                if (compuerta.input2Connected) {
                    compuerta.input2.value = false;
                }
            }
            compuerta.operar();
            System.out.println(compuerta.output.value);
        }
    }
}



