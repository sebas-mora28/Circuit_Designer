package Logica;

import Compuertas.Compuerta;

public class Grid {
    private static final int size = 10;

    private final int cols;
    private final int rows;


    public Grid(final double width, final double height, Compuerta compuerta){
        rows = (int) width/size;
        cols = (int) height/size;


    }


}
