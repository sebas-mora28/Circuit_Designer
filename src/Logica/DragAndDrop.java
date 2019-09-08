package Logica;

import Compuertas.Compuerta;
import Compuertas.CompuertaAND;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class DragAndDrop {
    private static GridPane gridPane;
    public static double posX, posY, newTranslationX, newTranslationY;
    private static double newPosX, newPosY, translationX, translationY;


    public static void SetDragAndDrop(GridPane gridPane, Group group) {

        setGridPane(gridPane);

        group.setOnMousePressed(mouseEvent -> {
            Group logicGateGroup = (Group)(mouseEvent.getSource());
            Compuerta logicGate = (Compuerta)(logicGateGroup.getUserData());
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = logicGate.logicGateGroup.getTranslateX();
            translationY = logicGate.logicGateGroup.getTranslateY();

            logicGate.posX = posX;
            logicGate.posY = posY;
        });

        group.setOnMouseDragged(mouseEvent -> {
            Group logicGateGroup = (Group)(mouseEvent.getSource());
            Compuerta logicGate = (Compuerta)logicGateGroup.getUserData();
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            newTranslationX = translationX + newPosX;
            newTranslationY = translationY + newPosY;


            logicGate.logicGateGroup.setTranslateX(newTranslationX);
            logicGate.logicGateGroup.setTranslateY(newTranslationY);
            logicGate.posX = newTranslationX;
            logicGate.posY = newTranslationY;

        });


        group.setOnMouseReleased(mouseEvent -> {
            if (VerifyCoordsInUse(gridPane, (Group) (mouseEvent.getSource()))) {
                Group logicGateGroup = (Group) (mouseEvent.getSource());
                Compuerta logicGate = (Compuerta)logicGateGroup.getUserData();
                logicGateGroup.setTranslateX(posX);
                logicGateGroup.setTranslateY(posY);
                logicGate.posX = posX;
                logicGate.posY = posY;

            }
        });

    }

        /**
         * Verifica si la nueva posición de la compuerta está siendo ocupada por otra para evitar que se sobrepongan
         * @param gridPane Se utilizada para comparar todos las compuertas que se encuentran en la pantalla
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
