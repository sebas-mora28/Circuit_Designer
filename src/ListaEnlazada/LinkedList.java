package ListaEnlazada;


/**
 * This class creates a LinkedList
 * @author Sebastian
 */

public class LinkedList {

    private Nodo head;
    private int length;

    /**
     * Constructor
     */

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    /**
     * Verify whether the list is empty or not
     *
     * @return True in case the list is empty, False in a opposite case
     */

    public boolean isEmpty() {

        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method add a new object to last position
     *
     * @param object Object added
     */

    public void addLast(Object object) {

        if (head == null) {
            head = new Nodo(object);
        } else {
            Nodo temp = head;
            Nodo newNodo = new Nodo(object);
            newNodo.setNext(temp);
            head = newNodo;
        }
        length++;
    }

    /**
     * This method return the length's list
     *
     * @return length
     */
    public int size() {
        return length;
    }


    /**
     * This method gets the object that is found in a especific position
     *
     * @param index
     * @return Object The object that is found in the index position
     */
    public Object getElement(int index) {
        int cont = 0;
        Nodo nodo = head;
        while (cont < index) {
            nodo.getNext();
        }
        return nodo.get();

    }
}