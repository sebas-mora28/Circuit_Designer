package GUI;


import Compuertas.Compuerta;
import Compuertas.CompuertaAND;
import LinkedList.LinkedList;
import Logica.GenerateTruthTable;
import Logica.LogicGateConexion;
import Logica.LogicGatesCreator;
import Logica.SimulateCircuit;
import javafx.application.Application;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;


/**
 * Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    private Pane pane;
    private Pane logicGatePane;
    private LogicGatesCreator logicGatesCreator = new LogicGatesCreator();
    private LinkedList<Compuerta> circuit;
    private LinkedList<Node> GUIcircuit;
    private SimulateCircuit simulateCircuit;
    private static int newPosyButton = 925;



    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {

        //Cuadrícula
        //-------------------------------------------------------------------------------------------------
        pane = new Pane();
        pane.setPrefSize(980, 900);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#e7ebda"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setOnMouseClicked(Connecting);

        Painter painter = new Painter(pane);


        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(978, 900);


        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        logicGatePane = new Pane();
        logicGatePane.setPrefSize(200, 1000);
        logicGatePane.setBackground(new Background(new BackgroundFill(Color.web("#91a3b0"), CornerRadii.EMPTY, Insets.EMPTY)));//"7EABA8"


        Label label = new Label("Compuertas ");
        label.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD, 24));
        label.setLayoutX(20);
        label.setLayoutY(35);
        logicGatePane.getChildren().add(label);


        ScrollPane logicGatesScroller = new ScrollPane();
        logicGatesScroller.setLayoutX(978);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setContent(logicGatePane);
        logicGatesScroller.setPrefSize(220, 760);


        //Botones
        //-------------------------------------------------------------------------------------------------

        createButtons();


        Button run = new Button("Run");
        run.setEffect(new DropShadow());
        run.setLayoutX(1050);
        run.setLayoutY(770);
        run.setPrefWidth(67);
        run.setOnMouseClicked(this.openWindow);

        Button clean = new Button("Clean");
        clean.setEffect(new DropShadow());
        clean.setLayoutX(1080);
        clean.setLayoutY(810);
        clean.setPrefWidth(90);
        clean.setOnMouseClicked(this.clean);

        Button refresh = new Button("Refresh");
        refresh.setEffect(new DropShadow());
        refresh.setLayoutX(995);
        refresh.setLayoutY(850);
        refresh.setOnMouseClicked(this.refresh);

        Button truthTable = new Button("Thuth Table");
        truthTable.setEffect(new DropShadow());
        truthTable.setLayoutX(1080);
        truthTable.setLayoutY(850);
        truthTable.setOnMouseClicked(this.thuthTable);

        Button saveCircuit = new Button("Save");
        saveCircuit.setEffect(new DropShadow());
        saveCircuit.setPrefWidth(67);
        saveCircuit.setLayoutX(995);
        saveCircuit.setLayoutY(810);
        saveCircuit.setOnMouseClicked(this.guardarCircuito);


        //Pantalla principal
        //-------------------------------------------------------------------------------------------------
        Pane root = new Pane(scrollPane, logicGatesScroller, run, clean, refresh, truthTable, saveCircuit);
        root.setBackground(new Background(new BackgroundFill(Color.web("2E5F68"), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setTitle("Circuit Designer");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();



    }

    //-----------------------------------------------------------------------------------------


    /**
     * Método que crea los botones de las compuertas básicas de la aplicación
     */

    public void createButtons(){
        LogicGatesCreator.LogicGateType[] logicGateTypes = LogicGatesCreator.LogicGateType.values();
        int posy = 90;
        for(int i=1; i<8; i++){
            final int index = i -1;
            Button newButton = new Button();
            ImageView imagen = new ImageView(new Image("Compuerta" + i + ".png"));
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
            newButton.setGraphic(imagen);
            newButton.setLayoutX(50);
            newButton.setLayoutY(posy);
            newButton.setEffect(new InnerShadow());
            newButton.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
            newButton.setCursor(Cursor.OPEN_HAND);
            newButton.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, logicGateTypes[index]));
            newButton.setOnMouseEntered(mouseEvent -> newButton.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
            newButton.setOnMouseExited(mouseEvent -> newButton.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));
            logicGatePane.getChildren().addAll(newButton);
            posy += 120;
        }
    }


    /**
     * Este método controla los métodos que se deben de llamar para realizar las conexiones de las compuertas
     */
    private EventHandler<MouseEvent> Connecting = mouseEvent -> LogicGateConexion.startConnexion(mouseEvent);


    /**
     * Por medio de este EventHandler se crea la ventana para pedir las entradas de las compuertas
     */
    private EventHandler<MouseEvent> openWindow = mouseEvent -> {
        try{
            simulateCircuit = new SimulateCircuit();

        }catch (NullPointerException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("No existen compuertas lógicas");
            alert.setContentText("Debe de crear al menos una compuerta lógica para simular el circuito");
            alert.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    };


    EventHandler<MouseEvent> clean = mouseEvent -> {
        cleanWindow();
    };

    private void cleanWindow() {
        System.out.println("Clean");
        pane.getChildren().clear();
        LogicGatesCreator.LogicGatesList.removeAll();
    }

    EventHandler<MouseEvent> refresh = mouseEvent -> {
        SimulateCircuit.simulatingCircuit = false;
        Painter.updateEnumeration();
    };

    EventHandler<MouseEvent> thuthTable = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

            try {
                if(LogicGatesCreator.LogicGatesList.size() ==0){ throw new NullPointerException(); }
                 GenerateTruthTable generateTruthTable = new GenerateTruthTable(LogicGatesCreator.LogicGatesList);
            } catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("No existen compuertas en el diseño");
                alert.setContentText("Debe crear al menos una compuerta para mostrar la tabla de verdad");
                alert.showAndWait();


            } catch (Exception e) {
            }
        }
    };


    EventHandler<MouseEvent> guardarCircuito  = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            saveCircuit();
        }
    };

    public void saveCircuit() {
        circuit = new LinkedList<Compuerta>();
        try {

            if(LogicGatesCreator.LogicGatesList.size() <=1){
                throw new NullPointerException();
            }
            for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
                Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
                circuit.add(compuerta);
            }

            GUIcircuit = new LinkedList<Node>();
            for (Node node : pane.getChildren()) {
                System.out.println("GUARDADO GRAFICAMENTE");
                GUIcircuit.add(node);
            }

            Button circuitSaved = new Button("   Circuito \n Guardado");
            circuitSaved.setLayoutX(50);
            circuitSaved.setLayoutY(newPosyButton);
            circuitSaved.setPrefSize(110, 100);
            circuitSaved.setOnMouseClicked(activarCircuito);
            logicGatePane.getChildren().add(circuitSaved);
            newPosyButton += 200;
            logicGatePane.setPrefHeight(logicGatePane.getHeight() + 50);


        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Verifique");
            alert.setContentText("Deben de existir al menos dos compuertas conectadas para guardar el circuito");
            alert.showAndWait();

        }
    }


    EventHandler<MouseEvent> activarCircuito = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            for(int i=0; i<=circuit.size()-1; i++){
                Compuerta compuerta = circuit.getElement(i);
                LogicGatesCreator.LogicGatesList.add(compuerta);
            }

            for(int i=0; i<= GUIcircuit.size()-1; i++){
                Node node = GUIcircuit.getElement(i);
                pane.getChildren().add(node);
            }

            Painter.updateEnumeration();
        }
    };
}




