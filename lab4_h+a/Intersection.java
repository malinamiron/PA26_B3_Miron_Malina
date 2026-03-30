package org.example;

import lombok.Getter;

import java.awt.*;
import java.util.Objects;

@Getter
public class Intersection implements Comparable<Intersection> {
    private final String name;
    private Point location;

    public Intersection(String name, Point location) {
        this.name = name;
        this.location = location;
    }



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