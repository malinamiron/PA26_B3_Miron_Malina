package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Intersection> intersections = IntStream.range(0, 10)
                .mapToObj(i -> new Intersection("i" + i))
                .collect(Collectors.toList());


        Set<Intersection> intersectionSet = new HashSet<>(intersections);
        intersectionSet.add(new Intersection("i0"));

        System.out.println("Număr intersecții în Set (fără duplicate): " + intersectionSet.size());


        List<Street> streets = new LinkedList<>();
        streets.add(new Street("S1", 5, intersections.get(0), intersections.get(1)));
        streets.add(new Street("S2", 12, intersections.get(1), intersections.get(2)));
        streets.add(new Street("S3", 3, intersections.get(2), intersections.get(3)));
        streets.add(new Street("S4", 8, intersections.get(3), intersections.get(0)));


        streets.sort(Street::compareTo);

        System.out.println("\nstrăzi sortate după lungime:");
        streets.forEach(System.out::println);
    }
}