package ListaEnlazada;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.List;

public class Main {


    public static void main(String[] args){

        LinkedList<Integer> lista = new LinkedList<Integer>();

        lista.add("se");
        lista.add("bas");
        lista.add("tian");
        lista.addFirst("Este es el primer elemento");
        lista.addLast("Este es el ultimo elemento");


        System.out.println(lista.getElement(0));
        System.out.println(lista.getElement(1));
        System.out.println(lista.getElement(4));


    }
}
