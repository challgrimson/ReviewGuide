package xb3_project;

//same idea as adjancy list, but uses a hashtable to hold them in a linked bag


//________________HOW TO IMPORT FROM JSON INTO THIS TO MAKE A GRAPH__________________
// - Currently you need to give a base size of the table in number of products **will resize if not large enough**
// - For a given product(key) you are storing the related/also bought products
// - You can either create a linkedBag (class in repo) and add all the similiar products first then use addBag(key,Bag) --FASTER--
// - or using addEdge(key,productname) you can dynamically add to the product adj bag as you process the json --SLOWER--


/**
 *A hashtable implementation that that uses a string as the key and contains the keys edges in a bag
 */
public class HashAdjGraph {

    private int size = 0;           // number of key-value pairs in the symbol table
    private int tableSize;           // size of linear probing table
    private String[] keys;      // the keys
    private linkedBag[] adjBag;    // the bags stored at each key

    /**
     * Constructor for the table, initializes empty values of given size
     * @param V initial size of table
     */
    public HashAdjGraph(int V) {
    	tableSize = V;
        keys = new String[tableSize];
        adjBag = new linkedBag[tableSize];
    }

    /**
     * Accessor for current table size
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * returns if a given key contains a vertex
     * @param key The key to check
     * @return a boolean of true if there is a vertex, false otherwise
     */
    public boolean containsVertex(String key) {
        return getEdges(key) != null;
    }

    /**
     * Hash function used to hash the keys
     * @param key The key to hash
     * @return The has of the key
     * hash function used from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    /**
     * resizes the table by a factor of 2 and replaces all the original elements
     */
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
    
    /**
     * Sets an  entire adjacency bag to be associated with the key
     * @param key The key that the bags has the vertices of
     * @param bag Contains vertices of the key
     * Should only be done for a key of an empty bag, or to overwrite a keys current information
     */
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

    /**
     * Place a new edge for the key
     * @param key The key that the edge is attached to
     * @param product The vertex of the key
     */
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

    /**
     * returns the bag containing all adj edges
     * use the bag iterator to go through each item
     * @param key The string of the bag
     * @return The linked bag containing the keys adj edges
     */
    public linkedBag getEdges(String key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % size)
            if (keys[i].equals(key))
                return adjBag[i];
        return null;
    }


}
