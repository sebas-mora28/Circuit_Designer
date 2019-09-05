package Logica;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class DragAndDrop {
    private static GridPane gridPane;
    private static double posX, posY, newPosX, newPosY, translationX, translationY, newTranslationX, newTranslationY;


    public static void SetDragAndDrop(GridPane gridPane, Group group) {

        setGridPane(gridPane);

        group.setOnMousePressed(mouseEvent -> {
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = ((Group) (mouseEvent.getSource())).getTranslateX();
            translationY = ((Group) (mouseEvent.getSource())).getTranslateY();
        });

        group.setOnMouseDragged(mouseEvent -> {
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            newTranslationX = translationX + newPosX;
            newTranslationY = translationY + newPosY;
            ((Group) (mouseEvent.getSource())).setTranslateX(newTranslationX);
            ((Group) (mouseEvent.getSource())).setTranslateY(newTranslationY);

        });


        group.setOnMouseReleased(mouseEvent -> {
            if (VerifyCoordsInUse(gridPane, (Group) (mouseEvent.getSource()))) {
                ((Group) (mouseEvent.getSource())).setTranslateX(posX);
                ((Group) (mouseEvent.getSource())).setTranslateY(posY);
            }
        });

    }

        /**
         * Verifica si la nueva posición de la compuerta está siendo ocupada por otra para evitar que se sobrepongan
         * @param gridPane Se utilizada para comparar todos las compuertas que se encuntran en la pantalla
         * @param newlogicGateGroup Compuerta que se cambió a una nueva posición
         * @return true en caso de que la posición este siendo ocupada, false en caso contrario
         */
        private static boolean VerifyCoordsInUse(GridPane gridPane, Group newlogicGateGroup){
            for (Node nodo : gridPane.getChildren()) {
                if (!newlogicGateGroup.equals(nodo) && newlogicGateGroup.getBoundsInParent().intersects(nodo.getBoundsInParent())) {
                    return true;
                }
            }
            return false;
    }

    private static void setGridPane(GridPane gridPane) {
        DragAndDrop.gridPane = gridPane;
    }
}
