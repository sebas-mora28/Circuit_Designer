package GUI;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;



public class Main extends Application {
    GridPane gridPane;
    Circle circle;
    double posX, posY, newPosX, newPosY;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {


        // Cuadricula
        gridPane = new GridPane();
        gridPane.setPrefSize(1200, 900);
        ScrollPane scrollPane = new ScrollPane(gridPane);

        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(1200, 900);
        scrollPane.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 200, 200, 200));


        // Paleta de compuertas logicas

        HBox paleta = new HBox();
        paleta.setPrefSize(280, 900);
        paleta.setMaxWidth(280);
        paleta.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

        Circle circle = new Circle(50, Color.RED);
        circle.setLayoutX(150);
        circle.setLayoutY(200);
        circle.setCursor(Cursor.MOVE);
        circle.setOnMousePressed(circleMousePressedEventHandler);
        circle.setOnMouseDragged(circleMouseDraggedEventHandler);
        paleta.getChildren().add(circle);


        ScrollPane logicGatesScroller = new ScrollPane(paleta);
        logicGatesScroller.setLayoutX(1200);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setPrefSize(300,830);

        logicGatesScroller.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 245, 245, 245));





        //
        AnchorPane root = new AnchorPane(scrollPane, logicGatesScroller);



        Scene scene = new Scene(root, 1500, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
        primaryStage.show();

    }



    EventHandler<MouseEvent> circleMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            newPosX = ((Circle)(mouseEvent.getSource())).getTranslateX();
            newPosY =  ((Circle)(mouseEvent.getSource())).getTranslateY();
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
                ((Circle)(mouseEvent.getSource())).setTranslateX(newposx);
                ((Circle)(mouseEvent.getSource())).setTranslateY(newposy);

            }
            System.out.print("Eje x: " + newposx + " " + "Eje y: "+ newposx);

        }
    };
}
