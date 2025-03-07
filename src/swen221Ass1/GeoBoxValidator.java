package swen221Ass1;

public final class GeoBoxValidator {
    private static final int MAX_DEGREE_DIFFERENCE = 30;
    private GeoBoxValidator() {} // prevent instantiation

    private static boolean latsValid(GeoPoint ne, GeoPoint sw) {return isValidSpan(ne.latitude() - sw.latitude());}

    private static boolean isValidSpan(double span) {return span <= MAX_DEGREE_DIFFERENCE && span >= 0;}
    private static boolean longsValid(GeoPoint ne, GeoPoint sw) {
        double diff = ne.longitude() - sw.longitude() % 360;
        return isValidSpan(diff + (diff < 0 ? 360 : 0));
    }

    public static boolean isValidGeoBox(GeoPoint ne, GeoPoint sw) {
        return GeoPointValidator.validateBoxPoint(ne) && GeoPointValidator.validateBoxPoint(sw) &&
                latsValid(ne, sw) && longsValid(ne, sw);
    }
}
