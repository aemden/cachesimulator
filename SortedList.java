// TO DO: add your implementation and JavaDocs.
/**
 * Sorted list extended from BasicList.
 * @param <T> the type of element in the list
 * @author Aidan Emden
 
 */

//sorted list extended from BasicList
public class SortedList<T extends Comparable<T>> extends BasicList<T> { 

	// add a value into current list;
	// make sure all values are sorted in an ascending order from start to end of the list

	// - you can assume before add() is called, all existing values are sorted	
	// - if there is a tie, insert the new value as the last (i.e. closest to 
	//   the end of the list) among all values that are tied.
	
	// - if value is null, throw an IllegalArgumentException (with any error msg)
	
	//O(n) where n is the number of items in list

	/**
	 * Adds a value to the list in ascending order.
	 * 
	 * @param value the value to add to the list
	 * @throws IllegalArgumentException if value is null
	 */
	
	public void add(T value){

		if (value == null) {
            throw new IllegalArgumentException();
        }
        Node<T> newNode = new Node<>(value);
        if (head == null || head.getData().compareTo(value) >= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null && current.getNext().getData().compareTo(value) <= 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }
		

	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******        Remember to add JavaDoc         *******
	//******************************************************

	/**
	 * Main method to test the SortedList class.
	 * @param args command line arguments
	 * 
	 */
	public static void main(String[] args) {
		SortedList<String> names = new SortedList<>();
		
		names.add("Mason");
		System.out.println(names.listToString());
		names.add("George");
		System.out.println(names.listToString());
		names.add("Washington");

		


		
		if (names.size()==3 && names.getFirst().equals("George") &&
			names.listToString().equals("George Mason Washington")){		
			System.out.println("Yay1");	
		}

		//System.out.println(names.listToString());
		
		/**
		 * Class to test the SortedList class.
		 */
		class SomeType implements Comparable<SomeType> {

			/**
			 * The value of the SomeType.
			 */
			private String value;

			/**
			 * Constructor for the SomeType class.
			 * 
			 * @param value the value of the SomeType
			 */
			public SomeType(String value) { this.value = value; }
			
			/**
			 * Compares the length of the value of this SomeType to the length of the value of another SomeType.
			 * 
			 * @param other the other SomeType to compare to
			 * @return the difference in length between the two values
			 */
			public int compareTo(SomeType other) {
				//use the length to compare
				return this.value.length() - other.value.length();
			}
			
			/**
			 * Returns the value of the SomeType.
			 * 
			 * @return the value of the SomeType
			 */
			public String toString(){ return value;}
		}	
		
		SomeType item1 = new SomeType("123");
		SomeType item2 = new SomeType("1234");
		SomeType item3 = new SomeType("12345");
		SomeType item4 = new SomeType("7890");
		
		
		SortedList<SomeType> items = new SortedList<>();
		items.add(item2);
		items.add(item1);
		items.add(item3);
		
		boolean ok = items.listToString().equals("123 1234 12345");
		items.add(item4);
		//add with a tie: 7890 should be inserted after 1234
		if ( ok && items.listToString().equals("123 1234 7890 12345")){
			System.out.println("Yay2");				
		}
		

		
	}	
}
