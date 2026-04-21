package org.example;

import org.example.compulsory.Database;
import org.example.compulsory.GenreDAO;

public class Main {
    public static void main(String[] args) {
        try {
            Database.executeSqlFile("C:/Users/Milika/Desktop/JAVA/lab6/src/main/resources/movies.sql");
            GenreDAO genres = new GenreDAO();

            genres.create("Sci-Fi");
            genres.create("Fantasy");

            Integer id = genres.findByName("Sci-Fi");
            System.out.println("Found Sci-Fi with ID: " + id);

            if (id != null) {
                String name = genres.findById(id);
                System.out.println("Confirmed ID " + id + " is " + name);
            }

        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}