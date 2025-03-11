package swen221Ass1;

/**
 * Utility class primarily providing the static method overlaps(...) to determine whether a
 * {@link GeoBox} overlaps with another {@link GeoBox}.
 * Cannot be instantiated.
 */
public final class GeoBoxCalculator {

    //Private constructor to prevent instantiation.
    private GeoBoxCalculator() {}

    /**
     * Calculates the normalised difference between two longitudes, accounting for wrapping around the anti-meridian.
     *
     * @param longOne The longitude of the first point.
     * @param longTwo The longitude of the second point.
     * @return The normalised longitude difference in the range [0, 360). A value less than 180 indicates that longOne
     * is West of longTwo, while a value greater than 180 indicates that longOne is East of longTwo.
     */
    public static double normalisedLongitudeDifference(double longOne, double longTwo) {
        // Calculates the longitude difference between the leftmost point of otherBox,
        // and the rightmost point of geoBox
        double rawLongitudeDifference = longOne - longTwo;

        // Handles wrapping around the anti meridian.
        // Adding 360 ensures the result is positive, and modulo 360 keeps it within the range [0, 360).
        return (rawLongitudeDifference + 360) % 360;
    }

    /**
     * Checks if one GeoBox is completely left of another.
     *
     * @param geoBox The first bounding box.
     * @param otherBox The second bounding box.
     * @return {@code true} if geoBox is entirely to the left of otherBox, {@code false} otherwise.
     */
    private static boolean isLeftOf(GeoBox geoBox, GeoBox otherBox) {
        return normalisedLongitudeDifference(otherBox.sw().longitude(), geoBox.ne().longitude()) < 180;
    }

    /**
     * Checks if one GeoBox is completely above another.
     *
     * @param geoBox The first bounding box.
     * @param otherBox The second bounding box.
     * @return {@code true} if geoBox is entirely to above otherBox, {@code false} otherwise.
     */
    private static boolean isAbove(GeoBox geoBox, GeoBox otherBox) {
        return geoBox.sw().latitude() > otherBox.ne().latitude();
    }

    /**
     * Determines whether two geographical bounding boxes overlap.
     *
     * @param geoBox The first bounding box.
     * @param otherBox The second bounding box.
     * @return {@code true} if the two boxes overlap, {@code false} otherwise.
     */
    public static boolean overlaps(GeoBox geoBox, GeoBox otherBox) {
        return !(isLeftOf(geoBox, otherBox) || isLeftOf(otherBox, geoBox) ||
                isAbove(geoBox, otherBox) || isAbove(otherBox, geoBox));
    }
}
