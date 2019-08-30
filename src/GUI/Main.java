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
import javafx.scene.shape.Circle;

import javafx.stage.Stage;

import javax.swing.*;

/**
 * @see Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    GridPane gridPane;
    Pane pane;
    AnchorPane root;
    ScrollPane scrollPane;
    double posX, posY, newPosX, newPosY, translationX, translationY;
    private int index_image;

    private EventHandler dragOverRoot = null, dragDropped = null, dragOverPanel;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {

        //Cuadrícula
        //-------------------------------------------------------------------------------------------------
        gridPane = new GridPane();
        gridPane.setPrefSize(950, 900);
        gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(950, 900);


        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        Pane pane = new Pane();
        pane.setPrefSize(250, 900);
        pane.setMaxWidth(280);


        Label label = new Label("Compuertas");
        label.setLayoutX(90);
        label.setLayoutY(10);
        pane.getChildren().add(label);


        //Compuertas
        //--------------------------------------------------------------------------------------------------


        Button button = new Button();
        ImageView imagen = new ImageView("Compuerta1.png");
        imagen.setFitHeight(90);
        imagen.setFitWidth(90);
        button.setGraphic(imagen);

        button.setLayoutX(70);
        button.setLayoutY(90);
        button.setOnMouseClicked(createLogicGateAND);
        pane.getChildren().add(button);



        ScrollPane logicGatesScroller = new ScrollPane(pane);
        logicGatesScroller.setLayoutX(950);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setPrefSize(250, 830);


        //Pantalla principal
        //-------------------------------------------------------------------------------------------------
        AnchorPane root = new AnchorPane(scrollPane, logicGatesScroller);


        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();

    }


    //------------------------------------------------------------------------------------------

    private void createButtons() {
        int posy = 100;
        for (int i = 0; i < 1; i++) {
            System.out.print(i);
            setIndex_image(i);
            Button button = new Button();

            ImageView imagen = new ImageView("Compuerta1.png");
            imagen.setFitHeight(90);
            imagen.setFitWidth(90);
            button.setGraphic(imagen);

            button.setLayoutX(50);
            button.setLayoutY(posy);
            button.setOnMouseClicked(createLogicGateAND);


            pane.getChildren().add(button);
            posy += 100;

        }
    }

    EventHandler<MouseEvent> createLogicGateAND = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            ImageView imageViewAND = new ImageView(new Image("Compuerta1.png"));
            imageViewAND.setFitWidth(80);
            imageViewAND.setFitHeight(60);
            imageViewAND.setCursor(Cursor.HAND);
            imageViewAND.setOnMousePressed(MousePressed);
            imageViewAND.setOnMouseDragged(MousedDragged);
            gridPane.getChildren().add(imageViewAND);
        }
    };


    EventHandler<MouseEvent> MousePressed = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((ImageView)(mouseEvent.getSource())).getTranslateX();
            translationY = ((ImageView)(mouseEvent.getSource())).getTranslateY();
        }
    };

    EventHandler<MouseEvent> MousedDragged = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;

            double newTranslationX = translationX + newPosX;
            double newTranslationY = translationY + newPosY;
            ((ImageView)(mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((ImageView)(mouseEvent.getSource())).setTranslateY(newTranslationY);
        }
    };






    public int getIndex_image() {
        return index_image;
    }

    public void setIndex_image(int index_image) {
        this.index_image = index_image;
    }
}




