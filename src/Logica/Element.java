package Logica;

import java.util.List;

interface Element {
    List<Element> inputs = null;
    List<Element> outputs = null;

    void operar();

}
