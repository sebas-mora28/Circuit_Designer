package Logica;


import Compuertas.Compuerta;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SimulateCircuit {
    private Pane root = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private Button buttonRun;
    private Scene scene;
    private Label labelLogicGate, labelEntrada;
    private LinkedList<Compuerta> inputs = new LinkedList<>();
    private LinkedList<ComboBox<Boolean>> comboBoxList = new LinkedList<>();
    public static boolean simulatingCircuit = false;
    private Stage stage = new Stage();


    public SimulateCircuit() throws Exception{
        start();
    }


    private void start() throws Exception {

        root.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        Label labelTitle = new Label();
        labelTitle.setText("Ingrese las entradas");
        labelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        labelTitle.setLayoutX(200);
        labelTitle.setLayoutY(30);
        root.getChildren().addAll(labelTitle);


        buttonRun = new Button();
        buttonRun.setText("Run");
        buttonRun.setFont(Font.font("Arial Black", FontWeight.BOLD, 20));
        buttonRun.setOnMouseEntered(mouseEvent -> buttonRun.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        buttonRun.setOnMouseExited(mouseEvent -> buttonRun.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));
        buttonRun.setLayoutX(550);
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


        scrollPane.setContent(root);
        scrollPane.setPrefSize(700,400);
        scene = new Scene(scrollPane);
        stage.setScene(scene);
        stage.setTitle("Ingresar las entradas ");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        setStage(stage);
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
        int posy = 90;
        int index = 0;
        for(int i = 0; i <= inputs.size()-1; i++){
            System.out.println("inputs size " + i);
            Compuerta compuerta = inputs.getElement(i);
            if(!compuerta.input1Connected){
                createComboBox(posx, posy);
                createLabel(posx, posy-20, "Input" + index + ":");
                posy += 80;
                index +=1;
            }
            if(!compuerta.input2Connected){
                createLabel(posx, posy-20, "Input" + index + ":");
                createComboBox(posx, posy);
                posy += 90;
                index +=1;
            }
        }
        updatePaneSize(posy);

    }

    public void updatePaneSize(int posy){
        if(posy < 400){
            posy +=150;
        }
        root.setPrefSize(700, posy);
        buttonRun.setLayoutY(posy - 100);
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
                        compuerta.input1.value = true;
                        index += 1;
                    }
                    if (!((Boolean) entry.getValue())) {
                        compuerta.inputs.add(false);
                        compuerta.input1.value = false;
                        index += 1;
                    }

                    if (entry.getValue() == null) {
                        throw new NullPointerException();
                    }
                }
                if (!compuerta.input2Connected) {
                    ComboBox entry = comboBoxList.getElement(index);
                    if ((Boolean) entry.getValue()) {
                        compuerta.inputs.add(true);
                        compuerta.input2.value = true;
                        index += 1;
                    }
                    if (!((Boolean) entry.getValue())) {
                        compuerta.input2.value = false;
                        compuerta.inputs.add(false);
                        index += 1;
                    }
                    if (entry.getValue() == null) {
                        throw new NullPointerException();
                    }
                }

            }
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
            System.out.println("Salida " + compuerta.output.value +"  " + "Size: " + compuerta.inputs.size());
        }
        for(int i=0; i<= LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            compuerta.inputs.removeAll();
        }
        Painter.updateEnumeration();
        simulatingCircuit = false;
    }


    EventHandler<MouseEvent> simulateCircuit = mouseEvent -> {
        setInputsValues();
        inputs.removeAll();
        comboBoxList.removeAll();
        stage.close();

    };

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



