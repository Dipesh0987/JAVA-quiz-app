package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

// Import MySQL database classes
import database.DBFetch;
import database.DBUpdate;
import models.Question;
import java.awt.Color;

public class UpdateGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionIdField;
	
	// TextArea fields as class fields
	private JTextArea questionTextArea;
	private JTextArea optionATextArea;
	private JTextArea optionBTextArea;
	private JTextArea optionCTextArea;
	private JTextArea optionDTextArea;
	private JComboBox<String> correctOptionComboBox;
	private JComboBox<String> difficultyComboBox;
	
	// Store the current question being edited
	private Question currentQuestion;
	private boolean isQuestionLoaded = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateGUI frame = new UpdateGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Question");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(180, 10, 150, 27);
		contentPane.add(lblNewLabel);
		
		JButton back_1 = new JButton("Back");
		back_1.setForeground(new Color(255, 255, 255));
		back_1.setBackground(new Color(4, 121, 251));
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap = new AdminPanel();
				ap.setVisible(true);
				dispose();
			}
		});
		back_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		back_1.setBounds(10, 10, 84, 27);
		contentPane.add(back_1);
		
		JLabel lblQuestionId = new JLabel("Question ID:");
		lblQuestionId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblQuestionId.setBounds(50, 50, 90, 20);
		contentPane.add(lblQuestionId);
		
		questionIdField = new JTextField();
		questionIdField.setColumns(10);
		questionIdField.setBounds(140, 47, 100, 27);
		contentPane.add(questionIdField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(4, 121, 251));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchQuestion();
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setBounds(250, 47, 100, 27);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(4, 121, 251));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnClear.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClear.setBounds(360, 47, 80, 27);
		contentPane.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Question");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(31, 84, 91, 20);
		contentPane.add(lblNewLabel_1);
		
		questionTextArea = new JTextArea();
		questionTextArea.setBounds(31, 114, 417, 55);
		contentPane.add(questionTextArea);
		
		JLabel lblNewLabel_1_1 = new JLabel("Options");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(31, 180, 91, 20);
		contentPane.add(lblNewLabel_1_1);
		
		optionATextArea = new JTextArea();
		optionATextArea.setBounds(53, 210, 165, 35);
		contentPane.add(optionATextArea);
		
		optionBTextArea = new JTextArea();
		optionBTextArea.setBounds(277, 210, 165, 35);
		contentPane.add(optionBTextArea);
		
		optionCTextArea = new JTextArea();
		optionCTextArea.setBounds(53, 255, 165, 35);
		contentPane.add(optionCTextArea);
		
		JLabel lblNewLabel_2 = new JLabel("A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(31, 215, 24, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("C");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(31, 260, 24, 30);
		contentPane.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("B");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(255, 215, 24, 30);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("D");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1_1_1.setBounds(255, 260, 24, 30);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		optionDTextArea = new JTextArea();
		optionDTextArea.setBounds(277, 255, 165, 35);
		contentPane.add(optionDTextArea);
		
		JLabel lblNewLabel_2_2 = new JLabel("Correct Option");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(74, 305, 131, 29);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Difficulty");
		lblNewLabel_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_2_1.setBounds(300, 305, 131, 29);
		contentPane.add(lblNewLabel_2_2_1);
		
		correctOptionComboBox = new JComboBox<>();
		correctOptionComboBox.setForeground(new Color(255, 255, 255));
		correctOptionComboBox.setBackground(new Color(4, 121, 251));
		correctOptionComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Select Option", "A", "B", "C", "D"}));
		correctOptionComboBox.setSelectedIndex(0);
		correctOptionComboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		correctOptionComboBox.setBounds(74, 345, 116, 30);
		contentPane.add(correctOptionComboBox);
		
		difficultyComboBox = new JComboBox<>();
		difficultyComboBox.setForeground(new Color(255, 255, 255));
		difficultyComboBox.setBackground(new Color(4, 121, 251));
		difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Select Difficulty", "Beginner", "Intermediate", "Advanced"}));
		difficultyComboBox.setSelectedIndex(0);
		difficultyComboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		difficultyComboBox.setBounds(277, 345, 130, 30);
		contentPane.add(difficultyComboBox);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(4, 121, 251));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateQuestion();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdate.setBounds(205, 390, 96, 32);
		contentPane.add(btnUpdate);
		
		// Add a status label
		JLabel lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		lblStatus.setBounds(31, 175, 300, 15);
		contentPane.add(lblStatus);

	}
	
	private void searchQuestion() {
		try {
			String input = questionIdField.getText().trim();
			
			if (input.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please enter a Question ID", 
					"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int id = Integer.parseInt(input);
			
			// Use DBFetch to get question from MySQL database
			currentQuestion = DBFetch.getQuestionById(id);
			
			if (currentQuestion != null) {
				// Load question details into the form
				loadQuestionIntoForm(currentQuestion);
				isQuestionLoaded = true;
				
				JOptionPane.showMessageDialog(this, 
					"Question ID " + id + " loaded successfully.\nYou can now edit and update it.", 
					"Question Found", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, 
					"Question not found with ID: " + id, 
					"Not Found", JOptionPane.WARNING_MESSAGE);
				clearForm();
				isQuestionLoaded = false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID", 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadQuestionIntoForm(Question question) {
		questionTextArea.setText(question.getQuestionText());
		optionATextArea.setText(question.getOptionA());
		optionBTextArea.setText(question.getOptionB());
		optionCTextArea.setText(question.getOptionC());
		optionDTextArea.setText(question.getOptionD());
		
		// Set correct option in combo box
		String correctOption = question.getCorrectOption();
		switch (correctOption.toUpperCase()) {
			case "A": correctOptionComboBox.setSelectedIndex(1); break;
			case "B": correctOptionComboBox.setSelectedIndex(2); break;
			case "C": correctOptionComboBox.setSelectedIndex(3); break;
			case "D": correctOptionComboBox.setSelectedIndex(4); break;
			default: correctOptionComboBox.setSelectedIndex(0);
		}
		
		// Set difficulty in combo box
		String difficulty = question.getDifficulty();
		switch (difficulty.toLowerCase()) {
			case "beginner": difficultyComboBox.setSelectedIndex(1); break;
			case "intermediate": difficultyComboBox.setSelectedIndex(2); break;
			case "advanced": difficultyComboBox.setSelectedIndex(3); break;
			default: difficultyComboBox.setSelectedIndex(0);
		}
	}
	
	private void updateQuestion() {
		if (!isQuestionLoaded || currentQuestion == null) {
			JOptionPane.showMessageDialog(this, 
				"Please search for a question first before updating.", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Get updated values from form
		String updatedQuestionText = questionTextArea.getText().trim();
		String updatedOptionA = optionATextArea.getText().trim();
		String updatedOptionB = optionBTextArea.getText().trim();
		String updatedOptionC = optionCTextArea.getText().trim();
		String updatedOptionD = optionDTextArea.getText().trim();
		String updatedCorrectOption = (String) correctOptionComboBox.getSelectedItem();
		String updatedDifficulty = (String) difficultyComboBox.getSelectedItem();
		
		// Validation
		if (updatedQuestionText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Question text cannot be empty", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			questionTextArea.requestFocus();
			return;
		}
		
		if (updatedOptionA.isEmpty() || updatedOptionB.isEmpty() || 
			updatedOptionC.isEmpty() || updatedOptionD.isEmpty()) {
			JOptionPane.showMessageDialog(this, "All options must be filled", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if ("Select Option".equals(updatedCorrectOption)) {
			JOptionPane.showMessageDialog(this, "Please select a correct option", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if ("Select Difficulty".equals(updatedDifficulty)) {
			JOptionPane.showMessageDialog(this, "Please select a difficulty level", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Create updated question object
		Question updatedQuestion = new Question(
			updatedQuestionText, 
			updatedOptionA, 
			updatedOptionB, 
			updatedOptionC, 
			updatedOptionD, 
			updatedCorrectOption, 
			updatedDifficulty
		);
		updatedQuestion.setId(currentQuestion.getId()); // Set the ID from current question
		
		// Confirm update
		int confirm = JOptionPane.showConfirmDialog(this, 
			"Are you sure you want to update Question ID " + currentQuestion.getId() + "?\n\n" +
			"Old Question: " + currentQuestion.getQuestionText() + "\n" +
			"New Question: " + updatedQuestionText, 
			"Confirm Update", JOptionPane.YES_NO_OPTION);
		
		if (confirm == JOptionPane.YES_OPTION) {
			// Perform update using DBUpdate
			boolean success = DBUpdate.updateQuestion(
				updatedQuestion.getId(),
				updatedQuestion.getQuestionText(),
				updatedQuestion.getOptionA(),
				updatedQuestion.getOptionB(),
				updatedQuestion.getOptionC(),
				updatedQuestion.getOptionD(),
				updatedQuestion.getCorrectOption(),
				updatedQuestion.getDifficulty()
			);
			
			if (success) {
				JOptionPane.showMessageDialog(this, 
					"Question ID " + currentQuestion.getId() + " updated successfully!", 
					"Update Successful", JOptionPane.INFORMATION_MESSAGE);
				
				// Update current question reference
				currentQuestion = updatedQuestion;
				
				// Optionally clear form or keep it loaded
				int choice = JOptionPane.showConfirmDialog(this, 
					"Question updated successfully!\n\nDo you want to clear the form and search for another question?", 
					"Continue?", JOptionPane.YES_NO_OPTION);
				
				if (choice == JOptionPane.YES_OPTION) {
					clearForm();
				}
			} else {
				JOptionPane.showMessageDialog(this, 
					"Failed to update question. Please try again.", 
					"Update Failed", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void clearForm() {
		questionIdField.setText("");
		questionTextArea.setText("");
		optionATextArea.setText("");
		optionBTextArea.setText("");
		optionCTextArea.setText("");
		optionDTextArea.setText("");
		correctOptionComboBox.setSelectedIndex(0);
		difficultyComboBox.setSelectedIndex(0);
		currentQuestion = null;
		isQuestionLoaded = false;
		questionIdField.requestFocus();
	}
}