package swen221Ass1;

public record GeoPoint(double latitude, double longitude) {

    public GeoPoint {
        if (!(isLatitudeValid(latitude) && isLongitudeValid(longitude))) throw new IllegalArgumentException();
    }

    private boolean isLatitudeValid(double latitude) {return latitude >= -90 && latitude <= 90;}

    private boolean isLongitudeValid(double longitude) {return longitude >= -180 && longitude < 180;}

    private int offsetForAntimeridian(GeoPoint otherPoint) {
        return Math.abs(longitude - otherPoint.longitude()) > 180 ? -180 : 0;
    }

    public GeoPoint average(GeoPoint otherPoint) {
        return new GeoPoint((latitude + otherPoint.latitude()) / 2,
                (longitude + otherPoint.longitude()) / 2 + offsetForAntimeridian(otherPoint));
    }
    @Override
    public String toString() {
        return toString(8);
    }

    public String toString(int precision) {
        return String.format("[latitude: %." + precision + "f, longitude: %." + precision +"f]", latitude, longitude);
    }
}
