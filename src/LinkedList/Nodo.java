package LinkedList;

/**
 * Esta clase crea los Nodo para la lista enlazada
 * @author Sebastian
 */

public class Nodo<T> {

    public Nodo next;
    public T value;


    /**
     * Constructor
     */

    public Nodo(T object) {
        this.value = object;
        this.next = null;
    }

}