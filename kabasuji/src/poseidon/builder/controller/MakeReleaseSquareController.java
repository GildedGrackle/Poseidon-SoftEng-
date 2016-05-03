package poseidon.builder.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Switches between release number placement mode and normal mode.
 *  
 *  @author Morgan Hopeman
 *  @author Alex Titus
 */
public class MakeReleaseSquareController implements ItemListener{
	
	/** The representation of the level currently under construction. */
	BuilderView view;

	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the representation of the level currently under construction
	 */
	public MakeReleaseSquareController(BuilderView view) {
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
	public void itemStateChanged(ItemEvent ie)	{
		
		// If was just selected
		if(ie.getStateChange() == ItemEvent.SELECTED)
		{
			view.getBoard().setReleaseNumberMode(true);
			view.getBullpen().getModel().setPieceSelected(null);
			// Bullpen controller will also disallow any selections of pieces
		}
		else  // Was just deselected
		{
			view.getBoard().setReleaseNumberMode(false);
		}

		view.modelUpdated();
	}

}
