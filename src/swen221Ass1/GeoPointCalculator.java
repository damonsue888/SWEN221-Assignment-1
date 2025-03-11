package swen221Ass1;

/**
 * A utility class for performing calculations for a {@link GeoPoint}.
 * Provides the method {@link GeoPointCalculator#average(GeoPoint, GeoPoint)} to correctly average two GeoPoints,
 * handling the anti meridian.
 * This class cannot be instantiated.
 */
public final class GeoPointCalculator {

    // Private constructor to prevent instantiation.
    private GeoPointCalculator() {}

    /**
     * Determines whether the average longitude between two longitudes involves crossing the antimeridian.
     *
     * @param longitude The longitude of the first GeoPoint.
     * @param otherLongitude The longitude of the second GeoPoint.
     * @return {@code true} if averaging involves crossing the antimeridian, {@code false} otherwise.
     */
    private static boolean doesCrossAntimeridian(double longitude, double otherLongitude) {
        return Math.abs(longitude - otherLongitude) > 180;
    }

    /**
     * Computes the necessary adjustment to the raw averaged longitude when crossing the antimeridian.
     *
     * @param longitude The longitude of the first GeoPoint.
     * @param otherLongitude The longitude of the second GeoPoint.
     * @return The adjustment value (+180 or -180) to correctly adjust the average longitude value.
     */
    private static int calculateLongitudeAdjustment(double longitude, double otherLongitude) {
        return longitude > otherLongitude ? 180 : -180;
    }

    /**
     * Computes the average of two GeoPoints, handling the antimeridian.
     *
     * @param geoPoint The first GeoPoint.
     * @param otherPoint The second GeoPoint.
     * @return A new GeoPoint representing the midpoint between the two given GeoPoints.
     */
    public static GeoPoint average(GeoPoint geoPoint, GeoPoint otherPoint) {
        double avgLat = (geoPoint.latitude() + otherPoint.latitude()) / 2;
        double avgLon = (geoPoint.longitude() + otherPoint.longitude()) / 2;
        
        // Adjust the longitude if the midpoint involves crossing the antimeridian
        if (doesCrossAntimeridian(geoPoint.longitude(), otherPoint.longitude())) {
            avgLon += calculateLongitudeAdjustment(geoPoint.longitude(), otherPoint.longitude());
        }

        return new GeoPoint(avgLat, avgLon);
    }
}
