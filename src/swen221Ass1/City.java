package swen221Ass1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class City {
    private final String name;
    private int population;
    private final Set<Street> streets;

    public City(String name, int population) {
        Objects.requireNonNull(name, "City name cannot be null");
        isPopulationValid(population);
        this.name = name;
        this.population = population;
        this.streets = new HashSet<>();
    }
    // getters
    public String name() {return name;}
    public int population() {return population;}
    public Set<Street> streets() {return Collections.unmodifiableSet(streets);}

    // setter
    public void population(int population) {
        isPopulationValid(population);
        this.population = population;
    }
    private void isPopulationValid(int population) {
        if (population < 0) throw new IllegalArgumentException("City population cannot be negative");
    }

    public void addStreet(Street street) {
        Objects.requireNonNull(street, "Cannot add a null street");
        streets.add(street);
    }
    @Override
    public String toString() {
        return "City[name='" + name + ", population=" + population + ", streets=" + streets + "]";
    }
}
