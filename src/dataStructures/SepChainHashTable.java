package dataStructures;
/**
 * SepChain Hash Table
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class SepChainHashTable<K,V> extends HashTable<K,V> {

    //Load factors
    static final float IDEAL_LOAD_FACTOR =0.75f;
    static final float MAX_LOAD_FACTOR =0.9f;

    // The array of Map with singly linked list.
    private Map<K,V>[] table;

    public SepChainHashTable( ){
        this(DEFAULT_CAPACITY);
    }
    
    public SepChainHashTable( int capacity ){
        super(capacity);
        int primeCapacity = nextPrime(capacity);

        this.table = (Map<K,V>[]) new Map[primeCapacity];

        for (int i = 0; i < primeCapacity; i++) {
            this.table[i] = new MapSinglyList<K,V>();
        }

        this.maxSize = (int)(primeCapacity * MAX_LOAD_FACTOR);
    }

    // Returns the hash value of the specified key.
    protected int hash( K key ){
        return Math.abs( key.hashCode() ) % table.length;
    }
    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     *
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key,
     * or null if the dictionary does not have an entry with that key
     */
    public V get(K key) {
        int pos = hash(key);
        return table[pos].get(key);
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with key,
     * or null if the dictionary does not have an entry with that key
     */
    public V put(K key, V value) {
        if (isFull())
            rehash();
        if (isFull()) {
            rehash();
        }

        int pos = hash(key);
        V oldValue = table[pos].put(key, value);

        if (oldValue == null) {
            currentSize++;
        }

        return oldValue;
    }


    private void rehash() {
        Map<K, V>[] oldTable = table;

        int newCapacity = nextPrime(table.length * 2);
        table = (Map<K, V>[]) new Map[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            table[i] = new MapSinglyList<>();
        }

        maxSize = (int) (newCapacity * MAX_LOAD_FACTOR);
        currentSize = 0;

        for (int i = 0; i < oldTable.length; i++) {
            Iterator<Map.Entry<K, V>> it = oldTable[i].iterator();
            while (it.hasNext()) {
                Map.Entry<K, V> entry = it.next();
                put(entry.key(), entry.value());
            }
        }
    }

        /**
         * If there is an entry in the dictionary whose key is the specified key,
         * removes it from the dictionary and returns its value;
         * otherwise, returns null.
         *
         * @param key whose entry is to be removed from the map
         * @return previous value associated with key,
         * or null if the dictionary does not an entry with that key
         */
        public V remove (K key){
            int pos = hash(key);
            V removed = table[pos].remove(key);

            if (removed != null) {
                currentSize--;
            }

            return removed;
        }

        /**
         * Returns an iterator of the entries in the dictionary.
         *
         * @return iterator of the entries in the dictionary
         */
        public Iterator<Entry<K, V>> iterator () {
            return new SepChainHashTableIterator<>(table);
        }


}
