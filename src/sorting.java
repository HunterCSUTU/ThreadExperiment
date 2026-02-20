import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class sorting {

    static int[] arr = new Random().ints(45000, 0, 100000).toArray(); //used method chaining
    //possible race condition

    public static void main(String[] args) {

        CountDownLatch startLatch = new CountDownLatch(1); //1 just means first time I call startLatch.countDown(), the threads will run.

        Thread t1 = new Thread ( () -> {
            try {

                startLatch.await();
                //sort first half of array
                System.out.println("Hello from t1");

                bubbleSort(0, arr.length/2);

            }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        );


        Thread t2 = new Thread( new Runnable() {
            @Override
            public void run() {
                try {

                    startLatch.await();
                    insertionSort(arr.length/2, arr.length-1);

                }
                catch (InterruptedException e) {e.printStackTrace();}

                //sort second half of array
            }
        }
        );

    }

    public static void bubbleSort(int start, int stop) {
        int passes = -1;
        for (int i=start; i < stop-1; i++) {
            passes++;
            for (int j=start; j < stop - 1 - passes; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void insertionSort(int start, int stop) {

    }
}
