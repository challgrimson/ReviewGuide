package xb3_project;

import java.util.Iterator;
import java.util.NoSuchElementException;

//christian
//accessing the items in the array is done through the iterator class at the bottom, easiest way, let me know if needs more functionality
public class linkedBag {
   
    private Node first;    // beginning of bag
    private int n;               // number of elements in bag

    private static class Node {
        private String product;
        private Node next;
    }

    public linkedBag() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void add(String product) {
        Node oldfirst = first;
        first = new Node();
        first.product = product;
        first.next = oldfirst;
        n++;
    }
    
    
    //sedgewick
    public Iterator<String> iterator()  {
        return new ListIterator();  
    }

    // an iterator over a linked list
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
