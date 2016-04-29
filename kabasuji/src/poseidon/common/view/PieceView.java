package poseidon.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JPanel;

import poseidon.entities.PieceContainer;

/**
 *  Associates a color with a PieceContainer.
 *  
 *  @author Alex Titus
 */
public class PieceView extends JPanel
{
	/** The model of the piece. */
	PieceContainer model;
	/** The color of the constituent squares of this piece. */
	Color pieceColor;
	/** The color of the edges of the constituent squares of this piece. */
	Color pieceBorder;
	

	/**
	 *  Constructor.
	 *  
	 *  @param model  the PieceContainer to be drawn
	 *  @param view  the parent container
	 */
	public PieceView(PieceContainer model)
	{
		setOpaque(false);
		this.model = model;
		
		// Pick a random color for the Piece
		switch(new Random().nextInt(5))
		{
		case 0:
			pieceColor = Color.yellow;
			pieceBorder = Color.orange;
			break;
		case 1:
			pieceColor = Color.pink;
			pieceBorder = Color.red;
			break;
		case 2:
			pieceColor = Color.cyan;
			pieceBorder = Color.blue;
			break;
		case 3:
			pieceColor = Color.green;
			pieceBorder = Color.darkGray;
			break;
		case 4:
			pieceColor = Color.orange;
			pieceBorder = Color.red;
			break;
		}
		
		setPreferredSize(new Dimension(BullpenView.PIECE_SIZE, BullpenView.PIECE_SIZE));
	}
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The PieceContainer associated with this object. */
	public PieceContainer getModel()
	{
		return model;
	}
	/** @return  The Color of the piece (the fill). */
	public Color getPieceColor()
	{
		return pieceColor;
	}
	/** @return  The Color of the piece's border.*/
	public Color getPieceBorder()
	{
		return pieceBorder;
	}
}
