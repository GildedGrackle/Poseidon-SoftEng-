package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.common.view.BullpenView;
import poseidon.entities.PieceContainer;

/**
 *  Rotates the selected Piece in the Bullpen clockwise.
 *  
 * @author Alex Titus
 */
public class RotateCWController implements ActionListener
{
	/** The visual representation of the Bullpen. */
	BullpenView view;
	
	
	/**
	 *  Constructor.
	 * @param view  the visual representation of the Bullpen
	 */
	public RotateCWController(BullpenView view)
	{
		this.view = view;
	}

	
	/**
	 *  Rotates the selected Piece in the Bullpen clockwise.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		PieceContainer pc = view.getModel().getPieceSelected();
		
		// If there is no selected Piece, then leave
		if(pc == null)
		{
			return ;
		}
		
		// Else Piece exists, so rotate it
		pc.getPiece().rotateCW();
		
		view.repaint();
	}

}
