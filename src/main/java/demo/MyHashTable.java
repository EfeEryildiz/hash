package demo;

/**
 * A hash table using separate chaining with MyList for storing House instances
 */
public class MyHashTable {
    private MyList[] buckets;
    private int size;
    private double loadFactor = 0.75;
    private int bucketSize = 4;

    /**
     * Default constructor. Initializes hash table with 4 buckets.
     */
    public MyHashTable() {
        buckets = new MyList[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            buckets[i] = new MyList();
        }
        size = 0;
    }

    /**
     * Copy constructor that creates a deep copy of another MyHashTable.
     * @param other The MyHashTable to copy.
     */
    public MyHashTable(MyHashTable other) {
        this.buckets = new MyList[other.buckets.length];
        this.size = other.size;

        // Create deep copy of each bucket
        for (int i = 0; i < other.buckets.length; i++) {
            this.buckets[i] = other.buckets[i].deepCopy();
        }
    }

    /**
     * Creates a deep copy of the current MyHashTable instance
     * @return A new MyHashTable that is a deep copy of this table
     */
    public MyHashTable deepCopy() {
        return new MyHashTable(this);
    }

    /**
     * Adds a house to the hash table
     * Resizes if load factor exceeds 0.75
     * @param house The house to add
     */
    public void add(House house) {
        // Check if resize is needed
        if ((double) (size + 1) / buckets.length > loadFactor) {
            resize();
        }

        // Get hash value and calculate bucket index using mod
        int hashValue = house.hashCode();
        int bucketIndex = Math.abs(hashValue % buckets.length);

        // Add house to appropriate bucket
        buckets[bucketIndex].add(house);
        size++;
    }

    /**
     * Finds a house by owner name
     * @param owner The owner to search for
     * @return The House if found otherwise null
     */
    public House find(String owner) {
        // Create temporary house to get hash code
        House temp = new House(owner, 0);
        int hashValue = temp.hashCode();
        int bucketIndex = Math.abs(hashValue % buckets.length);

        // Search in the appropriate bucket
        return buckets[bucketIndex].find(owner);
    }

    /**
     * Displays the contents of the hash table by bucket
     */
    public void show() {
        System.out.println("\nHash Table:");
        System.out.println("Total number of houses: " + size);
        System.out.println("Number of buckets: " + buckets.length);
        System.out.println("Current load factor: " + String.format("%.2f", (double) size / buckets.length));
        System.out.println("\n");

        for (int i = 0; i < buckets.length; i++) {
            System.out.println("\nBucket " + i + ":");
            if (buckets[i].isEmpty()) {
                System.out.println("  Empty");
            } else {
                House[] houses = buckets[i].getAllHouses();
                for (House house : houses) {
                    System.out.println("  " + house.toString());
                }
            }
        }
        System.out.println();
    }

    /**
     * Resizes the hash table
     */
    private void resize() {
        // Store old buckets
        MyList[] oldBuckets = buckets;

        // Create new array with double capacity
        buckets = new MyList[oldBuckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new MyList();
        }

        // Reset size to 0
        size = 0;

        // Rehash all existing elements
        for (MyList bucket : oldBuckets) {
            if (!bucket.isEmpty()) {
                House[] houses = bucket.getAllHouses();
                for (House house : houses) {
                    add(house);
                }
            }
        }
    }

    /**
     * Gets the current number of houses in the hash table
     * @return The size of the hash table
     */
    public int getSize() {
        return size;
    }
}