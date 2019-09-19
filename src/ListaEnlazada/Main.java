package ListaEnlazada;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.List;

public class Main {


    public static void main(String[] args){

        LinkedList<String> lista = new LinkedList<>();

        lista.add("Compuerta1");
        lista.add("Compuerta2");
        lista.add("Compuerta3");

        lista.remove(0);

        lista.showData();




    }
}
