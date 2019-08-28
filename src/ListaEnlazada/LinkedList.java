package ListaEnlazada;


/**
 * This class creates a LinkedList
 * @author Sebastian
 */

public class LinkedList {

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



    public void add(Object object){
        if(head==null){
            head = new Nodo(object,null);
        }
        else{
            Nodo temp = head;
            head.setNext(new Nodo(object,null));
            Nodo newNodo = new Nodo(object,head);
            head = newNodo;

        }
    }

    /**
     * This method add a new object to the first position
     *
     * @param object Object added
     */

    public void addFirst(Object object) {

        if (head == null) {
            head = new Nodo(object,null);
            end = head;
        } else {
            Nodo temp = head;
            Nodo newNodo = new Nodo(object,head);
            end = head;
            head = newNodo;
        }
        length++;
    }


    /**
     * This method inserts the nodes to the last position
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
     * This method returns the length's list
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
            nodo = nodo.getNext();
            cont++;
        }
        return nodo.get();
    }


    /**
     * This method removes a element from a especific position
     */
    public void remove(int index){
        int con = 0;

        while(con < length-1) {
            if (con == index) {
            } else{
            }
            con++;
        }


    }

    /**
     * This method shows the data that is contained in the list
     */
    public void showData(){
        Nodo nodo = head;
        while (nodo != null){
            System.out.println(nodo.get());
            nodo = nodo.getNext();
        }
    }
}