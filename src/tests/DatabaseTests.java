package tests;

import database.*;
import models.Question;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DatabaseTests {
    
    @Test
    public void testDatabaseConnection() {
        // Test that database connection works
        System.out.println("Testing database connection...");
        // Since DBConnection.main() just connects and prints, we can call it
        DBConnection.main(new String[]{});
        System.out.println("Database connection test completed.");
    }
    
    @Test
    public void testQuestionCRUD() {
        // Test Create, Read, Update, Delete operations
        
        // 1. Insert a test question
        int questionId = DBInsertion.insertQuestion(
            "Test Question from JUnit",
            "Option A", "Option B", "Option C", "Option D",
            "A", "Beginner"
        );
        
        System.out.println("Inserted question ID: " + questionId);
        assertTrue(questionId > 0, "Question should be inserted successfully");
        
        // 2. Read the question back
        Question question = DBFetch.getQuestionById(questionId);
        assertNotNull(question, "Question should be found in database");
        assertEquals("Test Question from JUnit", question.getQuestionText());
        
        // 3. Update the question
        boolean updateSuccess = DBUpdate.updateQuestion(
            questionId,
            "Updated Test Question",
            "Updated A", "Updated B", "Updated C", "Updated D",
            "B", "Intermediate"
        );
        assertTrue(updateSuccess, "Question should be updated successfully");
        
        // 4. Verify update
        Question updatedQuestion = DBFetch.getQuestionById(questionId);
        assertEquals("Updated Test Question", updatedQuestion.getQuestionText());
        assertEquals("Intermediate", updatedQuestion.getDifficulty());
        
        // 5. Delete the question
        boolean deleteSuccess = DBDelete.deleteQuestion(questionId);
        assertTrue(deleteSuccess, "Question should be deleted successfully");
        
        // 6. Verify deletion
        Question deletedQuestion = DBFetch.getQuestionById(questionId);
        assertNull(deletedQuestion, "Question should no longer exist");
    }
    
    @Test
    public void testGetAllQuestions() {
        List<Question> questions = DBFetch.getAllQuestions();
        assertNotNull(questions, "Question list should not be null");
        System.out.println("Total questions in database: " + questions.size());
        
        // If there are questions, test some properties
        if (!questions.isEmpty()) {
            Question firstQuestion = questions.get(0);
            assertNotNull(firstQuestion.getQuestionText());
            assertNotNull(firstQuestion.getDifficulty());
        }
    }
    
    @Test
    public void testUserAuthentication() {
        // Test login functionality
        boolean authResult = DBLogin.authenticate("admin", "admin123");
        System.out.println("Authentication result for admin: " + authResult);
        
        // Test getting user role
        String role = DBLogin.getUserRole("admin");
        System.out.println("Admin role: " + role);
        assertEquals("admin", role);
        
        // Test getting user ID
        int userId = DBLogin.getUserId("admin");
        System.out.println("Admin user ID: " + userId);
        assertTrue(userId > 0);
    }
    
    @Test
    public void testQuizDatabaseOperations() {
        // Test leaderboard functionality
        List<String[]> leaderboard = DBQuiz.getLeaderboard(5);
        assertNotNull(leaderboard, "Leaderboard should not be null");
        System.out.println("Leaderboard size: " + leaderboard.size());
        
        // Test getting questions by difficulty
        List<Question> beginnerQuestions = DBQuiz.getQuestionsByDifficulty("Beginner", 3);
        assertNotNull(beginnerQuestions, "Questions list should not be null");
        System.out.println("Beginner questions retrieved: " + beginnerQuestions.size());
        
        // Test user registration (clean up after test)
        String testUsername = "testuser_" + System.currentTimeMillis();
        boolean registerResult = DBQuiz.registerUser(testUsername, "password123", "test@example.com");
        System.out.println("Registration result for " + testUsername + ": " + registerResult);
        assertTrue(registerResult, "New user registration should succeed");
    }
    
    @Test
    public void testGetUserRank() {
        // Test rank functionality for different difficulties
        String[] difficulties = {"Beginner", "Intermediate", "Advanced", "All"};
        
        for (String difficulty : difficulties) {
            String rank = DBQuiz.getUserRank("admin", difficulty);
            System.out.println("Rank for admin (" + difficulty + "): " + rank);
            assertNotNull(rank, "Rank should not be null for difficulty: " + difficulty);
        }
    }
}