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
	
	/**
	 * Parses the data and using the given string in the ui it obtains its reviews 
	 * then it sorts the reviews and makes a line graph for output
	 * @param event response to button click in gui
	 * @throws IOException
	 */
	
	/**
	 * An open source gson library was used for the parsing of the gson data "gson-2.8.2.jar"
	 * https://github.com/google/gson
	 */
	
	/**
	 * Also an open source json library was used for easy and quick parsing of the json data "json-simple-1.1.jar"
	 * https://github.com/fangyidong/json-simple
	 * https://code.google.com/archive/p/json-simple/
	 * by Yidong Fang
	 */
	
	@FXML
	public void productTrend(ActionEvent event) throws IOException {
		
        //Get the main Product ID from the user
		String ID = firstID.getText();
		
		Map<String, ArrayList<Product>> reviews = new HashMap<String, ArrayList<Product>>();
		
		reviews = trendDataParse();  //parses through the data
		
		//This is used to convert our arraylist to an array. This arraylist would come from the hash table
		Product[] proArr = new Product[reviews.get(ID).size()];
		reviews.get(ID).toArray(proArr);
		
		Heap.sortHeap(proArr, proArr.length);
		
		
		//used to output the rating vs time graph
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		for (int j = 0; j < proArr.length; j++) {
			dataset.addValue( proArr[j].getOverall() , "trend" , proArr[j].getReviewTime() );
		}
	    LineChart chart = new LineChart(
	       "Ratings Vs Date" ,
	       "Ratings vs Date",
	       dataset);
	    chart.pack( );
	    RefineryUtilities.centerFrameOnScreen( chart );
	    chart.setVisible( true );
	}
	
	/**
	 * Given two product ids in the gui, gives a relation between the two based on how close they are in frequently 
	 * bought products
	 * @param event response to button click in gui
	 */
	@FXML
	public void relatedProduct(ActionEvent event) {
		
	    HashAdjGraph table = new HashAdjGraph(10);
		table = similarityDataParse();
		String ID2 = secondID.getText();
		String ID3 = thirdID.getText();
		
		result.setText(PSimilarity.Similarity(ID2, ID3, table));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * parses the data for use in the trend module, stores it in a Map<String, ArrayList<Product>>
	 * @return returns the hashMap of the product id as the key, and its reviews as the data in a ArrayList
	 * @throws IOException
	 */
	public static Map<String, ArrayList<Product>> trendDataParse() throws IOException {
		Map<String, ArrayList<Product>> reviews = new HashMap<String, ArrayList<Product>>();
		
		Gson gson = new Gson();
		BufferedReader br = null;
		
		br = new BufferedReader(new FileReader("data.json"));
		String review;
		
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
			}
		}

		br.close();
		
		return reviews;
	}
	
	/**
	 * parses the data for use in the similarity module, stores the data in a HashAdjGraph
	 * uses a json parser to obtain the data 
	 * @return a table of the information. The table contains the product as key, and the adj products as the value as a linkedBag
	 */
	public static HashAdjGraph similarityDataParse() {
	    HashAdjGraph table = new HashAdjGraph(10);
	    
        //The following code outputs the asin numbers and related products on the console.
		JSONParser jsonParser = new JSONParser();

		try {
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("data2.json"));
			Iterator<?> iter = jsonArray.iterator();
			while (iter.hasNext()) {
				JSONObject jsonObject = (JSONObject) iter.next();
				String asin = (String) jsonObject.get("asin");
				JSONObject relatedObject = (JSONObject) jsonObject.get("related");
				JSONArray related = (JSONArray) relatedObject.get("also_bought");
				linkedBag adjBAG = new linkedBag();
				for (int l = 0; l < related.size(); l++) {
					adjBAG.add((String) related.get(l));
				}
				table.addBag(asin, adjBAG);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return table;
		
	}
	
	
}
