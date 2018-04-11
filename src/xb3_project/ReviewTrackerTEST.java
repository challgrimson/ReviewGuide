package xb3_project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class ReviewTrackerTEST {
	@Test
	void testSimilarity() { //tests the similarity module based on expected results
		HashAdjGraph table = MainController.similarityDataParse();
		String ID1 = "0000031852";
		String ID2 = "5500371852";
		assertTrue(PSimilarity.Similarity(ID1, ID2, table).equals("Very Strongly related"));
		ID1 = "0000031852";
		ID2 = "B01D23RC6W";
		assertTrue(PSimilarity.Similarity(ID1, ID2, table).equals("Strongly related"));
		ID1 = "0594481813";
		ID2 = "0972683275";
		assertTrue(PSimilarity.Similarity(ID1, ID2, table).equals("Not related"));
	}
	
	@Test
	void testTrend() throws IOException { //The following uses jUnit to test based on whether the reviews are ordered correctly
		String ID = "0972683275";
		Map<String, ArrayList<Product>> reviews = new HashMap<String, ArrayList<Product>>();
		reviews = MainController.trendDataParse();
		Product[] proArr = new Product[reviews.get(ID).size()];
		reviews.get(ID).toArray(proArr);
		
		Heap.sortHeap(proArr, proArr.length);
		
		assertTrue(isSorted(proArr));
		
		ID = "0972683275";
		proArr = new Product[reviews.get(ID).size()];
		reviews.get(ID).toArray(proArr);
		
		Heap.sortHeap(proArr, proArr.length);
		
		assertTrue(isSorted(proArr));
	}
	
	public static boolean isSorted(Product[] a) { 
		  for (int i = 1; i < a.length; i++)
		   if (a[i].compareTo(a[i-1]) == -1)
		   {
		    return false;
		   }
		  return true;
		 }

}
