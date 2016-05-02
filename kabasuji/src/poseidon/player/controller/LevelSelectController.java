package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;

/**
 *  Creates and displays the level select screen.
 *  
 *  @author Alex Titus
 */
public class LevelSelectController implements ActionListener
{
	/** The top-level entity object, representing the game. */
	LevelPlayerModel model;
	/** The top-level GUI object. */
	LevelPlayerView game;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object, representing the game
	 *  @param view  the top-level GUI object
	 */
	public LevelSelectController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}
	
	
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  level select screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		toLevelSelect();
	}
	
	
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  level select screen.
	 *  
	 *  @return  Indicator of whether the operation completed successfully.
	 */
	public Boolean toLevelSelect()
	{
		LevelSelectView newScreen = new LevelSelectView(model, game);  // The new screen to display
		
		// Set new screen
		game.setCurrentView(newScreen);
		game.getFrame().setContentPane(newScreen);
		
		// Display the new screen
		game.getFrame().setVisible(true);
		
		return true;
	}

}
