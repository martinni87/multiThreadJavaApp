import java.util.ArrayList;

public class MyArray {
    private ArrayList<Integer> array;
    private int maxSize;
    private int count;
    private int round;
    private int maxIterations;
    private boolean isErasing;
    private int msTimer;

    public MyArray (int maxSize, int iterations, int msTimer){
        this.array = new ArrayList<>();
        this.maxSize = maxSize;
        this.count = 0;
        this.round = 1;
        this.maxIterations = iterations;
        this.isErasing = false;
        this.msTimer = msTimer;
    }

    public int getCount(){
        return count;
    }

    public boolean workDone(){
        return (round >= maxIterations);
    }

    public synchronized void fill (String name){
        if (!isErasing){
            System.out.println("Ronda " + round + ". Añadir.");
            for (int i = 0; i < maxSize; i++){
                int valor = (int)(Math.random()*100);
                array.add(valor);
                System.out.println(name + ": Valor añadido: " + valor);
                try{
                    Thread.sleep(msTimer);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            isErasing = true;
            notifyAll();
        }
    }

    public synchronized void empty (String name){
        try{
            wait();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        count = 0;
        if (isErasing){
            System.out.println("Ronda " + round + ". Eliminar.");
            while(!array.isEmpty()){
                System.out.println(name + ": Valor eliminado: " + array.get(0));
                array.remove(0);
                count++;
                try{
                    Thread.sleep(msTimer);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            isErasing = false;
            round++;
        }

    }
}
