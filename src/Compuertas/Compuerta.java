package Compuertas;

import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public abstract class Compuerta {
    public Group logicGateGroup;
    public LinkedList<Line> lines = new LinkedList<>();

    public LinkedList<Compuerta> outputs = new LinkedList<>();
    public LinkedList<Compuerta> inputs1 = new LinkedList<>();
    public LinkedList<Compuerta> inputs2 = new LinkedList<>();

    public Nodo<Boolean> output = new Nodo<>(true);
    public Nodo<Boolean> input1 = new Nodo<>(null);
    public Nodo<Boolean> input2 = new Nodo<>(null);
    public double posX, posY;
    public boolean outputConnected = true, input1Connected = true, input2Connected = true;

    public abstract void operar();

}
