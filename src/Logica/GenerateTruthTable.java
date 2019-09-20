package Logica;


import Compuertas.Compuerta;
import ListaEnlazada.LinkedList;

/**
 * Clase que genera la tabla de verdad del circuito diseñado
 */
public class GenerateTruthTable {
    private LinkedList<Compuerta> compuertaLinkedList = new LinkedList<>();
    private LinkedList<Compuerta> inputsLinkedList = new LinkedList<>();
    private int numberOfInputs =0;


    public GenerateTruthTable(final LinkedList<Compuerta> compuertaLinkedList){
        table(compuertaLinkedList);

    }


    public void table(final LinkedList<Compuerta> compuertaLinkedList) {
        for (int i = 0; i <= compuertaLinkedList.size() - 1; i++) {
            Compuerta compuerta = compuertaLinkedList.getElement(i);
            this.compuertaLinkedList.add(compuerta);
        }
        inputs();


    }


    public void inputs() {
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
        }
    }
}
