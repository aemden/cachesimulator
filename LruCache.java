/**
 * Cache implementing LRU replacement policy.
 * @author Aidan Emden
 */


//Cache implementing LRU replacement policy


public class LruCache implements Cache { 
	
	/**
	 * The capacity of the cache.
	 */
	private int capacity;

	/**
     * The storage of the cache.
     */
	private BasicList<String> storage; //each address is a string

	// YOU CANNOT ADD MORE DATA MEMBERS!
	// ADD PRIVATE HELPER METHODS IF NEEDED!
	
	//constructor for a cache with capacity as cap	
	// - if cap is not positive, throw an IllegalArgumentException (with any error msg)
	//O(1)

	/**
	 * Constructor for a cache with capacity as cap.
	 * 
	 * @param cap the capacity of the cache
	 * @throws IllegalArgumentException if cap is not positive
	 */

	public LruCache(int cap){
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity must be positive.");
		}
		capacity = cap;
		storage = new BasicList<String>();
	}
	
	//return true if cache is full; false otherwise
	//O(1)

	/**
	 * Returns true if the cache is full; false otherwise.
	 * 
	 * @return true if the cache is full; false otherwise
	 */

	public boolean isFull(){
		if(storage.size() == capacity){
			return true;
		}
		return false;
	}


	//report max number of items allowed in cache
	//O(1)

	/**
     * Reports max number of items allowed in cache.
	 * 
	 * @return max number of items allowed in cache
     */
	public int capacity(){
		return capacity;

	}

	//report the number of items stored in cache
	//O(1)

	/**
	 * Reports the number of items stored in cache.
	 * 
	 * @return number of items stored in cache
	 */

	public int size(){
		return storage.size();
	}
	
	//return item that will be evicted if the next access is a miss
	//return null if no item will be evicted
	//O(1)

	/**
	 * Reports the item that will be evicted if the next access is a miss.
	 * 
	 * @return item that will be evicted if the next access is a miss. Return null if no item will be evicted
	 */

	public String nextToReplace(){
		if(storage.size() == 0){
			return null;
		}
		return storage.getFirst();
	}
	
	//determine whether the access to addr is a hit or miss
	//return true for a hit and false for a miss
	// perform necessary updating to maintain the LRU cache
	// - if addr is null, throw an IllegalArgumentException (with any error msg)
	//O(n) where n is the number of items in cache

	/**
	 * This is the method that accepts the addr as the next access to cache and performs necessary maintenance based on a cache replacement policy.
	 * 
	 * @param addr the address requested by the next access	
	 * @return true if the access is a cache hit, false if it is a cache miss
	 */

	public boolean access(String addr){
		if(addr == null){
			throw new IllegalArgumentException("Address cannot be null.");
		}

		for(String s: storage){
			if(s.equals(addr)){
				storage.remove(s);
				storage.addLast(addr);
				return true;
			}
		}

		if(storage.size() == capacity){
			storage.removeFirst();
		}
			
		storage.addLast(addr);
		return false;
	}

			
	//return a string representing all items in cache
	// - follow the order from LRU to MRU
	//return an empty string if cache is empty
	//O(n) where n is the number of items in cache

	/**
	 * Returns a string representing all items in cache.
	 * 
	 * @return a string representing all items in cache
	 */
	
	@Override
	public String toString(){
		if(storage.size() == 0){
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for(String s : storage){
			sb.append(s + " ");
		}
		return sb.toString().trim();
	}
	
}
