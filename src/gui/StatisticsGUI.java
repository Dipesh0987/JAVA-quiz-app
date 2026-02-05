package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * Statistics window for admin to view comprehensive player performance data.
 * Displays filtered leaderboard statistics with ability to filter by difficulty level.
 * Shows detailed player metrics including rank, games played, correct answers,
 * success rates, best scores and last activity.
 * 
 */
public class StatisticsGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
     * Launch the application - main entry point for testing.
     * Creates and displays the Statistics window.
     * 
     * @param args Command line arguments
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsGUI frame = new StatisticsGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
     * Create the Statistics frame with all UI components.
     * Initializes the statistics table with difficulty filtering capability.
     */
	public StatisticsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton back = new JButton("Back");
		back.setForeground(new Color(255, 255, 255));
		back.setBackground(new Color(4, 121, 251));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel ap = new AdminPanel();
				ap.setVisible(true);
				dispose();
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 15));
		back.setBounds(10, 10, 84, 20);
		contentPane.add(back);

		JLabel lblNewLabel = new JLabel("Statistics");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(205, 10, 101, 33);
		contentPane.add(lblNewLabel);

		// Difficulty filter
		javax.swing.JLabel lblFilter = new javax.swing.JLabel("Filter by Difficulty:");
		lblFilter.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblFilter.setBounds(30, 60, 150, 25);
		contentPane.add(lblFilter);

		final javax.swing.JComboBox<String> difficultyComboBox = new javax.swing.JComboBox<>();
		difficultyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
				"All", "Beginner", "Intermediate", "Advanced"
		}));
		difficultyComboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
		difficultyComboBox.setBounds(160, 60, 120, 25);

		contentPane.add(difficultyComboBox);

		// Table setup with ScrollPane
		javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
		scrollPane.setBounds(30, 100, 430, 250);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "Rank", "Username", "Difficulty", "Games", "Correct", "Success Rate", "Best Score",
						"Last Active" }
				) {
					@Override
				    public boolean isCellEditable(int row, int column) {
				        return false;
				    }
				});
		scrollPane.setViewportView(table);

		loadStatistics("All");

		// Add action listener for combo box
		difficultyComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
				loadStatistics(selectedDifficulty);
			}
		});
	}
	/**
     * Loads statistics from the database and displays in the table.
     * Filters results by difficulty level and displays up to 50 records.
     * 
     * @param difficulty The difficulty level to filter by ("All", "Beginner", "Intermediate", "Advanced")
     */
	private void loadStatistics(String difficulty) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);

		java.util.List<String[]> stats = database.DBQuiz.getLeaderboardByDifficulty(difficulty, 50);
		for (String[] stat : stats) {
			model.addRow(stat);
		}
	}
}
