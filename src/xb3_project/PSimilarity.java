package xb3_project;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
//christiano
//Will return the shortest distance between p1 and p2 in the undirected equal weight graph G

public class PSimilarity {
	private static Map<String, String> parent = new HashMap<String, String>();;
	
	public static String Similarity(String p1, String p2, HashAdjGraph G) {
		bfs(p1,p2,G);  //start
		
		int count = 0;
		
		try {
			for (String i = p2; !i.equals(p1); i = parent.get(i)) {
				count+=1;
			}
		} catch (NullPointerException e) {
			System.out.println("Not related");
			System.exit(0);
		}
		return  quantify(count);
	}
	
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
