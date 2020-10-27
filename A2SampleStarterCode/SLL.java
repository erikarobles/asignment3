
public class SLL <T extends Comparable<T>> {
	   private Node<T> head;
	   private Node<T> tail;

	   
	   private void addHead(Node<T> n) {
		   n.setNext(head);
		   head = n;
		   // add a node to the head of the list.
	   }
	   
	   private void addTail(Node<T> n) 
	   {
	       if(head != null) {
	    	   Node<T> mover = head;
	    	   while(mover.getNext() != null) {
	    		   mover = mover.getNext();
	    	   }
	    	   mover.setNext(n);
	       }
	       else {
	    	   head = n;
	       }
	   }


	   private void addInOrder(Node<T> n)
	   {
		   
		   if(head == null) {
			   head = n;
		   }
			   
		Node<T> mover = head;
		while(mover != null) {
	    	   if(mover.getData().compareTo(n.getData()) < 0) {
	    		   mover.setNext(n);
	    	   }
	    	   else {
	    	   mover.getNext();
	    	   }
	       }
		   }
	   
	   
	   private Node<T> find(String key) 
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
	   
	   private Node<T> delete (String key) 
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
	   
	   private void printList()
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
