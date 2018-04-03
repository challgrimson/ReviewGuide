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
