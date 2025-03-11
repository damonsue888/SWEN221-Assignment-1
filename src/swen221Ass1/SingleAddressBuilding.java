package swen221Ass1;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a building with a single address.
 *
 * @param boundingBox The {@link GeoBox} bounding box of the building.
 * @param primaryAddress The primary {@link Address} of the building.
 */
public record SingleAddressBuilding(GeoBox boundingBox, Address primaryAddress) implements Building {

    /**
     * Constructs a SingleAddressBuilding ensuring the building meets validation requirements.
     *
     * @param boundingBox The {@link GeoBox} bounding box of the building (must not be null).
     * @param primaryAddress The primary {@link Address} of the building (must not be null).
     * @throws NullPointerException If any parameter is null.
     * @throws IllegalArgumentException If the building GeoBox does not overlap the GeoBox of its primary address.
     */
    public SingleAddressBuilding {
        Objects.requireNonNull(boundingBox, "Building boundingBox cannot be null");
        Objects.requireNonNull(primaryAddress, "Building primary address cannot be null");

        // Validate that the building overlaps with its primary address.
        if (!overlapsAllAddresses(boundingBox, Set.of(primaryAddress))) {
            throw new IllegalArgumentException("SingleAddressBuilding doesn't meet the requirements");
        }
    }
}
