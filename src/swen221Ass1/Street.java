package swen221Ass1;

import java.util.Objects;

/**
 * Represents a street with a bounding box, name, and a length.
 *
 * @param boundingBox The bounding box of the street (must not be null).
 * @param name The name of the street (must not be null).
 * @param length The length of the street in meters (must not be null).
 */
public record Street(GeoBox boundingBox, String name, int length) {

    /**
     * Constructs a new street and ensures all parameters are valid.
     *
     * @param boundingBox The bounding box of the street (must not be null).
     * @param name The name of the street (must not be null).
     * @param length The length of the street in meters (must be positive).
     * @throws NullPointerException If the bounding box or name is null.
     * @throws IllegalArgumentException If the length is not positive.
     */
    public Street {
        Objects.requireNonNull(boundingBox, "Street boundingBox cannot be null");
        Objects.requireNonNull(name, "Street name cannot be null");

        if (length <= 0) {
            throw new IllegalArgumentException("Street length must be positive");
        }
    }

    /**
     * Returns a string representation of the street.
     *
     * @return A string containing the street name.
     */
    @Override public String toString() {
        return name;
    }
}