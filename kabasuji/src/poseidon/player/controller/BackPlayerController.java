package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;

public class BackPlayerController implements ActionListener
{
	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelPlayerView game;  // The top-level GUI object
	
	
	/**
	 *  Constructor
	 * @param model
	 * @param view
	 */
	public BackPlayerController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		goBack();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen
	 */
	public Boolean goBack()
	{	
		// Reset LPV to original view
		game.setCurrentView(null);
		game.initialize();
		
		// Display the new screen
		game.getFrame().setVisible(true);
		
		return true;
	}

}
