package org.example.lab6_a;

import org.example.lab6_a.Database;

import java.sql.*;

public class GenreDAO {
    public void create(String name) throws SQLException {
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        String sql = "SELECT id FROM genres WHERE name = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("id") : -1;
        }
    }

    public Integer findByNameOrCreate(String name) throws SQLException {
        int id = this.findByName(name);
        if(id == -1){
            create(name);
            id = findByName(name);
        }
        return id;
    }

    public String findById(int id) throws SQLException {
        String sql = "SELECT name FROM genres WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getString("name") : null;
        }
    }
}