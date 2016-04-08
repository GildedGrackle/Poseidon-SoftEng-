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
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.CardLayout;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelPlayer extends JFrame {

	private JPanel contentPane;
	private final JButton btnNext = new JButton("Next");
	private final JButton btnFlip = new JButton("Flip");
	private final JButton btnReplay = new JButton("Replay");

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
		
		JPanel LevelPlayerMenu = new JPanel();
		
		JLabel lblKabasujiLevelPlayer_1 = DefaultComponentFactory.getInstance().createTitle("Kabasuji Level Player");
		lblKabasujiLevelPlayer_1.setFont(new Font("Lucida Handwriting", Font.BOLD, 35));
		
		JButton btnLevelSelect = new JButton("Level Select");
		btnLevelSelect.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnCustomLevels = new JButton("Custom Levels");
		btnCustomLevels.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		GroupLayout gl_LevelPlayerMenu = new GroupLayout(LevelPlayerMenu);
		gl_LevelPlayerMenu.setHorizontalGroup(
			gl_LevelPlayerMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addGroup(gl_LevelPlayerMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
							.addGap(127)
							.addComponent(lblKabasujiLevelPlayer_1))
						.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
							.addGap(234)
							.addComponent(btnStartGame)))
					.addContainerGap(126, Short.MAX_VALUE))
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(btnLevelSelect)
					.addGap(240))
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addContainerGap(224, Short.MAX_VALUE)
					.addComponent(btnCustomLevels, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addGap(218))
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addContainerGap(280, Short.MAX_VALUE)
					.addComponent(btnAbout)
					.addGap(273))
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addComponent(btnExit)
					.addGap(286))
		);
		gl_LevelPlayerMenu.setVerticalGroup(
			gl_LevelPlayerMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_LevelPlayerMenu.createSequentialGroup()
					.addGap(43)
					.addComponent(lblKabasujiLevelPlayer_1)
					.addGap(54)
					.addComponent(btnStartGame)
					.addGap(44)
					.addComponent(btnLevelSelect)
					.addGap(30)
					.addComponent(btnCustomLevels)
					.addGap(37)
					.addComponent(btnAbout)
					.addGap(32)
					.addComponent(btnExit)
					.addContainerGap(159, Short.MAX_VALUE))
		);
		LevelPlayerMenu.setLayout(gl_LevelPlayerMenu);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(LevelPlayerMenu, "name_1460039050221102000");
		
		JPanel LevelSelect = new JPanel();
		contentPane.add(LevelSelect, "name_1460039050203913000");
		
		JLabel lblLevelSelect = new JLabel("Level Select");
		lblLevelSelect.setBounds(227, 41, 216, 49);
		lblLevelSelect.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		
		JLabel lblPuzzle = new JLabel("Puzzle:");
		lblPuzzle.setBounds(30, 135, 87, 31);
		lblPuzzle.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JLabel lblLightning = new JLabel("Lightning:");
		lblLightning.setBounds(30, 273, 124, 31);
		lblLightning.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JLabel lblRelease = new JLabel("Release:");
		lblRelease.setBounds(30, 412, 128, 31);
		lblRelease.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		
		JButton Puzzle_1 = new JButton("1");
		Puzzle_1.setBounds(188, 122, 75, 68);
		
		JButton Puzzle_2 = new JButton("2");
		Puzzle_2.setBounds(281, 122, 75, 68);
		
		JButton Puzzle_3 = new JButton("3");
		Puzzle_3.setBounds(374, 122, 75, 68);
		
		JButton Puzzle_4 = new JButton("4");
		Puzzle_4.setBounds(467, 122, 75, 68);
		
		JButton Puzzle_5 = new JButton("5");
		Puzzle_5.setBounds(564, 122, 75, 68);
		
		JButton Lightning_1 = new JButton("1");
		Lightning_1.setBounds(188, 255, 75, 68);
		
		JButton Lightning_2 = new JButton("2");
		Lightning_2.setBounds(281, 256, 75, 68);
		
		JButton Lightning_3 = new JButton("3");
		Lightning_3.setBounds(374, 256, 75, 68);
		
		JButton Lightning_4 = new JButton("4");
		Lightning_4.setBounds(467, 256, 75, 68);
		LevelSelect.setLayout(null);
		LevelSelect.add(lblPuzzle);
		LevelSelect.add(lblRelease);
		LevelSelect.add(lblLightning);
		LevelSelect.add(Lightning_1);
		LevelSelect.add(Lightning_2);
		LevelSelect.add(Lightning_3);
		LevelSelect.add(Lightning_4);
		
		JButton Lightning_5 = new JButton("5");
		Lightning_5.setBounds(564, 255, 75, 68);
		LevelSelect.add(Lightning_5);
		LevelSelect.add(Puzzle_1);
		LevelSelect.add(Puzzle_2);
		LevelSelect.add(Puzzle_3);
		LevelSelect.add(Puzzle_4);
		LevelSelect.add(Puzzle_5);
		LevelSelect.add(lblLevelSelect);
		
		JButton Level_Back = new JButton("Back");
		Level_Back.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		Level_Back.setBounds(30, 546, 149, 68);
		LevelSelect.add(Level_Back);
		
		JButton Level_Play = new JButton("Play");
		Level_Play.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		Level_Play.setBounds(484, 546, 155, 68);
		LevelSelect.add(Level_Play);
		
		JButton Release_1 = new JButton("1");
		Release_1.setBounds(188, 397, 75, 68);
		LevelSelect.add(Release_1);
		
		JButton Release_2 = new JButton("2");
		Release_2.setBounds(281, 398, 75, 68);
		LevelSelect.add(Release_2);
		
		JButton Release_3 = new JButton("3");
		Release_3.setBounds(374, 398, 75, 68);
		LevelSelect.add(Release_3);
		
		JButton Release_4 = new JButton("4");
		Release_4.setBounds(467, 398, 75, 68);
		LevelSelect.add(Release_4);
		
		JButton Release_5 = new JButton("5");
		Release_5.setBounds(564, 397, 75, 68);
		LevelSelect.add(Release_5);
		
		StarViewer L1Pstars = new StarViewer();
		L1Pstars.setBounds(194, 191, 69, 31);
		LevelSelect.add(L1Pstars);
		
		StarViewer L2Pstars = new StarViewer();
		L2Pstars.setBounds(287, 191, 69, 31);
		LevelSelect.add(L2Pstars);
		
		StarViewer L3Pstars = new StarViewer();
		L3Pstars.setBounds(380, 191, 69, 31);
		LevelSelect.add(L3Pstars);
		
		StarViewer L4Pstars = new StarViewer();
		L4Pstars.setBounds(473, 191, 69, 31);
		LevelSelect.add(L4Pstars);
		
		StarViewer L5Pstars = new StarViewer();
		L5Pstars.setBounds(570, 191, 69, 31);
		LevelSelect.add(L5Pstars);
		
		StarViewer L1Lstars = new StarViewer();
		L1Lstars.setBounds(194, 324, 69, 31);
		LevelSelect.add(L1Lstars);
		
		StarViewer L2Lstars = new StarViewer();
		L2Lstars.setBounds(287, 324, 69, 31);
		LevelSelect.add(L2Lstars);
		
		StarViewer L3Lstars = new StarViewer();
		L3Lstars.setBounds(380, 324, 69, 31);
		LevelSelect.add(L3Lstars);
		
		StarViewer L4Lstars = new StarViewer();
		L4Lstars.setBounds(473, 324, 69, 31);
		LevelSelect.add(L4Lstars);
		
		StarViewer L5Lstars = new StarViewer();
		L5Lstars.setBounds(564, 324, 69, 31);
		LevelSelect.add(L5Lstars);
		
		StarViewer L1Rstars = new StarViewer();
		L1Rstars.setBounds(194, 466, 69, 31);
		LevelSelect.add(L1Rstars);
		
		StarViewer L2Rstars = new StarViewer();
		L2Rstars.setBounds(287, 466, 69, 31);
		LevelSelect.add(L2Rstars);
		
		StarViewer L3Rstars = new StarViewer();
		L3Rstars.setBounds(380, 466, 69, 31);
		LevelSelect.add(L3Rstars);
		
		StarViewer L4Rstars = new StarViewer();
		L4Rstars.setBounds(473, 466, 69, 31);
		LevelSelect.add(L4Rstars);
		
		StarViewer L5Rstars = new StarViewer();
		L5Rstars.setBounds(570, 466, 69, 31);
		LevelSelect.add(L5Rstars);
		
		JPanel AboutPage = new JPanel();
		contentPane.add(AboutPage, "name_1460040278225538000");
		AboutPage.setLayout(null);
		
		JLabel AboutLbl = new JLabel("About");
		AboutLbl.setBounds(269, 6, 131, 90);
		AboutLbl.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		AboutPage.add(AboutLbl);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		btnNewButton.setBounds(20, 552, 125, 72);
		AboutPage.add(btnNewButton);
		btnNext.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		btnNext.setBounds(517, 557, 125, 72);
		AboutPage.add(btnNext);
		
		JTextArea aboutTextArea = new JTextArea();
		aboutTextArea.setWrapStyleWord(true);
		aboutTextArea.setLineWrap(true);
		aboutTextArea.setText("Welcome to Kabasuji Level player! We will figure out what we want to put here and then get back to you about how to actually play the game! ");
		aboutTextArea.setBounds(39, 96, 589, 439);
		AboutPage.add(aboutTextArea);
		
		JPanel LevelView = new JPanel();
		contentPane.add(LevelView, "name_1460040350501362000");
		LevelView.setLayout(null);
		
		JLabel lblLevelTypename = new JLabel("Level Type/Name");
		lblLevelTypename.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		lblLevelTypename.setBounds(165, 6, 340, 56);
		LevelView.add(lblLevelTypename);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 64, 402, 134);
		LevelView.add(scrollPane);
		btnFlip.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnFlip.setBounds(553, 76, 95, 56);
		LevelView.add(btnFlip);
		
		JButton button = new JButton("<");
		button.setBounds(553, 144, 44, 42);
		LevelView.add(button);
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(603, 144, 44, 42);
		LevelView.add(button_1);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnQuit.setBounds(17, 294, 95, 56);
		LevelView.add(btnQuit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnReset.setBounds(17, 377, 95, 56);
		LevelView.add(btnReset);
		
		JButton btnFinishLevel = new JButton("Finish Level");
		btnFinishLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFinishLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnFinishLevel.setBounds(17, 477, 95, 96);
		LevelView.add(btnFinishLevel);
		
		JPanel panel = new JPanel();
		panel.setBounds(134, 225, 402, 346);
		LevelView.add(panel);
		
		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblLevel.setBounds(26, 6, 86, 56);
		LevelView.add(lblLevel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(36, 56, 55, 56);
		LevelView.add(panel_1);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblScore.setBounds(562, 336, 86, 42);
		LevelView.add(lblScore);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(548, 377, 116, 56);
		LevelView.add(panel_2);
		
		JLabel lblBest = new JLabel("Best:");
		lblBest.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblBest.setBounds(31, 144, 82, 31);
		LevelView.add(lblBest);
		
		JLabel lblCountdown = new JLabel("Countdown:");
		lblCountdown.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCountdown.setBounds(548, 213, 116, 42);
		LevelView.add(lblCountdown);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(548, 248, 116, 76);
		LevelView.add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 187, 116, 56);
		LevelView.add(panel_5);
		
		JPanel EndLevel = new JPanel();
		contentPane.add(EndLevel, "name_1460071702538425000");
		EndLevel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Level #");
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		lblNewLabel.setBounds(258, 23, 153, 81);
		EndLevel.add(lblNewLabel);
		
		JPanel BigStarView = new JPanel();
		BigStarView.setBounds(85, 163, 499, 196);
		EndLevel.add(BigStarView);
		btnReplay.setBounds(27, 450, 129, 81);
		EndLevel.add(btnReplay);
		
		JButton btnLevelselect = new JButton("Level Select");
		btnLevelselect.setBounds(270, 450, 129, 81);
		EndLevel.add(btnLevelselect);
		
		JButton btnNextLevel = new JButton("Next Level");
		btnNextLevel.setBounds(518, 450, 129, 81);
		EndLevel.add(btnNextLevel);
	}
}
