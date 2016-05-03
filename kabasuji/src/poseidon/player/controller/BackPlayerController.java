package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;

/**
 *  Returns to (creates and displays) the main menu.
 *  
 *  @author Alex Titus
 */
public class BackPlayerController implements ActionListener
{
	/** The top-level entity object, representing the game. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param view  the top-level GUI object
	 */
	public BackPlayerController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		goBack();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen.
	 *  
	 *  @return  Indication of whether the operation completed successfully.
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
