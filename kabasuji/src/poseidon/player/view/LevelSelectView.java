package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.BackPlayerController;
import poseidon.player.controller.PlaySelectedController;
import poseidon.player.controller.SelectLevelController;

public class LevelSelectView extends JPanel implements IGameScreen
{
	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelPlayerView game;  // The top-level GUI object
	LevelView currentlyPlaying;  // The GUI of the Level being played
	StarView levels[][];  // The selectable level icons
	LevelContainer selectedLevel;  // The level selected to play by the player
	JButton btnPlay;

	
	/**
	 *  Create the panel. Does some work to figure out which levels are
	 *  available for play
	 */
	public LevelSelectView(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		game = view;
		setLayout(null);
		
		// TODO get levels, which are unlocked and such
		
		initialize();
		
		
	}

	
	/**
	 *  Creates and fills the screen with selectable levels
	 *  and buttons
	 */
	private void initialize()
	{
		JLabel title = new JLabel("Level Select");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(217, 15, 245, 50);
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
		backButton.addActionListener(new BackPlayerController(model, game));
		add(backButton);
		
		btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPlay.setBounds(470, 550, 180, 50);
		btnPlay.addActionListener(new PlaySelectedController(model, this, game));
		add(btnPlay);
		

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				// If the level is unlocked
				if(j <= model.getCurrentLevel()[i])
				{
					// Then create a selectable icon
					StarView levelButton = new StarView(model.getLevels()[i][j]);
					levelButton.setBounds(175 + 95 * j, 170 + 90 * i, 60, 80);
					levelButton.addActionListener(new SelectLevelController(this));
					add(levelButton);
				}
				else  // Level is locked
				{
					// Then create a nonselectable "level locked" icon
					StarView lockButton = new StarView();
					lockButton.setBounds(175 + 95 * j, 170 + 90 * i, 60, 80);
					lockButton.setEnabled(false);
					add(lockButton);
				}
			}
		}
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
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public LevelContainer getSelectedLevel()
	{
		return selectedLevel;
	}
	public void setSelectedLevel(LevelContainer selected)
	{
		selectedLevel = selected;
	}
	
	public JButton getPlay(){
		return btnPlay;
	}
}
