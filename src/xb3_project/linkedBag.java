package xb3_project;

import java.util.Iterator;
import java.util.NoSuchElementException;


//accessing the items in the array is done through the iterator class at the bottom

/**
 * 
 * Linked bag implementation that holds a set of strings
 */
public class linkedBag {
   
    private Node first;    // beginning of bag
    private int n;               // number of elements in bag

    /*
     * class used to create the nodes in the bag, contains
     * a string name and a pointer to the next node
     */
    private static class Node {
        private String product;
        private Node next;
    }

    /**
     * Initializes the bag
     */
    public linkedBag() {
        first = null;
        n = 0;
    }

    /**
     * Returns the state of whether or not the bag is currently empty
     * @return A boolean of true if empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Accessor for the current number of items in the bag
     * @return The size of the bag
     */
    public int size() {
        return n;
    }

    /**
     * add a new string to the bag
     * @param product The product name that is added to the bag
     */
    public void add(String product) {
        Node oldfirst = first;
        first = new Node();
        first.product = product;
        first.next = oldfirst;
        n++;
    }
    
    
    /**
     * Obtain an iterator for the bag to go through the strings inside
     * @return The iterator of the bag
     */
    public Iterator<String> iterator()  {
        return new ListIterator();  
    }

    /**
     * Iterator for the bag that goes through each of its items
     * inspired from Algorithms, 4th Edition by Robert Sedgewick and Kevin Wayne
     */
    private class ListIterator implements Iterator<String>{
        private Node current;

        public ListIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        // returns the next item in the iterator (and advances the iterator)
        public String next() {
            if (!hasNext()) 
            	throw new NoSuchElementException();
            
            String product = current.product;
            current = current.next; 
            return product;
        }
    }
}
