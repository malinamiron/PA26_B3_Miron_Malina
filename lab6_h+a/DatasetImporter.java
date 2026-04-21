package org.example.lab6_a;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatasetImporter {
    public void importMovies(String filePath) throws Exception {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext(); // Sare peste header
            MovieDAO movieDAO = new MovieDAO();
            GenreDAO genreDAO = new GenreDAO();
            int cnt = 1;
            while ((line = reader.readNext()) != null && cnt <= 200) {
                cnt++;
                String genre_str = line[3].trim();
                String genre_name = getFirstGenre(genre_str);
                int genre = genreDAO.findByNameOrCreate(genre_name);

                String id_str = line[5].trim();
                String title = line[8].trim();
                String rel_date = line[14].trim();
                String score_str = line[22].trim();
                String duration_str = line[16].trim();
                int duration = -1;
//                int score = -1;
//                int id = -1;
                if(duration_str == null){
                    duration = (int) Double.parseDouble(duration_str);
                }
                float score = (float) Double.parseDouble(score_str);
                int id = (int) Double.parseDouble(id_str);

                LocalDate releaseDate= LocalDate.now();
//                System.out.println(title + " " + rel_date + "\n");
                try {
                    String cleanDate = rel_date.replaceAll("[^0-9-]", "");
                    releaseDate = LocalDate.parse(cleanDate);

                } catch (DateTimeParseException e) {
                    System.err.println("Format de dată invalid pentru filmul " + title + ": " + rel_date);
                }

                movieDAO.create(new Movie(id,title, Date.valueOf(releaseDate), duration, score, genre));
            }
        }
    }
    private String getFirstGenre(String json) {
        if (json == null || json.isEmpty() || json.equals("[]")) return "Unknown";

        Pattern pattern = Pattern.compile("'name':\s*'([^']+)'");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Unknown";
    }
}