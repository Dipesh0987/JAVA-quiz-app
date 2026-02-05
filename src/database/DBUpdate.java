package database;

import java.sql.*;

public class DBUpdate {
    
    public static boolean updateQuestion(int questionId, String question, String optA, 
                                         String optB, String optC, String optD, 
                                         String correctOpt, String difficulty) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        String query = "UPDATE questions SET " +
                      "question_text = ?, " +
                      "option_a = ?, " +
                      "option_b = ?, " +
                      "option_c = ?, " +
                      "option_d = ?, " +
                      "correct_answer = ?, " +
                      "difficulty_level = ? " +
                      "WHERE question_id = ?";
        
        System.out.println("Updating question ID: " + questionId);
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, question);
            pstmt.setString(2, optA);
            pstmt.setString(3, optB);
            pstmt.setString(4, optC);
            pstmt.setString(5, optD);
            pstmt.setString(6, correctOpt);
            pstmt.setString(7, difficulty);
            pstmt.setInt(8, questionId); 
            
            int rowsAffected = pstmt.executeUpdate();
            
            System.out.println("Rows affected by update: " + rowsAffected);
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error updating question: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}