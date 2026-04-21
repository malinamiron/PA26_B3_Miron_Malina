package org.example.compulsory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection = null;

    private Database() { }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:postgresql://localhost:5432/movies_db";
            String user = "postgres";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void executeSqlFile(String filePath) throws SQLException, IOException {

        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        String[] queries = content.split(";");

        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {

            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    stmt.addBatch(query);
                }
            }
            stmt.executeBatch();
            System.out.println("schema creata cu succes: " + filePath);
        }
    }
}