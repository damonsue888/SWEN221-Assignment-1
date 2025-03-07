package swen221Ass1;

import java.util.Objects;
import java.util.Set;

public interface Building {
    GeoBox boundingBox();
    Address primaryAddress();

    static Building of(GeoBox boundingBox, Address address) {
        return new SingleAddressBuilding(boundingBox, address);
    }
    static Building of(GeoBox boundingBox, Address address, Set<Address> addresses) {
        return new MultiAddressBuilding(boundingBox, address, addresses);
    }

    default Set<Address> secondaryAddresses() {return Set.of();}

    default boolean geoBoxNotCoveringAllAddresses(GeoBox boundingBox, Set<Address> addresses) {
        return addresses.stream().allMatch(address -> boundingBox.overlaps(address.street().boundingBox()));
    }
}
