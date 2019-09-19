package Logica;

import Compuertas.Compuerta;
import GUI.Painter;
import javafx.scene.input.MouseEvent;

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
        /*
        if(!conectingOutput){
            if(selectingInput){
                System.out.println("Pasa la primera compuerta");
                SelectGate(mouseEvent);
            }
            if(selectingNewGate){
                System.out.println("Pasa la segunda compuerta");
                ConnectingInput(mouseEvent);
            }

        }

         */

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
            logicGateTo = LogicGatesCreator.LogicGatesList.getElement(i);
            if (!(mouseEvent.getX() + 1 == currentLogicGate.posX && mouseEvent.getY() + 1 == currentLogicGate.posY)) {
                if (mouseEvent.getX() + 1 == logicGateTo.posX && mouseEvent.getY() + 1 == logicGateTo.posY) {
                    if (input1) {
                        if(!logicGateTo.input1Connected) {
                            logicGateTo.outputs.add(currentLogicGate);
                            //logicGateTo.input1 = currentLogicGate.output;
                            System.out.println("Entrada 1: " + logicGateTo.input1.value);
                            input1 = false;
                            logicGateTo.input1Connected = true;
                            currentLogicGate.outputConnected = true;
                            break;
                        }else{
                            Painter.removeLines();
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
                            break;
                        }else{
                            Painter.removeLines();
                            System.out.println("Esta entrada ya se encuentra seleccionada");
                        }
                    }else{
                        System.out.println("No se seleccionó ninguna compuerta");
                    }
                }
            } else {
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

    /**
     * Este método busca la entrada que se quiere conectar en caso de que el origen sea otra salida
     * @param mouseEvent
     */

    private static void ConnectingInput(MouseEvent mouseEvent){
        for(int i=0; i<=LogicGatesCreator.LogicGatesList.size()-1; i++){
            logicGateTo = LogicGatesCreator.LogicGatesList.getElement(i);
            if(!(mouseEvent.getX()+1 == currentLogicGate.posX && mouseEvent.getY()+1 == currentLogicGate.posY)){
                if(mouseEvent.getX() +1 == logicGateTo.posX && mouseEvent.getY()+1 == logicGateTo.posY){
                    if(input1){
                        if(input1Selected){
                            System.out.println("Se conecta la entrada1 del currentGate a la entrada 1 de la nueva compuerta");
                            //logicGateTo.input1ToInput1.add(currentLogicGate);
                            currentLogicGate.input1ToInput1.add(logicGateTo);
                            logicGateTo.input1Connected = true;
                            input1 =false;
                        }
                        if(input2Selected){
                            System.out.println("Input 1: Se conecta desde la entrada2 del currentGate");
                            //logicGateTo.input2ToInput2.add(currentLogicGate);
                            currentLogicGate.input1ToInput2.add(logicGateTo);
                            logicGateTo.input2Connected = true;
                            input1 = false;
                        }
                    }
                    if(input2){
                        if(input1Selected){
                            System.out.println("Input 2: Se conecta desde la entrada1 del currentGate");
                            currentLogicGate.input2ToInput1.add(logicGateTo);
                            logicGateTo.input2Connected = true;
                            input2 = false;
                        }
                        if(input2Selected){
                            System.out.println("Input 2: Se conecta desde la entrada2 del currentGate");
                            currentLogicGate.input2ToInput2.add(logicGateTo);
                            logicGateTo.input2Connected = true;
                            input2 = false;
                        }
                    }
                }
            }else{
                System.out.println("Es la misma compuerta");
            }
        }
        input1Selected = false;
        input2Selected = false;
        conectingInput = false;
        selectingInput = false;
        selectingNewGate = false;
        selected = false;
        Painter.updateEnumeration();
    }
}
