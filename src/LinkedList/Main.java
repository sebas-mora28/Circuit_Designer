package LinkedList;

public class Main {


    public static void main(String[] args){

        LinkedList<String> lista = new LinkedList<String>();

        lista.add("Compuerta1");
        lista.add("Compuerta2");
        lista.add("Compuerta3");

        lista.remove(0);
        convertir(1);


        lista.showData();
        int num = 52;
        System.out.println(Math.pow(2, num));

    }

    public static void convertir(int num) {
        int res = 0;
        int exp =0;
        while (num != 0) {
            if (num % 2 == 1) {
                res += 1*10^exp;
                exp+=1;
            } else if (num % 2 == 0) {
                res += 0*10^exp;
                exp+=1;
            }
            num = num / 2;
        }
        System.out.println(res);
    }
}
