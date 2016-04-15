package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.player.view.CustomLevelsView;
import poseidon.player.view.LevelPlayerView;


public class CustomLevelController implements ActionListener
{
//	LevelPlayerModel model;  // The top-level entity object, representing the game
	LevelPlayerView game;  // The top-level GUI object


	/**
	 *  Constructor
	 */
	public CustomLevelController(/* LevelPlayerModel model, */LevelPlayerView view)
	{
//		this.model = model;
		this.game = view;
	}
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  Custom Level Select screen
	 */
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		toCustomLevelSelect();
	}

	
	/**
	 *  Switches the panel currently displayed in LevelPlayerView to the
	 *  Custom Level Select screen
	 */
	public Boolean toCustomLevelSelect()
	{
		CustomLevelsView newScreen = new CustomLevelsView(game);  // The new screen to display
		
		// Set new screen
		game.setCurrentView(newScreen);
		game.getfrmKabasuji().setContentPane(newScreen);
		
		// Display the new screen
		game.getfrmKabasuji().setVisible(true);
		
		return true;
	}
}
