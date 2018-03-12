package xb3_project;

/**
* The Product abstract data type to store the product information
* It stores the product ID, date and rating of the product
* The date fields (month, day, year) are stored as integers
*
* @author  Team 7
* @version 1.0
* @since   2018-03-08
*/

public class Product implements Comparable<Product>{
	
	// The variables to store the productID, date and rating of the product
	private String productID;
	private String date;
	private double rating;
	
	/**
	   * This method is the constructor. This is
	   * used to initialize the fields in each job
	   * @param productID This is the ID of the product
	   * @param date  This is the date the review was made
	   * @param rating This is rating for the product
	   */
	public Product(String productID, String date, double rating) {
		this.productID = productID;
		this.rating = rating;
		this.date = date;
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the year as an integer
	   * @param data this is the date of the review
	   * @return returns the year the review was posted
	   */
	public int getYear(String data) {
		String[] s1 = data.split(",");
		return Integer.parseInt(s1[1]);
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the month as an integer
	   * @param data this is the date of the review
	   * @return returns the month the review was posted
	   */
	public int getMonth(String data) {
		String[] s2 = data.split(",");
		String[] s3 = s2[0].split(" ");
		return Integer.parseInt(s3[1]);
	}
	
	/**
	   * This method is a getter. This is
	   * used to get the day as an integer
	   * @param data this is the date of the review
	   * @return returns the day the review was posted
	   */
	public int getDay(String data) {
		String[] s4 = data.split(",");
		String[] s5 = s4[0].split(" ");
		return Integer.parseInt(s5[0]);
	}
	
	/**
	   * This method gives the review date.
	   * @return date This returns the date the review was generated
	   */
	public String getDate() {
		return this.date;
	}
	
	/**
	   * This method gives the productID.
	   * @return productID This returns the product ID for the product
	   */
	public String getProductID() {
		return this.productID;
	}
	
	/**
	   * This method gives the review rating.
	   * @return getRating This returns the rating for the review
	   */
	public double getRating() {
		return this.rating;
	}

	/**
	   * This method compares the products based on the review dates first comparing the years.
	   * If the date of the argument review is greater than
	   * the first one it returns -1, if it's less it returns 1.
	   * If the year's are equal it compares them based on month.
	   * If the months are equal it compares them based on day.
	   * @return returns 1 if the argument product is less than the first product
	   * @return returns -1 if the current product is less than the argument product
	   */
	@Override
	public int compareTo(Product o) {
		if (this.getYear(this.getDate()) < o.getYear(o.getDate())) return -1;
		if (this.getYear(this.getDate()) > o.getYear(o.getDate())) return 1;
		if (this.getYear(this.getDate()) == o.getYear(this.getDate())) {
			if (this.getMonth(this.getDate()) < o.getMonth(o.getDate())) {
				return -1;
			} else if (this.getMonth(this.getDate()) > o.getMonth(o.getDate())) {
				return 1;
			} else if (this.getMonth(this.getDate()) == o.getMonth(o.getDate())) {
				if (this.getDay(this.getDate()) <= o.getDay(o.getDate())) {
					return -1;
				} else if (this.getDay(this.getDate()) > o.getDay(o.getDate())) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	
}
