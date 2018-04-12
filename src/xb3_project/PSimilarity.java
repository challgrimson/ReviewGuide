package xb3_project;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//Will return the shortest distance between p1 and p2 in the undirected equal weight graph G
/**
 * 
 * Will return the shortest distance between p1 and p2 in the undirected equal weight graph G
 */
public class PSimilarity {
	private static Map<String, String> parent = new HashMap<String, String>();; //used to record the parent of each node during bfs, to backtrack and find the recusrive depth
	
	/**
	 * Wrapper for the bfs
	 * @param p1 The string you start from
	 * @param p2 the string you go too
	 * @param G The graph containing the edges
	 * @return a string that quantifies the distance between the nodes by a string
	 */
	public static String Similarity(String p1, String p2, HashAdjGraph G) {
		bfs(p1,p2,G);  //start
		
		int count = 0; //used to count the depth
		
		try { //finds the depth by parents back to p1
			for (String i = p2; !i.equals(p1); i = parent.get(i)) {
				count+=1;
			}
		} catch (NullPointerException e) {
			return "Not related";
		}
		return  quantify(count);
	}
	/**
	 * Given a value, give that value a meaning
	 * @param val The value to quantify
	 * @return a string referring to how related the items were, by how far away they took to get to
	 */
	private static String quantify(int val) {
		if (0<= val && val < 5) {
			return "Very Strongly related";
		} else if (val < 20) {
			return "Strongly related";
		} else if (val < 50) {
			return "related";
		} else if (val < 100) {
			return "barely related";
		}
		return "not related";
	}
	
	/**
	 * A basic bfs that includes a table to record the parents
	 * @param p1 The starting string
	 * @param p2 The ending string
	 * @param G The table for the edges
	 */
	private static void bfs(String p1, String p2, HashAdjGraph G) {
		linkedBag checked = new linkedBag();
		Queue<String> queue = new LinkedList<String>();
		String s;      
		String nSrc;
		linkedBag adjProd;
	
		queue.add(p1);
		checked.add(p1);
		
		while(!queue.isEmpty()) {
			nSrc = queue.remove();
			adjProd = G.getEdges(nSrc);
			if (adjProd != null) {
				Iterator<String> adjProdIT = adjProd.iterator();
				while(adjProdIT.hasNext()) {
					s = adjProdIT.next();
					if (!isChecked(s, checked)) {
						parent.put(s,nSrc);
						checked.add(s);
						queue.add(s);
					}
					if(s.equals(p2))
						return;
				}
			}
		}
	return;
	}

	/**
	 * A helper function that looks through a given bag and returns true if the string is in the bag
	 * @param s The string to check for
	 * @param checked The bag of strings to check in
	 * @return true if the string is in the bag, false otherwise
	 */
	private static boolean isChecked(String s,linkedBag checked ) {
		Iterator<String> checkedIT = checked.iterator();
		String s2;
		while (checkedIT.hasNext()) {
			s2 = checkedIT.next();
			
			if (s2.equals(s)) {
				return true;
			}
		}
		
		return false;
	}
}
