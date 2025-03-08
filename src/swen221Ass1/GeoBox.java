package swen221Ass1;

import java.util.Objects;

public record GeoBox(GeoPoint ne, GeoPoint sw) {
    public GeoBox {
        Objects.requireNonNull(ne, "NorthEast (ne) cannot be null");
        Objects.requireNonNull(sw, "SouthWest (sw) cannot be null");
        if (!GeoBoxValidator.isValidGeoBox(ne, sw)) {
            throw new IllegalArgumentException("GeoBox doesn't meet requirements");
        }
    }
    public GeoPoint nw() {
        return GeoBoxCalculator.calculateNW(ne, sw);
    }

    public GeoPoint se() {
        return GeoBoxCalculator.calculateSW(ne, sw);
    }

    public GeoPoint center() {
        return GeoBoxCalculator.calculateCenter(ne, sw);
    }

    public boolean overlaps(GeoBox other) {
        return GeoBoxCalculator.overlaps(this, other);
    }
}
