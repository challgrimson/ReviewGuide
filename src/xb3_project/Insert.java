package xb3_project;

import java.util.Hashtable;

public class Insert {
	private static Hashtable ht = new Hashtable();
	
	public static void create(Product[] x) {
		for(int i = 0; i<x.length; i++)
			ht.put(x[i].getAsin(), x[i]);
	}
	public static Product getRatings(Product x) {
		return (Product) ht.get(x.getAsin());
	}
}
