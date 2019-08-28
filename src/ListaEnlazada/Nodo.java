package ListaEnlazada;

/**
 * This class create the Nodes
 * @author Sebastian
 */

public class Nodo {

    private Nodo next;
    private Nodo prev;
    private Object object;


    /**
     * This method creates a new Node
     * @param obj
     * @param nodo
     */

    public Nodo(Object obj,Nodo nodo){
        this.object = obj;
        this.next = nodo;
    }


    /**
     * This method sets the next node
     * @param nodo
     */

    public void setNext(Nodo nodo){
        this.prev = next;
        this.next = nodo;
    }


    /**
     * This method gets the next node
     *
     */

    public Nodo getNext(){
        return next;
    }


    /**
     * This method returns the objects contained in the node
     * @return Node the next node
     */
    public Object get(){
        return object;
    }
}