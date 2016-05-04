package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LightningLevel;
import poseidon.entities.XMLHandler;
import poseidon.player.view.EndLevelView;
import poseidon.player.view.LevelPlayerView;

/**
 *  Handles the "Finish Level" button press scenario.
 *  Displays the end level screen, with options to replay the game, return to the 
 *  level select screen, or advance to the next level.
 *  
 *  @author Alex Titus
 */
public class EndLevelController implements ActionListener
{
	/** The top-level GUI object. */
	LevelPlayerView game;

	
	/**
	 *  Constructor.
	 *  
	 *  @param game  the top-level GUI object, for changing the display
	 */
	public EndLevelController(LevelPlayerView game)
	{
		this.game = game;
	}


	/**
	 *  Create and display the end level screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Set score
		LevelContainer currentContainer = game.getModel().getPlayingLevel();
		LevelModel currentLevel = currentContainer.getLevel();
		// If have a new high score
		if(currentContainer.getScore() < currentLevel.getScore())
		{
			// Then record it
			currentContainer.setScore(currentLevel.getScore());
			XMLHandler.saveScore(currentContainer);
		}
		// If new level unlocked
		if(currentContainer.getScore() > 0 &&
				currentContainer.getLevelNumber() == game.getModel().getCurrentLevel()[currentLevel.getGameMode() - 1])
		{
			game.getModel().getCurrentLevel()[currentLevel.getGameMode() - 1]++;
		}
		
		// If the level was a Lightning level
		if(currentLevel.getGameMode() == LevelModel.LIGHTNING)
		{
			// Then stop the timer
			LightningLevel level = (LightningLevel) currentLevel;
			level.stopTimer();
		}
		
		// Create and display new screen
		EndLevelView newScreen = new EndLevelView(game.getModel(), game);
		game.getFrame().setContentPane(newScreen);
		game.setCurrentView(newScreen);
		game.getFrame().setVisible(true);
	}

}
