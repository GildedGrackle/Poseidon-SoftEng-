package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.entities.IMove;
import poseidon.entities.RedoManager;
import poseidon.entities.UndoManager;

/**
 *  Handles requests to undo moves.
 *  
 *  @author Alex Titus
 */
public class UndoController implements ActionListener
{
	/** The GUI of the level being built. */
	BuilderView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI of the level being built
	 */
	public UndoController(BuilderView view)
	{
		this.view = view;
	}

	
	/**
	 *  Undo most recently made move and push that move onto the RedoManager.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Check that there is a move to undo, just in case
		if( ! (UndoManager.instance().isEmpty()) )
		{
			IMove move = UndoManager.instance().getLastMove();
			// Undo move
			if(move.undoMove())
			{
				// If successful, push onto redo stack
				RedoManager.instance().recordMove(move);
			}
			
			// Else problem, just going to ignore it
		}
		
		// Update display
		view.modelUpdated();
	}

}
