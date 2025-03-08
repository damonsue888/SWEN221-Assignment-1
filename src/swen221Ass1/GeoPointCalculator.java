package swen221Ass1;

public final class GeoPointCalculator {
    private GeoPointCalculator() {}

    private static int getOffset(double longitude, double otherLongitude) {
        return longitude > otherLongitude ? 180 : -180;
    }
    
    private static boolean crossesAntimeridian(double longitude, double otherLongitude) {
        return Math.abs(longitude - otherLongitude) > 180;
    }

    public static GeoPoint average(GeoPoint geoPoint, GeoPoint otherPoint) {
        double avgLat = (geoPoint.latitude() + otherPoint.latitude()) / 2;
        double avgLon = (geoPoint.longitude() + otherPoint.longitude()) / 2;
        
        if (crossesAntimeridian(geoPoint.longitude(), otherPoint.longitude())) {
            avgLon += getOffset(geoPoint.longitude(), otherPoint.longitude());
        }

        return new GeoPoint(avgLat, avgLon);
    }
}
