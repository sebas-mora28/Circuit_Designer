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
    public int[] numerationLogicGate;


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
        int index = 0;
        for(int i = 0; i <= inputs.size()-1; i++){
            Compuerta compuerta = inputs.getElement(i);
            if(!compuerta.input1Connected || !compuerta.input2Connected){
                createLabel(posx, posy-40, "Compuerta" + i);
            }

            if(!compuerta.input1Connected){
                createComboBox(posx, posy);
                createLabel(posx, posy-20, "Input" + index + ":");
                posy += 60;
                index +=1;
            }
            if(!compuerta.input2Connected){
                createLabel(posx, posy-20, "Input" + index + ":");
                createComboBox(posx, posy);
                posy += 20;
                index +=1;
            }

            posy += 70;
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

    public void createLabel(int posx, int posy, String name){
        Label label = new Label();
        label.setText(name);
        label.setLayoutX(posx);
        label.setLayoutY(posy);
        root.getChildren().add(label);


    }

    public void setInputsValues() {
        int index = 0;
        try {
            for (int i = 0; i <= inputs.size() - 1; i++) {
                Compuerta compuerta = inputs.getElement(i);
                if (!compuerta.input1Connected) {
                    ComboBox entry = comboBoxList.getElement(index);
                    if ((Boolean) entry.getValue()) {
                        System.out.println("true 1");
                        compuerta.inputs.add(true);
                        //compuerta.input1.value = true;
                        //setInputs(compuerta, 1);
                        index += 1;
                    }
                    if (!((Boolean) entry.getValue())) {
                        System.out.println("false 1");
                        compuerta.inputs.add(false);
                        //compuerta.input1.value = false;
                        //setInputs(compuerta, 1);
                        index += 1;
                    }
                    if (entry.getValue() == null) {
                        //throw new NullPointerException();
                    }
                }
                if (!compuerta.input2Connected) {
                    ComboBox entry = comboBoxList.getElement(index);
                    if ((Boolean) entry.getValue()) {
                        System.out.println("true 2");
                        compuerta.inputs.add(true);
                        //compuerta.input2.value = true;
                        setInputs(compuerta, 2);
                        index += 1;
                    }
                    if (!((Boolean) entry.getValue())) {
                        //compuerta.input2.value = false;
                        System.out.println("false 2");
                        compuerta.inputs.add(false);
                        setInputs(compuerta, 2);
                        index += 1;
                    }
                    if (entry.getValue() == null) {
                        //throw new NullPointerException();
                    }
                }

            }
            System.out.println("Llega");
            operateLogicGates();
        }catch (NullPointerException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ENTRY NULL", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Entrada Inválida");
            alert.setContentText("Una de las entradas tiene un valor nulo, por favor ingrese un valor válido");
            alert.show();

        }
    }


    private void operateLogicGates(){
        simulatingCircuit = true;
        for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            compuerta.operar();
        }
        Painter.updateEnumeration();
        simulatingCircuit = false;
    }

    public void setInputs(Compuerta compuerta, int input){
        if(input==1){
            for(int i = 0; i<= compuerta.input1ToInput1.size()-1; i++){
                Compuerta newCompuerta = compuerta.input1ToInput1.getElement(i);
                newCompuerta.input1.value = compuerta.input1.value;
            }
            for(int i=0; i<= compuerta.input1ToInput2.size()-1; i++){
                Compuerta newCompuerta = compuerta.input1ToInput2.getElement(i);
                newCompuerta.input2.value = compuerta.input1.value;
            }
        }
        if(input==2){
            for(int i = 0; i<= compuerta.input2ToInput2.size()-1; i++){
                Compuerta newCompuerta = compuerta.input2ToInput2.getElement(i);
                newCompuerta.input2.value = compuerta.input2.value;
            }
            for(int i=0; i<= compuerta.input2ToInput2.size()-1; i++){
                Compuerta newCompuerta = compuerta.input1ToInput2.getElement(i);
                newCompuerta.input2.value = compuerta.input1.value;
            }

        }
    }


    EventHandler<MouseEvent> simulateCircuit = mouseEvent -> {
        setInputsValues();
    };
}



