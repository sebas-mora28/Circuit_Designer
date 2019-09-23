package Logica;

import Compuertas.Compuerta;
import GUI.Painter;
import javafx.scene.input.MouseEvent;

import static GUI.Painter.paintLine;

/**
 * Clase donde se maneja la logica de las conexciones entre compuertas
 */

public class LogicGateConexion {

    public static boolean conectingOutput = false;
    public static boolean selectingOutput = false;
    public static boolean selected = false;
    public static boolean selectingNewGate = false;
    public static boolean conectingInput = false;
    public static boolean selectingInput = false;
    public static boolean input1Selected = false;
    public static boolean input2Selected = false;
    public static boolean input1;
    public static boolean input2;
    public static Compuerta currentLogicGate;
    public static Compuerta logicGateTo;

    /**
     * Controla el estado de la conexión entre las compuertas para llamar los correspondientes métodos
     * @param mouseEvent
     */
    public static void startConnexion(MouseEvent mouseEvent){
        if(conectingOutput) {
            if (selectingOutput) {
                selectGateOutput(mouseEvent);
            }
            if (selectingNewGate) {
                selectInputToConnect(mouseEvent);
            }
        }
    }


    /**
     * Este método busca la compuerta del origen de la conexión
     * @param mouseEvent
     */
    private static void selectGateOutput(MouseEvent mouseEvent) {
        for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
            currentLogicGate = LogicGatesCreator.LogicGatesList.getElement(i);
            if (mouseEvent.getX() + 1 == currentLogicGate.posX && mouseEvent.getY() + 1 == currentLogicGate.posY) {
                System.out.println("Se selecciona la compuerta");
                if(conectingOutput) {
                    selectingOutput = false;
                    break;
                }
                if(!conectingOutput) {
                    selectingInput = false;
                    selected = true;
                    break;
                }
            }
        }
    }

    /**
     * Este método busca la entrada que se quiere conectar en caso de que el origen sea una salida
     * @param mouseEvent
     */
    private static void selectInputToConnect(MouseEvent mouseEvent) {
        for (int i = 0; i <= LogicGatesCreator.LogicGatesList.size() - 1; i++) {
            //logicGateTo = LogicGatesCreator.LogicGatesList.getElement(i);
            System.out.println(mouseEvent.getX() + 1 == logicGateTo.posX && mouseEvent.getY() + 1 == logicGateTo.posY);
            if (!(mouseEvent.getX() + 1 == currentLogicGate.posX && mouseEvent.getY() + 1 == currentLogicGate.posY)) {
                if (mouseEvent.getX() + 1 == logicGateTo.posX && mouseEvent.getY() + 1 == logicGateTo.posY) {
                    if (input1) {
                        if(!logicGateTo.input1Connected) {
                            System.out.println("Entrada 1: " + logicGateTo.input2.value);
                            logicGateTo.outputs.add(currentLogicGate);
                            //logicGateTo.input1 = currentLogicGate.output;
                            input1 = false;
                            logicGateTo.input1Connected = true;
                            currentLogicGate.outputConnected = true;
                            logicGateTo.listLines.add(paintLine);
                            break;
                        }else{
                            paintLine.removeLines();
                            System.out.println("Esta entrada ya se encuentra seleccionada");
                        }
                    }
                    if (input2) {
                        if(!logicGateTo.input2Connected) {
                            logicGateTo.outputs.add(currentLogicGate);
                            //logicGateTo.input2 = currentLogicGate.output;
                            System.out.println("Entrada 2: " + logicGateTo.input2.value);
                            logicGateTo.input2Connected = true;
                            currentLogicGate.outputConnected = true;
                            input2 = false;
                            logicGateTo.listLines.add(paintLine);
                            break;
                        }else{
                            paintLine.removeLines();
                            System.out.println("Esta entrada ya se encuentra seleccionada");
                        }
                    }else{
                        System.out.println("No se seleccionó ninguna compuerta");
                    }
                }
            } else {
                paintLine.removeLines();
                System.out.println("La compuerta es la misma");
                break;
            }
        }
        System.out.println("Sale");
        selectingNewGate = false;
        selectingOutput = false;
        conectingOutput = false;
        Painter.updateEnumeration();
    }

}
