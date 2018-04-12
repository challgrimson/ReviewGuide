package xb3_project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
* The Product abstract data type to store the product information
* It stores the product ID, reviewTime and overall of the product
* The reviewTime fields (month, day, year) are stored as integers
*
* @author  Team 7
* @version 1.0
* @since   2018-03-08
*/

public class Product implements Comparable<Product>{
	
	// The variables to store the asin, reviewTime and overall of the product
	@SerializedName("asin")
	@Expose
	private String asin;
	@SerializedName("overall")
	@Expose
	private Double overall;
	@SerializedName("reviewTime")
	@Expose
	private String reviewTime;
	
	/**
	   * This method is the constructor. This is
	   * used to initialize the fields in each job
	   * @param asin This is the ID of the product
	   * @param reviewTime  This is the reviewTime the review was made
	   * @param overall This is overall for the product
	   */
	public Product(String asin, String reviewTime, double overall) {
		this.asin = asin;
		this.overall = overall;
		this.reviewTime = reviewTime;
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the year as an integer
	   * @param data this is the reviewTime of the review
	   * @return returns the year the review was posted
	   */
	public int getYear(String data) {
		String[] s1 = data.split(", ");
		return Integer.parseInt(s1[1]);
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the month as an integer
	   * @param data this is the reviewTime of the review
	   * @return returns the month the review was posted
	   */
	public int getMonth(String data) {
		String[] s2 = data.split(",");
		String[] s3 = s2[0].split(" ");
		return Integer.parseInt(s3[0]);
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the day as an integer
	   * @param data this is the reviewTime of the review
	   * @return returns the day the review was posted
	   */
	public int getDay(String data) {
		String[] s4 = data.split(",");
		String[] s5 = s4[0].split(" ");
		return Integer.parseInt(s5[1]);
	}
	
	/**
	   * This method gives the review reviewTime.
	   * @return reviewTime This returns the reviewTime the review was generated
	   */
	public String getReviewTime() {
		return this.reviewTime;
	}
	
	/**
	   * This method gives the asin.
	   * @return asin This returns the product ID for the product
	   */
	public String getAsin() {
		return this.asin;
	}
	
	/**
	   * This method gives the review overall.
	   * @return getOverall This returns the overall for the review
	   */
	public double getOverall() {
		return this.overall;
	}

	/**
	   * This method compares the products based on the review reviewTimes first comparing the years.
	   * If the reviewTime of the argument review is greater than
	   * the first one it returns -1, if it's less it returns 1.
	   * If the year's are equal it compares them based on month.
	   * If the months are equal it compares them based on day.
	   * @return returns 1 if the argument product is less than the first product
	   * @return returns -1 if the current product is less than the argument product
	   */
	@Override
	public int compareTo(Product o) {
		if (this.getYear(this.getReviewTime()) < o.getYear(o.getReviewTime())) {return -1;}
		if (this.getYear(this.getReviewTime()) > o.getYear(o.getReviewTime())) {return 1;}
		if (this.getYear(this.getReviewTime()) == o.getYear(this.getReviewTime())) {
			if (this.getMonth(this.getReviewTime()) < o.getMonth(o.getReviewTime())) {
				return -1;
			} else if (this.getMonth(this.getReviewTime()) > o.getMonth(o.getReviewTime())) {
				return 1;
			} else if (this.getMonth(this.getReviewTime()) == o.getMonth(o.getReviewTime())) {
				if (this.getDay(this.getReviewTime()) < o.getDay(o.getReviewTime())) {
					return -1;
				} else if (this.getDay(this.getReviewTime()) > o.getDay(o.getReviewTime())) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	
}
