package org.example.lab6_a;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab6AApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab6AApplication.class, args);
        try {
//            GenreDAO genreDAO = new GenreDAO();
//
//            // 1. Inserare
//            genreDAO.create("Action");
//            System.out.println("Gen inserat cu succes!");
//
//            // 2. Verificare (Citire)
//            Integer id = genreDAO.findByName("Action");
//            System.out.println("ID-ul genului gasit: " + id);
//             String URL = "jdbc:postgresql://localhost:5432/movies_db";
//             String USER = "postgres";
//             String PASSWORD = "password";
//
//            Flyway flyway = Flyway.configure().dataSource(URL, USER, PASSWORD).load();
//            flyway.migrate();
            Database.runMigrations();

//            DatasetImporter importer = new DatasetImporter();
//            importer.importMovies("src/main/resources/movies_metadata.csv");

            ReportGenerator generator = new ReportGenerator();
            generator.generateReport("movie_report.html");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}