package org.example;

import java.util.Objects;

public class Intersection implements Comparable<Intersection> {
    private String name;

    public Intersection(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public int compareTo(Intersection other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intersection that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Intersection{" + name + "}";
    }
}