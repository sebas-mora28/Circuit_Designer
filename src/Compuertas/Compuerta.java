package Compuertas;

import GUI.PaintLine;
import GUI.Painter;
import LinkedList.LinkedList;
import LinkedList.Nodo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Paint;

public class Compuerta {
    public Group logicGateGroup;

    public LinkedList<Compuerta> outputs = new LinkedList<Compuerta>();
    public LinkedList inputs = new LinkedList<>();
    public LinkedList<PaintLine> listLines = new LinkedList<PaintLine>();

    public Nodo<Boolean> output = new Nodo<Boolean>(null);
    public Nodo<Boolean> input1 = new Nodo<Boolean>(null);
    public Nodo<Boolean> input2 = new Nodo<Boolean>(null);

    public DoubleProperty lineOutputPosX = new SimpleDoubleProperty();
    public DoubleProperty lineOutputPosY = new SimpleDoubleProperty();
    public DoubleProperty lineInput1PosX = new SimpleDoubleProperty();
    public DoubleProperty lineInput1PosY = new SimpleDoubleProperty();
    public DoubleProperty lineInput2PosX = new SimpleDoubleProperty();
    public DoubleProperty lineInput2PosY = new SimpleDoubleProperty();


    public double posX, posY;

    public boolean outputConnected = false, input1Connected = false, input2Connected = false;

    public void operar(){}




    public void menu(){
        ContextMenu contextMenu = new ContextMenu();

        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(actionEvent -> {
            //deleteLinesConnection();
            ///deteleLogicGateFromGUI();
            //deleteLogicGateFromLinkedListAndConnetions();
            Painter.updateEnumeration();


        });
                contextMenu.getItems().add(delete);
                contextMenu.show(logicGateGroup, posX, posY);
    }




    /*

    public void deleteLogicGateFromLinkedListAndConnetions(){
        for(int i=0; i<=LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            if(logicGateGroup.getUserData().equals(compuerta)){continue; }
            for(int j = 0; j<= compuerta.outputs.size()-1; i++){
                if(logicGateGroup.getUserData().equals(compuerta.outputs.getElement(j))){
                    compuerta.outputs.remove(j);
                    System.out.println("Entra");
                    compuerta.input1Connected = false;
                    break; }}
            for(int k=0; k<=compuerta.outputs2.size()-1; i++){
                if(logicGateGroup.getUserData().equals(compuerta.outputs2.getElement(k))){
                    compuerta.outputs.remove(k);
                    System.out.println("ENTRA 2 ");
                    compuerta.input2Connected = false;
                    break; }}}

        for(int i = 0; i<= LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            if(compuerta.logicGateGroup.getUserData().equals(compuerta)){
                LogicGatesCreator.LogicGatesList.remove(i);
                break;
            }
        }

    }

    public void deleteLinesConnection(){
        Pane pane = ((Pane)logicGateGroup.getParent());
        Compuerta compuerta = (Compuerta)logicGateGroup.getUserData();
        for(int i=0; i<= compuerta.listLines.size()-1; i++){
            PaintLine paintLine = (PaintLine) compuerta.listLines.getElement(i);
            paintLine.removeLines();

        }

    }

    public void deteleLogicGateFromGUI(){
        for(int i=0; i <= ((Pane)logicGateGroup.getParent()).getChildren().size()-1; i++){
            if(logicGateGroup.equals((((Pane) logicGateGroup.getParent()).getChildren().get(i)))){
                ((Pane) logicGateGroup.getParent()).getChildren().remove(i);
                break;
            }
        }
    }

     */





}
