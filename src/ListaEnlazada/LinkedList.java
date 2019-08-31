package ListaEnlazada;






/**
 * Se crea la lista enlazada
 * @author Sebastian
 */

public class LinkedList<T>{
    private Nodo head;
    private Nodo end;
    private int length;

    /**
     * Constructor
     */

    public LinkedList() {
        this.head = null;
        this.end = null;
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
            head = new Nodo(object,null);
            end = head;
        } else {
            Nodo newNodo = new Nodo(object,head);
            end = head;
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
            head = new Nodo(object,null);
        }else{
            Nodo nodo = head;
            while(nodo.getNext() != null){
                nodo = nodo.getNext();
            }
            nodo.setNext(new Nodo(object,null));
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
    public Object getElement(int index) {
        int cont = 0;
        Nodo nodo = head;
        while (cont < index) {
            nodo = nodo.getNext();
            cont++;
        }
        return nodo.get();
    }


    /**
     * Este método remueve el nodo de la posición especificada
     */
    public void remove(int index){
        int i=0;
        Nodo current = head;
        while(current.getNext() != null && i<index){
            if (i==index){
                break;
            }
            current = current.getNext();
            i++;
        }
        current = current.getNext().getNext();
    }
    /**
     * Este método muestra toda los elementos de la lista
     */
    public void showData(){
        Nodo nodo = head;
        while (nodo != null){
            System.out.println(nodo.get());
            nodo = nodo.getNext();
        }
    }
}