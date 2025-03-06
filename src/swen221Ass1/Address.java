package swen221Ass1;

import java.util.Objects;

public record Address(Street street, City city, String number) {
    public Address {
        Objects.requireNonNull(street, "Address Street cannot be null");
        Objects.requireNonNull(city, "Address City cannot be null");
        Objects.requireNonNull(number, "Address number cannot be null");
    }

    @Override
    public String toString() {return "Address[street=" + street + ", city=" + city.name() + ", number=" + number + "]";}
}
