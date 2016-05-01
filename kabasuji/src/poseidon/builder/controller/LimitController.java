package poseidon.builder.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import poseidon.entities.LevelModel;

/**
 *  Handles a change level allocated limit request.
 *  
 *  @author Alex Titus
 */
public class LimitController implements PropertyChangeListener
{
	/** The state of the level being modified. */
	LevelModel model;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the level state to modify
	 */
	public LimitController(LevelModel model)
	{
		this.model = model;
	}

	
	/**
	 *  Changes the level's limit to the one indicated.
	 *  
	 *  Implements PropertyChangeListener.propertyChange().
	 *  
	 *  @param evt  the initiating event
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
		int newLimit = (int) evt.getNewValue();

		model.setMaxLimit(newLimit);
	}

}
