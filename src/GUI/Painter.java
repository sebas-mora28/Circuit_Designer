package GUI;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 * Clase que crear inserta las imágenes
 */

public class Painter {
    private ImageView image;
    public static GridPane gridPane;
    public static Rectangle rectangle;
    public static Rectangle rectangleImage;


    public static Group logicGateGroup;

    public Painter(){ }

    /**
     * Este método se encarga de insertar la imagen en un rectángulo
     * @param image La imágen que se inserta en el rectángulo
     * @return rectangleImage El rectángulo con la imágen
     */

    public static Rectangle insertImage(Image image){
        rectangleImage = new Rectangle(0,0,100,100);
        rectangleImage.setStroke(Color.rgb(1,1,1,0.0));
        rectangleImage.setFill(new ImagePattern(image));
        rectangleImage.setCursor(Cursor.OPEN_HAND);
        rectangleImage.setX(10);
        rectangleImage.setY(50);
        return rectangleImage;
    }

    /**
     * Se encarga de insertar un punto en la compuerta para que funcione como salida o entrada de la misma
     * @param x Posición en x
     * @param y Posición en y
     * @param gridPane Gridpane donde se coloca el rectángulo
     */


    public static void paintRec(double x, double y, GridPane gridPane){
        rectangle = new Rectangle(10,10);
        rectangle.setStroke(Color.rgb(0,0,0));
        rectangle.setCursor(Cursor.CROSSHAIR);
        rectangle.setX(x);
        rectangle.setY(y);
        gridPane.getChildren().add(rectangle);
    }



    /**
     * Método que actualiza la posición del rectangulo de entrada o salida en patalla
     * @param x Posición en x
     * @param y Posición en y
     * */

    public static void translateRec(double x, double y){
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
    }

}
