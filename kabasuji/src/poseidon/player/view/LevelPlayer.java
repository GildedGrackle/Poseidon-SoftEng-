package poseidon.player.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class LevelPlayer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelPlayer frame = new LevelPlayer();
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
	public LevelPlayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblKabasujiLevelPlayer = new JLabel("Kabasuji Level Player");
		lblKabasujiLevelPlayer.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnLevelSelect = new JButton("Level Select");
		btnLevelSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnCustomLevels = new JButton("Custom Levels");
		btnCustomLevels.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addComponent(lblKabasujiLevelPlayer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(244)
							.addComponent(btnLevelSelect))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(231)
							.addComponent(btnStartGame))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(220)
							.addComponent(btnCustomLevels, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
					.addGap(129))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addComponent(btnAbout)
					.addGap(274))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(327, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addGap(289))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(lblKabasujiLevelPlayer)
					.addGap(68)
					.addComponent(btnStartGame)
					.addGap(38)
					.addComponent(btnLevelSelect)
					.addGap(30)
					.addComponent(btnCustomLevels)
					.addGap(32)
					.addComponent(btnAbout)
					.addGap(37)
					.addComponent(btnExit)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
