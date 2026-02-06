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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * User Dashboard - Main Menu After Login
 * 
 * This window is displayed after a user successfull log in to Quiz Mania.
 * It provides access to all main features of the quiz application through
 * simple buttons and dropdown menus.
 * 
 */
public class UserDashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private String currentUsername;
    private JComboBox<String> difficultyComboBox;

    /**
     * Launch the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserDashboard frame = new UserDashboard("testUser");
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
    public UserDashboard(String username) {
        this.currentUsername = username;
        initialize();
        
        
    }
    
    /**
     * Create dashboard for a guest user
     */
    public UserDashboard() {
        this("Guest");
    }
    /**
     * Set up all the UI components for the dashboard
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 499, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel welcome = new JLabel("Welcome " + currentUsername + ",");
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
        welcome.setBounds(150, 25, 250, 40);
        contentPane.add(welcome);
        
        JButton logout = new JButton("Log Out");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login log = new Login();
                log.setVisible(true);
                dispose();
            }
        });
        logout.setForeground(Color.WHITE);
        logout.setFont(new Font("Times New Roman", Font.BOLD, 16));
        logout.setBackground(Color.RED);
        logout.setBounds(376, 10, 99, 29);
        contentPane.add(logout);
        
        JButton play = new JButton("Play Quiz");
        play.setBackground(new Color(128, 255, 0));
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                if ("Select Difficulty".equals(selectedDifficulty)) {
                    JOptionPane.showMessageDialog(UserDashboard.this, 
                        "Please select a difficulty level first!", 
                        "Select Difficulty", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // FIXED: Pass parameters to PlayQuiz
                PlayQuiz playQuiz = new PlayQuiz(selectedDifficulty, currentUsername);
                playQuiz.setVisible(true);
                setVisible(false);
            }
        });
        play.setFont(new Font("Times New Roman", Font.BOLD, 18));
        play.setBounds(169, 199, 130, 29);
        contentPane.add(play);
        
        JButton viewScore = new JButton("View Score");
        viewScore.setForeground(new Color(255, 255, 255));
        viewScore.setBackground(new Color(4, 121, 251));
        viewScore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ScoreViewGUI scoreView = new ScoreViewGUI(currentUsername);
                scoreView.setVisible(true);
                dispose();
            }
        });
        viewScore.setFont(new Font("Times New Roman", Font.BOLD, 18));
        viewScore.setBounds(30, 282, 130, 29);
        contentPane.add(viewScore);
        
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setForeground(new Color(255, 255, 255));
        difficultyComboBox.setBackground(new Color(4, 121, 251));
        difficultyComboBox.setFont(new Font("Times New Roman", Font.BOLD, 15));
        difficultyComboBox.setModel(new DefaultComboBoxModel<String>(
            new String[] {"Select Difficulty", "Beginner", "Intermediate", "Advanced"}));
        difficultyComboBox.setBounds(30, 136, 130, 40);
        contentPane.add(difficultyComboBox);
        
        JButton leaderboard = new JButton("Leaderboard");
        leaderboard.setForeground(new Color(255, 255, 255));
        leaderboard.setBackground(new Color(4, 121, 251));
        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LeaderboardGUI leaderboardGUI = new LeaderboardGUI(currentUsername);
                leaderboardGUI.setVisible(true);
                dispose();
            }
        });
        leaderboard.setFont(new Font("Times New Roman", Font.BOLD, 17));
        leaderboard.setBounds(332, 282, 130, 29);
        contentPane.add(leaderboard);
        
        JLabel lblNewLabel_1 = new JLabel("Quiz Mania");
        lblNewLabel_1.setForeground(new Color(0, 0, 255));
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 21));
        lblNewLabel_1.setBounds(160, 75, 165, 51);
        contentPane.add(lblNewLabel_1);
    }
}