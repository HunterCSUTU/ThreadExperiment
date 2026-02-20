public class testing {

    static int[] arr = {10,9,8,7,2,45,2,3,4,8,20};

    public static void main(String[] args) {
        //bubbleSort(3, 8);
        insertionSort(1, 4);
        for (int num : arr) {
            System.out.print(num + ", ");
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

}







