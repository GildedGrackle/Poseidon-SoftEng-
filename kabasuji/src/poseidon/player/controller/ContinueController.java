package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;

/**
 *  Finds lowest-numbered of the most recently unlocked Levels and starts it.
 *  
 *  @author Alex Titus
 */
public class ContinueController implements ActionListener
{
	/** The top-level entity object. */
	LevelPlayerModel model;
	/** The top-level boundary object. */
	LevelPlayerView game;
	

	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param view  the top-level boundary object
	 */
	public ContinueController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Need to add check to see if the game has already started or is new
		beginGame();
	}
	
	
	/**
	 *  Finds lowest-numbered of the most recently unlocked Levels and starts it.
	 *  
	 *  @return indicator of whether operation was successful
	 */
	public Boolean beginGame()
	{
		// Find the lowest number of the modes
		int lowestLevel = 0;
		int gameMode = 0;
		for(int i = 1; i < 3; i++)  // Compare the progress on the Puzzle levels to other modes
		{
			// If the current lowest level unlocked is further along than
			// the level unlocked at game mode i
			if(lowestLevel > model.getCurrentLevel()[i])
			{
				// Then set new lowest mode
				lowestLevel = model.getCurrentLevel()[i];
				gameMode = i;
			}
		}
		
		// Now set currently playing in LevelPlayerModel to the level determined above
		model.setPlayingLevel(model.getLevels().get(gameMode).get(lowestLevel));
		LevelView newScreen = new LevelView(model, game);
		
		// Set new screen
		// First set level select screen as current screen for main GUI
		game.setCurrentView(new LevelSelectView(model, game));
		// Then set new level as that screen's currently playing
		game.getCurrentView().setCurrentlyPlaying(newScreen);
		game.getfrmKabasuji().setContentPane(newScreen);
			
		// Display the new screen
		game.getfrmKabasuji().setVisible(true);
		model.getPlayingLevel().getLevel().initialize(newScreen);
		newScreen.modelUpdated();  // To show initial time in Lightning levels
			
		return true;
	}

}
