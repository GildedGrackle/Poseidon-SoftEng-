package poseidon.player.controller;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.EndLevelView;
import poseidon.player.view.LevelPlayerView;

/**
 *  Ends a level when the limit runs out.
 *  
 *  Also changes the limit display to red based on remaining limit (moves or time).
 *  
 *  @author Alex Titus
 *
 */
public class LimitEndController implements PropertyChangeListener
{
	/** The top-level entity object. */
	LevelPlayerModel topModel;
	/** The top-level GUI object, for switching screens. */
	LevelPlayerView application;

	
	/**
	 *  Constructor.
	 *  
	 *  @param topModel  the top-level entity object, with the LevelContainer object currently being played
	 *  @param application  the top-level GUI object
	 */
	public LimitEndController(LevelPlayerModel topModel, LevelPlayerView application)
	{
		this.topModel = topModel;
		this.application = application;
	}


	/**
	 *  Depending on the value contained after the change, do nothing, change display, or end the game.
	 *  
	 *  @param pce  the initiating event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent pce)
	{
		String rawNewLimit = (String) pce.getNewValue();
		
		int newLimit = getLimit(rawNewLimit);
		
		// If below 5 moves/seconds remaining (update visible on 5)
		if(newLimit < 6)
		{
			// Then change the display color to red
			JLabel limitLabel = (JLabel) pce.getSource();
			limitLabel.setForeground(Color.red);
		}
		
		// If the game has ended (out of moves)
		if(newLimit == 0)
		{
			// Then save score (if new high score) and go to the end level screen
			// Set record score
			LevelContainer currentContainer = application.getModel().getPlayingLevel();
			LevelModel currentLevel = currentContainer.getLevel();
			if(currentContainer.getScore() < currentLevel.getScore())  // If have a new high score
			{
				// Then record it
				currentContainer.setScore(currentLevel.getScore());
			}
			// If new level unlocked
			if(currentContainer.getScore() > 0 &&
					currentContainer.getLevelNumber() == application.getModel().getCurrentLevel()[currentLevel.getGameMode() - 1])
			{
				application.getModel().getCurrentLevel()[currentLevel.getGameMode() - 1]++;
			}

			// Create and display new screen
			EndLevelView newScreen = new EndLevelView(application.getModel(), application);
			application.getFrame().setContentPane(newScreen);
			application.setCurrentView(newScreen);
			application.getFrame().setVisible(true);
		}
	}
	
	
	/**
	 *  Parses the limit string to get the actual limit.
	 *  
	 *  @param input  the string to be parsed
	 *  @return  The limit as an integer.
	 */
	int getLimit(String input)
	{
		// Isolate the actual number
		String[] splitString = input.split(">");  // Remove the front
		String[] numberString = splitString[3].split("<");  // Remove the back
		
		return Integer.parseInt(numberString[0]);
	}

}
