package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.BackPlayerController;
import poseidon.player.controller.PlaySelectedController;
import poseidon.player.controller.SelectLevelController;

/**
 *  Creates the Level Select screen.
 *  
 *  Allows player to select all Levels that have been added to the game.
 *  TODO figure out if player-made levels are always unlocked
 *  
 *  @author Alex Titus
 */
public class LevelSelectView extends JPanel implements IGameScreen
{
	/** The top-level entity object, representing the game. */
	LevelPlayerModel model;
	/** The base GUI object. */
	LevelPlayerView game;
	/** The GUI of the Level being played. */
	LevelView currentlyPlaying;
	/** The selectable level icons. */
	StarView levels[][];
	/** The level selected to play by the player. */
	LevelContainer selectedLevel;
	/** The "Play Level" button. */
	JButton btnPlay;
	/** The panel holding the Puzzle Levels. */
	SelectableLevelsView puzzlePanel;
	/** The panel holding the Lightning Levels. */
	SelectableLevelsView lightningPanel;
	/** The panel holding the Release Levels. */
	SelectableLevelsView releasePanel;
	/** The button to return to the main menu (LevelPlayerView). */
	JButton backButton;

	
	/**
	 *  Constructor.
	 *  
	 *  Does some work to figure out which levels are
	 *  available for play
	 *  
	 *  @param model  the top-level model, with references to the Levels
	 *  @param view  the base GUI object
	 */
	public LevelSelectView(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		game = view;
		setLayout(null);
		
		initialize();
	}

	
	/**
	 *  Creates and fills the screen with selectable levels
	 *  and buttons 
	 *  TODO use panels and scrollpanes here
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
		puzzleLabel.setBounds(10, 170, 90, 30);
		add(puzzleLabel);
		
		JLabel lightningLabel = new JLabel("Lightning");
		lightningLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lightningLabel.setBounds(10, 280, 105, 30);
		add(lightningLabel);
		
		JLabel releaseLabel = new JLabel("Release");
		releaseLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		releaseLabel.setBounds(10, 390, 90, 30);
		add(releaseLabel);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		backButton.setBounds(10, 550, 180, 50);
		backButton.addActionListener(new BackPlayerController(model, game));
		add(backButton);
		
		btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPlay.setBounds(470, 550, 180, 50);
		btnPlay.setEnabled(false);  // Can't select initially
		btnPlay.addActionListener(new PlaySelectedController(model, this, game));
		add(btnPlay);
		
		// Create selectable level buttons
		int puzzle = 0;
		int lightning = 1;
		int release = 2;
		puzzlePanel = new SelectableLevelsView(model, this, puzzle);
		lightningPanel = new SelectableLevelsView(model, this, lightning);
		releasePanel = new SelectableLevelsView(model, this, release);
		
		JScrollPane puzzleContainer = new JScrollPane(puzzlePanel);
		puzzleContainer.setBounds(140, 135, 464, 102);
		puzzleContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(puzzleContainer);
		JScrollPane lightningContainer = new JScrollPane(lightningPanel);
		lightningContainer.setBounds(140, 245, 464, 102);
		lightningContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(lightningContainer);
		JScrollPane releaseContainer = new JScrollPane(releasePanel);
		releaseContainer.setBounds(140, 355, 464, 102);
		releaseContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		add(releaseContainer);
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
