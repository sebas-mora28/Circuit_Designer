package Compuertas;

import GUI.PaintLine;

import LinkedList.LinkedList;
import LinkedList.Nodo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.io.Serializable;


/**
 * Clase que hereda sus atributos a las clases de las demás compuertas lógicas.
 */

public class Compuerta {
    public Group logicGateGroup;



    public LinkedList<Compuerta> outputs = new LinkedList<Compuerta>();
    public LinkedList inputs = new LinkedList<>();
    public LinkedList<PaintLine> listLines = new LinkedList<PaintLine>();

    public Nodo<Boolean> output = new Nodo<Boolean>(null);
    public Nodo<Boolean> input1 = new Nodo<Boolean>(null);
    public Nodo<Boolean> input2 = new Nodo<Boolean>(null);


    public DoubleProperty lineOutputPosX = new SimpleDoubleProperty(95);
    public DoubleProperty lineOutputPosY = new SimpleDoubleProperty(90);
    public DoubleProperty lineInput1PosX = new SimpleDoubleProperty();
    public DoubleProperty lineInput1PosY = new SimpleDoubleProperty();
    public DoubleProperty lineInput2PosX = new SimpleDoubleProperty();
    public DoubleProperty lineInput2PosY = new SimpleDoubleProperty();

    public double posX, posY;

    public boolean outputConnected = false, input1Connected = false, input2Connected = false;

    public void operar(){}





































}
