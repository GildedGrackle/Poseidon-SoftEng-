package poseidon.common.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.common.view.BullpenView;
import poseidon.entities.PieceContainer;

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
		PieceContainer pc = view.getModel().getPieceSelected();
		
		// If there is no selected Piece, then leave
		if(pc == null)
		{
			return ;
		}
		
		// Else Piece exists, so flip it
		pc.getPiece().flipVertical();
		
		view.repaint();
	}

}
