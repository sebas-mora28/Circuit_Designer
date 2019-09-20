package GUI;


import Logica.GenerateTruthTable;
import Logica.LogicGateConexion;
import Logica.LogicGatesCreator;
import Logica.SimulateCircuit;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Cursor;
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


/**
 * Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    private Pane pane;
    private Pane logicGatePane;
    private LogicGatesCreator logicGatesCreator = new LogicGatesCreator();
    private SimulateCircuit simulateCircuit;
    private Button AND, NAND, NORD, NOT, OR, XNOR, XOR;



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
        logicGatesScroller.setPrefSize(220, 830);


        //Botones
        //-------------------------------------------------------------------------------------------------
        Button run = new Button("Run");
        run.setEffect(new DropShadow());
        run.setLayoutX(990);
        run.setLayoutY(850);
        run.setOnMouseClicked(this.openWindow);
        run.setPrefSize(50, 25);

        Button clean = new Button("Clean");
        clean.setLayoutX(1060);
        clean.setLayoutY(850);
        clean.setOnMouseClicked(this.clean);

        Button refresh = new Button("Refresh");
        refresh.setLayoutX(1130);
        refresh.setLayoutY(850);
        refresh.setOnMouseClicked(this.refresh);




        //Pantalla principal
        //-------------------------------------------------------------------------------------------------
        Pane root = new Pane(scrollPane, logicGatesScroller, run, clean, refresh);
        root.setBackground(new Background(new BackgroundFill(Color.web("2E5F68"), CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setTitle("Circuit Designer");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();


        // Botones


        AND = new Button();
        ImageView imagenAND = new ImageView(new Image("Compuerta1.png"));
        imagenAND.setFitHeight(90);
        imagenAND.setFitWidth(90);
        AND.setGraphic(imagenAND);
        AND.setLayoutX(50);
        AND.setLayoutY(90);
        AND.setEffect(new InnerShadow());
        AND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        AND.setCursor(Cursor.OPEN_HAND);
        AND.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.AND));
        AND.setOnMouseEntered(mouseEvent -> AND.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        AND.setOnMouseExited(mouseEvent -> AND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        NAND = new Button();
        ImageView imagenNAND = new ImageView(new Image("Compuerta2.png"));
        imagenNAND.setFitHeight(90);
        imagenNAND.setFitWidth(90);
        NAND.setGraphic(imagenNAND);
        NAND.setLayoutX(50);
        NAND.setLayoutY(220);
        NAND.setEffect(new InnerShadow());
        NAND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NAND.setCursor(Cursor.OPEN_HAND);
        NAND.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.NAND));
        NAND.setOnMouseEntered(mouseEvent -> NAND.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NAND.setOnMouseExited(mouseEvent -> NAND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        OR = new Button();
        ImageView imagenOR = new ImageView(new Image("Compuerta3.png"));
        imagenOR.setFitHeight(90);
        imagenOR.setFitWidth(90);
        OR.setGraphic(imagenOR);
        OR.setLayoutX(50);
        OR.setLayoutY(350);
        OR.setEffect(new InnerShadow());
        OR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        OR.setCursor(Cursor.OPEN_HAND);
        OR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.OR));
        OR.setOnMouseEntered(mouseEvent -> OR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        OR.setOnMouseExited(mouseEvent -> OR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        NORD = new Button();
        ImageView imagenNORD = new ImageView(new Image("Compuerta4.png"));
        imagenNORD.setFitHeight(90);
        imagenNORD.setFitWidth(90);
        NORD.setGraphic(imagenNORD);
        NORD.setLayoutX(50);
        NORD.setLayoutY(480);
        NORD.setEffect(new InnerShadow());
        NORD.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NORD.setCursor(Cursor.OPEN_HAND);
        NORD.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.NORD));
        NORD.setOnMouseEntered(mouseEvent -> NORD.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NORD.setOnMouseExited(mouseEvent -> NORD.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        NOT = new Button();
        ImageView imagenNOT = new ImageView(new Image("Compuerta5.png"));
        imagenNOT.setFitHeight(70);
        imagenNOT.setFitWidth(90);
        NOT.setGraphic(imagenNOT);
        NOT.setLayoutX(50);
        NOT.setLayoutY(610);
        NOT.setEffect(new InnerShadow());
        NOT.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NOT.setCursor(Cursor.OPEN_HAND);
        NOT.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.NOT));
        NOT.setOnMouseEntered(mouseEvent -> NOT.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NOT.setOnMouseExited(mouseEvent -> NOT.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        XOR = new Button();
        ImageView imagenXOR = new ImageView(new Image("Compuerta6.png"));
        imagenXOR.setFitHeight(90);
        imagenXOR.setFitWidth(90);
        XOR.setGraphic(imagenXOR);
        XOR.setLayoutX(50);
        XOR.setLayoutY(740);
        XOR.setEffect(new InnerShadow());
        XOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        XOR.setCursor(Cursor.OPEN_HAND);
        XOR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.XOR));
        XOR.setOnMouseEntered(mouseEvent -> XOR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        XOR.setOnMouseExited(mouseEvent -> XOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        XNOR = new Button();
        ImageView imagenXNOR = new ImageView(new Image("Compuerta7.png"));
        imagenXNOR.setFitHeight(90);
        imagenXNOR.setFitWidth(90);
        XNOR.setGraphic(imagenXNOR);
        XNOR.setLayoutX(50);
        XNOR.setLayoutY(870);
        XNOR.setEffect(new InnerShadow());
        XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        XNOR.setCursor(Cursor.OPEN_HAND);
        XNOR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.XNOR));
        XNOR.setOnMouseEntered(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        XNOR.setOnMouseExited(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        logicGatePane.getChildren().addAll(AND, NAND, OR, NORD, NOT, XOR, XNOR);
    }


    //-----------------------------------------------------------------------------------------



    public void createButtons(){
        LogicGatesCreator.LogicGateType[] logicGateTypes = new LogicGatesCreator.LogicGateType[7];
        for(int i=0; i<=7; i++){
            XNOR = new Button();
            ImageView imagenXNOR = new ImageView(new Image("Compuerta7.png"));
            imagenXNOR.setFitHeight(90);
            imagenXNOR.setFitWidth(90);
            XNOR.setGraphic(imagenXNOR);
            XNOR.setLayoutX(50);
            XNOR.setLayoutY(870);
            XNOR.setEffect(new InnerShadow());
            XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
            XNOR.setCursor(Cursor.OPEN_HAND);
            XNOR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(pane, LogicGatesCreator.LogicGateType.XNOR));
            XNOR.setOnMouseEntered(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
            XNOR.setOnMouseExited(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));




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
        GenerateTruthTable generateTruthTable = new GenerateTruthTable(LogicGatesCreator.LogicGatesList);
        SimulateCircuit.simulatingCircuit = false;
        Painter.updateEnumeration();
    };



}




