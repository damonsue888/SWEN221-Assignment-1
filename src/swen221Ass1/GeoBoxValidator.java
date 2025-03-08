package swen221Ass1;

public final class GeoBoxValidator {
    private static final int maxDegreeDifference = 30;
    private GeoBoxValidator() {} // prevent instantiation

    private static boolean latsValid(GeoPoint ne, GeoPoint sw) {
        return isValidSpan(ne.latitude() - sw.latitude());
    }

    private static boolean isValidSpan(double span) {return span <= maxDegreeDifference && span >= 0;}
    private static boolean longsValid(GeoPoint ne, GeoPoint sw) {
        return isValidSpan((ne.longitude() - sw.longitude() + 360) % 360);
    }

    private static boolean isValidBoxPoint(GeoPoint geoPoint) {
        return GeoPointValidator.isValidLat(geoPoint.latitude(), 80) &&
                GeoPointValidator.isValidLong(geoPoint.longitude());
    }

    public static boolean isValidGeoBox(GeoPoint ne, GeoPoint sw) {
        return isValidBoxPoint(ne) && isValidBoxPoint(sw) && latsValid(ne, sw) && longsValid(ne, sw);
    }
}
