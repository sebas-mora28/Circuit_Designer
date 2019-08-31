package GUI;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import javafx.stage.Stage;



/**
 * Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    private GridPane gridPane;
    private Pane pane;


    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {

        //Cuadrícula
        //-------------------------------------------------------------------------------------------------
        gridPane = new GridPane();
        gridPane.setPrefSize(978, 900);
        gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(978, 900);


        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        pane = new Pane();
        pane.setPrefSize(200, 900);
        pane.setMaxWidth(280);


        Label label = new Label("Compuertas");
        label.setLayoutX(90);
        label.setLayoutY(10);
        pane.getChildren().add(label);

        createButtons();

        ScrollPane logicGatesScroller = new ScrollPane(pane);
        logicGatesScroller.setLayoutX(978);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setPrefSize(220, 830);


        //Pantalla principal
        //-------------------------------------------------------------------------------------------------
        AnchorPane root = new AnchorPane(scrollPane, logicGatesScroller);


        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setTitle("Circuit Designer");
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();

    }

    //------------------------------------------------------------------------------------------


    LogicGatesCreator logicGatesCreator = new LogicGatesCreator();

    private void createButtons() {
        int posy = 100;
        for (int i = 1; i <= 4; i++) {
            Button button = new Button();
            String name = new String("Compuerta" + i + ".png");
            ImageView imagen = new ImageView(new Image(name));
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
            button.setGraphic(imagen);
            button.setLayoutX(50);
            button.setLayoutY(posy);
            button.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, name));


            pane.getChildren().add(button);
            posy += 130;

        }

    }
}





