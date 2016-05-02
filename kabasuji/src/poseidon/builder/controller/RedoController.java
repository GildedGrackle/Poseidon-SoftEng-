package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.entities.IMove;
import poseidon.entities.RedoManager;
import poseidon.entities.UndoManager;

/**
 *  Handles requests to redo moves.
 *  
 *  @author Alex Titus
 */
public class RedoController implements ActionListener
{
	/** The GUI of the level being built. */
	BuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI of the level being built
	 */
	public RedoController(BuilderView view)
	{
		this.view = view;
	}

	
	/**
	 *  Redo most recently made move and push that move onto the UndoManager.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Check that there is a move to redo, just in case
		if( ! (RedoManager.instance().isEmpty()) )
		{
			IMove move = RedoManager.instance().getLastMove();
			// Redo move
			if(move.doMove())
			{
				// If successful, push onto redo stack
				UndoManager.instance().recordMove(move);
			}

			// Else problem, just going to ignore it
		}

		// Update display
		view.modelUpdated();
	}

}
