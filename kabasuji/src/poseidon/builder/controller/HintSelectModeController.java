package poseidon.builder.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

import poseidon.builder.view.BuilderView;

/**
 *  Switches between hint creation mode and normal mode.
 * @author Narfnof
 *
 */
public class HintSelectModeController implements ItemListener
{
	/** The representation of the level currently under construction. */
	BuilderView view;

	public HintSelectModeController(BuilderView view)
	{
		this.view = view;
	}


	/**
	 *  Switches into or out of hint creation mode, depending.
	 *  
	 *  @param ie  the initiating event
	 */
	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		// If was selected
		if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			view.getBoard().setHintSelectionMode(true);
			view.getBullpen().getModel().setPieceSelected(null);
			// Bullpen controller will also disallow any selections of pieces
		}
		else  // Was deselected
		{
			view.getBoard().setHintSelectionMode(false);
		}

		view.modelUpdated();
	}

}
