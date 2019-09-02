package GUI;

import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.awt.image.WritableRaster;
import java.time.temporal.ChronoUnit;


/**
 * Clase que crear inserta las imágenes
 */

public class Painter {
    private ImageView image;
    private static  Circle salida, entrada1, entrada2;
    private static  Rectangle rectangleImage;
    public static Line lines;

    public Painter(){ }

    /**
     * Este método se encarga de insertar la imagen en un rectángulo
     * @param image La imágen que se inserta en el rectángulo
     * @return rectangleImage El rectángulo con la imágen
     */

    public static Rectangle insertImage(Image image) {
        rectangleImage = new Rectangle(0, 0, 100, 100);
        rectangleImage.setStroke(Color.rgb(1, 1, 1, 0.0));
        rectangleImage.setFill(new ImagePattern(image));
        rectangleImage.setCursor(Cursor.OPEN_HAND);
        rectangleImage.setX(10);
        rectangleImage.setY(50);
        return rectangleImage;

    }


    public static void crearEntradasSalidas(Group logicGateGroup){
        salida(logicGateGroup);
        entradas(logicGateGroup);

    }


    private static void salida(Group logicGateGroup){
        salida = new Circle(10);
        salida.setOnMouseEntered(mouseEvent -> salida.setCursor(Cursor.CROSSHAIR));
        salida.setLayoutX(105);
        salida.setLayoutY(92);
        salida.setId("Salida");
        //circle.setOpacity(0.0);

        logicGateGroup.getChildren().add(salida);
    }

    private static void entradas(Group logicGateGroup){
        entrada1 = new Circle(10);
        entrada1.setOnMouseClicked(mouseEvent -> entrada1.setCursor(Cursor.CROSSHAIR));
        entrada1.setLayoutX(25);
        entrada1.setLayoutY(110);
        entrada1.setId("Entrada1");
        //entrada1.setOpacity(0.0);


        entrada2 = new Circle(10);
        entrada2.setOnMouseClicked(mouseEvent -> entrada2.setCursor(Cursor.CROSSHAIR));
        entrada2.setLayoutX(25);
        entrada2.setLayoutY(80);
        entrada2.setId("Entrada2");
        //entrada2.setOpacity(0.0);

        logicGateGroup.getChildren().addAll(entrada1, entrada2);
    }


}
