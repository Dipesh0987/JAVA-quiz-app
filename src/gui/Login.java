package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import database.DBLogin;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginlbl = new JLabel("Log In");
		loginlbl.setFont(new Font("Times New Roman", Font.BOLD, 18));
		loginlbl.setBounds(251, 21, 72, 32);
		contentPane.add(loginlbl);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(148, 66, 114, 24);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(148, 100, 155, 24);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPassword.setBounds(148, 141, 114, 24);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(148, 175, 155, 24);
		contentPane.add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performLogin();
			}
		});
		login.setBackground(new Color(0, 255, 0));
		login.setFont(new Font("Times New Roman", Font.BOLD, 16));
		login.setBounds(148, 223, 114, 32);
		contentPane.add(login);
		
		JLabel lblNewLabel_1 = new JLabel("Don't Have an Account? Register Here.");
		lblNewLabel_1.setForeground(new Color(0, 128, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(148, 266, 233, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton register = new JButton("Register");
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register reg = new Register();
				reg.setVisible(true);
				dispose();
			}
		});
		register.setBackground(new Color(0, 128, 255));
		register.setFont(new Font("Times New Roman", Font.BOLD, 16));
		register.setBounds(148, 296, 114, 32);
		contentPane.add(register);
	}
	
	private void performLogin() {
		String enteredUsername = username.getText();
		String enteredPassword = new String(password.getPassword());
		
		if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter both username and password", 
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Use database authentication instead of hardcoded values
		if (DBLogin.authenticate(enteredUsername, enteredPassword)) {
		    String role = DBLogin.getUserRole(enteredUsername);
		    
		    if ("admin".equals(role)) {
		        JOptionPane.showMessageDialog(this, "Login successful as Admin!", 
		            "Success", JOptionPane.INFORMATION_MESSAGE);
		        AdminPanel adminPanel = new AdminPanel();
		        adminPanel.setVisible(true);
		        dispose();
		    } else {
		        JOptionPane.showMessageDialog(this, "Login successful as User!", 
		            "Success", JOptionPane.INFORMATION_MESSAGE);
		        // FIX: Pass username to UserDashboard
		        UserDashboard userDashboard = new UserDashboard(enteredUsername);
		        userDashboard.setVisible(true);
		        dispose();
		 
		    }
		}
	}
}