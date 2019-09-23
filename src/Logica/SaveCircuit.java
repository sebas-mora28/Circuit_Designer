package Logica;

import Compuertas.Compuerta;
import LinkedList.LinkedList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SaveCircuit implements Cloneable{

    private LinkedList<Compuerta> circuitSaved = new LinkedList<Compuerta>();


    public SaveCircuit(LinkedList<Compuerta> circuit, Pane pane){
        this.circuitSaved = circuit;
    }


}
