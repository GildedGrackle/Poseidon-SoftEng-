package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.XMLHandler;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelView;

/**
 *  Handles resetting a Level back to its initial state.
 *  
 *  @author Alex Titus
 *  @author Jacob Wennersten
 */
public class ResetController implements ActionListener
{
	/** The top-level model. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the model of the level to reset
	 *  @param view  the representation of the level
	 */
	public ResetController(LevelPlayerModel model, LevelPlayerView game)
	{
		this.model = model;
		this.game = game;
	}

	
	/**
	 *  
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		resetLevel();
	}

	/**
	 * Resets the Level back to its initial state.
	 *  
	 * Current score earned on the Level will be lost.
	 * 
	 * @return true if successful
	 */
	boolean resetLevel() {
		// Reload the current level
		LevelContainer currentLevelContainer = model.getPlayingLevel();
		LevelModel currentLevel = currentLevelContainer.getLevel();
		LevelModel resetLevel = XMLHandler.loadXML(currentLevel.getLevelName() + ".xml",
				currentLevel.getIsCustom(),
				currentLevel.getIsCustom());
		currentLevelContainer.setLevel(resetLevel);
		
		// Now set currently playing in LevelPlayerModel to the level determined above
		model.setPlayingLevel(currentLevelContainer);
		LevelView newScreen = new LevelView(model, game);

		// Set new screen
		// Then set new level as that screen's currently playing
		game.getCurrentView().setCurrentlyPlaying(newScreen);
		game.getFrame().setContentPane(newScreen);

		// Display the new screen
		game.getFrame().setVisible(true);
		model.getPlayingLevel().getLevel().initialize(newScreen);
		newScreen.modelUpdated();  // To show initial time in Lightning levels
		
		return true;
	}
}
