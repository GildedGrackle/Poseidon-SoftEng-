package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.player.view.EndLevelView;
import poseidon.player.view.LevelPlayerView;

/**
 *  Handles both the "Finish Level" button press and the 3-star completion scenario.
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
		// Set record score
		LevelContainer currentContainer = game.getModel().getPlayingLevel();
		LevelModel currentLevel = currentContainer.getLevel();
		if(currentContainer.getScore() < currentLevel.getScore())  // If have a new high score
		{
			// Then record it
			currentContainer.setScore(currentLevel.getScore());
		}
		EndLevelView newScreen = new EndLevelView(game.getModel(), game);
		game.getfrmKabasuji().setContentPane(newScreen);
		game.setCurrentView(newScreen);
		
		// Display new screen
		game.getfrmKabasuji().setVisible(true);
	}

}
