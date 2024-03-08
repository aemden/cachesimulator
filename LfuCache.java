import java.util.Iterator;

/**
 * Cache implementing LFU replacement policy.
 * @author Aidan Emden
 */



//Cache implementing LFU replacement policy
public class LfuCache implements Cache {

    //******************************************************
    //*******     BELOW THIS LINE IS PROVIDED code   *******
    //*******             Do NOT edit code!          *******
    //*******		   Remember to add JavaDoc		 *******
    //******************************************************

    //wrap each item we need to save in cache in a block and keep the access count


    /**
     * The Block class.
     */
    private class Block implements Comparable<Block> {

        
        /**
         * The data item to store in cache.
         */
        private String data;

        

        /**
         * How many times this item is accessed since it is loaded in.
         */
        private int count;

        //constructor
        /**
         * Constructor for a block with address addr.
         * 
         * @param addr the address of the block
         */

        public Block(String addr){
            this.data = addr;
            count = 1;
        }

        //getter of count
        /**
         * Getter of count.
         * 
         * @return the count of the block
         */

        @SuppressWarnings("unused")
        public int getCount(){
            return count;
        }

        //increment count 
        /**
         * Increments the count of the block.
         */
        public void incCount(){
            count++;
        }

        //getter of data item
        /**
         * Getter of data item.
         * @return the data item of the block
         */

        public String getData(){
            return data;
        }

        //used to determine whether an item is already in cache
        /**
         * Used to determine whether an item is already in cache.
         * 
         * @param other the other block to compare
         * @return true if the blocks are equal, false otherwise
         */

        @Override
        public boolean equals(Object other){
            if (other instanceof Block){
                if (((Block)other).data.equals(this.data)){
                    return true;
                }
            }
            return false;
        }

        //used to compare two items based on their access counts
        /**
         * Used to compare two items based on their access counts.
         * 
         * @param other the other block to compare
         * @return the difference between the counts of the two blocks
         */

        public int compareTo(Block other){
            return this.count - other.count;
        }

        /**
         * String representation including access count.
         * 
         * @return the string representation of the block
         */
        @Override
        public String toString(){
            return "<"+data.toString()+","+count+">";
        }
    }

    /**
     * The capacity of the cache.
     */

    private int capacity;

    /**
     * The storage of the cache.
     */
    private SortedList<Block> storage; //NOTE: SortedList! List of Blocks! 

    //******************************************************
    //*******    	END of PROVIDED Code 	 		 *******
    //*******    	Do NOT Change PROVIDED Code 	 *******
    //******************************************************

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

    public LfuCache(int cap){
        if(cap <= 0){
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        capacity = cap;
        storage = new SortedList<Block>();
    }

    //return true if cache is full; false otherwise
    //O(1)

    /**
     * Returns true if the cache is full; false otherwise.
     * 
     * @return true if the cache is full; false otherwise
     */

    @Override
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
     * Return item that will be evicted if the next access is a miss.
     * 
     * @return item that will be evicted if the next access is a miss. Return null if no item will be evicted
     */

    public String nextToReplace(){
        if(storage.size() == 0){
            return null;
        }
        return storage.getFirst().getData();

    }

    //determine whether the access to addr is a hit or miss
    //return true for a hit and false for a miss
    // perform necessary updating to maintain the LFU cache
    // - if addr is null, throw an IllegalArgumentException (with any error msg)
    //O(n) where n is the number of items in cache

    /**
     * Determine whether the access to addr is a hit or miss.
     * 
     * @param addr the address requested by the next access
     * @return true if the access is a cache hit, false if it is a cache miss
     * @throws IllegalArgumentException if addr is null
     */

    public boolean access(String addr){
        if(addr == null){
            throw new IllegalArgumentException("Address cannot be null.");
        }
        Block newBlock = new Block(addr);
        Iterator<Block> iter = storage.iterator();
        while(iter.hasNext()){
            Block current = iter.next();
            if(current.equals(newBlock)){
                current.incCount();
                storage.remove(current);
                storage.add(current);
                return true;
            }
        }
        if(isFull()){
            storage.remove(storage.getFirst());
        }
        storage.add(newBlock);
        return false;
    }


    //return a string representing all items in cache
    // - follow the order from LFU to MFU
    // - if there is a tie, items should be included from LRU to MRU
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
        for(Block b : storage){
            sb.append(b.toString());
        }
        return sb.toString().trim();
    }
}
