package swen221Ass1;

public class GeoPointValidator {
    private static final int LAT_MIN = -90;
    private static final int LAT_MAX = 90;
    private static final int LONG_MIN = -180;
    private static final int LONG_MAX = 180;

    private GeoPointValidator() {} // prevent instantiation

    private static boolean isValidVal(double val, int min, int max) {return val >= min && val <= max;}

    public static boolean isValidLat(double lat) {return isValidVal(lat, LAT_MIN, LAT_MAX);}
    public static boolean isValidLong(double lon) {return lon >= LONG_MIN && lon < LONG_MAX;}


}
