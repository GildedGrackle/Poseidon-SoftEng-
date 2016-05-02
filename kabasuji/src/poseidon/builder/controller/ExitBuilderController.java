package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Closes the application and window.
 *  
 *  @author Alex Titus
 */
public class ExitBuilderController implements ActionListener
{
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView view;

	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object, representing the application's state
	 *  @param game  the top-level GUI object
	 */
	public ExitBuilderController( LevelBuilderModel model, LevelBuilderView game)
	{
		this.model = model;
		view = game;
	}
	
	
	/**
	 *  Called when user clicks Exit button, closes game.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		view.getFrame().dispose();
		System.exit(0);
	}
}
