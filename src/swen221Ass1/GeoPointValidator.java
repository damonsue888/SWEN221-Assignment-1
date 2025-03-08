package swen221Ass1;

public final class GeoPointValidator {
    private GeoPointValidator() {} // prevent instantiation
    private static boolean isValidLat(double lat) {return isValidLat(lat, 90);}
    public static boolean isValidLat(double lat, int absMax) {return Math.abs(lat) <= absMax;}
    public static boolean isValidLong(double lon) {return lon >= -180 && lon < 180;}
    public static boolean validate(double lat, double lon) {return isValidLat(lat) && isValidLong(lon);}
}
