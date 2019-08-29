package GUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;



public class Main extends Application {
    GridPane gridPane;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {


        // Cuadricula
        gridPane = new GridPane();
        gridPane.setPrefSize(1200,900);
        ScrollPane scrollPane = new ScrollPane(gridPane);

        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setPrefSize(1200, 900);
        scrollPane.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 200, 200, 200));


        Label label = new Label("Sebastian");
        gridPane.add(label,1,1);



        // Paleta de compuertas logicas

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(300,900);


        ScrollPane logicGatesScroller = new ScrollPane(anchorPane);
        logicGatesScroller.setLayoutX(1200);
        logicGatesScroller.setLayoutY(0);
        logicGatesScroller.setPrefSize(300,900);
        logicGatesScroller.setStyle(String.format("-fx-background: rgb(%d, %d, %d);" +
                "-fx-background-color: -fx-background;", 245, 245, 245));


        anchorPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });

        anchorPane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                label.setText(db.getString());
            }
        });




        AnchorPane root = new AnchorPane(scrollPane,logicGatesScroller);



        Scene scene = new Scene(root, 1500, 900);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(windowEvent -> System.exit(0));
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