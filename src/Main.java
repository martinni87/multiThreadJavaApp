/**
 * Clase Main: Método main. Se declara un objeto array personalizado, un hilo de llenado y 3 hilos de vaciado
 * @author Martín Antonio Córdoba Getar
 * @version 1.0
 * @see MyArray
 * @see FillArray
 * @see EmptyArray
 */

public class Main {
    public static void main(String[] args) {
        /**
         * Contenido del método main
         *
         * @param array: arreglo para llenar y vaciar.
         * @param filler: hilo de que ejecuta el llenado del arreglo
         * @param emptier1: hilo 1 que ejecuta el vaciado del arreglo
         * @param emptier2: hilo 2 que ejecuta el vaciado del arreglo
         * @param emptier3: hilo 3 que ejecuta el vaciado del arreglo
         *
         * Los 3 EmptyArray comparten el objeto MyArray como árbitro para dar paso a uno y otro hilo
         */
        MyArray array = new MyArray(10,20,10);
        FillArray filler = new FillArray(array,"Thread 0");
        EmptyArray emptier1 = new EmptyArray(array,"Thread 1");
        EmptyArray emptier2 = new EmptyArray(array,"Thread 2");
        EmptyArray emptier3 = new EmptyArray(array,"Thread 3");

        //Ejecutamos los 4 hilos.
        emptier1.start();
        emptier2.start();
        emptier3.start();
        filler.start();
    }
}
