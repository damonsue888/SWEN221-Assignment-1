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

    public boolean overlaps(GeoBox other) {
        System.out.println("Box 1:");test(ne, sw);
        System.out.println();
        System.out.println("Box 2");test(other.ne(), other.sw());
        return true;
    }
    private void test(GeoPoint ne, GeoPoint sw) {
        System.out.println("North=" +ne.latitude() + "\nEast=" + ne.longitude() +
                "\nSouth=" + sw.latitude() + "\nWest=" + sw.longitude());
    }
}
