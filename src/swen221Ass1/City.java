package swen221Ass1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a city with a name, population, and a collection of initially empty streets.
 * Immutable other than the population of the city.
 */
public class City {
    private final String name;
    private int population;
    private final Set<Street> streets;

    /**
     * Constructs a new City instance.
     *
     * @param name The name of the city (must not be null).
     * @param population The population of the city (must be non-negative).
     * @throws NullPointerException If {@code name} is null.
     * @throws IllegalArgumentException If {@code population} is negative.
     */
    public City(String name, int population) {
        this.name = Objects.requireNonNull(name, "City name cannot be null");
        this.population = validatePopulation(population);
        this.streets = new HashSet<>();
    }

    /**
     * Gets the name of the city.
     *
     * @return The name of the city.
     */
    public String name() {
        return name;
    }

    /**
     * Gets the population of the city.
     *
     * @return The population of the city.
     */
    public int population() {
        return population;
    }

    /**
     * Gets an unmodifiable collection of the streets in the city.
     *
     * @return An unmodifiable set of streets.
     */
    public Set<Street> streets() {
        return Collections.unmodifiableSet(streets);
    }

    /**
     * Updates the population of the city.
     *
     * @param population The new population of the city (must be non-negative).
     * @throws IllegalArgumentException If {@code population} is negative.
     */
    public void population(int population) {
        this.population = validatePopulation(population);
    }

    /**
     * Adds a street to the city.
     *
     * @param street The {@link Street} to add (must not be null).
     * @throws NullPointerException If {@code street} is null.
     */
    public void addStreet(Street street) {
        Objects.requireNonNull(street, "Cannot add a null street");
        streets.add(street);
    }

    /**
     * Ensures that the population of a city is non-negative.
     *
     * @param population The population value to validate.
     * @throws IllegalArgumentException If {@code population} is negative.
     */
    private static int validatePopulation(int population) {
        if (population < 0) {
            throw new IllegalArgumentException("City population cannot be negative");
        }
        return population;
    }

    /**
     * Returns a string representation of a city
     * Example: City[name=Wellington, population=458, streets=[Main Street1, Main Street2]]
     *
     * @return The string representation of a city
     */
    @Override public String toString() {
        return "City[name=" + name + ", population=" + population + ", streets=" + streets + "]";
    }
}
