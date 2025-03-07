package swen221Ass1;

import java.util.Objects;
import java.util.Set;

public record SingleAddressBuilding(GeoBox boundingBox, Address primaryAddress) implements Building {
    public SingleAddressBuilding {
        Objects.requireNonNull(boundingBox, "Building boundingBox cannot be null");
        Objects.requireNonNull(primaryAddress, "Building primary address cannot be bull");
        if (geoBoxNotCoveringAllAddresses(boundingBox, Set.of(primaryAddress))) {
            throw new IllegalArgumentException("SingleAddressBuilding doesn't meet the requirements");
        }
    }
}
