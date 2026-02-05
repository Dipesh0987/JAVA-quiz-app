package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogin {
    
    // Authenticate user
    public static boolean authenticate(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if user exists
            
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return false;
        }
    }
    
    // Get user role
    public static String getUserRole(String username) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String password = "";
        
        String query = "SELECT role FROM users WHERE username = ?";
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("role");
            }
            return null;
            
        } catch (SQLException e) {
            System.out.println("Error getting user role: " + e.getMessage());
            return null;
        }
    }
    
    // Get user ID (add this method)
    public static int getUserId(String username) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String password = "";
        
        String query = "SELECT id FROM users WHERE username = ?";  // Changed to id
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
            return -1;
            
        } catch (SQLException e) {
            System.out.println("Error getting user ID: " + e.getMessage());
            return -1;
        }
    }
}