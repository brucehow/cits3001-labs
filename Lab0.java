import java.util.Arrays;

public class Lab0 {

    private static int[] arr; // Used for mergeSort

    /**
     * Perform the insertion sort on the given array
     * @param array array to perform the insertion sort on
     * @return the sorted array
     */
    public static int[] insertionSort(int[] array) {
        arr = array.clone();
        int len = arr.length;
        if (len == 1) return arr;
        for (int i = 1; i < len; i++) {
            int key = arr[i];
            int j = i -1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr;
    }

    /**
     * Perform the merge sort algorithm on the given array
     * @param array array to sort
     * @return the sorted array
     */
    public static int[] mergeSort(int[] array) {
        arr = array.clone();
        merge(0, arr.length-1);
        return arr;
    }

    /**
     * Recursively split the arr array
     * @param l the left bound of the array 
     * @param r the right bound of the array
     */
    private static void merge(int l, int r) {
        if (l < r) {
            int mid = (l + r)/2;
            merge(l, mid);
            merge(mid+1, r);
            mergeSort(l, mid, r);
        }        
    }

    /**
     * Merge the two arrays into one
     * @param l the left bound of the array
     * @param m the middle bound of the array
     * @param r the right bound of the array
     */
    private static void mergeSort(int l, int m, int r) {
        int arr1Size = m - l + 1; 
        int arr2Size = r - m; 
        int[] arr1 = new int[arr1Size];
        int[] arr2 = new int[arr2Size];

        // Copying data into temp arrays
        for (int i = 0; i < arr1Size; i++) {
            arr1[i] = arr[l+i];
        }
        for (int i = 0; i < arr2Size; i++) {
            arr2[i] = arr[m + i + 1];
        }
       
        // Merge the data accordingly
        int first = 0; // Index for the first array
        int second = 0; // Index for the second array
        for (int index = l; index <= r; index++) {
            if (first == arr1Size) {
                arr[index] = arr2[second++];
            } else if (second == arr2Size || arr1[first] < arr2[second]) {
                arr[index] = arr1[first++];
            } else {
                arr[index] = arr2[second++];
            }
        }
    }

    public static int[] radixSort(int[] array) {
        arr = array.clone();
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // Perform countSort starting from the least significant exponent
        for (int exp = 1; max/exp > 0; exp*=10) {
            countSort(exp);
        }
        return arr;
    }
 
    private static void countSort(int exp) {  
        int size = arr.length;  
        int output[] = new int[size]; // output array 
        int count[] = new int[10]; 
        int i;
        Arrays.fill(count, 0); // Initial occurences is 0 for each digit

        for (i = 0; i < size; i++) {
            int digit = (arr[i]/exp)%10;
            count[digit]++; 
        }

        // Calculate the running-sum so c[i] = num elements <= i
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1]; 
        }

        // Build the output array 
        for (i = size - 1; i >= 0; i--) {
            int digit = (arr[i]/exp)%10;
            output[count[digit] - 1] = arr[i]; 
            count[digit]--; 
        } 
  
        // Copy the output array to arr[], so that arr[] now 
        // contains sorted numbers according to curent digit 
        for (i = 0; i < size; i++) {
            arr[i] = output[i]; 
        }
    }
}