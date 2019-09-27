package GUI;


import Compuertas.Compuerta;
import Compuertas.CompuertaAND;
import LinkedList.LinkedList;
import Logica.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.management.monitor.MonitorSettingException;
import java.util.List;


/**
 * Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    private Pane pane;
    private Pane logicGatePane;
    private LogicGatesCreator logicGatesCreator = new LogicGatesCreator();
    private LinkedList<LinkedList<Compuerta>> savedCircuits = new LinkedList<LinkedList<Compuerta>>();
    private LinkedList<LinkedList<Node>> GUISavedcircuit = new LinkedList<LinkedList<Node>>();
    private static int numberOfSavedCircuits = 0;
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
        run.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
        run.setEffect(new DropShadow());
        run.setLayoutX(1050);
        run.setLayoutY(770);
        run.setPrefWidth(67);
        run.setOnMouseClicked(this.openWindow);

        Button clean = new Button("Clean");
        clean.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
        clean.setEffect(new DropShadow());
        clean.setLayoutX(1080);
        clean.setLayoutY(810);
        clean.setPrefWidth(90);
        clean.setOnMouseClicked(this.clean);

        Button refresh = new Button("Refresh");
        refresh.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
        refresh.setEffect(new DropShadow());
        refresh.setLayoutX(995);
        refresh.setLayoutY(850);
        refresh.setOnMouseClicked(this.refresh);

        Button truthTable = new Button("Thuth Table");
        truthTable.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
        truthTable.setEffect(new DropShadow());
        truthTable.setLayoutX(1080);
        truthTable.setLayoutY(850);
        truthTable.setOnMouseClicked(this.thuthTable);

        Button saveCircuit = new Button("Save");
        saveCircuit.setFont(Font.font("Arial Black", FontWeight.BOLD, 12));
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

    public void createButtons() {
        LogicGatesCreator.LogicGateType[] logicGateTypes = LogicGatesCreator.LogicGateType.values();
        int posy = 90;
        for (int i = 1; i < 8; i++) {
            final int index = i - 1;
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
        try {
            SimulateCircuit simulateCircuit = new SimulateCircuit();

        } catch (NullPointerException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("No existen compuertas lógicas");
            alert.setContentText("Debe de crear al menos una compuerta lógica para simular el circuito");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };


    /**
     * Evento que llama al método cleanWindow para borrar el circuito
     */
    EventHandler<MouseEvent> clean = mouseEvent -> {
        cleanWindow();
    };


    /**
     * Método que se encarga de borrar el circuito tanto gráfica como lógicamente
     */
    private void cleanWindow() {
        System.out.println("Clean");
        pane.getChildren().clear();
        LogicGatesCreator.LogicGatesList.removeAll();
    }





    /**
     * Evento que actualiza la numeración despues de simular el circuito para que las entradas vuelvan a tener numeración
     */
    EventHandler<MouseEvent> refresh = mouseEvent -> {
        SimulateCircuit.simulatingCircuit = false;
        Painter.updateEnumeration();

    };




    /**
     * Evento que se encarga de crear llamar a la clase GenerateTruthTable para generar la tabla de verdad
     */

    EventHandler<MouseEvent> thuthTable = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {

            try {
                System.out.println(LogicGatesCreator.LogicGatesList.size());
                if (LogicGatesCreator.LogicGatesList.size() == 0) {
                    throw new NullPointerException();
                }
                GenerateTruthTable generateTruthTable = new GenerateTruthTable(LogicGatesCreator.LogicGatesList);


            } catch (NullPointerException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.setHeaderText("No existen compuertas en el diseño");
                alert.setContentText("Debe crear al menos una compuerta para mostrar la tabla de verdad");
                alert.showAndWait();

            } catch (Exception e) {
            }
        }
    };


    /**
     * Evento asociado al boton Save. Guarda el circuito y crea un botón en el panel de compuertas lógicas.
     */

    EventHandler<MouseEvent> guardarCircuito = mouseEvent -> saveCircuit();


    /**
     * Evento que guarda las compuertas tantos lógica como gráficamente para usarlo posteriormente durante la ejecución
     * circuito
     */

    public void saveCircuit() {
        LinkedList<Compuerta> circuit = new LinkedList<Compuerta>();
        try {
            if (LogicGatesCreator.LogicGatesList.size() <= 1) {
                throw new NullPointerException(); }

            for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
                Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
                circuit.add(compuerta);
            }
            savedCircuits.add(circuit);


            LinkedList<Node> GUIcircuit = new LinkedList<Node>();
            for (Node node : pane.getChildren()) {
                System.out.println("GUARDADO GRAFICAMENTE");
                GUIcircuit.add(node);
            }

            GUISavedcircuit.add(GUIcircuit);

            Button circuitSaved = new Button("   Circuito \n Guardado" + (numberOfSavedCircuits + 1));
            circuitSaved.setLayoutX(50);
            circuitSaved.setLayoutY(newPosyButton);
            circuitSaved.setPrefSize(110, 100);
            circuitSaved.setOnMouseClicked(activarCircuito);
            circuitSaved.setUserData(numberOfSavedCircuits);


            logicGatePane.getChildren().add(circuitSaved);
            newPosyButton += 125;
            logicGatePane.setPrefHeight(logicGatePane.getHeight() + 50);
            logicGatePane.setPrefHeight(logicGatePane.getHeight() + 150);

            numberOfSavedCircuits++;

        } catch (NullPointerException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setHeaderText("Verifique");
            alert.setContentText("Deben de existir al menos dos compuertas conectadas para guardar el circuito");
            alert.showAndWait();

        }
    }




    /**
     * Evento que muestra el circuito guardado y lo muestra en pantalla
     */

    EventHandler<MouseEvent> activarCircuito = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            int index = (int)((Button) mouseEvent.getSource()).getUserData();
            LinkedList<Compuerta> circuit = savedCircuits.getElement(index);
            LinkedList<Node> GUIcircuit = GUISavedcircuit.getElement(index);

            for (int i = 0; i <= circuit.size() - 1; i++) {
                Compuerta compuerta = circuit.getElement(i);
                compuerta.inputs.removeAll();
                LogicGatesCreator.LogicGatesList.add(compuerta);
            }

            for (int i = 0; i <= GUIcircuit.size() - 1; i++) {
                Node node = GUIcircuit.getElement(i);
                pane.getChildren().add(node);
            }
            Painter.updateEnumeration();
        }
    };

}




