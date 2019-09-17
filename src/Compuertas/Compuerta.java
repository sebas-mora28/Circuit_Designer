package Compuertas;

import ListaEnlazada.LinkedList;
import ListaEnlazada.Nodo;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public abstract class Compuerta {
    public Group logicGateGroup;
    public LinkedList<Line> lines = new LinkedList<>();

    public LinkedList<Compuerta> outputs = new LinkedList<>();
    public LinkedList inputs = new LinkedList<>();


    public LinkedList<Compuerta> input1ToInput1 = new LinkedList<>();
    public LinkedList<Compuerta> input1ToInput2 = new LinkedList<>();
    public LinkedList<Compuerta> input2ToInput2 = new LinkedList<>();
    public LinkedList<Compuerta> input2ToInput1 = new LinkedList<>();

    public Nodo<Boolean> output = new Nodo<>(true);
    public Nodo<Boolean> input1 = new Nodo<>(null);
    public Nodo<Boolean> input2 = new Nodo<>(null);
    public double posX, posY;
    public boolean outputConnected = false, input1Connected = false, input2Connected = false;

    public Label labelOutput= new Label();
    public Label labelInput1 = new Label();
    public Label labelInput2 = new Label();

    public abstract void operar();

}
