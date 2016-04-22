package poseidon.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JPanel;

import poseidon.entities.PieceContainer;

/**
 *  Used to render a PieceContainer in the Bullpen.
 *  
 *  @author Alex Titus
 */
public class PieceView extends JPanel
{
	/** The model of the Piece. */
	PieceContainer model;
	/** The GUI of the Bullpen. */
	BullpenView view;
	/** The color of the constituent squares of this Piece. */
	Color pieceColor;
	/** The color of the edges of the constituent squares of this Piece. */
	Color pieceBorder;
	/** Indicates if this is being rendered on the Board or in the Bullpen. */
	boolean onBoard;
	

	/**
	 *  Constructor.
	 *  
	 *  @param model  the PieceContainer to be drawn
	 *  @param view  the parent container
	 */
	public PieceView(PieceContainer model, BullpenView view)
	{
		setOpaque(false);
		this.model = model;
		this.view = view;
		onBoard = false;
		
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
		
		setPreferredSize(new Dimension(BullpenView.PIECE_SIZE,BullpenView.PIECE_SIZE));
	}
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************//***/
	public PieceContainer getModel()
	{
		return model;
	}
	public Color getPieceColor()
	{
		return pieceColor;
	}
	public Color getPieceBorder()
	{
		return pieceBorder;
	}
	public void setOnBoard(boolean onBoard)
	{
		this.onBoard = onBoard;
	}
}
