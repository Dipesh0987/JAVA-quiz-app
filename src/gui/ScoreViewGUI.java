package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import database.DBQuiz;

public class ScoreViewGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable scoresTable;
	private String currentUsername;
	private int userId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreViewGUI frame = new ScoreViewGUI("testUser");
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
	public ScoreViewGUI(String username) {
		this.currentUsername = username;
		this.userId = DBQuiz.getUserId(username);
		initialize();
		loadScores();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDashboard dashboard = new UserDashboard(currentUsername);
				dashboard.setVisible(true);
				dispose();
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 15));
		back.setBounds(10, 10, 84, 20);
		contentPane.add(back);
		
		JLabel lblNewLabel = new JLabel("Your Quiz Scores");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(270, 10, 180, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Player: " + currentUsername);
		lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblUsername.setBounds(270, 45, 300, 25);
		contentPane.add(lblUsername);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 650, 280);
		contentPane.add(scrollPane);
		
		scoresTable = new JTable();
		scoresTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Difficulty", "Round", "Score", "Success Rate", "Date Played"
			}
		));
		scrollPane.setViewportView(scoresTable);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadScores();
			}
		});
		btnRefresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnRefresh.setBounds(378, 370, 120, 30);
		contentPane.add(btnRefresh);
		
		JButton btnClearAll = new JButton("Clear All Scores");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllScores();
			}
		});
		btnClearAll.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnClearAll.setBounds(518, 370, 150, 30);
		contentPane.add(btnClearAll);
	}
	
	private void loadScores() {
		DefaultTableModel model = (DefaultTableModel) scoresTable.getModel();
		model.setRowCount(0);
		
		List<String[]> scores = DBQuiz.getUserScores(userId);
		
		if (scores.isEmpty()) {
			Object[] row = {"No scores yet!", "-", "-", "-", "-"};
			model.addRow(row);
		} else {
			for (String[] score : scores) {
				model.addRow(score);
			}
		}
		
		// Update stats label
		double totalSuccess = 0;
		int totalGames = scores.size();
		for (String[] score : scores) {
			String successStr = score[3].replace("%", "");
			totalSuccess += Double.parseDouble(successStr);
		}
		
		double averageSuccess = totalGames > 0 ? totalSuccess / totalGames : 0;
		
		JLabel lblStats = new JLabel(String.format("Total Games: %d | Average Success: %.2f%%", 
			totalGames, averageSuccess));
		lblStats.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStats.setBounds(20, 380, 300, 25);
		contentPane.add(lblStats);
	}
	
	private void clearAllScores() {
	    // Show confirmation dialog
	    int confirm = JOptionPane.showConfirmDialog(this,
	        "Are you sure you want to delete ALL your scores?\n" +
	        "This action cannot be undone!",
	        "Confirm Delete All Scores",
	        JOptionPane.YES_NO_OPTION,
	        JOptionPane.WARNING_MESSAGE);
	    
	    if (confirm == JOptionPane.YES_OPTION) {
	        // Call the database method to delete scores
	        boolean success = DBQuiz.clearUserScores(userId);
	        
	        if (success) {
	            JOptionPane.showMessageDialog(this,
	                "All your scores have been cleared successfully!",
	                "Scores Cleared",
	                JOptionPane.INFORMATION_MESSAGE);
	            
	            // Refresh the table
	            loadScores();
	        } else {
	            JOptionPane.showMessageDialog(this,
	                "Failed to clear scores. Please try again.",
	                "Error",
	                JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
}