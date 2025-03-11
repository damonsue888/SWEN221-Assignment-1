package swen221Ass1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a building with multiple addresses within a buildings bounding box.
 * @param boundingBox The {@link GeoBox} bounding box of the building.
 * @param primaryAddress The primary {@link Address} of the building.
 * @param secondaryAddresses Additional addresses of the building.
 */
public record MultiAddressBuilding(GeoBox boundingBox, Address primaryAddress, Set<Address> secondaryAddresses)
        implements Building {

    /**
     * Constructs a MultiAddressBuilding, ensuring the building meets validation requirements.
     *
     * @param boundingBox The {@link GeoBox} bounding box of the building (must not be null).
     * @param primaryAddress The primary {@link Address} of the building (must not be null).
     * @param secondaryAddresses Additional addresses of the building (must not be null).
     * @throws NullPointerException If any parameter is null.
     * @throws IllegalArgumentException If the building GeoBox does not overlap all of its addresses bounding boxes,
     * or the secondary addresses is empty.
     */
    public MultiAddressBuilding {
        Objects.requireNonNull(boundingBox, "Building boundingBox cannot be null");
        Objects.requireNonNull(primaryAddress, "Building primary address cannot be null");
        Objects.requireNonNull(secondaryAddresses, "Building secondary addresses cannot be null");

        // Copy to ensure immutability
        secondaryAddresses = new HashSet<>(secondaryAddresses);

        // Ensure the building meets the required constraints
        Set<Address> allAddresses = new HashSet<>(secondaryAddresses);
        allAddresses.add(primaryAddress);
        if (secondaryAddresses.isEmpty() || !overlapsAllAddresses(boundingBox,allAddresses)) {
            throw new IllegalArgumentException("MultiAddressBuilding doesn't meet requirements");
        }
    }

    /**
     * Returns an unmodifiable view of the secondary addresses.
     *
     * @return An unmodifiable set of secondary addresses.
     */
    @Override public Set<Address> secondaryAddresses() {
        return Collections.unmodifiableSet(secondaryAddresses);
    }
}
