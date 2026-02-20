import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class sorting {

    static int[] arr = new Random().ints(100000, 0, 100000).toArray(); //used method chaining
    //possible race condition
    static long[] TATs = {-1, -1};


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch startLatch = new CountDownLatch(1); //1 just means first time I call startLatch.countDown(), the threads will run.

        //DEFINING THREADS
        Thread t1 = new Thread ( () -> {
            try {

                startLatch.await();
                //sort first half of array
                System.out.println("Hello from t1");
                long t1Start = System.nanoTime();
                System.out.println("t1 start: "+t1Start);
                bubbleSort(0, arr.length/2);
                long t1Finish = System.nanoTime();
                System.out.println("t1 end: "+t1Finish);
                long t1TAT = t1Finish - t1Start;
                logTAT(t1TAT, 0);

            }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        );

        Thread t2 = new Thread( new Runnable() {
            @Override
            public void run() {
                try {

                    startLatch.await();
                    System.out.println("Hello from t2");
                    long t2Start = System.nanoTime();
                    System.out.println("t2 start: "+t2Start);
                    insertionSort(arr.length/2, arr.length);
                    long t2Finish = System.nanoTime();
                    System.out.println("t2 end: "+t2Finish);
                    long t2TAT = t2Finish - t2Start;
                    logTAT(t2TAT, 1);

                }
                catch (InterruptedException e) {e.printStackTrace();}
            }
        }
        );

        //RUNNING THREADS
        t1.start();
        t2.start();
        startLatch.countDown();

        t1.join();
        t2.join();
        for (int i = 0; i < TATs.length; i++) {
            int threadNum = i+1;
            System.out.println("thread "+threadNum+" took "+TATs[i]/1e9+" seconds");
        }

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
        for (int i = start+1; i < stop; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= start && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }

    public static void logTAT(long TAT, int index) {
        TATs[index] = TAT;
    }

}
