package Logica;


import Compuertas.Compuerta;
import LinkedList.LinkedList;
import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que genera la tabla de verdad del circuito diseñado
 */
public class GenerateTruthTable {
    private LinkedList<Compuerta> compuertaLinkedList = new LinkedList<Compuerta>();
    private LinkedList<Compuerta> inputsLinkedList = new LinkedList<Compuerta>();
    private Compuerta outputLogicGate;
    private int combinations;
    private int numberOfInputs = 0;
    private Stage stage;
    private Pane root;
    private Scene scene;
    private TableView<ObservableList<String>> tableView;
    private ScrollPane scrollPane;
    private String values = new String();
    private static int indexPrev = 0;


    public GenerateTruthTable(final LinkedList<Compuerta> compuertaLinkedList) throws Exception {
        start();
        table(compuertaLinkedList);




    }


    private void start() throws Exception {


        root = new Pane();

        tableView = new TableView();
        tableView.setMaxSize(500,500);
        tableView.setMinSize(300,300);
        scrollPane = new ScrollPane(tableView);
        scrollPane.setMinSize(300,300);
        root.getChildren().addAll(scrollPane);

        scene = new Scene(root);
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tabla de verdad");
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
            if (!compuerta.input1Connected) {
                numberOfInputs += 1;
            }
            if (!compuerta.input2Connected) {
                numberOfInputs += 1;
            }
            if (!compuerta.input1Connected || !compuerta.input2Connected) {
                inputsLinkedList.add(compuerta);
            }
            if (!compuerta.outputConnected) {
                this.outputLogicGate = compuerta;
            }
        }
        combinacion();

    }

    private void combinacion() {
        combinations = (int) Math.pow(2, numberOfInputs);
        for (int i = 0; i < combinations; i++) {
            ArrayList<Boolean> InputsValues = new ArrayList<>();
            if (i == 0) {
                for (int k = 0; k <= numberOfInputs - 1; k++) {
                    InputsValues.add(false);
                    values += "0,";

                }
            } else {
                int temp = i;
                while (temp != 0) {
                    if (temp % 2 == 1) {
                        InputsValues.add(true);
                        values += "1,";
                    } else if (temp % 2 == 0) {
                        InputsValues.add(false);
                        values += "0,";
                    }
                    temp = temp / 2;
                }
            }
            if (InputsValues.size() < numberOfInputs) {
                while (InputsValues.size() < numberOfInputs) {
                    InputsValues.add(false);
                    values += "0,";
                }
            }
            asignar(InputsValues);
        }
        generateTable();
    }

    public void asignar(final ArrayList<Boolean> InputsValues) {
        int index = 0;
        for (int i = 0; i <= this.inputsLinkedList.size() - 1; i++) {
            Compuerta compuerta = this.inputsLinkedList.getElement(i);
            if (!compuerta.input1Connected) {
                compuerta.inputs.add(InputsValues.get(index));
                index += 1;

            }
            if (!compuerta.input2Connected) {
                compuerta.inputs.add(InputsValues.get(index));
                index += 1;
            }
        }
        operate();
        if(outputLogicGate.output.value){
            values += "1,";
        }else{
            values += "0,";
        }
    }

    public void operate() {
        System.out.println("Tamano de la lista compuerta" + compuertaLinkedList.size());
        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            compuerta.operar();
            System.out.println("El valor de la salida de la compuerta " + i + " es " + compuerta.output.value);
        }
        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            compuerta.inputs.removeAll();
        }
    }

    public void generateTable() {
        TableColumn inputsColumn = new TableColumn("Inputs");
        inputsColumn.setMinWidth(tableView.getMaxWidth()/2);
        TableColumn<ObservableList<String>, String> outputColumn = new TableColumn("Outputs");
        outputColumn.setMinWidth(tableView.getMaxWidth()/2);
        outputColumn.setCellValueFactory(values -> new ReadOnlyObjectWrapper<>(values.getValue().get(numberOfInputs-1)));



        for (int i = 0; i < numberOfInputs; i++) {
            final int index = i;
            String name = "int" + i;
            TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>(name);
            newColumn.setCellValueFactory(values -> new ReadOnlyObjectWrapper<>(values.getValue().get(index)));

            inputsColumn.getColumns().add(newColumn);
        }
        tableView.getColumns().addAll(inputsColumn, outputColumn);

        String[] newValues = values.split(",");
        for(int i=0; i< combinations; i++){
            ArrayList<String> valuesToAdd = getValues(newValues, i + indexPrev);
            System.out.println(valuesToAdd);
            tableView.getItems().add(FXCollections.observableArrayList(valuesToAdd));
            indexPrev += numberOfInputs;
        }


    }

    public ArrayList<String> getValues(String[] newValues, int indice) {
        ArrayList<String> valuesToAdd = new ArrayList<>();
        int limite = indice + numberOfInputs;
        for(int i=indice; i<= limite; i++) {
            valuesToAdd.add(newValues[i]);
        }
        return valuesToAdd;
    }
}

