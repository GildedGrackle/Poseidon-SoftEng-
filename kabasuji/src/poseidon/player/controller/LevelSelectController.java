package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;

public class LevelSelectController implements ActionListener
{
	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelPlayerView game;  // The top-level GUI object

	
	/**
	 *  Constructor
	 * @param view
	 */
	public LevelSelectController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}
	
	
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  Level Select screen
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		toLevelSelect();
	}
	
	
	/**
	 * Switches the panel currently displayed in LevelPlayerView to the
	 *  Level Select screen 
	 */
	public Boolean toLevelSelect()
	{
		LevelSelectView newScreen = new LevelSelectView(model, game);  // The new screen to display
		
		// Set new screen
		game.setCurrentView(newScreen);
		game.getfrmKabasuji().setContentPane(newScreen);
		
		// Display the new screen
		game.getfrmKabasuji().setVisible(true);
		
		return true;
	}

}
