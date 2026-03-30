package org.example;

import lombok.Getter;
@Getter
public class Street implements Comparable<Street> {

    private String name;
    private int length;
    private final Intersection from;
    private final Intersection to;

    public Street(String name, int length, Intersection from, Intersection to) {
        this.name = name;
        this.length = length;
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Street other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format("Street %s (%d km) joins %s and %s",
                name, length, from.getName(), to.getName());
    }
}