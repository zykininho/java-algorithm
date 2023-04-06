public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9};
        System.out.printf("index of item in list is %d\n", search(array, 3));
        System.out.printf("index of item in list is %d\n", search(array, -1));
    }

    public static int search(int[] array, int item) {
        int index = 0;
        int maxIndex = array.length - 1;
        while (index <= maxIndex) {
            int mid = (index + maxIndex) / 2;
            int guess = array[mid];
            if (guess == item) {
                return mid;
            } else if (guess > item) {
                maxIndex = mid - 1;
            } else {
                index = mid + 1;
            }
        }
        return -1;
    }
}
