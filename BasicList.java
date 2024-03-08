import java.util.Iterator;
/**
 * BasicList class is a singly linked list that does not use dummy nodes.
 * It provides basic operations such as addFirst, addLast, removeFirst, removeLast, remove, indexOf, and listToString.
 * It also provides methods to move a node to the front, to the back, forward, or backward.
 * @author Aidan Emden
 * @param <T> the type of element in the linked list
 */

public class BasicList<T> implements Iterable<T> { 
	
	/**
	 * The head of the list.
	 */
	protected Node<T> head = null;  

	/**
	 * Iterator for the list.
	 * 
	 * @return an iterator that traverses from the beginning to the end of the list
	 */

	@Override
	public Iterator<T> iterator() {
		//Return an iterator that traverses from
		//the beginning to the end of the list.
		//This provided code would work if you have set up the list correctly.
		
		return new Iterator<T>() {
			Node<T> current = head;
			
			public boolean hasNext(){
				return current!=null;
			}
				
			//next() would throw a NullPointerException
			//if you try to use next when there are no more items 
			public T next(){
				T toReturn = current.getData();
				current = current.getNext();
				return toReturn;
			}
		};
	}

	// -define additional instance variables as needed, no public ones allowed
	// -you can define additional helper methods but they must be private
	
	// initialize the list to be an empty list

	/**
	 * Constructor for BasicList.
	 */
	public BasicList(){
		this.head = null;
	}
	
	// report number of items
	// O(1)

	/**
	 * Returns the size of the list.
	 * 
	 * @return the size of the list
	 */

	public int size(){
		int size = 0;
		Node<T> current = head;
		while(current != null){
			size++;
			current = current.getNext();
		}
		return size;
	}
	
	// return the first value from the beginning of the list
	// do not remove the value!
	// return null if list is empty
	// O(1)

	/**
	 * Returns the first value in the list.
	 * 
	 * @return the first value in the list
	 */
	
	public T getFirst() {
		if(head == null){
			return null;
		} else {
			return head.getData();
		}
	}

	// insert a new node with value at the beginning of the list
	// null value not allowed: 
	// throw IllegalArgumentException (with any error msg) if value is null
	// O(1)

