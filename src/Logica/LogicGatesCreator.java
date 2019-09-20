package Logica;


import Compuertas.*;
import GUI.Painter;
import ListaEnlazada.LinkedList;
import javafx.scene.layout.Pane;

public class LogicGatesCreator {
    public static LinkedList<Compuerta> LogicGatesList = new LinkedList<>();

    /**
     * Constructor
     */
    public LogicGatesCreator() {}


    /**
     * Método que crea la compuerta lógica cada vez que se presiona el botón
     * @param gridPane
     * @param logicGateType
     */
    public void createLogicGates(Pane gridPane, LogicGateType logicGateType) {
        if (logicGateType == LogicGateType.AND) {
            Compuerta logicGateAND = new CompuertaAND(gridPane);
            LogicGatesList.add(logicGateAND);
            logicGateAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NAND) {
            Compuerta logicGateNAND = new CompuertaNAND(gridPane);
            LogicGatesList.add(logicGateNAND);
            logicGateNAND.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == logicGateType.OR) {
            Compuerta logicGateOR = new CompuertaOR(gridPane);
            LogicGatesList.add(logicGateOR);
            logicGateOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NORD) {
            Compuerta logicGateNORD = new CompuertaNORD(gridPane);
            LogicGatesList.add(logicGateNORD);
            logicGateNORD.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.NOT) {
            Compuerta logicGateNOT = new CompuertaNOT(gridPane);
            LogicGatesList.add(logicGateNOT);
            logicGateNOT.logicGateGroup.setUserData(LogicGatesList.getLast());
        } else if (logicGateType == LogicGateType.XOR) {
            Compuerta logicGateXOR = new CompuertaXOR(gridPane);
            LogicGatesList.add(logicGateXOR);
            logicGateXOR.logicGateGroup.setUserData(LogicGatesList.getLast());

        } else {
            Compuerta logicGateXNOR = new CompuertaXNOR(gridPane);
            LogicGatesList.add(logicGateXNOR);
            logicGateXNOR.logicGateGroup.setUserData(LogicGatesList.getLast());
        }
        Painter.updateEnumeration();


    }


    public enum LogicGateType{
        AND,
        NAND,
        NORD,
        NOT,
        OR,
        XOR,
        XNOR;
    }



}
