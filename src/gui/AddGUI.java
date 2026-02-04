package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

// Import the MySQL database classes
import database.DBInsertion;
import java.awt.Color;

public class AddGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea questiontextArea;
	private JTextArea optionA;
	private JTextArea optionB;
	private JTextArea optionC;
	private JTextArea optionD;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGUI frame = new AddGUI();
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
	public AddGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Questions");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(188, 10, 130, 30);
		contentPane.add(lblNewLabel);
		
		JButton back_1 = new JButton("Back");
		back_1.setBackground(new Color(192, 192, 192));
		back_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap = new AdminPanel();
				ap.setVisible(true);
				dispose();
			}
		});
		back_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		back_1.setBounds(10, 10, 84, 30);
		contentPane.add(back_1);
		
		JLabel lblNewLabel_1 = new JLabel("Question");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(37, 51, 91, 20);
		contentPane.add(lblNewLabel_1);
		
		questiontextArea = new JTextArea();
		questiontextArea.setBounds(37, 81, 417, 69);
		contentPane.add(questiontextArea);
		
		JLabel lblNewLabel_1_1 = new JLabel("Options");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(37, 160, 91, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("A");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(37, 195, 24, 30);
		contentPane.add(lblNewLabel_2);
		
		optionA = new JTextArea();
		optionA.setBounds(59, 190, 165, 35);
		contentPane.add(optionA);
		
		optionB = new JTextArea();
		optionB.setBounds(283, 190, 165, 35);
		contentPane.add(optionB);
		
		JLabel lblNewLabel_2_1 = new JLabel("B");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(261, 195, 24, 30);
		contentPane.add(lblNewLabel_2_1);
		
		optionC = new JTextArea();
		optionC.setBounds(59, 235, 165, 35);
		contentPane.add(optionC);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("C");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(37, 240, 24, 30);
		contentPane.add(lblNewLabel_2_1_1);
		
		optionD = new JTextArea();
		optionD.setBounds(283, 235, 165, 35);
		contentPane.add(optionD);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("D");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2_1_1_1.setBounds(261, 240, 24, 30);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Correct Option");
		lblNewLabel_2_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_2.setBounds(80, 285, 131, 29);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Difficulty");
		lblNewLabel_2_2_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2_2_1.setBounds(306, 285, 131, 29);
		contentPane.add(lblNewLabel_2_2_1);
		
		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Select Option", "A", "B", "C", "D"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(80, 325, 116, 30);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"Select Difficulty", "Beginner", "Intermediate", "Advanced"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		comboBox_1.setBounds(283, 325, 130, 30);
		contentPane.add(comboBox_1);
		
		JButton add = new JButton("Add");
		add.setBackground(new Color(192, 192, 192));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addQuestionToDatabase();
			}
		});
		add.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add.setBounds(205, 371, 84, 32);
		contentPane.add(add);
	}
	
	private void addQuestionToDatabase() {
		// Validate inputs
		String questionText = questiontextArea.getText().trim();
		String optA = optionA.getText().trim();
		String optB = optionB.getText().trim();
		String optC = optionC.getText().trim();
		String optD = optionD.getText().trim();
		String correctOpt = (String) comboBox.getSelectedItem();
		String difficulty = (String) comboBox_1.getSelectedItem();
		
		// Validation checks
		if (questionText.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter the question", 
				"Error", JOptionPane.ERROR_MESSAGE);
			questiontextArea.requestFocus();
			return;
		}
		
		if (optA.isEmpty() || optB.isEmpty() || optC.isEmpty() || optD.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter all options", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if ("Select Option".equals(correctOpt)) {
			JOptionPane.showMessageDialog(this, "Please select the correct option", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if ("Select Difficulty".equals(difficulty)) {
			JOptionPane.showMessageDialog(this, "Please select difficulty level", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Insert question into MySQL database and get the generated question ID
		int questionId = DBInsertion.insertQuestion(
			questionText, optA, optB, optC, optD, correctOpt, difficulty
		);
		
		if (questionId > 0) {
			// Show success message with question ID
			JOptionPane.showMessageDialog(this, 
				"Question added successfully!\n" +
				"Question ID: " + questionId, 
				"Success", JOptionPane.INFORMATION_MESSAGE);
			
			// Clear form
			clearForm();
		} else {
			JOptionPane.showMessageDialog(this, 
				"Failed to add question. Please check database connection.", 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void clearForm() {
		questiontextArea.setText("");
		optionA.setText("");
		optionB.setText("");
		optionC.setText("");
		optionD.setText("");
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		questiontextArea.requestFocus();
	}
}