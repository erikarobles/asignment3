import java.util.Comparator;

public class SLL <T extends Comparable<T>>{
	   private Node<T> head;
	   private Node<T> tail;
	   private Comparator <T> comp;

	   public SLL(){

	   }
	   public SLL(Comparator <T> comparator){
	   	this.comp = comparator;
	   }
	   public Node<T> getHead() {
		   return head;
	   }
	   
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


	public void addInOrder(Node<T> n)
	{
		if(isEmpty()||compare(n.getData(),head.getData())<=0){
			addHead(n);
		}
		else if (compare(n.getData(),tail.getData())>0){
			addTail(n);
		}
		else{
			Node<T> mover = head;
			while (mover.getNext()!=null && compare(n.getData(), mover.getNext().getData())>0){
				mover = mover.getNext();
			}
			n.setNext(mover.getNext());
			mover.setNext(n);
		}
	}
	
	public int compare(T o1, T o2) {
		if (comp == null) {
			return o1.compareTo(o2);
		} else {
			return comp.compare(o1, o2);
		}
	}

	public Node<T> find(T key)
	   {
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
	   
	   public int size(){
		   
		    int count = 0;
		    Node<T> currNode = head;
		    while (currNode!= null){
		        count++;
		        currNode=currNode.getNext();
		    }
		    return count;
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
	   
	   private boolean isEmpty() {
		   return head == null;
	   }

}
