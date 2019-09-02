import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        // Nothing to test
    }


    /////////////////////////////////////////////////
    // Lab code testing below. Do not touch below. //
    /////////////////////////////////////////////////
    
    private static void lab1(String text, String pattern) {
        print("Lab 1");
        print("Text: " + text + "\nPattern: " + pattern);
        Timer.startTimer();
        print("\nNaive Method\n" + Lab1.naive(text, pattern).toString());  
        print(Timer.getTime());
        Timer.startTimer();
        print("\nRabin Karp Algorithm\n" + Lab1.rabinKarp(text, pattern).toString()); 
        print(Timer.getTime());
        print("");
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
        print(Lab0.insertionSort(array));
        print(Timer.getTime());

        print("\nMerge Sort");
        Timer.startTimer();
        print(Lab0.mergeSort(array));
        print(Timer.getTime());

        print("\nRadix Sort");
        Timer.startTimer();
        print(Lab0.radixSort(array));
        print(Timer.getTime());

        print("");
    }

    public static void lab2() {
        KnapsackImp ks = new KnapsackImp();
        int values[] = {60, 100, 120};
        int weights[] = {10, 20, 30};
        int capacity = 50;
        System.out.println(ks.fractionalKnapsack(weights, values, capacity));
    }

    public static void lab4() throws IOException {
        String[] dict = new String[58109];
        FileInputStream fstream = new FileInputStream("corncob_caps.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        int i = 0;
        while ((strLine = br.readLine()) != null)   {
            dict[i] = strLine;
            i++;
        }
        fstream.close();


        WordChessImp wc = new WordChessImp();
        String[] res = wc.findPath(dict, "CENT", "DIME");
        for (int j = 0; j < res.length; j++) {
            System.out.println(res[j]);
        }
    }

    public static void print(String arg) {
        System.out.println(arg);
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

}