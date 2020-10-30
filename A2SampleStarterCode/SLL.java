/** 
 * COMP 2503 Winter 2020 Assignment 2
 * 
 * This program must read a input stream and keeps track of the 
 * frequency at which an avenger is mentioned either by name or alias.
 *
 * @author Maryam Elahi, Sidhant Kaushik, Erika Robles
 * @date Fall 2020
*/

import java.util.Comparator;

	/**
	 * initializing instance variables
	 * 
	 **/

public class SLL <T extends Comparable<T>>{
	   private Node<T> head;
	   private Node<T> tail;
	   private int counter = -1;
	   private Comparator <T> comp;
	   public Node<T> copy = null;
	   public SLL(){

	   }
	   
		/**
		 * Constructor
		 * @param the comparator used to sort the list
		 */
	   
	   public SLL(Comparator <T> comparator){
		   this.comp = comparator;
	   }
	   
		/**
		 * returns the head node of the list
		 * @return the head node
		 **/
	   
	   public Node<T> getHead() {
		   return head;
	   }
	   
		/**
		 * adds the given node to the head of the list
		 * @param the node to add to the beginning of the list
		 */
	   
	   private void addHead(Node<T> n) {
		   if (head == null) {
			   head = n;
			   tail = n;
		   }
		   else {
			   n.setNext(head);
			   head = n;
		   }
		   counter++;
	   }
	   
		/**
		 * adds the given node to the end of the list
		 * @param the node to add to the end of the list
		 */
	   
	   public void addTail(Node<T> a) {
	       if(head != null) {
	    	   Node<T> mover = tail;
	    	   mover.setNext(a);
	    	   tail = a;
	       }
	       else {
	    	   addHead(a);
	       }
	       counter++;
	   }
	   
		/**
		 * adds the given node to the middle of the list
		 * @param the node to add to the middle of the list
		 */
	   
	   public void addInOrder(Node<T> n) {
		   
		   if(isEmpty()||compare(n.getData(),head.getData())<=0){
			   addHead(n);
		   } else if (compare(n.getData(),tail.getData())>0){
			   addTail(n);
		   } else {
			   Node<T> mover = head;
			   while (mover.getNext()!=null && compare(n.getData(), mover.getNext().getData())>0){
			   mover = mover.getNext();
			   counter++;
		   }
			   
			n.setNext(mover.getNext());
			mover.setNext(n);
		   }
	    }
	
		/**
		 * compares two nodes, either based on
		 * the external comparator or the comparable interface 
		 * which the object implements
		 * @param the two objects to compare
		 * @return returns a negative integer if the first argument is less than,
		 * 			0 if they are equal, and
		 * 			positive if the first argument is greater than
		 */
	    public int compare(T o1, T o2) {
	    	if (comp == null) {
	    		return o1.compareTo(o2);
	    	} else {
	    		return comp.compare(o1, o2);
	    	}
	    }
	    
		/**
		 * finds a given node based on the given parameter 
		 * @param the key to look for in the list. ex: a string or reference
		 * @return returns the node, if found
		 */

	    public Node<T> find(T key) {
	    	
			Node<T> mover = head;
			
			while (mover != null) {
				if (mover.getData().equals(key)) { 
					return mover;
				} else {
					mover = mover.getNext();
				}
				
			}
			
			return null;
	    }
	    
		/**
		 * deletes a node based on the key given
		 * @param the key to look for in the list. ex: a string or reference
		 * @return removes the node then returns it, if found
		 */
	    
	   public Node<T> delete (Node<T> key) {   
			Node<T> mover = head;
			Node<T> previous = head;
			
			while (mover != null) {
				
				if (mover.getData().equals(key)) {
					if (mover == head && mover == tail) {
						emptyList();
						return mover;
					}
					
					if (mover == tail) {
						tail = previous;
					}
					
					if (mover == head) {
						head = mover.getNext();
					} else {
						previous.setNext(mover.getNext());
					}
					return mover;
					
				} else {
					previous = mover;
					mover = mover.getNext();
				}			
				
			}
			
			return null;
	   }
	   
		/**
		 * returns the number of nodes in the list
		 * @return number of nodes in the list, in total
		 */
	    
	   public int getSize() {
		   return counter;
	   }
	   
		/**
		 * returns a copy of the node, given the node's index
		 * @param the node's position in the list, where the first node is 1
		 * @return the node with that index
		 */

	   public Node<T> get(int index) {
		
		   SLL<T> copyList = clone();
		   Node<T> mover = copyList.getHead();

		   if (isEmpty()) {
			   return null;
		   }
		  
		   if (index < getSize() && index >= 0){

		   for ( int i = 0; i< index; i++){
					mover = mover.getNext();
			}

			return mover;
		   }
		   
		   return null;
	   }
	   
		/**
		 * clones the list and returns an exact copy of it
		 * @return the duplicate of the list
		 */
	    
	   public SLL<T> clone() {

		   SLL<T> copyList = new SLL<T>();
		   Node<T> mover = null;
		   Node<T> curr = null;

		   if (head != null) {
			   copy = new Node<T>(getHead().getData());
			   copyList.addHead(copy);
			   mover = getHead().getNext();
			   curr = copy;
		   }
		   
		   while(mover != null){
			   Node<T> newNode = new Node<T>(mover.getData());
			   curr.setNext(newNode);
			   curr = newNode;
			   mover = mover.getNext();
		   }

		   return copyList;

	    }

		/**
		 * displays each object in the list
		 * @return a String of all objects in the list
		 */
	    
	   public void printList() {
	       System.out.println();
	       Node<T> currentNode = head;
	       while (currentNode != null) {
	           System.out.println(currentNode.toString());
	           currentNode = currentNode.getNext();
	       }
	    }
	   
		/**
		 * empties the list. 
		 * sets the size to 0 and head to null
		 */
	    
	   public void emptyList() {
	       head = null;
	   }
	   
		/**
		 * checks if the list is empty
		 * if the list have no nodes
		 * @return true, if the list is empty
		 * 			false, if otherwise
		 */
	    
	   private boolean isEmpty() {
		   return head == null;
	   }

}
