package xb3_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class MainController implements Initializable {
	
	@FXML
	private TextField firstID;
	
	@FXML
	private TextField secondID;
	
	@FXML
	private TextField thirdID;
	
	@FXML
	private TextArea result;
	
	
	public static void main(String[] args) throws IOException {
        
		//0528881469
		//0000031852
		//B01D23RC6W
		
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
		
		Heap.sortHeap(proArr, proArr.length);
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
	    
	    HashAdjGraph table = new HashAdjGraph(10);
	    
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
				//System.out.println("Asin: " + asin);
				JSONObject relatedObject = (JSONObject) jsonObject.get("related");
				JSONArray related = (JSONArray) relatedObject.get("also_bought");
				linkedBag adjBAG = new linkedBag();
				for (int l = 0; l < related.size(); l++) {
					adjBAG.add((String) related.get(l));
				}
				table.addBag(asin, adjBAG);
				/*for (int k = 0; k < adjBAG.size(); k++) {
					System.out.println(adjBAG.);
				}*/
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Enter a new product ID: ");
		String ID2 = reader.next();
		
		//Graphing algorithm starts here to check the connection betweeen the two IDs.
        System.out.println("Enter another product ID to check the connection between the two: ");
		String ID3 = reader.next();
		
		System.out.println(PSimilarity.Similarity(ID2, ID3, table));
        
        //Graphing algorithm here to check the connection between the two.
		//int connection = BFS.connection(ID,ID2);
		
	}
	
	@FXML
	public void productTrend(ActionEvent event) throws IOException {
        
		//0528881469
		//0000031852
		//B01D23RC6W
		
        //Get the main Product ID from the user
        //Scanner reader = new Scanner(System.in);
        //System.out.println("Enter a product ID: ");
        //String ID = reader.next();
		
		String ID = firstID.getText();
		
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
		
		//System.out.println("Data.json file that has the product ratings and dates posted");
		//These console outputs are temporary.
		//System.out.println("UNSORTED: \n");
		//System.out.println(Arrays.toString(arr));
		/*for(int j = 0; j<proArr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Date: %s, Rating: %.1f\n", proArr[j].getAsin(), proArr[j].getMonth(proArr[j].getReviewTime()), proArr[j].getOverall());
		}*/
		
		Heap.sortHeap(proArr, proArr.length);
		//System.out.println("\nSORTED: \n");
		/*for(int j = 0; j<proArr.length; j++) {
			System.out.println("Review " + (j+1) + ":");
			System.out.printf("ASIN: %s, Time: %s, Rating: %.1f\n", proArr[j].getAsin(), proArr[j].getReviewTime(), proArr[j].getOverall());
		}*/
		
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
	}
	
	@FXML
	public void relatedProduct(ActionEvent event) {
        //Scanner reader = new Scanner(System.in);
		
	    HashAdjGraph table = new HashAdjGraph(10);
	    
        //The following code outputs the asin numbers and related products on the console.
		//Need to add this data to a hash table.
	    //System.out.println();
	    //System.out.println("Data2.json file that has the related products");
		JSONParser jsonParser = new JSONParser();

		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("data2.json"));
			Iterator<?> iter = jsonArray.iterator();
			while (iter.hasNext()) {
				JSONObject jsonObject = (JSONObject) iter.next();
				String asin = (String) jsonObject.get("asin");
				//System.out.println("Asin: " + asin);
				JSONObject relatedObject = (JSONObject) jsonObject.get("related");
				JSONArray related = (JSONArray) relatedObject.get("also_bought");
				linkedBag adjBAG = new linkedBag();
				for (int l = 0; l < related.size(); l++) {
					adjBAG.add((String) related.get(l));
				}
				table.addBag(asin, adjBAG);
				/*for (int k = 0; k < adjBAG.size(); k++) {
					System.out.println(adjBAG.);
				}*/
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//System.out.println("Enter a new product ID: ");
		//String ID2 = reader.next();
		
		String ID2 = secondID.getText();
		
		//Graphing algorithm starts here to check the connection betweeen the two IDs.
        //System.out.println("Enter another product ID to check the connection between the two: ");
		//String ID3 = reader.next();
		
		String ID3 = thirdID.getText();
		
		//System.out.println(PSimilarity.Similarity(ID2, ID3, table));
		result.setText(PSimilarity.Similarity(ID2, ID3, table));
        
        //Graphing algorithm here to check the connection between the two.
		//int connection = BFS.connection(ID,ID2);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
