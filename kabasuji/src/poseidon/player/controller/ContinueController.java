package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
import poseidon.player.view.LevelView;

public class ContinueController implements ActionListener
{
	LevelPlayerModel model;
	LevelPlayerView game;
	
	
	public ContinueController(LevelPlayerModel model, LevelPlayerView view)
	{
		this.model = model;
		this.game = view;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Need to add check to see if the game has already started or is new
		beginGame();
	}
	
	
	public Boolean beginGame()
	{
		/** TODO Find most recently unlocked level
		 *  and create it instead of making a new one here */
		LevelView newScreen = new LevelView(model, game);
		
		// Set new screen
		// First set level select screen as current screen for main GUI
		game.setCurrentView(new LevelSelectView(model, game));
		// Then set new level as that screen's currently playing
		game.getCurrentView().setCurrentlyPlaying(newScreen);
		game.getfrmKabasuji().setContentPane(newScreen);
			
		// Display the new screen
		game.getfrmKabasuji().setVisible(true);
			
		return true;
	}

}
