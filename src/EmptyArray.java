public class EmptyArray extends Thread {
    private MyArray array;
    private String name;
    private int count;

    public EmptyArray(MyArray array, String name){
        this.array = array;
        this.name = name;
        this.count = 0;
    }

    public void run (){
        while(!array.workDone()){
            array.empty(name);
            count += array.getCount();
        }
        try{
            sleep(2000);
            System.out.println("\nResultados " + name + ":\nEliminó: " + count + " elementos.");
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

    }
}
