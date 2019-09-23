package Logica;


import Compuertas.Compuerta;
import LinkedList.LinkedList;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

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

    /**
     * Construtor de la clase
     * @param compuertaLinkedList Lista enlazada del circuito
     * @throws Exception
     * @author Sebastián Mora Godínez
     */

    public GenerateTruthTable(final LinkedList<Compuerta> compuertaLinkedList) throws Exception {
        start();
        newCircuitList(compuertaLinkedList);

    }


    /**
     * Método que ejecuta la parte gráfica de la pantalla
     * @throws Exception
     */
    private void start() throws Exception {


        root = new Pane();

        tableView = new TableView();
        tableView.setMinSize(500,500);
        //scrollPane = new ScrollPane(tableView);
        //scrollPane.setMinSize(300,300);

        scene = new Scene(tableView);
        stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tabla de verdad");
        stage.setScene(scene);
        stage.setOnCloseRequest(click -> stage.close());
        stage.show();

    }

    /**
     * Método que recibe la lista enlazada del circuito y la ingresa en otra para manejo interno de la  clase
     * @param compuertaLinkedList Lista enlazada del circuito
     */
    private void newCircuitList(final LinkedList<Compuerta> compuertaLinkedList) {
        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            this.compuertaLinkedList.add(compuerta);
        }
        verifyInputs();
    }


    /**
     * Método que verifica cuantas entradas disponibles tiene el circuito y guarda la salida en una variable
     */
    private void verifyInputs() {
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
                // Se guarda la compuerta de salida para obtener el valor de esta. 
                this.outputLogicGate = compuerta;
            }
        }
        combinations();
    }


    /**
     * Método que calcula todas las combinaciones posibles del circuito para elaborar la tabla de verdad
     */

    private void combinations() {
        combinations = (int) Math.pow(2, numberOfInputs); //Se obtiene el total de combinaciones del circuito 
        for (int i = 0; i < combinations; i++) {
            ArrayList<Boolean> InputsValues = new ArrayList<>();
            if (i == 0) {
                // En caso de que todas las entradas sean 0, se rellena el array con false
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
                // En caso de que el tamano del array sea menor que el número de entradas se rellena con falsa
                while (InputsValues.size() < numberOfInputs) {
                    InputsValues.add(false);
                    values += "0,";
                }
            }
            assignInputs(InputsValues);
        }
        generateTable();
    }

    /**
     * Método que asigna a las entradas los valores  de las combinaciones a las entradas de la entradas disponibles
     * @param InputsValues
     */

    private void assignInputs(final ArrayList<Boolean> InputsValues) {
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

    /**
     * Método que opera cada una de las compuertas con cada una de las diferentes combinaciones para obtener el resultado
     * de la salida de esa combinación
     */

    private void operate() {
        outputLogicGate.operar();

        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            compuerta.inputs.removeAll();
        }
    }

    /**
     * Método que genera el tableView con los datos correspondientes.
     */

    private void generateTable() {
        TableColumn inputsColumn = new TableColumn("Inputs");
        inputsColumn.setMinWidth(tableView.getMaxWidth()/2);
        TableColumn<ObservableList<String>, String> outputColumn = new TableColumn("Outputs");
        outputColumn.setMinWidth(tableView.getMaxWidth()/2);
        outputColumn.setCellValueFactory(values -> new ReadOnlyObjectWrapper<>(values.getValue().get(numberOfInputs)));



        for (int i = 0; i < numberOfInputs; i++) {
            final int index = i;
            String name = "int" + i;
            TableColumn<ObservableList<String>, String> newColumn = new TableColumn<>(name);
            newColumn.setCellValueFactory(values -> new ReadOnlyObjectWrapper<>(values.getValue().get(index)));

            inputsColumn.getColumns().add(newColumn);
        }
        tableView.getColumns().addAll(inputsColumn, outputColumn);

        String[] newValues = values.split(",");
        System.out.println("El NUMERO DE COMBINACIONES ES " + combinations);
        for(int i=0; i< combinations; i++){
            ArrayList<String> valuesToAdd = getValues(newValues, i + indexPrev);
            System.out.println(valuesToAdd);
            tableView.getItems().add(FXCollections.observableArrayList(valuesToAdd));
            indexPrev += numberOfInputs;
        }


        outputLogicGate = null;
        compuertaLinkedList.removeAll();
        inputsLinkedList.removeAll();
        indexPrev=0;
    }


    private ArrayList<String> getValues(String[] newValues, int indice) {
        ArrayList<String> valuesToAdd = new ArrayList<>();
        int limite = indice + numberOfInputs;
        for(int i=indice; i<= limite; i++) {
            valuesToAdd.add(newValues[i]);
        }
        return valuesToAdd;
    }
}

