/**
 * Clase EmptyArray: define atributos del hilo de vaciado y posee el método run para el hilo de ejecución.
 * @author Martín Antonio Córdoba Getar
 * @version 1.0
 * @see MyArray
 * @see Thread
 * @see Runnable
 */

public class EmptyArray extends Thread {
    //Atributos
    private MyArray array;
    private String name;
    private int count; //Conteo de elementos eliminados por el hilo de ejecución

    /**
     * Constructor
     * @param array: objeto MyArray. Actua de árbitro entre hilos de ejecución
     * @param name: String que sirve de identificador del hilo
     */
    public EmptyArray(MyArray array, String name){
        this.array = array;
        this.name = name;
        this.count = 0;
    }

    /**
     * Método void, no devuelve resultado. Se entra en bucle while con la condición "mientras no esté terminado
     * el trabajo". Invoca al método empty para vaciar el array, pasa por parámetro el name identificativo.
     * Invoca el método getCount para recibir información en cada ciclo de los elementos eliminados por el hilo
     * y añade dicho valor al existente en count.
     */
    public void run (){
        while(!array.workDone()){
            array.empty(name);
            count += array.getCount();
        }
        //Dormimos el hilo 2 segundos antes de enviar resultados al finalizar la totalidad de ciclos.
        try{
            sleep(2000);
            System.out.println("\nResultados " + name + ":\nEliminó: " + count + " elementos.");
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
