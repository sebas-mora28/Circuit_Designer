package ListaEnlazada;


import java.util.Iterator;

/**
 * Se crea la lista enlazada
 * @author Sebastian
 */

public class LinkedList<T> implements Iterator<T> {
    private Nodo<T> head;
    private int length;

    /**
     * Constructor
     */

    public LinkedList() {
        this.head = null;
        this.length = 0;
    }

    /**
     * Este método verifica si la lista se encuentra vacía
     *
     * @return True en caso de que se encuentre vacía, false en caso contrario
     */

    public boolean isEmpty() {

        if (head == null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Método que agrega un nodo a la lista, se comporta igual que addLast
     * @param object
     */

    public void add(Object object){
        addLast(object);
    }


    /**
     * Método que agrega un nodo a la primera posición de la lista
     *
     * @param object Object added
     */

    public void addFirst(Object object) {

        if (head == null) {
            head = new Nodo(object);
        } else {
            Nodo temp = head;
            Nodo newNodo = new Nodo(object);
            newNodo.next = temp;
            head = newNodo;
        }
        length++;
    }


    /**
     * Este método agega un nodo a la última posición de la lista
     * @param object
     */

    public void addLast(Object object){
        if(head==null){
            head = new Nodo(object);
        }else{
            Nodo nodo = head;
            while(nodo.next != null){
                nodo = nodo.next;
            }
            nodo.next  = new Nodo(object);
        }
        length++;
    }

    /**
     * Este método retorna el tamaño de la lista
     *
     * @return lenth
     */
    public int size() {
        return length;
    }


    /**
     * Este método obtiene el objeto que se encuentra en la posición especificada
     *
     * @param index índice
     * @return El objeto de la posición
     */
    public T getElement(int index) {
        int cont = 0;
        Nodo nodo = head;
        while (cont < index) {
            nodo = nodo.next;
            cont++;
        }
        return (T) nodo.value;
    }


    /**
     * Este método remueve el nodo de la posición especificada
     */
    public void remove(int index){
        int i=0;
        Nodo current = head;
        while(i < index-1) {
            current = current.next;
            i++;
        }
        current.next = current.next.next;
     }
    /**
     * Este método muestra toda los elementos de la lista
     */
    public void showData(){
        Nodo nodo = head;
        while (nodo != null){
            System.out.println(nodo.value);
            nodo = nodo.next;
        }
    }

    public T getLast(){
        Nodo nodo = head;
        while(nodo.next != null){
            nodo = nodo.next;
        }
        return (T) nodo.value;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}