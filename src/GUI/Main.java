package GUI;


import Logica.LogicGatesCreator;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private GridPane gridPane;
    private Pane pane;
    private GraphicsContext context;
    private LogicGatesCreator logicGatesCreator = new LogicGatesCreator();


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {

        //Cuadrícula
        //-------------------------------------------------------------------------------------------------
        gridPane = new GridPane();
        gridPane.setPrefSize(978, 900);
        gridPane.setBackground(new Background(new BackgroundFill(Color.web("#e7ebda"), CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setOnMouseClicked(MouseClick);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(978, 900);


        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        pane = new Pane();
        pane.setPrefSize(200, 1000);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#91a3b0"), CornerRadii.EMPTY, Insets.EMPTY)));//"7EABA8"


        Label label = new Label("Compuertas ");
        label.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD, 24));
        label.setLayoutX(20);
        label.setLayoutY(25);
        pane.getChildren().add(label);

        //createButtons();

        ScrollPane logicGatesScroller = new ScrollPane();
        logicGatesScroller.setLayoutX(978);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setContent(pane);
        logicGatesScroller.setPrefSize(220, 830);


        //Botónes
        //-------------------------------------------------------------------------------------------------
        Button run = new Button("Run");
        run.setLayoutX(1025);
        run.setLayoutY(850);
        run.setPrefSize(50, 25);


        //Pantalla principal
        //-------------------------------------------------------------------------------------------------
        AnchorPane root = new AnchorPane(scrollPane, logicGatesScroller, run);
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setTitle("Circuit Designer");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();



        // Botones

        Button AND = new Button();
        ImageView imagenAND = new ImageView(new Image("Compuerta1.png"));
        imagenAND.setFitHeight(90);
        imagenAND.setFitWidth(90);
        AND.setGraphic(imagenAND);
        AND.setLayoutX(50);
        AND.setLayoutY(90);
        AND.setEffect(new InnerShadow());
        AND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        AND.setCursor(Cursor.OPEN_HAND);
        AND.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.AND));
        AND.setOnMouseEntered(mouseEvent -> AND.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        AND.setOnMouseExited(mouseEvent -> AND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        Button NAND = new Button();
        ImageView imagenNAND = new ImageView(new Image("Compuerta2.png"));
        imagenNAND.setFitHeight(90);
        imagenNAND.setFitWidth(90);
        NAND.setGraphic(imagenNAND);
        NAND.setLayoutX(50);
        NAND.setLayoutY(220);
        NAND.setEffect(new InnerShadow());
        NAND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NAND.setCursor(Cursor.OPEN_HAND);
        NAND.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.NAND));
        NAND.setOnMouseEntered(mouseEvent -> NAND.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NAND.setOnMouseExited(mouseEvent -> NAND.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));



        Button OR = new Button();
        ImageView imagenOR = new ImageView(new Image("Compuerta3.png"));
        imagenOR.setFitHeight(90);
        imagenOR.setFitWidth(90);
        OR.setGraphic(imagenOR);
        OR.setLayoutX(50);
        OR.setLayoutY(350);
        OR.setEffect(new InnerShadow());
        OR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        OR.setCursor(Cursor.OPEN_HAND);
        OR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.OR));
        OR.setOnMouseEntered(mouseEvent -> OR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        OR.setOnMouseExited(mouseEvent -> OR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));



        Button NORD = new Button();
        ImageView imagenNORD = new ImageView(new Image("Compuerta4.png"));
        imagenNORD.setFitHeight(90);
        imagenNORD.setFitWidth(90);
        NORD.setGraphic(imagenNORD);
        NORD.setLayoutX(50);
        NORD.setLayoutY(480);
        NORD.setEffect(new InnerShadow());
        NORD.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NORD.setCursor(Cursor.OPEN_HAND);
        NORD.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.NORD));
        NORD.setOnMouseEntered(mouseEvent -> NORD.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NORD.setOnMouseExited(mouseEvent -> NORD.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        Button NOT = new Button();
        ImageView imagenNOT = new ImageView(new Image("Compuerta5.png"));
        imagenNOT.setFitHeight(90);
        imagenNOT.setFitWidth(90);
        NOT.setGraphic(imagenNOT);
        NOT.setLayoutX(50);
        NOT.setLayoutY(610);
        NOT.setEffect(new InnerShadow());
        NOT.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        NOT.setCursor(Cursor.OPEN_HAND);
        NOT.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.OR));
        NOT.setOnMouseEntered(mouseEvent -> NOT.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        NOT.setOnMouseExited(mouseEvent -> NOT.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        Button XOR = new Button();
        ImageView imagenXOR = new ImageView(new Image("Compuerta6.png"));
        imagenXOR.setFitHeight(90);
        imagenXOR.setFitWidth(90);
        XOR.setGraphic(imagenXOR);
        XOR.setLayoutX(50);
        XOR.setLayoutY(740);
        XOR.setEffect(new InnerShadow());
        XOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        XOR.setCursor(Cursor.OPEN_HAND);
        XOR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.XOR));
        XOR.setOnMouseEntered(mouseEvent -> XOR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        XOR.setOnMouseExited(mouseEvent -> XOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        Button XNOR = new Button();
        ImageView imagenXNOR = new ImageView(new Image("Compuerta7.png"));
        imagenXNOR.setFitHeight(90);
        imagenXNOR.setFitWidth(90);
        XNOR.setGraphic(imagenXNOR);
        XNOR.setLayoutX(50);
        XNOR.setLayoutY(870);
        XNOR.setEffect(new InnerShadow());
        XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
        XNOR.setCursor(Cursor.OPEN_HAND);
        XNOR.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, LogicGatesCreator.LogicGateType.XNOR));
        XNOR.setOnMouseEntered(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
        XNOR.setOnMouseExited(mouseEvent -> XNOR.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));


        pane.getChildren().addAll(AND, NAND, OR, NORD, NOT, XOR, XNOR);
    }


    //------------------------------------------------------------------------------------------


    private void createButtons() {
        int posy = 90;
        LogicGatesCreator.LogicGateType[] Compuertas = LogicGatesCreator.LogicGateType.values();
        for (int i = 1; i <= 7; i++) {
            Button button = new Button();
            String name = "Compuerta" + i + ".png";
            ImageView imagen = new ImageView(new Image(name));
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
            button.setGraphic(imagen);
            button.setLayoutX(50);
            button.setLayoutY(posy);
            button.setEffect(new InnerShadow());
            button.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
            button.setCursor(Cursor.OPEN_HAND);
            //button.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, name));
            button.setOnMouseEntered(mouseEvent -> button.setBackground(new Background(new BackgroundFill(Color.web("B9E0EB"), CornerRadii.EMPTY, Insets.EMPTY))));
            button.setOnMouseExited(mouseEvent -> button.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY))));

            posy += 130;
            pane.getChildren().add(button);

        }
    }

    EventHandler<MouseEvent> MouseClick = mouseEvent -> {
        System.out.println(mouseEvent.getSource());

    };
}





