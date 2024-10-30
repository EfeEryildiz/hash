package demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main class
 */
public class Main {

    /**
     * The program entry point
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Create hash table
        MyHashTable hashTable = new MyHashTable();

        // Read data from file and populate hash table
        try {
            File file = new File("houses.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String owner = scanner.nextLine().trim();
                int value = Integer.parseInt(scanner.nextLine().trim());
                hashTable.add(new House(owner, value));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        } catch (NumberFormatException e) {
            System.out.println("Invalid");
        }

            // Print original hash table
            System.out.println("Original Hash Table:");
            hashTable.show();

            // Test copy constructor
            System.out.println("\nCopy Table:");
            MyHashTable copiedTable = new MyHashTable(hashTable);
            System.out.println("Copied Hash Table:");
            // Commented out to avoid printing the entire table
            // Uncomment below to see the copied table
            // copiedTable.show();

            // Test deepCopy
            System.out.println("\nDeep Copy Table:");
            MyHashTable deepCopiedTable = hashTable.deepCopy();
            System.out.println("Deep Copied Hash Table:");
            // Commented out to avoid printing the entire table
            // Uncomment below to see the deep copied table
            // deepCopiedTable.show();

            // Test find method with an existing owner in the data
            String testOwner = "Donald Cox";
            House found = hashTable.find(testOwner);
            System.out.println("\nSearching for owner: " + testOwner);
            if (found != null) {
                System.out.println("Found: " + found);
            } else {
                System.out.println("Owner not found");
            }

            // Test with an owner not in the data
            testOwner = "Tested Owner";
            found = hashTable.find(testOwner);
            System.out.println("\nSearching for owner: " + testOwner);
            if (found != null) {
                System.out.println("Found: " + found);
            } else {
                System.out.println("Owner not found");
            }

            // Verify that copies are independent by adding a house to the original hash table
            System.out.println("\nAdding a house to the original hash:");
            House newHouse = new House("Test Owner", 1000000);
            hashTable.add(newHouse);

            // Print sizes of each table
            // The original table should have one more house than the copied table and deep copied table
            System.out.println("Original Hash Table size: " + hashTable.getSize());
            System.out.println("Copied Hash Table size: " + copiedTable.getSize());
            System.out.println("Deep Copied Hash Table size: " + deepCopiedTable.getSize());

            // Adding a house to the original table should not affect the copied and deep copied tables
            System.out.println("\nSearching for 'Test Owner' in each table:");

            // Print the results of the find method for each table
            // The original table should find the house
            // The copied and deep copied tables should not find the house
            System.out.println("Original Table: " + (hashTable.find("Test Owner") != null ? "Found" : "Not Found"));
            System.out.println("Copied Table: " + (copiedTable.find("Test Owner") != null ? "Found" : "Not Found"));
            System.out.println("Deep Copied Table: " + (deepCopiedTable.find("Test Owner") != null ? "Found" : "Not Found"));
        }
    }