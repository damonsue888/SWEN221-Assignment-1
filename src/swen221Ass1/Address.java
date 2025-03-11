package swen221Ass1;

import java.util.Objects;

/**
 * Represents an address containing a street, city, and an alphanumeric address number such as '4r' or '3b'.
 * This class is immutable since it's a record.
 *
 * @param street The {@link Street} where the address is located (must not be null).
 * @param city The {@link City} where the address is located (must not be null).
 * @param number The alphanumeric address number (must not be null).
 */
public record Address(Street street, City city, String number) {

    /**
     * Constructs a new Address instance.
     *
     * @param street The {@link Street} where the address is located (must not be null).
     * @param city The {@link City} where the address is located (must not be null).
     * @param number The alphanumeric address number (must not be null).
     * @throws NullPointerException If any field is null.
     */
    public Address {
        Objects.requireNonNull(street, "Address Street cannot be null");
        Objects.requireNonNull(city, "Address City cannot be null");
        Objects.requireNonNull(number, "Address number cannot be null");
    }

    /**
     * Returns a string representation of the Address.
     * Example: Address[street=Main St, city=Wellington, number=3a]
     *
     * @return A string representation of the Address
     */
    @Override public String toString() {
        return "Address[street=" + street + ", city=" + city.name() + ", number=" + number + "]";
    }
}
