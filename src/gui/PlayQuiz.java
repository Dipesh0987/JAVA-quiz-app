package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import logic.QuizGame;
import database.DBQuiz;

public class PlayQuiz extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ButtonGroup optionGroup;
	
	// Game components
	private QuizGame quizGame;
	private String username; // Store username separately
	
	// UI Components
	private JLabel difficultyLabel;
	private JLabel roundLabel;
	private JLabel scoreLabel;
	private JLabel questionNumberLabel;
	private JTextArea questionTextArea;
	private JRadioButton optionA;
	private JRadioButton optionB;
	private JRadioButton optionC;
	private JRadioButton optionD;
	private JButton nextButton;

	/**
	 * Constructor with parameters
	 */
	public PlayQuiz(String difficulty, String username) {
		this.username = username; // Store username
		int userId = DBQuiz.getUserId(username);
		this.quizGame = new QuizGame(difficulty, username, userId);
		initialize();
		startGame();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Changed from EXIT_ON_CLOSE
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		optionGroup = new ButtonGroup();
		
		JLabel titleLabel = new JLabel("Kaun Banega Crorepati");
		titleLabel.setForeground(Color.BLUE);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		titleLabel.setBounds(200, 10, 246, 51);
		contentPane.add(titleLabel);
		
		difficultyLabel = new JLabel("Difficulty: ");
		difficultyLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		difficultyLabel.setBounds(30, 70, 200, 25);
		contentPane.add(difficultyLabel);
		
		roundLabel = new JLabel("Round: ");
		roundLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		roundLabel.setBounds(250, 70, 150, 25);
		contentPane.add(roundLabel);
		
		scoreLabel = new JLabel("Score: ");
		scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		scoreLabel.setBounds(450, 70, 150, 25);
		contentPane.add(scoreLabel);
		
		questionNumberLabel = new JLabel("Question: ");
		questionNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		questionNumberLabel.setBounds(30, 100, 200, 25);
		contentPane.add(questionNumberLabel);
		
		questionTextArea = new JTextArea();
		questionTextArea.setEditable(false);
		questionTextArea.setLineWrap(true);
		questionTextArea.setWrapStyleWord(true);
		questionTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		questionTextArea.setBounds(30, 130, 580, 80);
		contentPane.add(questionTextArea);
		
		optionA = new JRadioButton("");
		optionA.setForeground(new Color(255, 255, 255));
		optionA.setBackground(new Color(0, 0, 255));
		optionA.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		optionA.setBounds(50, 230, 260, 33);
		contentPane.add(optionA);
		
		optionB = new JRadioButton("");
		optionB.setForeground(new Color(255, 255, 255));
		optionB.setBackground(new Color(0, 0, 255));
		optionB.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		optionB.setBounds(350, 230, 260, 35);
		contentPane.add(optionB);
		
		optionC = new JRadioButton("");
		optionC.setForeground(new Color(255, 255, 255));
		optionC.setBackground(new Color(0, 0, 255));
		optionC.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		optionC.setBounds(50, 280, 260, 33);
		contentPane.add(optionC);
		
		optionD = new JRadioButton("");
		optionD.setForeground(new Color(255, 255, 255));
		optionD.setBackground(new Color(0, 0, 255));
		optionD.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		optionD.setBounds(350, 280, 260, 35);
		contentPane.add(optionD);
		
		optionGroup.add(optionA);
		optionGroup.add(optionB);
		optionGroup.add(optionC);
		optionGroup.add(optionD);
		
		nextButton = new JButton("Submit Answer");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				processAnswer();
			}
		});
		nextButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
		nextButton.setBounds(470, 350, 140, 35);
		contentPane.add(nextButton);
		
		JButton back = new JButton("Quit");
		back.setBackground(new Color(255, 0, 0));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quitGame();
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back.setBounds(30, 350, 100, 35);
		contentPane.add(back);
		
		// Option labels
		JLabel lblOptionA = new JLabel("A:");
		lblOptionA.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOptionA.setBounds(30, 235, 20, 20);
		contentPane.add(lblOptionA);
		
		JLabel lblOptionB = new JLabel("B:");
		lblOptionB.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOptionB.setBounds(330, 235, 20, 20);
		contentPane.add(lblOptionB);
		
		JLabel lblOptionC = new JLabel("C:");
		lblOptionC.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOptionC.setBounds(30, 285, 20, 20);
		contentPane.add(lblOptionC);
		
		JLabel lblOptionD = new JLabel("D:");
		lblOptionD.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOptionD.setBounds(330, 285, 20, 20);
		contentPane.add(lblOptionD);
	}
	
	private void startGame() {
		if (!quizGame.hasEnoughQuestions()) {
			JOptionPane.showMessageDialog(this, 
				"Not enough questions available for " + quizGame.getDifficulty() + " difficulty!\n" +
				"Minimum " + quizGame.getQuestionsPerRound() + " questions required.\n" +
				"Please ask admin to add more questions.",
				"Insufficient Questions", JOptionPane.WARNING_MESSAGE);
			returnToDashboard();
			return;
		}
		
		updateUI();
		loadCurrentQuestion();
	}
	
	private void loadCurrentQuestion() {
		if (quizGame.isGameComplete()) {
			endGame();
			return;
		}
		
		var question = quizGame.getCurrentQuestion();
		if (question == null) {
			endGame();
			return;
		}
		
		// Display question
		questionTextArea.setText(question.getQuestionText());
		optionA.setText(question.getOptionA());
		optionB.setText(question.getOptionB());
		optionC.setText(question.getOptionC());
		optionD.setText(question.getOptionD());
		
		// Clear selection
		optionGroup.clearSelection();
		
		// Update UI labels
		updateUI();
	}
	
	private void updateUI() {
		difficultyLabel.setText("Difficulty: " + quizGame.getDifficulty());
		roundLabel.setText("Round: " + quizGame.getCurrentRound() + "/" + quizGame.getTotalRounds());
		scoreLabel.setText("Score: " + quizGame.getScore());
		questionNumberLabel.setText("Question " + quizGame.getQuestionNumberInRound() + 
			"/" + quizGame.getQuestionsPerRound() + " in Round " + quizGame.getCurrentRound());
		
		// Update button text
		if (quizGame.isGameComplete()) {
			nextButton.setText("Finish Game");
		} else {
			nextButton.setText("Submit Answer");
		}
	}
	
	private void processAnswer() {
		// Check if an option is selected
		String selectedAnswer = getSelectedAnswer();
		if (selectedAnswer == null) {
			JOptionPane.showMessageDialog(this, 
				"Please select an answer!", 
				"No Answer Selected", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// Submit answer
		boolean roundComplete = quizGame.submitAnswer(selectedAnswer);
		
		// Update score display
		updateUI();
		
		if (roundComplete) {
			showRoundResults();
		} else if (quizGame.isGameComplete()) {
			endGame();
		} else {
			loadCurrentQuestion();
		}
	}
	
	private String getSelectedAnswer() {
		if (optionA.isSelected()) return "A";
		if (optionB.isSelected()) return "B";
		if (optionC.isSelected()) return "C";
		if (optionD.isSelected()) return "D";
		return null;
	}
	
	private void showRoundResults() {
	    String message = String.format("Round %d Complete!\n\n" +
	        "Round Score: %d/%d\n" +
	        "Total Score: %d/%d\n\n" +
	        "Ready for next round?", 
	        quizGame.getCurrentRound(),
	        quizGame.getRoundScore(), quizGame.getQuestionsPerRound(),
	        quizGame.getScore(), quizGame.getQuestionsAnswered());
	    
	    int choice = JOptionPane.showConfirmDialog(this, 
	        message, 
	        "Round Complete!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
	    
	    if (choice == JOptionPane.YES_OPTION) {
	        quizGame.moveToNextRound();
	        loadCurrentQuestion();
	    } else {
	        // Save progress and exit - only if not already saved
	        if (!quizGame.isGameSaved()) {
	            quizGame.saveGame();
	        }
	        returnToDashboard();
	    }
	}
	
	private void endGame() {
	    // FIX: Only save game once - check if already saved
	    if (!quizGame.isGameSaved()) {
	        // Save final score
	        quizGame.saveGame();
	    }
	    
	    // Show final results
	    String message = String.format("Quiz Complete!\n\n" +
	        "Difficulty: %s\n" +
	        "Rounds Played: %d/%d\n" +
	        "Questions Answered: %d/%d\n" +
	        "Final Score: %d\n" +
	        "Success Rate: %.2f%%\n\n" +
	        "Congratulations %s!", 
	        quizGame.getDifficulty(),
	        quizGame.getCurrentRound(), quizGame.getTotalRounds(),
	        quizGame.getQuestionsAnswered(), quizGame.getTotalQuestions(),
	        quizGame.getScore(),
	        (quizGame.getScore() * 100.0) / quizGame.getQuestionsAnswered(),
	        quizGame.getUsername());
	    
	    JOptionPane.showMessageDialog(this, 
	        message, 
	        "Game Over!", JOptionPane.INFORMATION_MESSAGE);
	    
	    returnToDashboard();
	}
	
	private void quitGame() {
		int confirm = JOptionPane.showConfirmDialog(this, 
			"Are you sure you want to quit?\nYour progress will be saved.",
			"Confirm Quit", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			quizGame.saveGame();
			returnToDashboard();
		}
	}
	
	private void returnToDashboard() {
		// Always pass the username when creating a new dashboard
		UserDashboard dashboard = new UserDashboard(username);
		dashboard.setVisible(true);
		dispose(); // Only dispose the PlayQuiz window, not the entire application
	}
	
	/**
	 * Launch the application for testing
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayQuiz frame = new PlayQuiz("Beginner", "testUser");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}