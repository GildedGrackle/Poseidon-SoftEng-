package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;

/**
 *  Flips the selected Piece in the Bullpen vertically.
 *  
 *  @author Alex Titus
 *
 */
public class VerticalFlipController implements ActionListener
{
	/** The visual representation of the Bullpen. */
	BullpenView view;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the visual representation of the Bullpen
	 */
	public VerticalFlipController(BullpenView view)
	{
		this.view = view;
	}

	
	/**
	 *  Flips the selected Piece in the Bullpen vertically.
	 *  
	 *  @param ae  the initiating action event (button press)
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
		pv.getModel().getPiece().flipVertical();
		
		view.repaint();
	}

}
