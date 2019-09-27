package Logica;


import Compuertas.*;
import GUI.Painter;
import LinkedList.LinkedList;
import javafx.scene.layout.Pane;


/**
 * Clase que funciona como fachada para comunicar los botones de cada compuerta con la respectiva clase de la compuerta
 */

public class LogicGatesCreator {
    public static LinkedList<Compuerta> LogicGatesList = new LinkedList<Compuerta>();

    /**
     * Constructor
     */
    public LogicGatesCreator() {}


    /**
     * Método que crea la compuerta lógica cada vez que se presiona el botón
     * @param pane Pane donde se agregan la parte gráfica de la compuerta
     * @param logicGateType
     */
    public void createLogicGates(Pane pane, LogicGateType logicGateType) {
        if (logicGateType == LogicGateType.AND) {
            Compuerta logicGateAND = new CompuertaAND(pane);
            LogicGatesList.add(logicGateAND);
            logicGateAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NAND) {
            Compuerta logicGateNAND = new CompuertaNAND(pane);
            LogicGatesList.add(logicGateNAND);
            logicGateNAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == logicGateType.OR) {
            Compuerta logicGateOR = new CompuertaOR(pane);
            LogicGatesList.add(logicGateOR);
            logicGateOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NORD) {
            Compuerta logicGateNORD = new CompuertaNORD(pane);
            LogicGatesList.add(logicGateNORD);
            logicGateNORD.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NOT) {
            Compuerta logicGateNOT = new CompuertaNOT(pane);

            LogicGatesList.add(logicGateNOT);
            logicGateNOT.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.XOR) {            Compuerta logicGateXOR = new CompuertaXOR(pane);
            LogicGatesList.add(logicGateXOR);
            logicGateXOR.logicGateGroup.setUserData(LogicGatesList.getLast());

        } else {
            Compuerta logicGateXNOR = new CompuertaXNOR(pane);
            LogicGatesList.add(logicGateXNOR);
            logicGateXNOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        }
        Painter.updateEnumeration();


    }


    public enum LogicGateType{
        AND,
        NAND,
        OR,
        NORD,
        NOT,
        XOR,
        XNOR;
    }



}
