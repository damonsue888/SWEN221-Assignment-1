package swen221Ass1;

import java.util.Objects;

public record GeoBox(GeoPoint ne, GeoPoint sw) {
    public GeoBox {
        Objects.requireNonNull(ne, "NorthEast (ne) cannot be null");
        Objects.requireNonNull(sw, "SouthWest (sw) cannot be null");
        if (!GeoBoxValidator.isValidGeoBox(ne, sw)) {
            throw new IllegalArgumentException("GeoBox doesn't meet requirements");
        }
    }
    public GeoPoint nw() {return new GeoPoint(ne.latitude(), sw.longitude());}

    public GeoPoint se() {return new GeoPoint(sw.latitude(), ne.longitude());}

    public GeoPoint center() {return ne.average(sw);}

    private boolean isLeftOf(GeoBox other) {return ((other.sw().longitude() - ne.longitude() + 360) % 360) < 180;}

    private boolean isAbove(GeoBox other) {return sw.latitude() >= other.ne().latitude();}

    public boolean overlaps(GeoBox other) {
        boolean isOneBoxLeft = isLeftOf(other) || other.isLeftOf(this);
        boolean isOneBoxAbove = isAbove(other) || other.isAbove(this);
        System.out.println(isOneBoxLeft);
        System.out.println(isOneBoxAbove);
        return !(isOneBoxLeft || isOneBoxAbove);
    }
}
