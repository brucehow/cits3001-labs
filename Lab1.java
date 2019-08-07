import java.util.ArrayList;

public class Lab1 {
    
    /**
     * Performs the naive method for string searching where each character is 
     * checked in every shift. O(m(n-m+1)) at worst
     * @param text The text to search on
     * @param pattern The pattern to search for
     * @return The shifts where the pattern is found if any
     */
    public static ArrayList<Integer> naive(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        if (text.length() < pattern.length()) {
            return result;
        }

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i+j) != pattern.charAt(j)) {
                    break;
                } else if (j == pattern.length() - 1) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    // The number of characters in the input alphabet
    private final static int d = 256;

     // Arbitrary large prime number
    private final static int q = 193;

    /**
     * Performs the Rabin-Karp algorithm for string searching
     * @param text The text to search on 
     * @param pattern The pattern to search for
     * @return the shifts where the pattern is found if any
     */
    public static ArrayList<Integer> rabinKarp(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        if (text.length() < pattern.length()) {
            return result;
        }

        int pHash = 0;
        int tHash = 0;
        int h = 1;

        for (int i = 0; i < pattern.length() - 1; i ++) {
            h = (h * d) % q;
        }

        for (int i = 0; i < pattern.length(); i++) {
            pHash = (d*pHash + pattern.charAt(i)) % q;
            tHash = (d*tHash + text.charAt(i)) % q;
        }

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (pHash == tHash) {
                // Check each character
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i+j) != pattern.charAt(j)) {
                        break;
                    } else if (j == pattern.length() - 1) {
                        result.add(i);
                    }
                }
            }
            // No hash match for current window
            tHash = (d*(tHash - text.charAt(i) * h) + text.charAt(i + pattern.length())) % q;
            if (tHash < 0) tHash = (tHash + q);
        }
        return result;
    }
}