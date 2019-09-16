package Logica;


import Compuertas.Compuerta;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SimulateCircuit extends Application {
    private Pane root = new Pane();
    private Label labelLogicGate, labelEntrada;
    private LinkedList<Compuerta> inputs = new LinkedList<>();
    private LinkedList<ComboBox<Boolean>> comboBoxList = new LinkedList<>();
    public static boolean simulatingCircuit = false;


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
        buttonRun.setOnMouseClicked(simulateCircuit);
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

        StartSimulating();
        VerifyEmptyInputs();


        stage.setScene(new Scene(root, 700, 700));
        stage.setTitle("Ingresar las entradas ");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();

    }

    public void StartSimulating(){
            if (LogicGatesCreator.LogicGatesList.size() == 0) {
                throw new NullPointerException();
            }
            for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
                Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
                if (!compuerta.input1Connected || !compuerta.input2Connected) {
                    inputs.add(compuerta);
                }
            }
    }

    public void VerifyEmptyInputs(){
        int posx = 50;
        int posy = 100;
        for(int i = 0; i <= inputs.size()-1; i++){
            Compuerta compuerta = inputs.getElement(i);
            if(!compuerta.input1Connected){
                createComboBox(posx, posy);
                posy += 30;
            }
            if(!compuerta.input2Connected){
                createComboBox(posx, posy);
                posy += 20;
            }

            posy += 50;
        }
    }

    public void createComboBox(int posx, int posy){
        ComboBox<Boolean> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList(true, false));
        comboBox.setPromptText("Seleccione un valor para la entrada");
        comboBox.setLayoutX(posx);
        comboBox.setLayoutY(posy);
        root.getChildren().addAll(comboBox);
        comboBoxList.add(comboBox);
    }

    public void setInputsValues() {
        int index = 0;
        try {
            for (int i = 0; i <= inputs.size() - 1; i++) {
                Compuerta compuerta = inputs.getElement(i);
                if (!compuerta.input1Connected) {
                    ComboBox entry = comboBoxList.getElement(index);
                    System.out.println(entry.getValue());
                    if ((Boolean) entry.getValue() == true) {
                        compuerta.input1.value = true;
                        index += 1;
                    }
                    if ((Boolean) entry.getValue() == false) {
                        compuerta.input1.value = false;
                        index += 1;
                    }
                    if (entry.getValue() == null) {
                        throw new NullPointerException();
                    }
                }
                if (!compuerta.input2Connected) {
                    ComboBox entry = comboBoxList.getElement(index);
                    if ((Boolean) entry.getValue() == true) {
                        compuerta.input2.value = true;
                        index += 1;
                    }
                    if ((Boolean) entry.getValue() == false) {
                        compuerta.input2.value = false;
                        index += 1;
                    }
                    if (entry.getValue() == null) {
                        throw new NullPointerException();
                    }
                }
            }
            operateLogicGates();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ENTRY NULL", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Entrada Inválida");
            alert.setContentText("Una de las entradas tiene un valor nulo, por favor ingrese un valor válido");
            alert.show();

        }
    }


    private void operateLogicGates(){
        simulatingCircuit = true;
        for(int i=0; i<= LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            compuerta.operar();
        }
        Painter.updateEnumeration();
        simulatingCircuit = false;
    }




    EventHandler<MouseEvent> simulateCircuit = mouseEvent -> {
        setInputsValues();
    };
}



