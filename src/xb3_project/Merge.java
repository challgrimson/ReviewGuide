package xb3_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;

import xb3_project.Product;

public class Merge {
	
	public static void main(String[] args) throws IOException {
        
        //Get the main Product ID from the user
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a product ID: ");
        String ID = reader.next();
		
        //change this and implement searching here
		Product[] arr = new Product[3];
		
		Gson gson = new Gson();
		BufferedReader br = null;
		
		br = new BufferedReader(new FileReader("data.json"));
		String review;
		int i = 0;
		
		while((review = br.readLine()) != null) {
			Product product = gson.fromJson(review, Product.class);

			if (product != null) {
				String productID = product.getAsin();
				
				
				arr[i] = new Product(productID, date, rating);
				i++;
			}
		}

		br.close();
		
        //Output a graph here instead of this sorted array
		System.out.println("UNSORTED: \n");
		//System.out.println(Arrays.toString(arr));
		for(int j = 0; j<arr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Date: %s, Rating: %.1f\n", arr[j].getAsin(), arr[j].getMonth(arr[j].getReviewTime()), arr[j].getOverall());
		}
		
		
		Merge.sortMerge(arr, 3);
		System.out.println("\nSORTED: \n");
		for(int j = 0; j<arr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Time: %s, Rating: %.1f\n", arr[j].getAsin(), arr[j].getReviewTime(), arr[j].getOverall());
		}
        
        //Graphing algorithm starts here to check the connection betweeen the two IDs.
        System.out.println("Enter another product ID to check the connection between the two: ");
		String ID2 = reader.next();
        
        //Pranav, we need to read the two things from the second json file. Asin and also_bought array.
        
        
        //Graphing algorithm here to check the connection between the two.
		
	}
	
	
	/**
	 * bottom up iterative merge sort for Product ADT
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param n - the size of the input array
	 */
	
	//Code largly inspired from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	public static void sortMerge( Product[] x, int n ) {
		Product[] extraS = new Product[x.length];
		for (int size = 1; size < n; size *=2)
			for (int low = 0; low < n-size; low += size *2)
				mergeEm(x,extraS,low,low+size-1,Math.min(low + (size * 2)-1, n-1));
	}
	
	
	/**
	 * Merge sort function that merges two different arrays
	 * @param x - the input array containing jobs that need to be sorted.
	 * @param extraS - extra working space for the merges
	 * @param low  - the low point of the first merge array
	 * @param mid - the first value of the second merge array
	 * @param high - the end of the second merge array
	 */
	
	//Code largly inspired from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
	private static void mergeEm(Product[] x,Product[] extraS, int low, int mid, int high) {
		int i = low, j = mid+1;
		
		for (int k = low; k <= high;k++)
			extraS[k] = x[k];
		
		for (int k = low; k <= high;k++)
			if (i > mid)  
				x[k] = extraS[j++];
			else if(j > high)
				x[k] = extraS[i++];
			else if(extraS[j].compareTo(extraS[i]) == -1) 
				x[k] = extraS[j++];
			else 
				x[k] = extraS[i++];
					
	}
}
