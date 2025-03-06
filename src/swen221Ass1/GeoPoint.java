package swen221Ass1;

public record GeoPoint(double latitude, double longitude) {

    public GeoPoint {
        if (!(GeoValidation.isValidLat(latitude) && GeoValidation.isValidLong(longitude))) {
            throw new IllegalArgumentException();
        }
    }

    private double distThroughMeridian(double otherLongitude) {return Math.abs(longitude - otherLongitude);}

    private int getOffset(double otherLongitude) {return longitude - otherLongitude > 0 ? 180 : -180;}

    private int offsetForAntimeridian(double otherLongitude) {
        // if both longitudes have the same sign
        if (longitude * otherLongitude > 0) return 0;
        return distThroughMeridian(otherLongitude) > 180 ? getOffset(otherLongitude) : 0;
    }

    public GeoPoint average(GeoPoint otherPoint) {
        return new GeoPoint((latitude + otherPoint.latitude()) / 2,
                (longitude + otherPoint.longitude()) / 2 + offsetForAntimeridian(otherPoint.longitude()));
    }
    @Override
    public String toString() {
        return toString(8);
    }

    public String toString(int precision) {
        return String.format("[latitude: %." + precision + "f, longitude: %." + precision +"f]", latitude, longitude);
    }
}
