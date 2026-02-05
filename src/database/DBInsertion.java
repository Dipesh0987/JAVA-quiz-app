package database;

import java.sql.*;

public class DBInsertion {
    
    // Modified to return the generated question ID
    public static int insertQuestion(String question, String optA, String optB, 
                                     String optC, String optD, String correctOpt, 
                                     String difficulty) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        // IMPORTANT: Add Statement.RETURN_GENERATED_KEYS
        String query = "INSERT INTO questions (question_text, option_a, option_b, " +
                      "option_c, option_d, correct_answer, difficulty_level) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, question);
            pstmt.setString(2, optA);
            pstmt.setString(3, optB);
            pstmt.setString(4, optC);
            pstmt.setString(5, optD);
            pstmt.setString(6, correctOpt);
            pstmt.setString(7, difficulty);
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                // Get the generated question ID
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Inserted question with ID: " + generatedId);
                    return generatedId; // Return the generated question ID
                }
            }
            System.out.println("No rows affected or no generated keys");
            return -1; // Return -1 if insertion failed
            
        } catch (SQLException e) {
            System.out.println("Error inserting the question to database: " + e.getMessage());
            e.printStackTrace();
            return -1; // Return -1 on error
        }
    }
}