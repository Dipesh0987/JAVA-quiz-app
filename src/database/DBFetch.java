package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Question;

public class DBFetch {
    
    // Get question by ID
    public static Question getQuestionById(int id) {
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        // Make sure column names match your database table
        String query = "SELECT * FROM questions WHERE question_id = ?";
        
        System.out.println("Searching for question ID: " + id);
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Create Question object from result set
                Question question = new Question(
                    rs.getString("question_text"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_answer"),
                    rs.getString("difficulty_level")
                );
                question.setId(rs.getInt("question_id"));
                System.out.println("Found question ID: "+id);
               
                return question;
            } else {
                System.out.println("No question found with ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("Error fetching question: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // Get all questions
    public static List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String dbUsername = "root";
        String dbPassword = "";
        
        String query = "SELECT * FROM questions ORDER BY question_id";
        
        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Question question = new Question(
                    rs.getString("question_text"),
                    rs.getString("option_a"),
                    rs.getString("option_b"),
                    rs.getString("option_c"),
                    rs.getString("option_d"),
                    rs.getString("correct_answer"),
                    rs.getString("difficulty_level")
                );
                question.setId(rs.getInt("question_id"));
                questions.add(question);
            }
            
            
        } catch (SQLException e) {
            System.out.println("Error fetching all questions: " + e.getMessage());
            e.printStackTrace();
        }
        return questions;
    }
}