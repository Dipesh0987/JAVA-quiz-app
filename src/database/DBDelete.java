package database;

import java.sql.*;

public class DBDelete {
    
    public static boolean deleteQuestion(int questionId) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        // FIX: Use question_id instead of id
        String query = "DELETE FROM questions WHERE question_id = ?";
        
        System.out.println("Deleting question ID: " + questionId);
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, questionId);
            int rowsAffected = pstmt.executeUpdate();
            
            System.out.println("Rows affected by delete: " + rowsAffected);
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error deleting the question: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}