package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LightningLevel;
import poseidon.player.view.LevelView;

/**
 *  Handles Timer event to decrement the time remaining in a Lightning level.
 *  
 *  @author Alex Titus
 */
public class TimeController implements ActionListener
{
	/** The level to modify. */
	LightningLevel game;
	/** The GUI representation of the level. */
	LevelView view;

	
	/**
	 *  Constructor.
	 *  
	 *  @param game  the level to modify
	 *  @param view  the GUI representation of the level
	 */
	public TimeController(LightningLevel game, LevelView view)
	{
		this.game = game;
		this.view = view;
	}
	
	
	/**
	 *  Every second, decrement time remaining by one.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// If there is no time remaining
		if(game.getLimit() <= 0)
		{
			// Then stop the timer
			game.stopTimer();
		}
		else
		{
			// Decrement the remaining time
			game.decrementTime();
		}
		
		view.modelUpdated();
	}

}
