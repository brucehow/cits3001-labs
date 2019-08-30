import java.util.Arrays;

/**
 * A class that implements the fractional and discrete Knapsack algorithms
 * by implementing the Knapsack interface.
 * @author Bruce How (22242664)
 */

public class KnapsackImp implements Knapsack {

    /**
     * Private subclass which stores each Item and their respective 
     * densities (value/weight). This class implements the Comparable
     * interface to compare each Item's density while keeping tracking
     * of their indexes within another array.
     */
    private class Item implements Comparable<Item> {
        private int index;
        private double density;

        public Item(int index, double density) {
            this.index = index;
            this.density = density;
        }

        @Override
        public int compareTo(Item item) {
            return Double.compare(this.density, item.density);
        }
    }

    @Override
    public int fractionalKnapsack(int[] weights, int[] values, int capacity) {
        int length = values.length;
        Item densities[] = new Item[length];

        for (int i = 0; i < length; i++) {
            densities[i] = new Item(i, (double) values[i] / (double) weights[i]);
        }
        // Sort by density using the comparable method compareTo
        Arrays.sort(densities);

        double totalWeight = 0.0;
        double totalValue = 0.0;

        for (int i = length-1; i >= 0; i--) {
            int j = densities[i].index;
            
            // Check if item can fit as a whole
            if (weights[j] + totalWeight <= capacity) {
                totalWeight += weights[j];
                totalValue += values[j];
            } else { // Fit a fraction of the item
                totalValue += densities[i].density * ((double) capacity - totalWeight);
                break;
            }
        }
        return (int) totalValue;
    }

    @Override
    public int discreteKnapsack(int[] weights, int[] values, int capacity) {
        int table[][] = new int[values.length+1][capacity+1];
        
        // Construct table[][] in a bottom-up manner
        for (int i = 0; i < values.length + 1; i++) {
            for (int j = 0; j < capacity+1; j++) {
                if (i == 0 || j == 0) {
                    table[i][j] = 0;
                } else if (weights[i-1] <= j) {
                    table[i][j] = Math.max(values[i-1] + table[i-1][j-weights[i-1]], table[i-1][j]);
                } else {
                    table[i][j] = table[i-1][j];
                }
            }
        }
        return table[values.length][capacity];
    }
}