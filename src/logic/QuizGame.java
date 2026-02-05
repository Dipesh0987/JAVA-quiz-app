package logic;

import java.util.List;
import models.Question;
import database.DBQuiz;

public class QuizGame {

    private String difficulty;
    private String username;
    private int userId;
    private boolean gameSaved = false;

    // Game state
    private List<Question> allQuestions;
    private int currentRound = 1;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int roundScore = 0;

    // Game configuration
    private static final int TOTAL_ROUNDS = 5;
    private static final int QUESTIONS_PER_ROUND = 5;
    private static final int TOTAL_QUESTIONS = TOTAL_ROUNDS * QUESTIONS_PER_ROUND;

    public QuizGame(String difficulty, String username, int userId) {
        this.difficulty = difficulty;
        this.username = username;
        this.userId = userId;
        loadQuestions();
    }

    private void loadQuestions() {
        // Load all questions the game (5 rounds × 5 questions = 25 questions)
        allQuestions = DBQuiz.getQuestionsByDifficulty(difficulty, TOTAL_QUESTIONS);

        if (allQuestions.size() < TOTAL_QUESTIONS) {
            System.out.println("Warning: Only " + allQuestions.size() +
                    " questions available for " + difficulty +
                    " difficulty (need " + TOTAL_QUESTIONS + ")");
        }
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < allQuestions.size()) {
            return allQuestions.get(currentQuestionIndex);
        }
        return null;
    }

    public boolean submitAnswer(String selectedOption) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion == null) {
            return true; // No more questions, end game
        }

        boolean isCorrect = selectedOption.equalsIgnoreCase(currentQuestion.getCorrectOption());

        if (isCorrect) {
            score++;
            roundScore++;
        }

        // Move to next question
        currentQuestionIndex++;

        // Check if round is complete
        if (isRoundComplete()) {
            return true;
        }
        if (isGameComplete()) {
            return true;
        }
        return false;
    }

    public boolean isRoundComplete() {
        // Round is complete when we've answered all questions for this round
        // but haven't reached the total limit
        int questionsInCurrentRound = currentQuestionIndex % QUESTIONS_PER_ROUND;
        return (questionsInCurrentRound == 0 && currentQuestionIndex > 0) &&
                !isGameComplete();
    }

    public boolean isGameComplete() {
        // Game is complete when:
        // 1. We've answered all available questions, OR
        // 2. We've answered the maximum number of questions (25), OR
        // 3. We've completed all rounds
        return currentQuestionIndex >= Math.min(allQuestions.size(), TOTAL_QUESTIONS) ||
                currentRound > TOTAL_ROUNDS;
    }

    public void moveToNextRound() {
        if (currentRound < TOTAL_ROUNDS) {
            currentRound++;
            roundScore = 0; // Reset round score
        } else {
            // Game is complete
            saveGame();
        }
    }

    public boolean isGameSaved() {
        return gameSaved;
    }

    public void saveGame() {
        if (gameSaved) {
            System.out.println("Game already saved, skipping...");
            return;
        }

        // Always save the game when it ends or user quits
        int questionsAnswered = getQuestionsAnswered();
        if (questionsAnswered > 0) {
            boolean saved = DBQuiz.saveScore(userId, difficulty, currentRound, score, questionsAnswered);
            if (saved) {
                gameSaved = true; // Mark as saved
                System.out.println("Game saved: " + saved +
                        ", User: " + userId +
                        ", Score: " + score + "/" + questionsAnswered);
            }
        }
    }

    // Getters
    public int getCurrentRound() {
        return Math.min(currentRound, TOTAL_ROUNDS);
    }

    public int getTotalRounds() {
        return TOTAL_ROUNDS;
    }

    public int getQuestionsPerRound() {
        return QUESTIONS_PER_ROUND;
    }

    public int getScore() {
        return score;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public int getQuestionsAnswered() {
        return currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return Math.min(allQuestions.size(), TOTAL_QUESTIONS);
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getUsername() {
        return username;
    }

    public int getQuestionNumberInRound() {
        return (currentQuestionIndex % QUESTIONS_PER_ROUND) + 1;
    }

    public boolean hasEnoughQuestions() {
        return allQuestions.size() >= QUESTIONS_PER_ROUND;
    }

    // Debug method to check game state
    public void printGameState() {
        System.out.println("=== Game State ===");
        System.out.println("Round: " + currentRound + "/" + TOTAL_ROUNDS);
        System.out.println("Question Index: " + currentQuestionIndex);
        System.out.println("Questions Available: " + allQuestions.size());
        System.out.println("Score: " + score);
        System.out.println("Round Score: " + roundScore);
        System.out.println("Questions in Round: " + getQuestionNumberInRound());
        System.out.println("Round Complete: " + isRoundComplete());
        System.out.println("Game Complete: " + isGameComplete());
        System.out.println("==================");
    }
}