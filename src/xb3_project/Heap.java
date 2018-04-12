package xb3_project;

public class Heap {
	/**
	 * heap sort for Product ADT
	 * @param x - the input array containing product that need to be sorted.
	 * @param n - the size of the input array
	 */
	
	public static void sortHeap ( Product[] x, int n ) {
        for (int k = n/2; k >= 1; k--)//uses the sink to form the heap from each item in the array
            sink(x, k, n);
        while (n > 1) {
        	swap(x, 1, n--);
            sink(x, 1, n);
        }
	}
	
	
	/**
	 * sink function for heap sort
	 * @param x - the input array containing products that need to be sorted.
	 * @param k - the value being sunk
	 * @param n - the size of the input array
	 */
	
	//Code largely inspired from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
    private static void sink(Product[] x, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && (x[j-1].compareTo(x[j]) == -1)) j++;
            if (!(x[k-1].compareTo(x[j-1]) == -1)) break;
            swap(x, k, j);
            k = j;
        }
    }
    
    /**
     * swap function for heap sort
     * @param x - The input array containing the values being exchanged
     * @param i - The first value being exhanged
     * @param j - The second value being exchanged
     */
    private static void swap(Product[] x, int i, int j) {
    	Product swapV = x[i-1];
        x[i-1] = x[j-1];
        x[j-1] = swapV;
    }
	
}
