package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.AboutPlayerView;
import poseidon.player.view.LevelPlayerView;

public class AboutPlayerController implements ActionListener
{
	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelPlayerView game;  // The top-level GUI object
	
	
	/**
	 *  Constructor
	 * @param view
	 */
	public AboutPlayerController(LevelPlayerModel model, LevelPlayerView view)
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
		toAbout();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen
	 */
	public Boolean toAbout()
	{
		AboutPlayerView newScreen = new AboutPlayerView(model, game);  // The new screen to display
		
		// Set new screen
		game.setCurrentView(newScreen);
		game.getFrame().setContentPane(newScreen);
		
		// Display the new screen
		game.getFrame().setVisible(true);
		
		return true;
	}

}
