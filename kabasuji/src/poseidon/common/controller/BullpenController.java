package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BullpenView;
import poseidon.entities.Bullpen;
import poseidon.entities.PieceContainer;

/**
 *  Handles click events on the Bullpen.
 *  
 *  Clicks to select and deselect a particular piece displayed in the Bullpen
 *  are handled here.
 *  
 *  @author Alex Titus
 */
public class BullpenController extends MouseAdapter
{
	/** The Bullpen model. */
	Bullpen model;
	/** The GUI object representing the model. */
	BullpenView view;
	
	
	/**
	 *  Constructor.
	 * @param game  the top level model entity
	 * @param view  the object displaying the bullpen
	 */
	public BullpenController(Bullpen model, BullpenView view)
	{
		this.model = model;
		this.view = view;
	}
	

	/**
	 *  Selects or deselects the Piece that was clicked on.
	 *  
	 *  @param me  the click starting this event
	 */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		// Calculate which piece was selected using
		// the coordinates of the MouseEvent and the
		// known width of rendered Pieces in the Bullpen
		int index = me.getX() / BullpenView.PIECE_SIZE;
		
		// Need to check that index is within the number of Pieces in Bullpen
		if(index >= model.getSize())  // >= because 0-based indexing
		{
			// Then index invalid, but deselect any selected Piece
			model.getPieceSelected().setIsSelected(false);
			model.setPieceSelected(null);
			view.setSelectedPiece(null);
		}
		else  // Safe to access using index
		{
			// If the Piece was already selected
			if(model.getPiece(index) == model.getPieceSelected())
			{
				// Then deselect that Piece
				model.setPieceSelected(null);
				view.setSelectedPiece(null);
			}
			else  // Piece not already selected
			{
				// Then select that Piece
				model.setPieceSelected(model.getPiece(index));
				view.setSelectedPiece(view.getPieceView(index));
			}
		}
		
		// Change view to reflect change
		view.update();
	}
}
