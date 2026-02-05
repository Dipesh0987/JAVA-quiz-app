package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Main dashboard for admin in the Quiz Mania application.
 * Provides navigation to all administrative functions including
 * question management, player statistics, and system administration.
 * 
 */

public class AdminPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
     * Launch the application
     * Creates and displays the Admin Panel window.
     * 
     * @param args Command line arguments
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
     * Create the Admin Panel frame with all navigation buttons.
     * Initializes the main dashboard interface for administrators
     * with access to CRUD operations, statistics, and player management.
     */
	public AdminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quiz Mania");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(213, 10, 137, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome Admin,");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(43, 56, 137, 29);
		contentPane.add(lblNewLabel_1);
		
		// Add button
		JButton add = new JButton("Add");
		add.setForeground(new Color(255, 255, 255));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGUI ad = new AddGUI();
				ad.setVisible(true);
				dispose();
			}
		});
		add.setBackground(new Color(4, 121, 251));
		add.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add.setBounds(43, 183, 84, 29);
		contentPane.add(add);
		
		//View button
		JButton view = new JButton("View");
		view.setForeground(new Color(255, 255, 255));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewGUI v = new ViewGUI();
				v.setVisible(true);
				dispose();
			}
		});
		view.setBackground(new Color(4, 121, 251));
		view.setFont(new Font("Times New Roman", Font.BOLD, 16));
		view.setBounds(137, 183, 84, 29);
		contentPane.add(view);
		
		// Update button
		JButton Update = new JButton("Update");
		Update.setForeground(new Color(255, 255, 255));
		Update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateGUI up = new UpdateGUI();
				up.setVisible(true);
				dispose();
			}
		});
		Update.setBackground(new Color(4, 121, 251));
		Update.setFont(new Font("Times New Roman", Font.BOLD, 16));
		Update.setBounds(43, 226, 84, 29);
		contentPane.add(Update);
		
		//delete
		JButton delete = new JButton("Delete");
		delete.setForeground(new Color(255, 255, 255));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteGUI del = new DeleteGUI();
				del.setVisible(true);
				dispose();
			}
		});
		delete.setBackground(new Color(4, 121, 251));
		delete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		delete.setBounds(137, 226, 84, 29);
		contentPane.add(delete);
		
		JLabel lblNewLabel_2 = new JLabel("Quiz Management System");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(166, 95, 240, 56);
		contentPane.add(lblNewLabel_2);
		
		// Logout button
		JButton logout = new JButton("Log Out");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login log = new Login();
				log.setVisible(true);
				dispose();
			}
		});
		logout.setForeground(new Color(255, 255, 255));
		logout.setBackground(new Color(255, 0, 0));
		logout.setFont(new Font("Times New Roman", Font.BOLD, 18));
		logout.setBounds(210, 292, 101, 29);
		contentPane.add(logout);
		
		JButton stats = new JButton("View Statistics");
		stats.setForeground(new Color(255, 255, 255));
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsGUI stats = new StatisticsGUI();
				stats.setVisible(true);
				dispose();
			}
		});
		stats.setFont(new Font("Times New Roman", Font.BOLD, 16));
		stats.setBackground(new Color(4, 121, 251));
		stats.setBounds(380, 184, 137, 28);
		contentPane.add(stats);
		
		JButton search = new JButton("Players");
		search.setForeground(new Color(255, 255, 255));
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayersGUI player = new PlayersGUI();
				player.setVisible(true);
				dispose();
			}
		});
		search.setFont(new Font("Times New Roman", Font.BOLD, 16));
		search.setBackground(new Color(4, 121, 251));
		search.setBounds(380, 227, 137, 28);
		contentPane.add(search);

	}
}
