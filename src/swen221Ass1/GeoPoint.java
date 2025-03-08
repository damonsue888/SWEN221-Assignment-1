package swen221Ass1;

public record GeoPoint(double latitude, double longitude) {

    public GeoPoint {
        if (!GeoPointValidator.validate(latitude, longitude)) {
            throw new IllegalArgumentException("GeoPoint doesn't meet requirements");
        }
    }

    public GeoPoint average(GeoPoint otherPoint) {
        return GeoPointCalculator.average(this, otherPoint);
    }

    @Override
    public String toString() {
        return toString(8);
    }

    public String toString(int precision) {
        return String.format("[latitude: %." + precision + "f, longitude: %." + precision +"f]", latitude, longitude);
    }
}
