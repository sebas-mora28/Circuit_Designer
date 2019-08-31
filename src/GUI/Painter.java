package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Painter {
    private ImageView image;
    public static GridPane gridPane;
    public static Rectangle rectangle;

    public Painter(){}

    public static void paintRec(double x, double y, GridPane gridPane){
        rectangle = new Rectangle(10,10,10,10);
        rectangle.setStroke(Color.rgb(0,0,0));
        //rectangle.setX(x);
        //rectangle.setY(y);
        gridPane.getChildren().add(rectangle);


    }


    public static void translateRec(double x, double y){
        rectangle.setTranslateX(x);
        rectangle.setTranslateY(y);
    }

}
