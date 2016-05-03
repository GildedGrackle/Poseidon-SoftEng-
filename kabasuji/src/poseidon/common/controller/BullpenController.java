package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.entities.Bullpen;
import poseidon.entities.PieceContainer;

/**
 *  Handles click events on the Bullpen.
 *  
 *  Clicks to select and deselect a particular piece displayed in the bullpen
 *  are handled here.
 *  
 *  @author Alex Titus
 */
public class BullpenController extends MouseAdapter
{
	/** The bullpen model. */
	Bullpen model;
	/** The GUI object representing the model. */
	BullpenView view;
	/** The GUI object representing the board. */
	BoardView boardView;
	
	
	/**
	 *  Constructor.
	 *  
	 * @param model  the bullpen model
	 * @param view  the GUI object displaying the bullpen
	 * @param boardView  the GUI object representing the board
	 */
	public BullpenController(Bullpen model, BullpenView view, BoardView boardView)
	{
		this.model = model;
		this.view = view;
		this.boardView = boardView;
	}
	

	/**
	 *  Selects or deselects the Piece that was clicked on.
	 *  
	 *  @param me  the mouse press starting this event
	 */
	@Override
	public void mousePressed(MouseEvent me)
	{
		// Calculate which piece was selected using
		// the coordinates of the MouseEvent and the
		// known width of rendered Pieces in the Bullpen
		int index = me.getX() / BullpenView.PIECE_SIZE;
		
		// If in hint selection or release number placement mode,
		// no selection of pieces is allowed
		if(!boardView.getHintSelectionMode() && !boardView.getReleaseNumberMode())
		{
			// Need to check that index is within the number of Pieces in Bullpen
			if(index >= model.getSize())  // >= because 0-based indexing
			{
				// Then index invalid, but deselect any selected Piece
				PieceContainer selected = model.getPieceSelected();
				if(selected != null)  // Inform currently selected piece that it is no longer selected
				{
					selected.setIsSelected(false);
				}
				model.setPieceSelected(null);
			}
			else  // Safe to access using index
			{
				// Then select that Piece
				model.setPieceSelected(model.getPiece(index));
			}

			// Change view to reflect change
			view.modelUpdated();
		}
	}
	

	
}
