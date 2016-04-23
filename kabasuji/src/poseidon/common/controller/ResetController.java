package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.view.LevelView;

/**
 *  Handles resetting a Level back to its initial state.
 *  
 *  @author Alex Titus
 */
public class ResetController implements ActionListener
{
	/** The model of the Level. */
	LevelPlayerModel model;
	/** The GUI representation of the Level. */
	LevelView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the model of the level to reset
	 *  @param view  the representation of the level
	 */
	public ResetController(LevelPlayerModel model, LevelView view)
	{
		this.model = model;
		this.view = view;
	}

	
	/**
	 *  Resets the Level back to its initial state.
	 *  
	 *  Current score earned on the Level will be lost.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//TODO write reset controller
	}

}
