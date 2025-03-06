package swen221Ass1;

import java.util.Objects;

public record GeoBox(GeoPoint ne, GeoPoint sw) {

    public GeoBox {
        Objects.requireNonNull(ne, "NorthEast (ne) cannot be null");
        Objects.requireNonNull(sw, "SouthWest (sw) cannot be null");
        if (!GeoBoxValidator.isValidGeoBox(ne, sw)) throw new IllegalArgumentException();
    }

    public GeoPoint nw() {return new GeoPoint(ne.latitude(), sw.longitude());}

    public GeoPoint se() {return new GeoPoint(sw.latitude(), ne.longitude());}

    public GeoPoint center() {return ne.average(sw);}
}
