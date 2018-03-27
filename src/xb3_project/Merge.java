package xb3_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.google.gson.Gson;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xb3_project.Product;

public class Merge {
	
	public static void main(String[] args) throws IOException {
        
        //Get the main Product ID from the user
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a product ID: ");
        String ID = reader.next();
		
        //change this and implement searching here
		
		Map<String, ArrayList<Product>> reviews = new HashMap<String, ArrayList<Product>>();
		//ArrayList<Product> arr = new ArrayList<Product>();
		
		Gson gson = new Gson();
		BufferedReader br = null;
		
		br = new BufferedReader(new FileReader("data.json"));
		String review;
		int i = 0;
		
		while((review = br.readLine()) != null) {
			Product product = gson.fromJson(review, Product.class);

			if (product != null) {
				String productID = product.getAsin();
				String date = product.getReviewTime();
				double rating = product.getOverall();
				
				reviews.putIfAbsent(productID, new ArrayList<Product>());
				
				if (reviews.containsKey(productID)) {
					reviews.get(productID).add(new Product(productID, date, rating));
				}
				//arr.add(new Product(productID, date, rating));
			}
		}

		br.close();
		
		
		
		//This is used to convert our arraylist to an array. This arraylist would come from the hash table
		//in the actual code, not from the simple data.json file we used here.
		Product[] proArr = new Product[reviews.get(ID).size()];
		reviews.get(ID).toArray(proArr);
		
		System.out.println("Data.json file that has the product ratings and dates posted");
		//These console outputs are temporary.
		System.out.println("UNSORTED: \n");
		//System.out.println(Arrays.toString(arr));
		for(int j = 0; j<proArr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Date: %s, Rating: %.1f\n", proArr[j].getAsin(), proArr[j].getMonth(proArr[j].getReviewTime()), proArr[j].getOverall());
		}
		
		Merge.sortMerge(proArr, proArr.length);
		System.out.println("\nSORTED: \n");
		for(int j = 0; j<proArr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Time: %s, Rating: %.1f\n", proArr[j].getAsin(), proArr[j].getReviewTime(), proArr[j].getOverall());
		}
		
		//used to output the rating vs time graph
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		for (int j = 0; j < proArr.length; j++) {
			dataset.addValue( proArr[j].getOverall() , "trend" , proArr[j].getReviewTime() );
		}
	    LineChart_AWT chart = new LineChart_AWT(
	       "Ratings Vs Date" ,
	       "Ratings vs Date",
	       dataset);
	    chart.pack( );
	    RefineryUtilities.centerFrameOnScreen( chart );
	    chart.setVisible( true );
        
        
        //The following code outputs the asin numbers and related products on the console.
		//Need to add this data to a hash table.
	    System.out.println();
	    System.out.println("Data2.json file that has the related products");
		JSONParser jsonParser = new JSONParser();

		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("data2.json"));
			Iterator<?> iter = jsonArray.iterator();
			while (iter.hasNext()) {
				JSONObject jsonObject = (JSONObject) iter.next();
				String asin = (String) jsonObject.get("asin");
				System.out.println("Asin: " + asin);
				JSONObject relatedObject = (JSONObject) jsonObject.get("related");
				JSONArray related = (JSONArray) relatedObject.get("also_bought");
				ArrayList<String> relatedList = new ArrayList<String>();
				for (int l = 0; l < related.size(); l++) {
					relatedList.add((String) related.get(l));
				}
				for (int k = 0; k < relatedList.size(); k++) {
					System.out.println(relatedList.get(k));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Graphing algorithm starts here to check the connection betweeen the two IDs.
        /*System.out.println("Enter another product ID to check the connection between the two: ");
		String ID2 = reader.next();*/
        
        //Graphing algorithm here to check the connection between the two.
		//int connection = BFS.connection(ID,ID2);
		
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
