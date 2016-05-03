package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;

/**
 *  Moves to the next level of the same gamemode.
 *  
 *  @author Alex Titus
 *
 */
public class NextLevelController implements ActionListener
{
	/** The top-level entity object. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param game  the top-level GUI object
	 */
	public NextLevelController(LevelPlayerModel model, LevelPlayerView game)
	{
		this.model = model;
		this.game = game;
	}


	/**
	 *  Move to the next playable level in this gamemode.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Determine gamemode and current level
		int gamemode = model.getPlayingLevel().getLevel().getGameMode();
		int currentLevel = model.getPlayingLevel().getLevelNumber();
		
		// Now set currently playing in LevelPlayerModel to the level determined above
		// Should be safe access because EndLevelView checked when making this button
		// enabled or not.
		model.setPlayingLevel(model.getLevels().get(gamemode -1).get(currentLevel + 1));
		LevelView newScreen = new LevelView(model, game);

		// Set new screen
		// First set level select screen as current screen for main GUI
		game.setCurrentView(new LevelSelectView(model, game));
		// Then set new level as that screen's currently playing
		game.getCurrentView().setCurrentlyPlaying(newScreen);
		game.getFrame().setContentPane(newScreen);

		// Display the new screen
		game.getFrame().setVisible(true);
		model.getPlayingLevel().getLevel().initialize(newScreen);
		newScreen.modelUpdated();  // To show initial time in Lightning levels

	}

}
