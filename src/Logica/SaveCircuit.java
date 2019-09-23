package Logica;

import Compuertas.Compuerta;
import LinkedList.LinkedList;
import javafx.scene.layout.Pane;

public class SaveCircuit {

    private LinkedList<Compuerta> circuitSaved = new LinkedList<Compuerta>();


    public SaveCircuit(LinkedList<Compuerta> circuit, Pane pane){
        this.circuitSaved = circuit;
    }




    public void cloneCircuit(){

    }
}
