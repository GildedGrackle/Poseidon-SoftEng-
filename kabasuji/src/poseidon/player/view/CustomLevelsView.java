package poseidon.player.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import poseidon.player.controller.BackPlayerController;

public class CustomLevelsView extends JPanel implements IGameScreen
{
	LevelPlayerView game;  // The top-level GUI object
	LevelView currentlyPlaying;  // The level currently being played on
	ArrayList<StarViewer> customLevels;  // Custom levels made by user

	
	/**
	 * Create the panel. Must figure out how many of each type of level are available
	 */
	public CustomLevelsView(LevelPlayerView view)
	{
		game = view;
		currentlyPlaying = null;  // Initially none

		setLayout(null);
		
		JLabel title = new JLabel("Custom Level Select");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(152, 15, 375, 50);
		add(title);
		
		JLabel puzzleLabel = new JLabel("Puzzle");
		puzzleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		puzzleLabel.setBounds(10, 180, 90, 30);
		add(puzzleLabel);
		
		JLabel lblLightning = new JLabel("Lightning");
		lblLightning.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLightning.setBounds(10, 270, 105, 30);
		add(lblLightning);
		
		JLabel lblRelease = new JLabel("Release");
		lblRelease.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblRelease.setBounds(10, 360, 90, 30);
		add(lblRelease);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		backButton.setBounds(10, 550, 180, 50);
		backButton.addActionListener(new BackPlayerController(game));
		add(backButton);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPlay.setBounds(470, 550, 180, 50);
		add(btnPlay);
		
		JButton pzl1 = new JButton("1");
		pzl1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pzl1.setBounds(175, 170, 50, 50);
		add(pzl1);
		
		JButton pzl2 = new JButton("2");
		pzl2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pzl2.setBounds(260, 170, 50, 50);
		add(pzl2);
		
		JButton pzl3 = new JButton("3");
		pzl3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pzl3.setBounds(345, 170, 50, 50);
		add(pzl3);
		
		JButton pzl4 = new JButton("4");
		pzl4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pzl4.setBounds(430, 170, 50, 50);
		add(pzl4);
		
		JButton pzl5 = new JButton("5");
		pzl5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pzl5.setBounds(515, 170, 50, 50);
		add(pzl5);
		
		JButton lgt1 = new JButton("1");
		lgt1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lgt1.setBounds(175, 260, 50, 50);
		add(lgt1);
		
		JButton lgt2 = new JButton("2");
		lgt2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lgt2.setBounds(260, 260, 50, 50);
		add(lgt2);
		
		JButton lgt3 = new JButton("3");
		lgt3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lgt3.setBounds(345, 260, 50, 50);
		add(lgt3);
		
		JButton lgt4 = new JButton("4");
		lgt4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lgt4.setBounds(430, 260, 50, 50);
		add(lgt4);
		
		JButton lgt5 = new JButton("5");
		lgt5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lgt5.setBounds(515, 260, 50, 50);
		add(lgt5);
		
		JButton rls1 = new JButton("1");
		rls1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rls1.setBounds(175, 350, 50, 50);
		add(rls1);
		
		JButton rls2 = new JButton("2");
		rls2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rls2.setBounds(260, 350, 50, 50);
		add(rls2);
		
		JButton rls3 = new JButton("3");
		rls3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rls3.setBounds(345, 350, 50, 50);
		add(rls3);
		
		JButton rls4 = new JButton("4");
		rls4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rls4.setBounds(430, 350, 50, 50);
		add(rls4);
		
		JButton rls5 = new JButton("5");
		rls5.setFont(new Font("Tahoma", Font.PLAIN, 25));
		rls5.setBounds(515, 350, 50, 50);
		add(rls5);
	}

	
	/**
	 *  Returns the current level boundary object
	 */
	@Override
	public LevelView getCurrentlyPlaying()
	{
		return currentlyPlaying;
	}
	
	
	/**
	 *  Sets the current level boundary object
	 */
	@Override
	public Boolean setCurrentlyPlaying(LevelView newGame)
	{
		this.currentlyPlaying = newGame;
		return true;
	}

}
