/**
 * Clase FillArray: define atributos del hilo de llenado y posee el método run para el hilo de ejecución.
 * @author Martín Antonio Córdoba Getar
 * @version 1.0
 * @see MyArray
 * @see Thread
 * @see Runnable
 */

public class FillArray extends Thread{
    //Atributos
    private MyArray array;
    private String name;

    /**
     * Constructor
     * @param array: objeto MyArray. Actua de árbitro entre hilos de ejecución
     * @param name: String que sirve de identificador del hilo
     */
    public FillArray(MyArray array, String name){
        this.array = array;
        this.name = name;
    }

    /**
     * Método void, no devuelve resultado. Se entra en bucle while con la condición "mientras no esté terminado
     * el trabajo". Invoca al método fill para rellenar el array, pasa por parámetro el name identificativo.
     */
    public void run (){
        while(!array.workDone()){
            array.fill(name);
        }
    }
}
