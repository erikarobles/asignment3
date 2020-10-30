/**
  * Node Class
  * @author Sidhant Kaushik, Erika Robles
  * @version Fall 2020
 */
public class Node<T extends Comparable<T>> {

	/**
	 * instance variables
	 */
    
	private T data;
	private Node<T> next;

	/**
	 * Constructor for objects of class Node
	 */
	public Node(T d) {
		data = d;
		next = null;
	}
	
	/**
	 * returns the data of the node
	 * @return removes the node then returns it, if found
	 */

	public T getData() {
		return data;
	}
	
	/**
	 * sets the data of the object
	 */
   
	public void setData(T o) {
		data = o;
	}
	
	/**
	 * gets the reference that the node points to
	 * @return the next node the current node points to
	 */
    
	public Node<T> getNext() {
		return next;
	}

	/**
	 * sets the current node to reference to a given node reference
	 * @param reference to next node
	 */
    
	public void setNext(Node<T> n) {
		next = n;
	}

    /**
  	  * returns the string representation of the node's data
  	  * @return string representation
  	*/

	public String toString() {
		return getData().toString();
	}
}
