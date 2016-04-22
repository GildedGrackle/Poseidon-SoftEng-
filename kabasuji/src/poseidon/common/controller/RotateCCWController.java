package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;

/**
 *  Rotates the selected Piece in the Bullpen counter-clockwise.
 *  
 * @author Alex Titus
 */
public class RotateCCWController implements ActionListener
{
	/** The visual representation of the Bullpen. */
	BullpenView view;
	
	
	/**
	 *  Constructor.
	 * @param view  the visual representation of the Bullpen
	 */
	public RotateCCWController(BullpenView view)
	{
		this.view = view;
	}

	
	/**
	 *  Rotates the selected Piece in the Bullpen counter-clockwise.
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
		
		// Else Piece exists, so rotate it
		pv.getModel().getPiece().rotateCCW();
		
		view.repaint();
	}

}