	/**
	 * Adds a value to the beginning of the list.
	 * 
	 * @param value the value to be added
	 * @throws IllegalArgumentException if the value is null
	 */
	public void addFirst(T value) {
		if(value == null){
			throw new IllegalArgumentException("Value cannot be null.");
		} else {
			Node<T> newNode = new Node<T>(value);
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	// remove and return the first value from the beginning of the list
	//return null if list is empty
	// O(1)

	/**
	 * Removes and returns the first value from the beginning of the list.
	 * 
	 * @return the first value from the beginning of the list
	 */
	public T removeFirst(){
		if(head == null){
			return null;
		} else {
			T value = head.getData();
			head = head.getNext();
			return value;
		}
	}

	// return the last value from the end of the list
	// do not remove the value!
	// return null if list is empty
	// O(1)

	/**
	 * Returns the last value in the list.
	 * 
	 * @return the last value in the list
	 */

	public T getLast() {
		if(head == null){
			return null;
		}

		Node<T> current = head;
		while(current.getNext() != null){
			current = current.getNext();
		}
		return current.getData();
	}
	

	// append a new value at the end of the list
	// null value not allowed:  see addFirst() above
	// O(1)

	/**
	 * Adds a value to the end of the list.
	 * 
	 * @param value the value to be added
	 * @throws IllegalArgumentException if the value is null
	 */

	public void addLast(T value) {	
		Node<T> newNode = new Node<T>(value);

		if(value == null){
			throw new IllegalArgumentException("Value cannot be null.");
		}
		
		if(head == null){
			head = newNode;
			return;
		}
			
		Node<T> current = head;
		while(current.getNext() != null){
			current = current.getNext();
		}
		current.setNext(newNode);
	}
	

	
	// remove and return the last value from the end of the list
	//return null if list is empty
	// O(n) where n is the number of items in list

	/**
	 * Returns the last value from the end of the list.
	 * 
	 * @return the last value from the end of the list
	 */
	
	public T removeLast() {
		if(head == null){
			return null;
		}

		Node<T> current = head;
		Node<T> previous = null;
		while(current.getNext() != null){
			previous = current;
			current = current.getNext();
		}
		if(previous == null){
			head = null;
		} else {
			previous.setNext(null);
		}
		return current.getData();
	}
	
	//remove and return the first occurence of value 
	// (i.e. the occurence that is closest to start of the list)
	// - return null if value is null or not present
	// - note: must return the value from list, not the argument value
	//O(n) where n is the number of items in list

	/**
	 * Removes and returns the first occurrence of a value.
	 * 
	 * @param value the value to be removed
	 * @return the value that was removed
	 */

	public T remove(T value){
		if(value == null || head == null){
			return null;
		}
	
		Node<T> current = head;
		Node<T> previous = null;
	
		while(current != null){
			if(current.getData().equals(value)){
				if(previous != null){
					previous.setNext(current.getNext());
				} else {
					head = current.getNext();
				}
				return current.getData(); 
			}
			previous = current;
			current = current.getNext();
		}
		return null;
	}

	//return the index of the first occurence of value 
	// (i.e. the occurence that is closest to the start of the list)
	// return -1 if value is null or not present	
	//O(n) where n is the number of items in list

	/**
	 * Returns the index of the first occurrence of a value.
	 * 
	 * @param value the value to be found
	 * @return the index of the first occurrence of the value
	 */

	public int indexOf(T value){
		if(value == null || head == null){
			return -1;
		}

		int index = 0;
		Node<T> current = head;
		while(current != null){
			if(current.getData().equals(value)){
				return index;
			}
			index++;
			current = current.getNext();
		}
		return index;
	
	}


	// return a string representing all values in the list, from beginning to end,
	// seperated by a single space
	// return empty string for empty list
	// O(n) where n is the number of items in list
	// Warning: concatenating String objects will yield a O(n^2) solution

	/**
	 * Returns a string representing all values in the list.
	 * 
	 * @return a string representing all values in the list
	 */

	public String listToString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = head;
		while(current != null){
			sb.append(current.getData()).append(" ");
        	current = current.getNext();
		}
		return sb.toString().trim();
	}

	//return first node from start that contains value
	//return null if value is null or not present
	// NOTE: the layout of nodes is typically hidden and not available to outside;
	//       we are declaring this public for testing purpose. 
	//       Do not use this method outside of BasicList/SortedList classes.
	//O(n) where n is the number of items in list

	/**
	 * Returns the first node that contains a given value.
	 * 
	 * @param value the value to be found
	 * @return the first node that contains the given value
	 */

	public Node<T> getNode(T value){
		Node<T> current = head;
		while(current != null){
			if(current.getData().equals(value)){
				return current;
			}
			current = current.getNext();
		}
		return null;
	}
	
	//find the first node that contains the given value, and move the node to the start of list
	// i.e. indexOf(value)==0 after moving completes
	// - return true if move can be performed
	// 	- no change if value already at the start but still return true
	// - return false if value is null or not present
	// Note: You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node.  
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list

	/**
	 * Moves a node to the start of the list.
	 * 
	 * @param value the value to be moved
	 * @return true if the move can be performed, false if the value is null or not present
	 */

	public boolean moveToFront(T value){
		if(value == null){
			return false;
		}

		Node<T> current = head;
		Node<T> previous = null;
		while(current != null){
			if(current.getData().equals(value)){
				if(previous != null){
					previous.setNext(current.getNext());
					current.setNext(head);
					head = current;
				}
				return true;
			}
			previous = current;
			current = current.getNext();
		}
		return false;
	}
	
	//find the first node that contains the given value, 
	// and move the node one location closer to start of the list
	// i.e. if indexOf(value)==x before the move, then after moving completes, indexOf(value)==x-1
	// - return true if move can be performed
	// 	- no change if value already at the start but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list

	/**
	 * Moves a node one location closer to the start of the list.
	 * 
	 * @param value the value to be moved
	 * @return true if the move can be performed, false if the value is null or not present
	 */

	public boolean moveForward(T value){
		
		if (value == null){
			return false;
		}

		if (head == null || head.getData().equals(value)){
			return true;
		}

		Node<T> previousPrevious = null;
    	Node<T> previous = head;
    	Node<T> current = head.getNext();

    	while(current != null){
        	if(current.getData().equals(value)){
            	if (previousPrevious != null) {
                	previousPrevious.setNext(current);
				} else {
					head = current;
				}
				previous.setNext(current.getNext());
            	current.setNext(previous);
            	return true;
        	}
			previousPrevious = previous;
        	previous = current;
        	current = current.getNext();
		}
		return false;
	}
	
	//find the first node that contains the given value, and move the node to the end of list
	// i.e. if only one node contains value, then indexOf(value)==size()-1 after moving completes 
	// - return true if move can be performed
	// 	- no change if value already at the end but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list

	/**
	 * Moves a node to the end of the list.
	 * @param value the value to be moved
	 * @return true if the move can be performed, false if the value is null or not present
	 */

	public boolean moveToBack(T value){
		if(value == null){
			return false;
		}

		Node<T> current = head;
		Node<T> previous = null;
		Node<T> toMove = null;
		Node<T> previousToMove = null;
		
		while(current != null){
			if(current.getData().equals(value)){
				toMove = current;
				previousToMove = previous;
			}
			previous = current;
			current = current.getNext();
		}
		if(toMove == null){
			return false;
		}

		if(toMove.getNext() == null){
			return true; 
		}

		if (toMove == head) {
			head = toMove.getNext(); 
		} else {
			previousToMove.setNext(toMove.getNext());
		}

		current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(toMove);
		toMove.setNext(null);

		return true;
	}
	
	//find the first node that contains the given value, 
	// and move the node one location closer to end of the list
	// i.e. if only one node contains value and indexOf(value)==x before the move, 
	//      then after moving completes, indexOf(value)==x+1
	// - return true if move can be performed
	// 	- no change if value already at the end but still return true
	// - return false if value is null or not present
	// Note:  You must reuse the existing node while move its location in list; 
	//        do NOT remove then add the value back using a new node
	//        Points will be deducted in grading if you do so.
	//O(n) where n is the number of items in list

	/**
	 * Moves a node one location closer to the end of the list.
	 * @param value the value to be moved
	 * @return true if the move can be performed, false if the value is null or not present
	 */

	public boolean moveBackward(T value){
		if(value == null || head == null){
			return false;
		}

		Node<T> previous = head;
    	Node<T> current = head.getNext();

   		while(current != null && current.getNext() != null){
			if(current.getData().equals(value)){
            	previous.setNext(current.getNext());
            	current.setNext(current.getNext().getNext());
            	previous.getNext().setNext(current);
            	return true;
        	}
			previous = current;
        	current = current.getNext();
		}
    	if (current != null && current.getData().equals(value)) {
        	return true;
    	}

    	return false;
	}
	
	

	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******        Remember to add JavaDoc         *******
	//******************************************************
	
	/**
	 * Main method to test the BasicList class.
	 * 
	 * @param args the command line arguments
	 */

	public static void main(String[] args) {


		System.out.println("lol");
		//a list of integers	
		/* 
		BasicList<Integer> nums = new BasicList<>();

		//basic operations
		nums.addLast(100);
		nums.addFirst(200);
		nums.addFirst(300);
		nums.addFirst(400);	

		
		
		if (nums.getFirst()==400 && nums.getLast()==100 &&
			nums.listToString().equals("400 300 200 100")) {
			System.out.println("Yay1");
		}
		
		System.out.println(nums.listToString());	
		*/
		
		 
		//a list of strings
		BasicList<String> names = new BasicList<>();
		names.addLast("apple");
		names.addLast("banana");
		names.addLast("blueberry");
		names.addLast("orange");
		names.addLast("blueberry");
		names.addLast("peach");

		if (names.removeFirst().equals("apple") && 
			names.remove("blueberry").equals("blueberry") && names.size() == 4 &&
			names.indexOf("blueberry") == 2 && 
			names.listToString().equals("banana orange blueberry peach")){
			System.out.println("Yay2");			
		}

		
		//getNode and move methods
		// -reminder: keep the original node but move it to a new location
		// - we will use getNode() to verify this as the examples below
		
		Node<String> node = names.getNode("orange");
		if (names.moveToFront("orange") && names.getNode("orange") == node &&
			names.listToString().equals("orange banana blueberry peach")){
			System.out.println("Yay3");						
		}

		node = names.getNode("peach");
		
		 
		if (names.moveForward("peach") && names.getNode("peach") == node &&
			names.listToString().equals("orange banana peach blueberry")){
			System.out.println("Yay4");
		}
		
		System.out.println(names.listToString());
		names.moveBackward("banana");
		System.out.println(names.listToString());
		
		
	
		
		//remove special case

		/**
		 * A class that implements the Comparable interface.
		 */
		class SomeType{
			private String value;

			public SomeType(String value) { this.value = value; }
			
			/**
			 * Compares two SomeType objects.
			 * 
			 * @param o the other SomeType object
			 * @return the result of the comparison
			 */
			public boolean equals(Object o) {
				if (!(o instanceof SomeType)) return false;
				
				//both null
				if (((SomeType)o).value == null && this.value==null) return true;
				
				//both empty string
				if (((SomeType)o).value.length() == 0 && this.value.length()==0) return true;
				
				//compare the leading chars
				return ((SomeType)o).value.charAt(0) == this.value.charAt(0);
			}
			
			public String toString(){ return value;}
		}
		
		SomeType item1 = new SomeType("Apple");
		SomeType item2 = new SomeType("Alligator");
		SomeType item3 = new SomeType("Bee");
		SomeType item4 = new SomeType("Alder");
		
		
		BasicList<SomeType> items = new BasicList<>();
		items.addLast(item1);
		items.addLast(item2);
		items.addLast(item3);
		
		SomeType deleted = items.remove(item4);
		if (deleted.toString().equals("Apple")){
			System.out.println("Yay5");
		}
	
	
		//add more test cases by yourself!
		
		
		
	}
}