public class Timer {
    private static long startTime = 0;
    
    public static void startTimer() {
        startTime = System.nanoTime();
    }

    public static String getTime() {
        long endTime = System.nanoTime();
        return "Time taken: " + (endTime - startTime) + "ns "
        + "(" + ((endTime - startTime)/1000) + "μs " + ((endTime - startTime)/1000000) + "ms)";
    }
}