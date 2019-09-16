package Logica;

import Compuertas.Compuerta;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


public class DragAndDrop {
    public static double posX, posY, newTranslationX, newTranslationY;
    private static double newPosX, newPosY, translationX, translationY;

    /**
     * Este método controla los diferentes EventHandler para el funcionamoento del DragAndDrop
     * @param gridPane
     * @param group
     */

    public static void SetDragAndDrop(Pane gridPane, Group group) {

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

            if(logicGate.outputConnected && (logicGate.input1Connected || logicGate.input2Connected)){
                updatePosition(group, mouseEvent.getSceneX(), mouseEvent.getSceneY());
            }
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
        private static boolean VerifyCoordsInUse(Pane gridPane, Group newlogicGateGroup){
            for (Node nodo : gridPane.getChildren()) {
                if (!newlogicGateGroup.equals(nodo) && newlogicGateGroup.getBoundsInParent().intersects(nodo.getBoundsInParent()) && !nodo.getId().equals("Linea")) {
                    System.out.println("Entra");
                    return true;
                }
            }
            return false;
    }


    public static  void updatePosition(Group group, double posX, double posY){
            Compuerta compuerta = (Compuerta)group.getUserData();
            for(Node nodo : group.getChildren()){
                if(nodo.getId().equals("Salida") || nodo.getId().equals("Entrada1") && nodo.getId().equals("Entrada2")){

                    for(int i=0; i<= compuerta.lines.size()-1; i++){
                        Line line = compuerta.lines.getElement(i);
                        if(compuerta.outputConnected){
                            line.setEndX(posX);
                            line.setEndY(posY);
                        }
                        if(compuerta.input1Connected){
                            line.setStartX(posX);
                            line.setStartY(posY);
                        }
                        if(compuerta.input2Connected){
                            line.setStartX(posX);
                            line.setStartY(posY);
                        }
                    }
                }

            }
    }
}
