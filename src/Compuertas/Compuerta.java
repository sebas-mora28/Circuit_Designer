package Compuertas;

import GUI.Painter;
import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import Logica.LogicGatesCreator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.nio.file.LinkOption;

public class Compuerta {
    public Group logicGateGroup;

    public LinkedList<Compuerta> outputs = new LinkedList<>();
    public LinkedList inputs = new LinkedList<>();


    public LinkedList<Compuerta> input1ToInput1 = new LinkedList<>();
    public LinkedList<Compuerta> input1ToInput2 = new LinkedList<>();
    public LinkedList<Compuerta> input2ToInput2 = new LinkedList<>();
    public LinkedList<Compuerta> input2ToInput1 = new LinkedList<>();

    public Nodo<Boolean> output = new Nodo<>(true);
    public Nodo<Boolean> input1 = new Nodo<>(null);
    public Nodo<Boolean> input2 = new Nodo<>(null);

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
            deleteLinesConnection();
            deleteLogicGateFromLinkedListAndConnetions();
            Painter.updateEnumeration();
            deteleLogicGateFromGUI();

        });
                contextMenu.getItems().add(delete);
                contextMenu.show(logicGateGroup, posX, posY);
    }





    public void deleteLogicGateFromLinkedListAndConnetions(){
        for(int i = 0; i<= LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            if(compuerta.logicGateGroup.getUserData().equals(compuerta)){
                LogicGatesCreator.LogicGatesList.remove(i);
                break;
            }
        }
        for(int i=0; i<=LogicGatesCreator.LogicGatesList.size()-1; i++){
            Compuerta compuerta = LogicGatesCreator.LogicGatesList.getElement(i);
            for(int j=0; j<= compuerta.outputs.size()-1; i++){
                if(logicGateGroup.getUserData().equals(compuerta.outputs.getElement(i))){
                    compuerta.outputs.remove(i);
                }
            }
        }

    }

    public void deleteLinesConnection(){
        Pane pane = ((Pane)logicGateGroup.getParent());
        Compuerta compuerta = (Compuerta)logicGateGroup.getUserData();
        for(int i=0; i<= pane.getChildren().size(); i++){
            Node node = pane.getChildren().get(i);
            if(node.getId().equals("Linea")){
                Line line = (Line)(node);
                if(line.startXProperty().getValue().equals(compuerta.lineOutputPosX.getValue())){
                    System.out.println(node.getId() + " " + "ENTRA");
                    pane.getChildren().remove(i);
                }
                System.out.println(line.endXProperty().getValue() +"[" + compuerta.lineInput1PosX.getValue() + " " + compuerta.lineInput2PosX.getValue() +"]");
                if(line.endXProperty().getValue().equals(compuerta.lineInput1PosX.getValue())){
                    System.out.println(node.getId() + " " + "ENTRA");
                    pane.getChildren().remove(i);
                }
                if(line.endXProperty().getValue().equals(compuerta.lineInput2PosX.getValue())){
                    System.out.println(node.getId() + " " + "ENTRA");
                    pane.getChildren().remove(i);
                }

            }
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





}
