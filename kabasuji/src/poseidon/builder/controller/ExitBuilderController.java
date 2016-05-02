package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelPlayerModel;

public class ExitBuilderController implements ActionListener
{
	LevelBuilderView view;
	LevelBuilderModel model;

	/**
	 *  Constructor
	 * @param game
	 */
	public ExitBuilderController( LevelBuilderModel model, LevelBuilderView game)
	{
		this.model = model;
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
