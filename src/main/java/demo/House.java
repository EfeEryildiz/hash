package demo;

import java.util.Objects;

/**
 * Represents a house with an owner and value.
 */
public class House {
    private String owner;
    private int value;

    /**
     * Default constructor.
     */
    public House() {
        this.owner = "";
        this.value = 0;
    }

    /**
     * Parameterized constructor.
     *
     * @param owner The owner of the house.
     * @param value The value of the house.
     */
    public House(String owner, int value) {
        this.owner = owner;
        this.value = value;
    }

    /**
     * Copy constructor.
     *
     * @param other The House object to copy.
     */
    public House(House other) {
        this.owner = other.owner;
        this.value = other.value;
    }

    /**
     * Creates a deep copy of this House object.
     *
     * @return A new House object with the same owner and value.
     */
    public House deepCopy() {
        return new House(this);
    }

    /**
     * Gets the owner of the house.
     *
     * @return The owner's name.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the house.
     *
     * @param owner The new owner's name.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets the value of the house.
     *
     * @return The house's value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the house.
     *
     * @param value The new value of the house.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Compares the houses with each other.
     * @param obj The object to compare with.
     * @return true or false if the objects are equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        House house = (House) obj;
        return owner.equals(house.owner);
    }

    /**
     * Generates a hash code for the House object using the owner field.
     * @return A hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(owner);
    }

    /**
     * Returns the house's owner and value.
     * @return The owner and value of the house.
     */
    @Override
    public String toString() {
        return "House: owner=" + owner + ", value=" + value;
    }
}