package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField password;
	private JTextField email;
	private JTextField username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(190, 20, 116, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(175, 140, 116, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(175, 210, 116, 23);
		contentPane.add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setBounds(175, 240, 142, 29);
		contentPane.add(password);
		
		email = new JTextField();
		email.setBounds(175, 170, 143, 29);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performRegistration();
			}
		});
		register.setBackground(new Color(128, 255, 0));
		register.setFont(new Font("Times New Roman", Font.BOLD, 16));
		register.setBounds(175, 280, 143, 29);
		contentPane.add(register);
		
		JButton backlogin = new JButton("Back to Login");
		backlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		backlogin.setBackground(new Color(0, 255, 255));
		backlogin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		backlogin.setBounds(175, 320, 144, 29);
		contentPane.add(backlogin);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(175, 70, 116, 23);
		contentPane.add(lblNewLabel_1_2);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(175, 100, 143, 29);
		contentPane.add(username);
	}
	
	private void performRegistration() {
		String enteredUsername = username.getText().trim();
		String enteredPassword = new String(password.getPassword());
		String enteredEmail = email.getText().trim();
		
		// Validation
		if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
			JOptionPane.showMessageDialog(this, 
				"Username and password are required!", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (enteredUsername.length() < 3) {
			JOptionPane.showMessageDialog(this, 
				"Username must be at least 3 characters!", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (enteredPassword.length() < 4) {
			JOptionPane.showMessageDialog(this, 
				"Password must be at least 4 characters!", 
				"Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Check if username already exists
		if (isUsernameTaken(enteredUsername)) {
			JOptionPane.showMessageDialog(this, 
				"Username already exists! Please choose another.", 
				"Username Taken", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Save to database
		if (saveUserToDatabase(enteredUsername, enteredPassword, enteredEmail)) {
			JOptionPane.showMessageDialog(this, 
				"Registration successful! You can now login.", 
				"Success", JOptionPane.INFORMATION_MESSAGE);
			Login login = new Login();
			login.setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, 
				"Registration failed. Please try again.", 
				"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isUsernameTaken(String username) {
		String url = "jdbc:mysql://localhost:3306/quiz_app";
		String dbUsername = "root";
		String dbPassword = "";
		
		String query = "SELECT id FROM users WHERE username = ?";
		
		try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			return rs.next(); // Returns true if username exists
			
		} catch (SQLException e) {
			System.out.println("Error checking username: " + e.getMessage());
			// In case of error, assume username is not taken to allow registration
			return false;
		}
	}
	
	private boolean saveUserToDatabase(String username, String password, String email) {
		String url = "jdbc:mysql://localhost:3306/quiz_app";
		String dbUsername = "root";
		String dbPassword = "";
		
		String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, 'player')";
		
		try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, email.isEmpty() ? null : email);
			
			int affectedRows = pstmt.executeUpdate();
			System.out.println("Registered new user: " + username);
			return affectedRows > 0;
			
		} catch (SQLException e) {
			System.out.println("Error registering user: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}