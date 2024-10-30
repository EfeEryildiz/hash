package demo;

/**
 * A singly-linked list implementation that stores House instances
 */
public class MyList {
    /**
     * Node class for the singly-linked list
     */
    private class Node {
        House data;
        Node next;

        /**
         * Constructs a new node with the given house data
         * @param house The house data to store in the node
         */
        Node(House house) {
            this.data = house;
            this.next = null;
        }

        /**
         * Creates a deep copy of a node
         * @param node The node to copy
         */
        Node(Node node) {
            this.data = node.data.deepCopy();
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Default constructor
     */
    public MyList() {
        head = null;
        size = 0;
    }

    /**
     * Copy constructor that creates a deep copy of another MyList
     * @param other The MyList to copy
     */
    public MyList(MyList other) {
        if (other.head == null) {
            return;
        }

        // Copy the first node
        head = new Node(other.head);
        Node current = head;
        Node otherCurrent = other.head.next;
        size = other.size;

        // Copy rest of the nodes
        while (otherCurrent != null) {
            current.next = new Node(otherCurrent);
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
    }

    /**
     * Creates a deep copy of the current MyList instance
     * @return A new MyList that is a deep copy of this list
     */
    public MyList deepCopy() {
        return new MyList(this);
    }

    /**
     * Adds a house to the start of the list
     * @param house The house to add
     */
    public void add(House house) {
        Node newNode = new Node(house);
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * Searches the list for a house with the given owner
     * @param owner The owner to search for
     * @return The House instance if found, null otherwise
     */
    public House find(String owner) {
        Node current = head;
        while (current != null) {
            if (current.data.getOwner().equals(owner)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Gets all houses in the list as an array
     * @return Array of houses in the list
     */
    public House[] getAllHouses() {
        House[] houses = new House[getSize()];
        Node current = head;
        int index = 0;
        while (current != null) {
            houses[index++] = current.data.deepCopy();
            current = current.next;
        }
        return houses;
    }

    /**
     * Gets the current size of the list
     * @return The number of elements in the list
     */
    public int getSize() {

        return size;
    }

    /**
     * Checks if the list is empty
     * @return True if the list is empty or false if not empty
     */
    public boolean isEmpty() {

        return head == null;
    }
}