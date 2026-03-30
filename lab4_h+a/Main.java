package org.example;

import com.github.javafaker.Faker;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Intersection> intersections = IntStream.range(0, 10)
                .mapToObj(i -> new Intersection(faker.address().streetName(),new Point((int) (Math.random()*100), (int) (Math.random()*100))))
                .toList();


        Set<Intersection> intersectionSet = new HashSet<>(intersections);
        intersectionSet.add(new Intersection("i0",new Point((int) (Math.random()*100), (int) (Math.random()*100))));

        //System.out.println("Număr intersecții în Set (fără duplicate): " + intersectionSet.size());


        List<Street> streets = new LinkedList<>();
        streets.add(new Street(faker.address().streetName(), 5, intersections.get(0), intersections.get(1)));
        streets.add(new Street(faker.address().streetName(), 12, intersections.get(1), intersections.get(2)));
        streets.add(new Street(faker.address().streetName(), 3, intersections.get(2), intersections.get(3)));
        streets.add(new Street(faker.address().streetName(), 8, intersections.get(3), intersections.get(0)));


        streets.sort(Street::compareTo);

        //System.out.println("\nstrăzi sortate după lungime:");
        //streets.forEach(System.out::println);

        City city = new City(streets, intersections);

        //System.out.println(city.mst().getTree());

        ProbGenerator problema = new ProbGenerator(10,30);

        City city2 = problema.generateCity();


        List<Integer> route = city2.getSurvCarRoute();

        System.out.println(route);
    }
}