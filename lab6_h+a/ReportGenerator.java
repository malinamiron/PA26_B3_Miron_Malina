package org.example.lab6_a;

import java.io.*;
import java.nio.file.*;
import java.sql.*;

public class ReportGenerator {
    public void generateReport(String outputPath) throws Exception {

        String template = new String(Files.readAllBytes(Paths.get("src/main/resources/report_template.html")));
        StringBuilder rows = new StringBuilder();

        String sql = "SELECT m.title, m.release_date, g.name AS genre, m.score\n" +
                "FROM movies m\n" +
                "JOIN genres g ON m.genre_id = g.id;";
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rows.append("<tr>")
                        .append("<td>").append(rs.getString("title")).append("</td>")
                        .append("<td>").append(rs.getDate("release_date")).append("</td>")
                        .append("<td>").append(rs.getString("genre")).append("</td>")
                        .append("<td>").append(rs.getFloat("score")).append("</td>")
                        .append("</tr>");
            }
        }

        String finalHtml = template.replace("{{ROWS}}", rows.toString());
        Files.write(Paths.get(outputPath), finalHtml.getBytes());
        System.out.println("Raport generat cu succes: " + outputPath);
    }
}