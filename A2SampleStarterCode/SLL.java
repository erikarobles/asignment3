import java.util.Comparator;

public class SLL <T extends Comparable<T>>{
	   private Node<T> head;
	   private Node<T> tail;

	   
	   public void addHead(Node<T> n) {
		   n.setNext(head);
		   head = n;
		   // add a node to the head of the list.
	   }
	   
	   public void addTail(Node<T> a)
	   {
	       if(head != null) {
	    	   Node<T> mover = head;
	    	   while(mover.getNext() != null) {
	    		   mover = mover.getNext();
	    	   }
	    	   mover.setNext(a);
	       }
	       else {
	    	   head = a;
	       }
	   }

	   public void addInOrder(Node<T> n, Comparator<Node<T>> a)
	   {
		   
		   if(head == null) {
			   head = n;
		   }
			   
		Node<T> mover = head;
		while(mover != null) {
			if(a.compare(n, mover)==0){
				mover.getNext();
			}
			else if(a.compare(n, mover)>0){
				mover.getNext();
			}
			else{
				mover.setNext(n);
			}
	       }
	   }
	   
	   
	   public Node<T> find(T key)
	   {
	       // implement find 
		   if(head != null) {
			   Node<T> found = null;
			   Node<T> mover = head;
    		   if (mover.getData() == key) {
    			   found = mover;
    		   }
	    	   while(mover.getNext() != null) {
	    		   mover = mover.getNext();
	    		   if (mover.getData() == key) {
	    			   found = mover;
	    		   }
	    	   }
	    	   return found;
		   }
		   else {
			   return null;
		   }
	   }
	   
	   public Node<T> delete (Node<T> key)
	   {   
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
	   
	   public void printList()
	   {
	       System.out.println();
	       Node<T> currentNode = head;
	       while (currentNode != null) 
	       {
	          // Visit the node. In this case, print it out. 
	           System.out.println(currentNode.toString());
	           currentNode = currentNode.getNext();
	       }
	   }
	   
	   public void emptyList()
	   {
	       head = null;
	   }

}
