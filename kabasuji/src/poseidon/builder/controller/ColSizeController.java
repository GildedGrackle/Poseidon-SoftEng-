package poseidon.builder.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import poseidon.builder.view.BuilderView;
import poseidon.entities.ResizeBoardMove;
import poseidon.entities.UndoManager;

/**
 *  Handles a Board column size change request.
 *  
 *  @author Alex Titus
 */
public class ColSizeController implements PropertyChangeListener
{
	/** The GUI for the Level under construction. */
	BuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI for the Level under construction
	 */
	public ColSizeController(BuilderView view)
	{
		this.view = view;
	}

	
	/**
	 *  When the value of the Board column size changes, change Board column size to that.
	 *  
	 *  @param evt  the initiating event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		// Call testable method
		resizeBoard(evt);
	}


	/**
	 *  When the value of the Board row size changes, change Board row size to that.
	 *  
	 *  @param evt  the initiating event
	 *  @return  indicator whether operation was successful
	 */
	Boolean resizeBoard(PropertyChangeEvent evt)
	{
		int oldVal = (int) evt.getOldValue();
		int newVal = (int) evt.getNewValue();
		int rowVal = (int) view.getRowSizeInput().getValue();
		ResizeBoardMove move = new ResizeBoardMove(view.getBoard(), rowVal, oldVal, rowVal, newVal);
		if(move.doMove())  // If move succeeds
		{
			// Then record it and indicate success
			UndoManager.instance().recordMove(move);
			return true;
		}
		// Else indicate failure
		return false;
	}
}
