package GUI;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.awt.*;


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
        gridPane.setBackground(new Background(new BackgroundFill(Color.web("#e7ebda"), CornerRadii.EMPTY, Insets.EMPTY)));
        Canvas canvas = new Canvas();
        canvas.setLayoutX(0);
        canvas.setLayoutY(0);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(978, 900);


        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        pane = new Pane();
        pane.setPrefSize(200, 900);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#91a3b0"),CornerRadii.EMPTY, Insets.EMPTY)));//"7EABA8"

        pane.setMaxWidth(280);


        Label label = new Label("Compuertas ");
        label.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD, 24));
        label.setLayoutX(20);
        label.setLayoutY(25);
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
        int posy = 90;
        for (int i = 1; i <=6; i++) {
            Button button = new Button();
            String name = new String("Compuerta" + i + ".png");
            ImageView imagen = new ImageView(new Image(name));
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
            button.setGraphic(imagen);
            button.setLayoutX(50);
            button.setLayoutY(posy);
            button.setEffect(new InnerShadow());
            button.setBackground(new Background(new BackgroundFill(Color.web("#d7d7d7"), CornerRadii.EMPTY, Insets.EMPTY)));//"ACC8C6"
            button.setCursor(Cursor.OPEN_HAND);
            button.setOnAction(MouseEvent -> logicGatesCreator.createLogicGates(gridPane, name));
            pane.getChildren().add(button);
            posy += 130;

        }

    }
}





