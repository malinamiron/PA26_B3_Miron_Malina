package org.example.lab6_a;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

    public void create(String name) throws SQLException {
        String sql = "INSERT INTO actors (name) VALUES (?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        String sql = "SELECT id FROM actors WHERE name = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt("id") : null;
        }
    }

    public String findById(int id) throws SQLException {
        String sql = "SELECT name FROM actors WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getString("name") : null;
        }
    }
}