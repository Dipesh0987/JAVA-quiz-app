package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import database.DBQuiz;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class LeaderboardGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable leaderboardTable;
    private String currentUsername;
    private JComboBox<String> difficultyComboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LeaderboardGUI frame = new LeaderboardGUI("testUser");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame with username.
     */
    public LeaderboardGUI(String username) {
        this.currentUsername = username;
        initialize();
        loadLeaderboard("All");
    }
    
    /**
     * Default constructor for compatibility
     */
    public LeaderboardGUI() {
        this("Guest");
    }
    
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 550); // Increased width
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton back = new JButton("Back");
        back.setBackground(new Color(4, 121, 251));
        back.setBounds(10, 12, 84, 25);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDashboard dashboard = new UserDashboard(currentUsername);
                dashboard.setVisible(true);
                dispose();
            }
        });
        back.setFont(new Font("Times New Roman", Font.BOLD, 15));
        contentPane.add(back);
        
        JLabel lblNewLabel = new JLabel("Quiz Leaderboard");
        lblNewLabel.setBounds(350, 11, 213, 33);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        contentPane.add(lblNewLabel);
        
        // Difficulty filter
        JLabel lblFilter = new JLabel("Filter by Difficulty:");
        lblFilter.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblFilter.setBounds(20, 50, 150, 25);
        contentPane.add(lblFilter);
        
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "All", "Beginner", "Intermediate", "Advanced"
        }));
        difficultyComboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
        difficultyComboBox.setBounds(150, 50, 120, 25);
        difficultyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                loadLeaderboard(selectedDifficulty);
            }
        });
        contentPane.add(difficultyComboBox);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 90, 800, 350);
        contentPane.add(scrollPane);
        
        leaderboardTable = new JTable();
        leaderboardTable.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        leaderboardTable.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Rank", "Player Name", "Difficulty", "Games Played", "Total Correct", 
                "Success Rate", "Best Score", "Last Active"
            }
        ) {
        	@Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        });
        scrollPane.setViewportView(leaderboardTable);
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setForeground(new Color(255, 255, 255));
        btnRefresh.setBackground(new Color(4, 121, 251));
        btnRefresh.setBounds(350, 460, 120, 30);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                loadLeaderboard(selectedDifficulty);
            }
        });
        btnRefresh.setFont(new Font("Times New Roman", Font.BOLD, 14));
        contentPane.add(btnRefresh);
        
        // Add "View Your Rank" button
        JButton btnYourRank = new JButton("View Your Rank");
        btnYourRank.setForeground(new Color(255, 255, 255));
        btnYourRank.setBackground(new Color(4, 121, 251));
        btnYourRank.setBounds(500, 460, 140, 30);
        btnYourRank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserRank();
            }
        });
        btnYourRank.setFont(new Font("Times New Roman", Font.BOLD, 14));
        contentPane.add(btnYourRank);
    }
    
    private void loadLeaderboard(String difficulty) {
        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();
        model.setRowCount(0);
        
        try {
            List<String[]> leaderboard = DBQuiz.getLeaderboardByDifficulty(difficulty, 50);
            
            for (String[] player : leaderboard) {
                model.addRow(player);
            }
            
        } catch (Exception e) {
            // Show error in table
            Object[] errorRow = {
                "Error", 
                "Could not load leaderboard: " + e.getMessage(),
                difficulty,
                "-", "-", "-", "-", "-"
            };
            model.addRow(errorRow);
            e.printStackTrace();
        }
    }
    private void showUserRank() {
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        String userRank = DBQuiz.getUserRank(currentUsername, difficulty);
        
        if (userRank != null && !userRank.contains("No ranking data")) {
            JOptionPane.showMessageDialog(this, 
                userRank,
                "Your Ranking", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                userRank != null ? userRank : "No ranking data found for " + currentUsername + " in " + difficulty,
                "No Ranking", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}