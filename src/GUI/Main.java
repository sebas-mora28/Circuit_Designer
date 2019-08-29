package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;



public class Main extends Application {
    Pane pane;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {


        // Cuadricula
        pane = new Pane();
        TableView tabla = new TableView();
        pane.setPrefSize(1200,900);
        ScrollPane scrollPane = new ScrollPane(pane);

        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(1200, 900);
        scrollPane.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 200, 200, 200));



        // Paleta de compuertas logicas

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(300,900);


        ScrollPane logicGatesScroller = new ScrollPane(anchorPane);
        logicGatesScroller.setLayoutX(1200);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setPrefSize(300,900);
        logicGatesScroller.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 245, 245, 245));






        AnchorPane root = new AnchorPane(scrollPane,logicGatesScroller);



        Scene scene = new Scene(root, 1500, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    private void handleDrop(DragEvent event){
        try{
            List<File> files = event.getDragboard().getFiles();
            Image image = new Image(new FileInputStream(files.get(0)));




        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}