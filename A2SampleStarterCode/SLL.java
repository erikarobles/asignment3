public class SLL {
	   private Node<String> head;
	   private Node<String> tail;
	   
	   private void addHead(Node<String> n) 
	   {
		   n.setNext(head);
		   head = n;
		   // add a node to the head of the list.
	   }
	   
	   private void addTail(Node<String> n) 
	   {
	       if(head != null) {
	    	   Node<String> mover = head;
	    	   while(mover.getNext() != null) {
	    		   mover = mover.getNext();
	    	   }
	    	   mover.setNext(n);
	       }
	       else {
	    	   head = n;
	       }
	   }

	   private void addInOrder(Node<String> n) 
	   {
		   
		   if(head == null) {
			   head = n;
		   }
			   
		Node<String> mover = head;
		while(mover != null) {
	    	   if(mover.getData().compareTo(n.getData()) < 0) {
	    		   mover.setNext(n);
	    	   }
	    	   else {
	    	   mover.getNext();
	    	   }
	       }
		   }
	   
	   
	   private Node<String> find(String key) 
	   {
	       // implement find
		   if(head != null) {
			   Node<String> found = null;
			   Node<String> mover = head;
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
	   
	   private Node<String> delete (String key) 
	   {   
		  // implement delete
	      return null;
	   }
	   
	   private void printList()
	   {
	       System.out.println();
	       Node<String> currentNode = head;
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
