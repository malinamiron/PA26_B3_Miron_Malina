package org.example;

import com.github.javafaker.Faker;
import org.example.advance.Concepts;
import org.example.compulsory.InvalidYearException;
import org.example.compulsory.Repository;
import org.example.compulsory.Resource;
import org.example.homework.ListCommand;
import org.example.homework.Load;
import org.example.homework.Report;
import org.example.homework.View;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();

        try {
            Faker faker = new Faker();

            for( int it = 0; it < 30; it++){
                EnumSet<Concepts> concepts = EnumSet.noneOf(Concepts.class);
                int nrOfConcepts = (int)(Math.random() * (Concepts.values().length)+1);

                for(int concept = 0; concept < nrOfConcepts; concept++){
                    int conceptNr = (int) (Math.random()*Concepts.values().length);
                    concepts.add(Concepts.values()[conceptNr]);
                }

                Resource res = new Resource(
                        String.valueOf(faker.number().randomNumber(4,true)),
                        faker.internet().domainName(),
                        new URI(faker.internet().url()),
                        faker.number().numberBetween(1950, 2026),
                        faker.funnyName().name().toString(),
                        concepts
                );
                repo.addResource(res);
            }


            List<Resource> result = repo.minimSet();
            System.out.println("setul minim de resurse");
            result.forEach(r -> System.out.println(r.getTitle() + " -> " + r.getConceptsList()));
//            Resource res2 = new Resource(
//                    "jvm25",
//                    "The Java Virtual Machine Specification",
//                    new URI("https://docs.oracle.com/javase/specs/jvms/se25/html/index.html"),
//                    2025,
//                    "Tim Lindholm & others"
//            );



//            repo.addResource(res2);
//            Load.load(repo, res2);

//            System.out.println("Repository-ul a fost creat cu succes:");
//            System.out.println(repo);


            //System.out.println("\nSe incearca deschiderea resursei: " + res2.getTitle());
//            res2.openResource();


            //System.out.println("\nTestare InvalidYearException:");
            //Resource resError = new Resource("bad_id", "Titlu", new URI("http://google.com"), -500, "Autor");


            ListCommand.list(repo);
//            Report.getReport(repo);
//            View.view(res1);

        } catch (InvalidYearException e) {
            System.err.println("Eroare de validare: " + e.getMessage() + " (Anul introdus a fost: " + e.getYear() + ")");
        } catch (URISyntaxException e) {
            System.err.println("Format URI invalid: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("A aparut o eroare neasteptata: " + e.getMessage());
        }
    }
}

