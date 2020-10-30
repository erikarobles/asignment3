/*
Node class

Authors: Sidhant Kaushik, Erika Robles
 */
public class Node<T extends Comparable<T>> {

	private T data;
	private Node<T> next;

	/**
	 * Constructor for objects of class Node
	 */
	public Node(T d) {
		data = d;
		next = null;
	}
	/*
	returns the data
	 */
	public T getData() {
		return data;
	}
	/*
	sets the nodes data
	 */
	public void setData(T o) {
		data = o;
	}
	/*
	returns the next node
	 */
	public Node<T> getNext() {
		return next;
	}
	/*
	sets the next node
	@param takes in a Node to set it as next
	 */
	public void setNext(Node<T> n) {
		next = n;
	}
	/*
	returns the getData as a String.
	@return the data as a String
	 */
	public String toString() {
		return getData().toString();
	}
}
