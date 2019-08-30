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

/**
 * @see Main Se encarga de inicializar la interfaz gráfica de la aplicación, se utiliza la biblioteca JavaFx para la creación de los componentes básicos
 */
public class Main extends Application {
    GridPane gridPane;
    Pane pane;
    AnchorPane root;
    Circle circle;
    double posX, posY, newPosX, newPosY;
    private ImageView compuertaAND;

    private EventHandler dragOverRoot = null, dragDropped = null, dragOverPanel;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {

        //Cuadrícula
        //-------------------------------------------------------------------------------------------------
        gridPane = new GridPane();
        gridPane.setPrefSize(950, 900);
        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(950, 900);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));




        // Paleta de compuertas
        //---------------------------------------------------------------------------------------------------

        Pane pane = new Pane();
        pane.setPrefSize(250, 900);
        pane.setMaxWidth(280);
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(150, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));


        Label label = new Label("Compuertas");
        label.setLayoutX(90);
        label.setLayoutY(10);
        pane.getChildren().add(label);


        //Compuertas
        //--------------------------------------------------------------------------------------------------


        // Compuerta AND
        ImageView imageViewAND = new ImageView(new Image("CompuertaAnd.png"));
        imageViewAND.setFitHeight(140);
        imageViewAND.setFitWidth(130);
        imageViewAND.setX(60);
        imageViewAND.setY(50);
        imageViewAND.setCursor(Cursor.CLOSED_HAND);
        pane.getChildren().add(imageViewAND);

        //Compuerta OR
        Image compuertaOR = new Image("CompuertaOR.png");
        ImageView imageViewOR = new ImageView(compuertaOR);
        imageViewOR.setFitHeight(160);
        imageViewOR.setFitWidth(130);
        imageViewOR.setX(55);
        imageViewOR.setY(180);
        pane.getChildren().add(imageViewOR);


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



    private void DragAndDrop(ImageView imageLogicGate){

        imageLogicGate.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.setOnDragOver(dragOverRoot);
                pane.setOnDragOver(dragOverPanel);
                pane.setOnDragDropped(dragDropped);

                ImageView imageLogicGate_ = (ImageView) mouseEvent.getSource();
            }
        });






    }




    EventHandler<MouseEvent> MousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
        }
    };


    EventHandler<MouseEvent> circleMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            double SposX = mouseEvent.getSceneX() - posX;
            double SPosY = mouseEvent.getSceneY() - posY;
            double newposx = newPosX + SposX;
            double newposy = newPosY + SPosY;
            if (newposx<200){
                ((ImageView)(mouseEvent.getSource())).setTranslateX(newposx);
                ((ImageView)(mouseEvent.getSource())).setTranslateY(newposy);

            }

        }
    };
}
