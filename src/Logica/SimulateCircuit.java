package Logica;


import Compuertas.Compuerta;
import GUI.Painter;
import LinkedList.LinkedList;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * Clase que se encarga de obtener los valores de las entradas que el usuario desee y obtener la salida con las
 * respectivas entradas
 */
public class SimulateCircuit {
    private Pane root = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private Button buttonRun;
    private Scene scene;
    private Label labelLogicGate, labelEntrada;
    private LinkedList<Compuerta> circuitInputs = new LinkedList<Compuerta>();
    private LinkedList<Compuerta> circuitsOutputs = new LinkedList<Compuerta>();
    private LinkedList<ComboBox<Boolean>> comboBoxList = new LinkedList<ComboBox<Boolean>>();
    public static boolean simulatingCircuit = false;
    private Stage stage = new Stage();


    /**
     * Constructor
     * @throws Exception
     */
    public SimulateCircuit() throws Exception{
        start();
    }


    /**
     * Método que ejecuta la interfaz gráfica
     * @throws Exception
     */
    private void start() throws Exception {

        root.setBackground(new Background(new BackgroundFill(Color.web("139AB4"), CornerRadii.EMPTY, Insets.EMPTY)));
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


    /**
     * Método que da comienzo a la simulación del circuito. En este método se agregan a una lista enlazada todas aquellas compuertas
     * que contienen entradas disponibles. Además se agregan las salidas del circuito a otra lista enlazada
     */

    public void StartSimulating(){
            if (LogicGatesCreator.LogicGatesList.size() == 0) {
                throw new NullPointerException();
            }
            for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
                Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
                if (!compuerta.input1Connected || !compuerta.input2Connected) {
                    circuitInputs.add(compuerta);
                }
                if(!compuerta.outputConnected){ ;
                    circuitsOutputs.add(compuerta);
                }
            }
    }


    /**
     * Método que verifica cuales entradas de cada compuerta se encuentra disponible para agregar un comboBox a la ventana
     * para asignarle el valor. Cada ComboBox se agrega a una nueva lista enlazada para posteriormente obtener los valores.
     */

    private void VerifyEmptyInputs(){
        int posx = 50;
        int posy = 90;
        int index = 0;
        for(int i = 0; i <= circuitInputs.size()-1; i++){
            System.out.println("inputs size " + i);
            Compuerta compuerta = circuitInputs.getElement(i);
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

    /**
     * Método que actualiza el tamaño del pane conforme se van creando botones
     * @param posy nuevo tamaño del pane
     */
    private void updatePaneSize(int posy){
        if(posy < 400){
            posy +=150;
        }
        root.setPrefSize(700, posy);
        buttonRun.setLayoutY(posy - 100);
    }


    /**
     * Método que crea cada uno de los comboBox
     * @param posx posición en x para el comboBox respectivo
     * @param posy posición en y para el comboBox respectivo
     */

    private void createComboBox(int posx, int posy){
        ComboBox<Boolean> comboBox = new ComboBox<>();
        comboBox.setItems(FXCollections.observableArrayList(true, false));
        comboBox.setPromptText("Seleccione un valor para la entrada");
        comboBox.setCursor(Cursor.CROSSHAIR);
        comboBox.setOnMouseEntered(mouseEvent ->  comboBox.setBackground(new Background(new BackgroundFill(Color.web("43D8CF"), CornerRadii.EMPTY, Insets.EMPTY))));
        comboBox.setOnMouseExited(mouseEvent -> comboBox.setBackground(new Background(new BackgroundFill(Color.web("BCC5C5"),CornerRadii.EMPTY, Insets.EMPTY))));
        comboBox.setLayoutX(posx);
        comboBox.setLayoutY(posy);
        root.getChildren().addAll(comboBox);
        comboBoxList.add(comboBox);
    }

    /**
     * Método que crear los labels para identificar a cual entrada correspone cada comboBox
     * @param posx posición en x
     * @param posy posición en y
     * @param name nombre de la entrada
     */
    private void createLabel(int posx, int posy, String name){
        Label label = new Label();
        label.setText(name);
        label.setLayoutX(posx);
        label.setFont(Font.font("Arial Black", FontWeight.BOLD, 15));
        label.setLayoutY(posy);
        root.getChildren().add(label);


    }


    /**
     * Método que asigna el valor elegido por el usuario por medio del comboBox a cada una de las entrada disponibles del
     * circuito
     */
    private void setInputsValues() {
        int index = 0;
        try {
            for (int i = 0; i <= circuitInputs.size() - 1; i++) {
                Compuerta compuerta = circuitInputs.getElement(i);
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


    /**
     * Método que operar las compuertas de salida del circuito para obtener el valor de la salida
     */
    private void operateLogicGates(){
        simulatingCircuit = true;

        for(int i=0; i<= circuitsOutputs.size()-1; i++){
            Compuerta compuerta = circuitsOutputs.getElement(i);
            compuerta.operar();
            System.out.println("El valor de la salida del circuito es " + compuerta.output.value);
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
        circuitInputs.removeAll();
        comboBoxList.removeAll();
        stage.close();

    };

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}



