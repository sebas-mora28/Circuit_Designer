package ListaEnlazada;

public class Main {


    public static void main(String[] args){

        LinkedList lista = new LinkedList();

        lista.addLast("Sebastian");
        lista.addLast("Mora");
        lista.addLast("Godinez");
        lista.addLast("117790130");

        //lista.showData();
        //int size = lista.size();
        //System.out.println(size);


        LinkedList lista2 = new LinkedList();
        lista2.addFirst("Sebastian");
        lista2.addFirst("Mora");
        lista2.addFirst("Godinez2");
        lista2.addFirst("117790130");

        lista2.showData();


        lista2.remove(2);
        System.out.println("-----------------------------------------");
        lista2.showData();




    }
}
