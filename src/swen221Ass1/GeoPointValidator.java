package swen221Ass1;

public class GeoPointValidator {
    private static final int ABS_BOX_LAT_MAX = 80;
    private static final int LAT_ABS_MAX = 90;
    private static final int LONG_MIN = -180;
    private static final int LONG_MAX = 180;
    public GeoPointValidator() {} // prevent instantiation
    private static boolean isValidLat(double lat) {return Math.abs(lat) <= LAT_ABS_MAX;}
    private static boolean isValidBoxLat(double lat) {return Math.abs(lat) < ABS_BOX_LAT_MAX;}
    private static boolean isValidLong(double lon) {return lon >= LONG_MIN && lon < LONG_MAX;}
    public static boolean validate(double lat, double lon) {return isValidLat(lat) && isValidLong(lon);}
    public static boolean validateBoxPoint(GeoPoint geoPoint) {
        return isValidBoxLat(geoPoint.latitude()) && isValidLong(geoPoint.longitude());
    }
}
