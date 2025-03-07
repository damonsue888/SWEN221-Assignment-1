package swen221Ass1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record MultiAddressBuilding(GeoBox boundingBox, Address primaryAddress, Set<Address> secondaryAddresses)
        implements Building {

    public MultiAddressBuilding {
        Objects.requireNonNull(boundingBox, "Building boundingBox cannot be null");
        Objects.requireNonNull(primaryAddress, "Building primary address cannot be bull");
        Objects.requireNonNull(secondaryAddresses, "Building secondary addresses cannot be null");
        secondaryAddresses = new HashSet<>(secondaryAddresses);
        if (secondaryAddresses.isEmpty() ||
                geoBoxNotCoveringAllAddresses(boundingBox, allAddresses(primaryAddress, secondaryAddresses))) {
            throw new IllegalArgumentException("MultiAddressBuilding doesn't meet requirements");
        }
    }

    private Set<Address> allAddresses(Address primaryAddress, Set<Address> secondaryAddresses) {
        Set<Address> allAddresses = new HashSet<>(secondaryAddresses);
        allAddresses.add(primaryAddress);
        return allAddresses;
    }

    @Override
    public Set<Address> secondaryAddresses() {return Collections.unmodifiableSet(secondaryAddresses);}
}
