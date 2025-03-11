package swen221Ass1;

/**
 * Utility class providing the static method {@link #isValidGeoBox(GeoPoint, GeoPoint)}
 * to validate a {@link GeoBox}.
 * Cannot be instantiated.
 */
public final class GeoBoxValidator {

    // Max allowed span for a valid GeoBox.
    private static final int maxDegreeDifference = 30;

    // Private constructor to prevent instantiation.
    private GeoBoxValidator() {}

    /**
     * Checks if the latitude span between the North and South bounds are valid.
     *
     * @param ne The North-East point of the GeoBox.
     * @param sw The South-West point of the GeoBox.
     * @return {@code true} if the latitude span is within the valid range, {@code false} otherwise.
     */
    private static boolean isLatitudeSpanValid(GeoPoint ne, GeoPoint sw) {
        return isValidSpan(ne.latitude() - sw.latitude());
    }

    /**
     * Checks if the longitude span between the West and East bounds are valid.
     *
     * @param ne The North-East point of the GeoBox.
     * @param sw The South-West point of the GeoBox.
     * @return {@code true} if the longitude span is within the valid range, {@code false} otherwise.
     */
    private static boolean isLongitudeSpanValid(GeoPoint ne, GeoPoint sw) {
        return isValidSpan(GeoBoxCalculator.normalisedLongitudeDifference(ne.longitude(), sw.longitude()));
    }

    /**
     * Ensures that the span is within the allowed degree difference.
     *
     * @param span The difference in latitude or longitude.
     * @return {@code true} if the span is valid, {@code false} otherwise.
     */
    private static boolean isValidSpan(double span) {
        return span >= 0 && span <= maxDegreeDifference ;
    }

    /**
     * Validates if a given {@link GeoPoint} has a valid latitude and longitude.
     *
     * @param geoPoint The GeoPoint to validate.
     * @return {@code true} if the point has a valid latitude and longitude to be a corner in {@link GeoBox},
     * {@code false} otherwise.
     */
    private static boolean isValidBoxPoint(GeoPoint geoPoint) {
        return GeoPointValidator.isLatitudeWithinBounds(geoPoint.latitude(), 80) &&
                GeoPointValidator.isValidLongitude(geoPoint.longitude());
    }

    /**
     * Validates whether a given {@link GeoBox} is valid based on the following latitude and longitude constraints.
     * <ul>
     * <li>The North and South latitude bounds must be within [-80, 80].</li>
     * <li>The West and East longitude bounds must be within [-180, 180).</li>
     * <li>The latitude and longitude span of the box is no more than 30.</li>
     * <li>The NE point is truly North-East of the SW point.</li>
     * </ul>
     *
     * @param ne The North-East corner of the GeoBox.
     * @param sw The South-West corner of the GeoBox.
     * @return {@code true} if the GeoBox is valid, {@code false} otherwise.
     */
    public static boolean isValidGeoBox(GeoPoint ne, GeoPoint sw) {
        return isValidBoxPoint(ne) &&
                isValidBoxPoint(sw) &&
                isLatitudeSpanValid(ne, sw) &&
                isLongitudeSpanValid(ne, sw);
    }
}
