package poseidon.builder.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import poseidon.builder.view.BuilderView;

/**
 *  Switches between hint creation mode and normal mode.
 *  
 *  @author Alex Titus
 */
public class HintSelectModeController implements ItemListener
{
	/** The representation of the level currently under construction. */
	BuilderView view;

	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the representation of the level currently under construction
	 */
	public HintSelectModeController(BuilderView view)
	{
		this.view = view;
	}


	/**
	 *  Switches into or out of hint creation mode, depending.
	 *  
	 *  Deselects any selected piece in and locks the bullpen as a
	 *  result of entering the mode.
	 *  
	 *  @param ie  the initiating event
	 */
	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		// If was just selected
		if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			view.getBoard().setHintSelectionMode(true);
			view.getBullpen().getModel().setPieceSelected(null);
			// Bullpen controller will also disallow any selections of pieces
		}
		else  // Was just deselected
		{
			view.getBoard().setHintSelectionMode(false);
		}
		
		view.modelUpdated();
	}

}
