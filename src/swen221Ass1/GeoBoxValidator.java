package swen221Ass1;

public final class GeoBoxValidator {
    private static final int MAX_DEGREE_DIFFERENCE = 30;
    private static final int MAX_NORMALISED_LONG = 360;
    private GeoBoxValidator() {} // prevent instantiation

    private static double normalisedLongitude(double lon) {return lon >= 0 ? lon : MAX_NORMALISED_LONG + lon;}

    private static boolean latsValid(GeoPoint ne, GeoPoint sw) {return isValidSpan(ne.latitude() - sw.latitude());}
        //double latitudeSpan = Math.abs(ne.latitude() - sw.latitude());
        //return ne.latitude() >= sw.latitude() && isValidSpan(latitudeSpan);

    private static boolean isValidSpan(double span) {return span <= MAX_DEGREE_DIFFERENCE && span >= 0;}

    private static boolean longsValid(GeoPoint ne, GeoPoint sw) {
        double swNormalisedLong = normalisedLongitude(sw.longitude());
        // if the sw longitude is able to wrap around the meridian to a valid positive longitude, add 360
        double neNormalisedLong = normalisedLongitude(ne.longitude()) +
                (isValidSpan(MAX_NORMALISED_LONG - swNormalisedLong) ? 360 : 0);
        return isValidSpan(neNormalisedLong - swNormalisedLong);
        //double longitudeSpan = neNormalisedLong - swNormalisedLong;
        //return neNormalisedLong >= swNormalisedLong && isValidSpan(longitudeSpan);
    }

    public static boolean isValidGeoBox(GeoPoint ne, GeoPoint sw) {
        return GeoPointValidator.validateBoxPoint(ne) && GeoPointValidator.validateBoxPoint(sw) &&
                latsValid(ne, sw) && longsValid(ne, sw);
    }
}
