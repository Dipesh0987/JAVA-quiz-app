package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayersGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayersGUI frame = new PlayersGUI();
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
	public PlayersGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton back = new JButton("Back");
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

		JLabel lblNewLabel = new JLabel("Players");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(209, 10, 76, 29);
		contentPane.add(lblNewLabel);

		// Table setup
		javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane();
		scrollPane.setBounds(30, 60, 430, 260);
		contentPane.add(scrollPane);

		javax.swing.JTable table = new javax.swing.JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		table.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Username", "Email" }));
		scrollPane.setViewportView(table);

		// Load data
		loadPlayers(table);
	}

	private void loadPlayers(javax.swing.JTable table) {
		javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
		model.setRowCount(0);

		java.util.List<String[]> players = database.DBQuiz.getAllPlayers();
		for (String[] player : players) {
			model.addRow(player);
		}
	}
}
