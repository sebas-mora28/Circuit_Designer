package ListaEnlazada;

public class Main {


    public static void main(String[] args){

        LinkedList lista = new LinkedList();

        lista.addLast("Sebastian");
        lista.addLast("Mora");
        lista.addLast("Godinez");


        int size = lista.size();
        System.out.println(size);
        System.out.println(lista.getElement(-1));

    }
}
