package ListaEnlazada;

/**
 * This class create the Nodes
 * @author Sebastian
 */

public class Nodo {

    private Nodo next;
    private Object object;

    /**
     * Constructor
     * @param object
     */

    public Nodo(Object object) {
        this.object= object;
    }


    /**
     * This method sets the next node
     * @param nodo
     */
    public void setNext(Nodo nodo){
        next = nodo;
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
