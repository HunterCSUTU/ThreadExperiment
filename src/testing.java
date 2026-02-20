public class testing {

    static int[] arr = {2,5,1,8,3,45,1,2,7};

    public static void main(String[] args) {
        bubbleSort(3, 8);
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
            int temp = arr[i];

        }
    }

}







