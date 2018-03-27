package xb3_project;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

//Will return the shortest distance between p1 and p2 in the undirected equal weight graph G
//returns the raw recursive depth
//how to interpret the value is not currently included in this class

public class PSimilarity {
	private static boolean found = false;			//used to determine if found p2
	private static Queue<String> queue = new LinkedList<String>();  //bfs queue
	private static linkedBag checked;		//bag of the checked nodes
	
	public static int Similarity(String p1, String p2, HashAdjGraph G) {
		checked = new linkedBag(); //reset
		queue = new  LinkedList<String>(); //reset
		found = false;  //reset
		checked.add(p1);    //add to checked
		return bfs(p1,p2,G,0);  //start
	}
	
	private static int bfs(String p1, String p2, HashAdjGraph G,int c) {
		String nextS;  //initi
		String s;      
		
		linkedBag adjPt = G.getEdges(p1);   //get all the similiar products to p1
		if (adjPt != null) {
			Iterator<String> adjPtsIT = adjPt.iterator(); // iterater for all the adj
			while (adjPtsIT.hasNext()) { // while more adj
				s = adjPtsIT.next(); // get the adj

				if (!isChecked(s)) { // if not already marked

					if (s.equals(p2)) { // if its equal to p2 youre done, found is true and return 1 to start
										// compounding the length
						found = true;
						return 1;
					}

					queue.add(s); // adds each adj to the queue for future recursions
				}
			}
		}
		if (!queue.isEmpty()) {
			nextS = queue.remove(); // get next on the queue
			checked.add(nextS); // add as checked
			c = bfs(nextS, p2, G, 0); // go into next depth
			if (found == true)
				return c + 1;
		}
	return -1; //only happens if none
	}
	
	

	
	private static boolean isChecked(String s) {
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
