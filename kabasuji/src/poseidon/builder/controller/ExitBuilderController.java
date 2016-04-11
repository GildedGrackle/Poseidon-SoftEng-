package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;

public class ExitBuilderController implements ActionListener
{
	LevelBuilderView view;
//	LevelPlayerModel model;

	/**
	 *  Constructor
	 * @param game
	 */
	public ExitBuilderController(/* LevelPlayerModel model, */LevelBuilderView game)
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
		view.getBuilder().dispose();
		System.exit(0);
	}
}
