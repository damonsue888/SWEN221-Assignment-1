package swen221Ass1;

public final class GeoValidation {
    private static final int LAT_MIN = -90;
    private static final int LAT_MAX = 90;
    private static final int LONG_MIN = -180;
    private static final int LONG_MAX = 180;
    private static final int BOX_LAT_MIN = -80;
    private static final int BOX_LAT_MAX = 80;
    private static final int MAX_DEGREE_DIFFERENCE = 30;

    private GeoValidation() {} // prevent instantiation

    private static boolean isValidVal(double val, int min, int max) {return val >= min && val <= max;}

    public static boolean isValidLat(double lat) {return isValidVal(lat, LAT_MIN, LAT_MAX);}
    public static boolean isValidLong(double lon) {return lon >= LONG_MIN && lon < LONG_MAX;}

    public static boolean isValidBoxLat(double lat) {return isValidVal(lat, BOX_LAT_MIN, BOX_LAT_MAX);}

    private static boolean isValidBoxPoint(GeoPoint geoPoint) {
        return isValidBoxLat(geoPoint.latitude()) && isValidLong(geoPoint.longitude());
    }

    private static double normalisedLongitude(double lon) {return lon >= 0 ? lon : 2 * LONG_MAX + lon;}

    private static boolean latsValid(GeoPoint ne, GeoPoint sw) {
        double latitudeSpan = Math.abs(ne.latitude() - sw.latitude());
        return ne.latitude() >= sw.latitude() && isValidSpan(latitudeSpan);
    }

    private static boolean isValidSpan(double span) {return span <= MAX_DEGREE_DIFFERENCE;}

    private static boolean longsValid(GeoPoint ne, GeoPoint sw) {
        double neNormalisedLong = normalisedLongitude(ne.longitude());
        double swNormalisedLong = normalisedLongitude(sw.longitude());
        double longitudeSpan = neNormalisedLong - swNormalisedLong;

        // if the sw longitude is able to wrap around the meridian to a valid positive longitude
        if (isValidSpan(2 * LONG_MAX - swNormalisedLong)) neNormalisedLong += 2 * LONG_MAX;
        return neNormalisedLong >= swNormalisedLong && isValidSpan(longitudeSpan);
    }

    public static boolean isValidGeoBox(GeoPoint ne, GeoPoint sw) {
        return isValidBoxPoint(ne) && isValidBoxPoint(sw) && latsValid(ne, sw) && longsValid(ne, sw);
    }
}
