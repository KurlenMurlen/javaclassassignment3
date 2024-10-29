import java.util.Random;

public class SortingAndBinarySearch {
    public static void main(String[] args) {
        // array size and number of searches
        int arraySize = 10000;
        int[] table = generateRandomArray(arraySize);

        // measure sorting time
        long sortStartTime = System.nanoTime();
        mergeSort(table, 0, table.length - 1);
        long sortEndTime = System.nanoTime();
        long sortTime = sortEndTime - sortStartTime;

        System.out.println("sorting time (ns): " + sortTime);

        // simulate search using random values from the array
        Random random = new Random();
        int[] elementsToSearch = new int[10];
        for (int i = 0; i < elementsToSearch.length; i++) {
            elementsToSearch[i] = table[random.nextInt(arraySize)];
        }

        // measure binary search time
        long totalSearchTime = 0;
        for (int element : elementsToSearch) {
            long searchStartTime = System.nanoTime();
            int position = binarySearch(table, element);
            long searchEndTime = System.nanoTime();
            long searchTime = searchEndTime - searchStartTime;
            totalSearchTime += searchTime;

            System.out.println("element " + element + " found at position: " + position + ", search time (ns): " + searchTime);
        }

        System.out.println("total binary search time (ns): " + totalSearchTime);
        System.out.println("total time (sorting + binary search): " + (sortTime + totalSearchTime) + " ns");
    }

    // generates a random array
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000); // random values between 0 and 99999
        }
        return array;
    }

    // merge sort algorithm
    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);
            merge(array, start, middle, end);
        }
    }

    public static void merge(int[] array, int start, int middle, int end) {
        int leftSize = middle - start + 1;
        int rightSize = end - middle;

        int[] left = new int[leftSize];
        int[] right = new int[rightSize];

        for (int i = 0; i < leftSize; i++) left[i] = array[start + i];
        for (int j = 0; j < rightSize; j++) right[j] = array[middle + 1 + j];

        int i = 0, j = 0, k = start;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) array[k++] = left[i++];
        while (j < rightSize) array[k++] = right[j++];
    }

    // binary search algorithm
    public static int binarySearch(int[] array, int key) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (array[middle] == key) return middle;
            else if (array[middle] < key) start = middle + 1;
            else end = middle - 1;
        }
        return -1; // element not found
    }
}
