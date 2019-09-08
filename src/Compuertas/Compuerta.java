package Compuertas;

import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import javafx.scene.Group;

public abstract class Compuerta {
    public Group logicGateGroup;
    public Nodo<Boolean> output = new Nodo<>(null);
    public Nodo<Boolean> input1 = new Nodo<>(null);
    public Nodo<Boolean> input2 = new Nodo<>(null);
    public double posX, posY;

    public abstract void operar();

}
