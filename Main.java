import java.util.Arrays;
import java.util.Random;

/**
 * Main class for executing the lab code
 * Written for CITS3001 Algorithms, Agents and Artificial Intelligence
 *
 * @author Bruce How
 * @github brucehow
 */

public class Main {
    public static void main(String[] args) {
        lab0(10, 10000);
    }

    private static void lab0(int arrSize, int maxNumber) {
        print("Lab 0");
        Random rand = new Random();
        int[] array = new int[arrSize];
        for (int i = 0; i < array.length; i ++) {
            array[i] = rand.nextInt(maxNumber);
        }
        print("Initial Array\n" + Arrays.toString(array) + "\n");
        
        print("\nInsertion Sort");
        Timer.startTimer();
        print(Timer.getTime());
        print(Lab0.insertionSort(array));

        print("\nMerge Sort");
        Timer.startTimer();
        print(Timer.getTime());
        print(Lab0.mergeSort(array));

        print("\nRadix Sort");
        Timer.startTimer();
        print(Timer.getTime());
        print(Lab0.radixSort(array));
    }

    public static void print(String arg) {
        System.out.println(arg);
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}