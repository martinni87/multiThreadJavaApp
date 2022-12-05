public class Main {
    public static void main(String[] args) {
        MyArray array = new MyArray(10,20,10);
        FillArray filler = new FillArray(array,"Thread 0");
        EmptyArray emptier1 = new EmptyArray(array,"Thread 1");
        EmptyArray emptier2 = new EmptyArray(array,"Thread 2");
        EmptyArray emptier3 = new EmptyArray(array,"Thread 3");

        emptier1.start();
        emptier2.start();
        emptier3.start();

        filler.start();
    }
}
