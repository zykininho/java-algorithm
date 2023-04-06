import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {5, 3, 6, 2, 10};
        System.out.println("Initial array: " + Arrays.toString(array));
        System.out.println("Sorted array: " + Arrays.toString(sort(array)));
    }

    public static int[] sort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[minIndex])
                    minIndex = j;
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
