package swen221Ass1;

/**
 * Utility class for validating a {@link GeoPoint}.
 * Ensures latitude and longitude values fall within valid ranges.
 * This class cannot be instantiated.
 */
public final class GeoPointValidator {

    // Private instructor to prevent instantiation
    private GeoPointValidator() {}

    /**
     * Checks whether the given latitude is within the specified absolute maximum.
     *
     * @param lat The latitude value to check.
     * @param absMax The absolute maximum allowed latitude.
     * @return {@code true} if the latitude is within bounds, {@code false} otherwise.
     */
    public static boolean isLatitudeWithinBounds(double lat, int absMax) {
        return Math.abs(lat) <= absMax;
    }

    /**
     * Checks whether the given latitude is valid within the default range [-90, 90].
     *
     * @param lat The latitude value to check.
     * @return {@code true} if the latitude is valid, {@code false} otherwise.
     */
    private static boolean isValidLatitude(double lat) {
        return isLatitudeWithinBounds(lat, 90);
    }

    /**
     * Checks whether the given longitude is within the range [-180, 180).
     *
     * @param lon The longitude value to check.
     * @return {@code true} if the longitude is valid, {@code false} otherwise.
     */
    public static boolean isValidLongitude(double lon) {
        return lon >= -180 && lon < 180;
    }

    /**
     * Validates whether the given latitude and longitude pair form a valid {@link GeoPoint}.
     *
     * @param lat The latitude value.
     * @param lon The longitude value.
     * @return {@code true} if both the latitude and longitude are valid, {@code false} otherwise.
     */
    public static boolean isValidGeoPoint(double lat, double lon) {
        return isValidLatitude(lat) && isValidLongitude(lon);
    }
}
