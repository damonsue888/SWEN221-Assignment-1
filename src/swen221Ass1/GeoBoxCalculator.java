package swen221Ass1;

public final class GeoBoxCalculator {
    private GeoBoxCalculator() {}

    public static GeoPoint calculateNW(GeoPoint ne, GeoPoint sw) {
        return new GeoPoint(ne.latitude(), sw.longitude());
    }

    public static GeoPoint calculateSW(GeoPoint ne, GeoPoint sw) {
        return new GeoPoint(sw.latitude(), ne.longitude());
    }

    public static GeoPoint calculateCenter(GeoPoint ne, GeoPoint sw) {
        return ne.average(sw);
    }

    private static boolean isLeftOf(GeoBox geobox, GeoBox otherBox) {
        return ((otherBox.sw().longitude() - geobox.ne().longitude() + 360) % 360) < 180;
    }

    private static boolean isAbove(GeoBox geoBox, GeoBox otherBox) {
        return geoBox.sw().latitude() >= otherBox.ne().latitude();
    }

    private static boolean isOneBoxLeft(GeoBox geoBox, GeoBox otherBox) {
        return isLeftOf(geoBox, otherBox) || isLeftOf(otherBox, geoBox);
    }

    private static boolean isOneBoxAbove(GeoBox geoBox, GeoBox otherBox) {
        return isAbove(geoBox, otherBox) || isAbove(otherBox, geoBox);
    }

    public static boolean overlaps(GeoBox geoBox, GeoBox otherBox) {
        return !(isOneBoxLeft(geoBox, otherBox) || isOneBoxAbove(geoBox, otherBox));
    }
}
