package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.player.view.LevelPlayerView;

/**
 *  Closes the application.
 *  
 *  @author Alex Titus
 *
 */
public class ExitPlayerController implements ActionListener
{
	/** The top-level GUI object. */
	LevelPlayerView view;

	/**
	 *  Constructor.
	 *  
	 *  @param game  the top-level GUI object to close
	 */
	public ExitPlayerController(LevelPlayerView game)
	{
		view = game;
	}
	
	
	/**
	 *  Called when user clicks Exit button, closes game.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		view.getFrame().dispose();
		System.exit(0);
	}
}
