package Logica;

import Compuertas.Compuerta;
import GUI.PaintLine;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class DragAndDrop {
    public static double posX, posY, newTranslationX, newTranslationY;
    private static double newPosX, newPosY, translationX, translationY;



    public static void setStartDragAndDrop(Pane pane, Group group) {
        SetDragAndDrop(pane, group);
    }


    /**
     * Este método controla los diferentes EventHandler para el funcionamoento del DragAndDrop
     *
     * @param gridPane
     * @param group
     */

    private static void SetDragAndDrop(Pane gridPane, Group group) {


        group.setOnMousePressed(mouseEvent -> {
            Group logicGateGroup = (Group) (mouseEvent.getSource());
            Compuerta logicGate = (Compuerta) (logicGateGroup.getUserData());
            posX = mouseEvent.getSceneX();
            posY = mouseEvent.getSceneY();
            translationX = logicGate.logicGateGroup.getTranslateX();
            translationY = logicGate.logicGateGroup.getTranslateY();
            logicGate.posX = posX;
            logicGate.posY = posY;
        });

        group.setOnMouseDragged(mouseEvent -> {
            Group logicGateGroup = (Group) (mouseEvent.getSource());
            Compuerta logicGate = (Compuerta) logicGateGroup.getUserData();
            newPosX = mouseEvent.getSceneX() - posX;
            newPosY = mouseEvent.getSceneY() - posY;
            newTranslationX = translationX + newPosX;
            newTranslationY = translationY + newPosY;
            logicGate.logicGateGroup.setTranslateX(newTranslationX);
            logicGate.logicGateGroup.setTranslateY(newTranslationY);
            logicGate.posX = newTranslationX;
            logicGate.posY = newTranslationY;
            setLogicGateProperties(logicGate, newTranslationX, newTranslationY);


            updateLines(logicGate);

        });


        group.setOnMouseReleased(mouseEvent -> {
            if (VerifyCoordsInUse(gridPane, (Group) (mouseEvent.getSource()))) {
                Group logicGateGroup = (Group) (mouseEvent.getSource());
                Compuerta logicGate = (Compuerta) logicGateGroup.getUserData();
                logicGateGroup.setTranslateX(posX);
                logicGateGroup.setTranslateY(posY);
                logicGate.posX = posX;
                logicGate.posY = posY;
                setLogicGateProperties(logicGate, posX, posY);
                updateLines(logicGate);

            }
        });
    }

    /**
     * Verifica si la nueva posición de la compuerta está siendo ocupada por otra para evitar que se sobrepongan
     *
     * @param gridPane Se utilizada para comparar todos las compuertas que se encuentran en la pantalla
     * @param newlogicGateGroup Compuerta que se cambió a una nueva posición
     * @return true en caso de que la posición este siendo ocupada, false en caso contrario
     */
    private static boolean VerifyCoordsInUse(Pane gridPane, Group newlogicGateGroup) {
        for (Node nodo : gridPane.getChildren()) {
            if (!newlogicGateGroup.equals(nodo) && newlogicGateGroup.getBoundsInParent().intersects(nodo.getBoundsInParent()) && !nodo.getId().equals("Linea")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Esté metodo actualiza los valores de las posiciones en X y Y para la entrada y salida
     * @param compuerta
     * @param newPosX
     * @param newPosY
     */

    private static void setLogicGateProperties(Compuerta compuerta, double newPosX, double newPosY){
        compuerta.lineOutputPosX.setValue(newPosX + 90);
        compuerta.lineOutputPosY.setValue(newPosY + 95);

        compuerta.lineInput1PosX.setValue(newPosX +25);
        compuerta.lineInput1PosY.setValue(newPosY +80);

        compuerta.lineInput2PosX.setValue(newPosX +25);
        compuerta.lineInput2PosY.setValue(newPosY +110);

    }


    /**
     * Método que actualiza las posiciones de las líneas que se encuentran conectadas a la compuerta en el momento de
     * realizar el drag and drop
     * @param logicGate compuerta que contiene la lista de líneas a actualizar.
     */
    private static void updateLines(Compuerta logicGate){
        for(int i=0; i<= logicGate.listLines.size()-1; i++) {
            PaintLine paintLine = logicGate.listLines.getElement(i);
            paintLine.updateLine();
        }

    }

}

