package swen221Ass1;

/**
 * Represents a geographical point with a latitude and longitude.
 * Ensures that the latitude and longitude values are valid.
 *
 * @param latitude The latitude of the point.
 * @param longitude The longitude of the point.
 */
public record GeoPoint(double latitude, double longitude) {

    /**
     * Constructs a GeoPoint and externally validates its latitude and longitude.
     * {@link GeoPointValidator#isValidGeoPoint(double, double)}
     *
     * @param latitude The latitude of the point.
     * @param longitude The longitude of the point.
     * @throws IllegalArgumentException If the latitude or longitude are invalid.
     */
    public GeoPoint {
        if (!GeoPointValidator.isValidGeoPoint(latitude, longitude)) {
            throw new IllegalArgumentException("GeoPoint latitude and/or longitude values are out of range");
        }
    }

    /**
     * Externally computes the average of this GeoPoint with another GeoPoint.
     *
     * @param otherPoint The other GeoPoint to average with.
     * @return A new GeoPoint representing the average position.
     */
    public GeoPoint average(GeoPoint otherPoint) {
        return GeoPointCalculator.average(this, otherPoint);
    }

    /**
     * Returns a default string representation of the GeoPoint with its latitude and longitude to 8 decimal places.
     *
     * @return A string representation of a GeoPoint.
     */
    @Override public String toString() {
        return toString(8);
    }

    /**
     * Returns a formatted string representation of a GeoPoint with the specified number of decimal places.
     *
     * @param precision The number of decimal places to round the latitude and longitude to.
     * @return A formatted string representing a GeoPoint.
     */
    public String toString(int precision) {
        return String.format("[latitude: %." + precision + "f, longitude: %." + precision +"f]", latitude, longitude);
    }
}
