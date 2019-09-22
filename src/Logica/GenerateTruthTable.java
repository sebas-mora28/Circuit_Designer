package Logica;


import Compuertas.Compuerta;
import LinkedList.LinkedList;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Clase que genera la tabla de verdad del circuito diseñado
 */
public class GenerateTruthTable {
    private LinkedList<Compuerta> compuertaLinkedList = new LinkedList<Compuerta>();
    private LinkedList<Compuerta> inputsLinkedList = new LinkedList<Compuerta>();
    private LinkedList<Boolean[]> totalOfCombinations = new LinkedList<Boolean[]>();
    private Compuerta outputLogicGate;
    private int numberOfInputs =0;
    private int numberOfOutputs = 0;
    private boolean[] InputsValues;
    private Stage stage;
    private Pane root;
    private Scene scene;
    private TableView<ObservableList<String>> tableView;
    private String values = new String();



    public GenerateTruthTable(final LinkedList<Compuerta> compuertaLinkedList) throws Exception{
        table(compuertaLinkedList);
        start();
        generateTable();

    }


    private void start() throws Exception {


        root = new Pane();

        tableView = new TableView();
        root.getChildren().add(tableView);

        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.show();



    }


    private void table(final LinkedList<Compuerta> compuertaLinkedList) {
        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            this.compuertaLinkedList.add(compuerta);
        }
        inputs();


    }
    private void inputs() {
        for (int i = 0; i <= this.compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = this.compuertaLinkedList.getElement(i);
            if (!compuerta.input1Connected) { numberOfInputs += 1; }
            if (!compuerta.input2Connected) { numberOfInputs += 1; }
            if (!compuerta.input1Connected || !compuerta.input2Connected) { inputsLinkedList.add(compuerta); }
            if(!compuerta.outputConnected){this.outputLogicGate = compuerta;}
        }
        combinacion();

    }

    private void combinacion() {
        int inputs = (int) Math.pow(2, numberOfInputs);
        InputsValues = new boolean[numberOfInputs];
        for (int i=0; i < inputs; i++) {
            boolean[] values = new boolean[numberOfInputs];
            if (i == 0) {
                for (int k = 0; k <= InputsValues.length - 1; k++) {
                    InputsValues[k] = false;

                }
            } else {
                int index = 0;
                int temp = i;
                while (temp > 0) {
                    if (temp % 2 == 1) {
                        InputsValues[index] = true;
                        index++;
                    } else if (temp % 2 == 0) {
                        InputsValues[index] = false;
                        index++;
                    }
                    temp = temp / 2;
                }
                totalOfCombinations.add(InputsValues);
            }
        }
        for(int i=0; i<= totalOfCombinations.size()-1; i++){
            }
    }


    public void asignar(){
        int index = 0;
        for(int i=0; i<= this.inputsLinkedList.size()-1; i++){
            Compuerta compuerta = this.inputsLinkedList.getElement(i);
            if(!compuerta.input1Connected){
                compuerta.inputs.add(InputsValues[index]);

            }
            if(!compuerta.input2Connected){
                compuerta.inputs.add(InputsValues[index]);
            }
            operate();
        }
    }

    public void operate(){
        System.out.println("Tamano de la lita compuerta" + compuertaLinkedList.size());
        for(int i=0; i<= compuertaLinkedList.size()-1; i++){
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            compuerta.operar();
            System.out.println("Entra" + compuerta.output.value);
        }
    }

    public void generateTable() {
        TableColumn inputsColumn = new TableColumn<>("Inputs");

        for (int i = 0; i <= numberOfInputs - 1; i++) {
            TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>("Input" + i);
            inputsColumn.getColumns().add(newColumn);
        }
        this.tableView.getColumns().add(inputsColumn);

        TableColumn outputsColumn = new TableColumn("Output");
        this.tableView.getColumns().add(outputsColumn);
    }

}

