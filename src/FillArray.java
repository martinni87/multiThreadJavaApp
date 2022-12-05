public class FillArray extends Thread{
    private MyArray array;
    private String name;

    public FillArray(MyArray array, String name){
        this.array = array;
        this.name = name;
    }

    public void run (){
        while(!array.workDone()){
            array.fill(name);
        }
    }
}
