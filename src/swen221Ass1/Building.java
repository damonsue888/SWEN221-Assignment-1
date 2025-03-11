package swen221Ass1;

import java.util.Set;

/**
 * Represents a building with a GeoBox bounding box and an address.
 */
public interface Building {

    /**
     * Gets the bounding box that defines the geographical bounds of the building.
     *
     * @return the bounding box of the building.
     */
    GeoBox boundingBox();

    /**
     * Gets the primary address of the building.
     *
     * @return the primary address of the building.
     */
    Address primaryAddress();

    /**
     * Creates a building with a single primary address.
     * THe returned instance does not support multiple addresses.
     *
     * @param boundingBox The geographical bounding box of the building.
     * @param address The primary address of the building.
     * @return A {@link SingleAddressBuilding} instance.
     */
    static Building of(GeoBox boundingBox, Address address) {
        return new SingleAddressBuilding(boundingBox, address);
    }

    /**
     * Creates a building with multiple addresses.
     *
     * @param boundingBox The geographical bounding box of the building.
     * @param address The primary address of the building.
     * @param addresses Additional addresses of the building.
     * @return A {@link MultiAddressBuilding} instance.
     */
    static Building of(GeoBox boundingBox, Address address, Set<Address> addresses) {
        return new MultiAddressBuilding(boundingBox, address, addresses);
    }

    /**
     * Gets the secondary addresses of the building.
     * By default, a building has no secondary addresses.
     *
     * @return An unmodifiable set of secondary addresses (empty if none).
     */
    default Set<Address> secondaryAddresses() {
        return Set.of();
    }

    /**
     * Checks whether the buildings bounding box overlaps with the bounding boxes of the streets for all given addresses.
     * Required to be {@code true} for a valid building instance.
     *
     * @param boundingBox The bounding box of the building to check against.
     * @param addresses The set of all addresses of the building.
     * @return {@code true} if the bounding box overlaps with all addresses, {@code false} otherwise.
     */
    default boolean overlapsAllAddresses(GeoBox boundingBox, Set<Address> addresses) {
        return addresses.stream().allMatch(address -> boundingBox.overlaps(address.street().boundingBox()));
    }
}
