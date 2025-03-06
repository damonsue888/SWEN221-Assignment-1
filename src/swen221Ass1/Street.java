package swen221Ass1;

import java.util.Objects;

public record Street(GeoBox boundingBox, String name, int length) {
    public Street {
        Objects.requireNonNull(boundingBox, "Street boundingBox cannot be null");
        Objects.requireNonNull(name, "Street name cannot be null");
        if (length <= 0) throw new IllegalArgumentException("Street length must be positive");
    }
    @Override
    public String toString() {return name;}
}