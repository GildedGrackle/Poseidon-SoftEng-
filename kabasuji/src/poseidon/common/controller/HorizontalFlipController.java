package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;

/**
 *  Flips the selected Piece in the Bullpen horizontally.
 *  
 *  @author Alex Titus
 */
public class HorizontalFlipController implements ActionListener
{
	/** The visual representation of the Bullpen. */
	BullpenView view;
	
	
	/**
	 *  Constructor.
	 * @param view  the visual representation of the Bullpen
	 */
	public HorizontalFlipController(BullpenView view)
	{
		this.view = view;
	}

	
	/**
	 *  Flips the selected Piece in the Bullpen horizontally.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		PieceView pv = view.getSelectedPiece();
		
		// If there is no selected Piece, then leave
		if(pv == null)
		{
			return ;
		}
		
		// Else Piece exists, so flip it
		pv.getModel().getPiece().flipHorizontal();
		
		view.repaint();
	}

}
