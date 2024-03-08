import java.util.Iterator;
// Purpose: Implement a basic hash map with separate chaining to record the access history of the cache simulator.

// ONLY need to implement these THREE METHODS: put(), get(), and delete()
// Keep other code AS IS but you will need to add JavaDocs
/**
 * BasicMap class.
 * @author Aidan Emden
 * @param <K> the type of the key
 * @param <V> the type of the value
 */


public class BasicMap<K, V> {

    /**
     * The Pair class.
     */

    private class Pair {

        /**
         * The key of the pair.
         */

        private K key;

        /**
         * The value of the pair.
         */

        private V value;

        /**
         * Constructor for the Pair class.
         * 
         * @param key the key of the pair
         * @param value the value of the pair
         */
        public Pair(K key, V value){
            this.key = key;
            this.value = value;
        }
        /**
         * Returns the key of the pair.
         * 
         * @return the key of the pair
         */
        public K getKey(){ return key; }

        /**
         * Returns the value of the pair.
         * 
         * @return the value of the pair
         */
        public V getValue(){ return value; }

        /**
         * Sets the value of the pair.
         * 
         * @param value the value of the pair
         */
        public void setValue(V value){ this.value = value; }

        /**
         * Returns a string representation of the pair.
         * 
         * @return a string representation of the pair
         */
        @Override
        public String toString(){
            return "<"+key.toString()+":"+value.toString()+">";
        }
        /**
         * Returns true if the pair is equal to another pair.
         * 
         * @param o the other pair
         * @return true if the pair is equal to another pair
         */
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if(o instanceof BasicMap<?,?>.Pair) {
                Pair pair = (Pair)o;
                return pair.key.equals(key);  
            }
            return false;
        }

        /**
         * Returns the hash code of the pair.
         * 
         * @return the hash code of the pair
         */
        @Override
        public int hashCode(){
            return key.hashCode();
        }
    }   
    /**
     * The BasicList class.
     * 
     * @param <E> the type of the elements in the list
     */
    private BasicList<Pair>[] buckets;

    /**
     * The default capacity of the map.
     */
    final static private int DEFAULT_CAPACITY = 7;

    /**
     * The number of key-value pairs in the map.
     */
    private int size;

    /**
     * Constructor for the BasicMap class.
     */
    @SuppressWarnings("unchecked")
    public BasicMap() {
        buckets = (BasicList<Pair>[])new BasicList[DEFAULT_CAPACITY];
        size = 0;
    }
    /**
     * Returns the number of key-value pairs in the map.
     * 
     * @return the number of key-value pairs in the map
     */
    public int size() {
        return size;
    }
    /**
     * Returns the capacity of the map.
     * 
     * @return the capacity of the map
     */
    private int capacity() {
        return buckets.length;
    }

    /**
     * Returns the hash value of the key.
     * 
     * @param key the key
     * @return the hash value of the key
     */
    private int getHash(K key) {
        return Math.abs(key.hashCode());
    }
    /**
     * Returns a string representation of the map.
     * 
     * @return a string representation of the map
     */

    public String toStringDebug() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<buckets.length; i++) {		
            BasicList<Pair> list = buckets[i];	
            sb.append("[");	
            if (list != null) {
                sb.append(list.listToString());
            }
            sb.append("]");
            if (i!=buckets.length-1)
                sb.append(",");	  
        }
        return "{" + sb.toString() + "}";
    }

    /**
     * Returns a string representation of the map.
     * 
     * @return a string representation of the map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<buckets.length; i++) {		
            BasicList<Pair> list = buckets[i];	
            if (list != null) {
                if (sb.length()>0)
                    sb.append(",");	 
                sb.append(list.listToString());
            }
        }
        return sb.toString();
    }

    /**
     * Puts a key-value pair into the map.
     * 
     * @param key the key
     * @param value the value
     */
     
    public void put(K key, V value) {
        if(key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }

        int hash = getHash(key);
        int index = hash % capacity();

        if(buckets[index] == null) {
            buckets[index] = new BasicList<>();
        }

        BasicList<Pair> list = buckets[index];
        Iterator<Pair> current = list.iterator();

        while(current.hasNext()) {
            Pair match = current.next();
            if(match.getKey().equals(key)) {
                match.setValue(value);
                return;
            }
        }

        list.addLast(new Pair(key, value));
        size++;
    }

    /**
     * Returns the value associated with the key.
     * 
     * @param key the key
     * @return the value associated with the key
     */

    public V get(K key) {
        if(key == null) {
            return null;
        }

        int hash = getHash(key);
        int index = hash % capacity();
        BasicList<Pair> list = buckets[index];

        if(list == null) {
            return null;
        } else {
            Iterator<Pair> current = list.iterator();
            while(current.hasNext()) {
                Pair match = current.next();
                if(match.getKey().equals(key)) {
                    return match.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Deletes the key-value pair associated with the key.
     * 
     * @param key the key
     * @return the value associated with the key
     */

    public V delete(K key){
        if(key == null) {
            return null;
        }

        int hash = getHash(key);
        int index = hash % capacity();
        BasicList<Pair> list = buckets[index];

        if(list == null) {
            return null;
        } else {
            Iterator<Pair> current = list.iterator();
            while(current.hasNext()) {
                Pair match = current.next();
                if(match.getKey().equals(key)) {
                    V value = match.getValue();
                    list.remove(match);
                    size--;
                    return value;
                }
            }
        }

        return null;
    }

    /**
     * Main method to test the BasicMap class.
     * 
     * @param args command line arguments
     */
    public static void main(String args[]) {

        System.out.println("yo");
        BasicMap<String, String> map = new BasicMap<>();

        map.put("apple", "red");
        map.put("pear", "yellow");
        map.put("eggplant", "purple");

        if (map.get("apple").equals("red") && map.get("eggplant").equals("purple") && map.size() == 3){
            System.out.println("Yay1");
        }

        
        map.put("apple", "green");
        if (map.get("apple").equals("green") && map.size()==3 && map.delete("pear").equals("yellow") 
            && map.size() == 2) {
            System.out.println("Yay2");
        }

        if (map.get("banana")==null && map.delete("pear")==null){
            System.out.println("Yay3");		
        }

        map.put("cherry", "red");
        if (map.toStringDebug().equals("{[],[<apple:green> <cherry:red>],[],[],[],[<eggplant:purple>],[]}")){
            System.out.println("Yay4");		
        }
    }

    
}
    
