package xb3_project;

import java.util.Iterator;

//christian
//same idea as adjancy list, but uses a hashtable to hold them in a linked bag


//________________HOW TO IMPORT FROM JSON INTO THIS TO MAKE A GRAPH__________________
// - Currently you need to give a base size of the table in number of products **will resize if not large enough**
// - For a given product(key) you are storing the related/also bought products
// - You can either create a linkedBag (class in repo) and add all the similiar products first then use addBag(key,Bag) --FASTER--
// - or using addEdge(key,productname) you can dynamically add to the product adj bag as you process the json --SLOWER--

public class HashAdjGraph {

    private int size = 0;           // number of key-value pairs in the symbol table
    private int tableSize;           // size of linear probing table
    private String[] keys;      // the keys
    private linkedBag[] adjBag;    // the bags stored at each key

    public static void main(String[] args) {
    	HashAdjGraph tableTEST = new HashAdjGraph(10);
    	tableTEST.addEdge("1","product1");
    	tableTEST.addEdge("5","product1sdfhkjs");
    	tableTEST.addEdge("1","product3");
    	linkedBag adjBAG = tableTEST.getEdges("5");
    	Iterator<String> adjPtsIT = adjBAG.iterator();
    	System.out.println(adjPtsIT.next());
    	//System.out.println(adjPtsIT.next());
    }

    //inti table to given capacity
    public HashAdjGraph(int V) {
    	tableSize = V;
        keys = new String[tableSize];
        adjBag = new linkedBag[tableSize];
    }

    //return size
    public int size() {
        return size;
    }

    //obv
    public boolean containsVertex(String key) {
        return getEdges(key) != null;
    }

    //obv
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    //obv
    private void resize() {
    	int sizeFactor = 2;
    	
    	HashAdjGraph tempTable = new HashAdjGraph(sizeFactor*tableSize);
    	
        for (int i = 0; i < tableSize; i++) {
            if (keys[i] != null) {
            	tempTable.addBag(keys[i], adjBag[i]);
            }
        }
        keys = tempTable.keys;
        adjBag = tempTable.adjBag;
        tableSize  = tempTable.tableSize;
    }
    
    //Used if you don't want to just put a value in the bag, but an entire bag
    public void addBag(String key, linkedBag bag) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % tableSize) {
            if (keys[i].equals(key)) {
            	adjBag[i] = bag;
                return;
            }
        }
        keys[i] = key;
        adjBag[i] = bag;
        size++;
    }

    //place product in corresponding keys bag
    public void addEdge(String key, String product) {

        // double table size if 50% full
        if (size >= tableSize/2) 
        	resize();
        
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % tableSize) {
            if (keys[i].equals(key)) {
                adjBag[i].add(product);
                return;
            }
        }
        keys[i] = key;
        adjBag[i] = new linkedBag();
        adjBag[i].add(product);
        size++;
    }

    //returns the **entire bag**
    public linkedBag getEdges(String key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % size)
            if (keys[i].equals(key))
                return adjBag[i];
        return null;
    }


}
