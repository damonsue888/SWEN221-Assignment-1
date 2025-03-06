package swen221Ass1;

public record GeoPoint(double latitude, double longitude) {

    public GeoPoint {
        if (!(isLatitudeValid(latitude) && isLongitudeValid(longitude))) throw new IllegalArgumentException();
    }

    private boolean isLatitudeValid(double latitude) {return latitude >= -90 && latitude <= 90;}

    private boolean isLongitudeValid(double longitude) {return longitude >= -180 && longitude <= 180;}

    public GeoPoint average(GeoPoint geoPoint) {
        return new GeoPoint((latitude + geoPoint.latitude()) / 2, (longitude + geoPoint.longitude) / 2);
    }
    @Override
    public String toString() {
        return toString(8);
    }

    public String toString(int precision) {
        return String.format("[latitude: %." + precision + "f, longitude: %." + precision +"f]", latitude, longitude);
    }
}
