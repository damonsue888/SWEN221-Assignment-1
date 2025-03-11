package swen221Ass1;

import java.util.Objects;

/**
 * Represents a geographical bounding box defined by a North-East (ne) and a South-West (sw) coordinate.
 *
 * @param ne The North-East corner (must not be null).
 * @param sw The South-West corner (must not be null).
 */
public record GeoBox(GeoPoint ne, GeoPoint sw) {

    /**
     * Constructs a GeoBox instance and validates that the NE and SW points are valid according to
     * {@link GeoBoxValidator#isValidGeoBox(GeoPoint, GeoPoint)}
     *
     * @param ne The North-East corner (must not be null).
     * @param sw The South-West corner (must not be null).
     * @throws NullPointerException If NE or SW is null.
     * @throws IllegalArgumentException If the NE and SW points do not adhere to {@link GeoBoxValidator} requirements.
     */
    public GeoBox {
        Objects.requireNonNull(ne, "NorthEast (ne) cannot be null");
        Objects.requireNonNull(sw, "SouthWest (sw) cannot be null");

        if (!GeoBoxValidator.isValidGeoBox(ne, sw)) {
            throw new IllegalArgumentException("GeoBox doesn't meet requirements");
        }
    }

    /**
     * Computes the North-West (nw) corner of the GeoBox by taking the latitude of the NE point,
     * and the longitude of the SW point.
     *
     * @return The North-West corner.
     */
    public GeoPoint nw() {
        return new GeoPoint(ne.latitude(), sw.longitude());
    }

    /**
     * Computes the South-East (sw) corner of the GeoBox by taking the latitude of the SW point,
     * and the longitude of the SW point.
     *
     * @return The South-East corner.
     */
    public GeoPoint se() {
        return new GeoPoint(sw.latitude(), ne.longitude());
    }

    /**
     * Computes the center point of the GeoBox by averaging the NE and SW coordinates.
     *
     * @return The central point of the GeoBox.
     */
    public GeoPoint center() {
        return ne.average(sw);
    }

    /**
     * Determines whether this GeoBox geographically overlaps with another GeoBox.
     * Uses an external calculator to perform the check.
     *
     * @param other The other GeoBox to compare with.
     * @return 'true' if the two GeoBoxes overlap, 'false' otherwise.
     */
    public boolean overlaps(GeoBox other) {
        return GeoBoxCalculator.overlaps(this, other);
    }
}
