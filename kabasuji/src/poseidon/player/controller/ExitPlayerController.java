package poseidon.player.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.player.view.LevelPlayerView;

public class ExitPlayerController implements ActionListener
{
	LevelPlayerView view;
//	LevelPlayerModel model;

	/**
	 *  Constructor
	 * @param game
	 */
	public ExitPlayerController(/* LevelPlayerModel model, */LevelPlayerView game)
	{
//		model = model;
		view = game;
	}
	
	
	/**
	 *  Called when user clicks Exit button, closes game
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		view.getfrmKabasuji().dispose();
		System.exit(0);
	}
}
