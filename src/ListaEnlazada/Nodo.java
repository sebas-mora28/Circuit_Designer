package ListaEnlazada;

/**
 * Esta clase crea los Nodo para la lista enlazada
 * @author Sebastian
 */

public class Nodo {

    private Nodo next;
    private Object object;


    /**
     * Constructor
     * @param obj
     * @param nodo
     */

    public Nodo(Object obj,Nodo nodo){
        this.object = obj;
        this.next = nodo;
    }


    /**
     * Este método establece el siguiente nodo de la lista
     * @param nodo
     */

    public void setNext(Nodo nodo){
        this.next = nodo;
    }


    /**
     * Este método obtiene el siguiente nodo de la lista
     *
     */

    public Nodo getNext(){
        return next;
    }


    /**
     * Este método devuelve el objeto encontrado en el nodo
     * @return Object
     */
    public Object get(){
        return object;
    }
}