//Importamos java.util.ArrayList para poder utilizarlo
import java.util.ArrayList;

/**
 * Clase MyArray: define atributos del hilo de vaciado y posee el método run para el hilo de ejecución.
 * @author Martín Antonio Córdoba Getar
 * @version 1.0
 * @see Thread
 */

public class MyArray {
    //Atributos
    private ArrayList<Integer> array; //El array que deberá llenarse y vaciarse
    private int maxSize; //Tamaño máximo del array
    private int count; // Cuenta de elementos eliminados por cada vez
    private int round; // Registro de ronda o ciclo en el que nos encontramos
    private int maxIterations; //Máximo número de ciclos.
    private boolean isErasing; //Flag. Nos indica si estamos borrando elementos o no.
    private int msTimer; //Tiempo en ms. de ejecución de instrucción sleep.

    /**
     * Constructor
     * @param maxSize: tamaño máximo del array a definir por el usuario
     * @param iterations: número máximo de ciclos de llenado y vaciado
     * @param msTimer: tiempo en milisegundos de ejecución para adición y eliminación de cada elemento por ciclo.
     */
    public MyArray (int maxSize, int iterations, int msTimer){
        this.array = new ArrayList<>();
        this.maxSize = maxSize;
        this.count = 0;
        this.round = 1;
        this.maxIterations = iterations;
        this.isErasing = false;
        this.msTimer = msTimer;
    }

    /**
     * Método getCount
     * @return integer count. Conteo de elementos eliminados.
     */
    public int getCount(){
        return count;
    }

    /**
     * Método workDone
     * @return boolean. True si se han realizado todos los ciclos, false en caso contrario.
     */
    public boolean workDone(){
        return (round >= maxIterations);
    }

    /**
     * Método synchronized fill para llenado de array
     * @param name: identificación del hilo de llenado.
     */
    public synchronized void fill (String name){
        //Entramos si cumplimos la condición "si no se está borrando".
        if (!isErasing){
            System.out.println("Ronda " + round + ". Añadir.");
            //Bucle para recorrer el array y llenarlo de valores
            for (int i = 0; i < maxSize; i++){
                //Llenamos el array de valores aleatorios entre 0 y 100
                int valor = (int)(Math.random()*100);
                array.add(valor);
                System.out.println(name + ": Valor añadido: " + valor);
                try{
                    //Ejecutamos un sleep de (msTimer) milisegundos
                    Thread.sleep(msTimer);
                }
                //Capturamos error en caso de fallo
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //Fuera del bucle el llenado se ha finalizado. Cambiamos valor de flag
            isErasing = true;
            //Notificamos a todos para abrir cerrojos y permitir vaciados
            notifyAll();
        }
    }

    /**
     * Método synchronized fill para llenado de array
     * @param name: identificación del hilo de vaciado en ejecución
     */
    public synchronized void empty (String name){
        try{
            /*
            Activamos cerrojo. Hacemos esperar a los hilos de vaciado
            Cuando se haya terminado el vaciado se dara notificación y
            se abrirán los cerrojos para continuar la ejecución.
             */
            wait();
        }
        //Capturamos error en caso de fallo
        catch (InterruptedException e){
            e.printStackTrace();
        }
        //Establecemos el contador de eliminados en 0 al inicio de cada ronda
        count = 0;
        //Entramos si se cumple la condición "si se está borrando"
        if (isErasing){
            System.out.println("Ronda " + round + ". Eliminar.");
            //Entramos al bucle si se cumple la condición "Mientras no esté vacío"
            while(!array.isEmpty()){
                System.out.println(name + ": Valor eliminado: " + array.get(0));
                //Eliminamos el item ubicado en la posición o index 0 en cada iteración.
                array.remove(0);
                //Aumentamos el contador por cada elemento eliminado
                count++;
                try{
                    //Ejecutamos un sleep de (msTimer) milisegundos
                    Thread.sleep(msTimer);
                }
                //Capturamos error en caso de fallo
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            //Fuera del bucle el vaciado se ha finalizado. Cambiamos valor de flag
            isErasing = false;
            //Aumentamos la ronda 1 unidad.
            round++;
        }
    }
}
