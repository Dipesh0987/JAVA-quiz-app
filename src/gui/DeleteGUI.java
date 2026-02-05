package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

// Import MySQL database classes
import database.DBFetch;
import database.DBDelete;
import models.Question;
import java.awt.Color;

public class DeleteGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField questionIdField;
	private JTextArea questionTextArea;
	private Question currentQuestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteGUI frame = new DeleteGUI();
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
	public DeleteGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton back_1 = new JButton("Back");
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ad = new AdminPanel();
				ad.setVisible(true);
				dispose();
			}
		});
		back_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		back_1.setBounds(10, 10, 84, 20);
		contentPane.add(back_1);
		
		JLabel lblDelete = new JLabel("Delete Question");
		lblDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDelete.setBounds(180, 10, 150, 27);
		contentPane.add(lblDelete);
		
		JLabel lblQuestionId = new JLabel("Question ID:");
		lblQuestionId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblQuestionId.setBounds(50, 50, 90, 20);
		contentPane.add(lblQuestionId);
		
		questionIdField = new JTextField();
		questionIdField.setColumns(10);
		questionIdField.setBounds(140, 47, 100, 27);
		contentPane.add(questionIdField);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(192, 192, 192));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchQuestion();
			}
		});
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSearch.setBounds(250, 47, 100, 27);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(192, 192, 192));
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
		lblNewLabel_1.setBounds(33, 87, 91, 20);
		contentPane.add(lblNewLabel_1);
		
		questionTextArea = new JTextArea();
		questionTextArea.setEditable(false);
		questionTextArea.setBounds(33, 117, 417, 55);
		contentPane.add(questionTextArea);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(255, 0, 0));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteQuestion();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnDelete.setBounds(198, 219, 96, 32);
		contentPane.add(btnDelete);

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
				questionTextArea.setText(currentQuestion.getQuestionText());
				JOptionPane.showMessageDialog(this, 
					"Question ID " + id + " found.\nYou can delete if you want to.", 
					"Question Found", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, 
					"Question not found with ID: " + id, 
					"Not Found", JOptionPane.WARNING_MESSAGE);
				clearForm();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID", 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteQuestion() {
		if (currentQuestion == null) {
			JOptionPane.showMessageDialog(this, 
				"Please search for a question first.", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Show detailed confirmation with question info
		String message = "Are you sure you want to delete this question?\n\n" +
						"ID: " + currentQuestion.getId() + "\n" +
						"Question: " + currentQuestion.getQuestionText() + "\n" +
						"Difficulty: " + currentQuestion.getDifficulty() + "\n\n" +
						"This action cannot be undone!";
		
		int confirm = JOptionPane.showConfirmDialog(this, 
			message, 
			"Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if (confirm == JOptionPane.YES_OPTION) {
			// Perform delete using DBDelete
			boolean success = DBDelete.deleteQuestion(currentQuestion.getId());
			
			if (success) {
				JOptionPane.showMessageDialog(this, 
					"Question ID " + currentQuestion.getId() + " deleted successfully", 
					"Success", JOptionPane.INFORMATION_MESSAGE);
				clearForm();
			} else {
				JOptionPane.showMessageDialog(this, 
					"Failed to delete question. Please try again.", 
					"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void clearForm() {
		questionIdField.setText("");
		questionTextArea.setText("");
		currentQuestion = null;
		questionIdField.requestFocus();
	}
}