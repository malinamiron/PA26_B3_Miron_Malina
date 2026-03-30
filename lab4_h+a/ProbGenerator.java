package org.example;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Getter
public class ProbGenerator {
   private int nrIntersections;
   private int nrStreets;

   private Faker faker = new Faker();

   private City city;

   List<Intersection> intersections;

   Set<Street> streets;

   public ProbGenerator(int nrIntersections, int nrStreets) {
      this.nrIntersections = nrIntersections;
      this.nrStreets = nrStreets;
   }

   private void generateIntersections(){
      intersections = IntStream.rangeClosed(1, nrIntersections).mapToObj(i->new Intersection("i" + i, new Point((int) (Math.random()*1000), (int) (Math.random()*1000)))).toList();
   }

   private void generateStreets(){
      streets = new HashSet<>();
      while(streets.size() < nrStreets){
         int i = (int) (Math.random() * nrIntersections);
         int j = (int) (Math.random()* nrIntersections);
         while (i==j){
            j = (int) (Math.random()*nrIntersections);
         }

         if(i> j ){
            int temp = i;
            i = j;
            j = temp;
         }

         Point a = intersections.get(i).getLocation();
         Point b = intersections.get(j).getLocation();
         int dist = (int) (a.distance(b) * a.distance(b));

         streets.add(new Street(faker.address().streetName(), dist, intersections.get(i), intersections.get(j)));
      }

   }

   public City generateCity(){
      generateIntersections();
      generateStreets();
      return new City(streets.stream().toList(), intersections);
   }
}
