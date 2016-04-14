package poseidon.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;

public class PieceView extends JPanel
{
	PieceContainer model;  // The model of the Piece
	BullpenView view;  // The GUI of the Bullpen
	Color pieceColor;  // The color of this Piece
	

	/**
	 * Create the panel.
	 */
	public PieceView(PieceContainer model, BullpenView view)
	{
		this.model = model;
		this.view = view;
		
		// Pick a random color for the Piece
		switch(new Random().nextInt(5))
		{
		case 0:
			pieceColor = Color.yellow;
			break;
		case 1:
			pieceColor = Color.red;
			break;
		case 2:
			pieceColor = Color.blue;
			break;
		case 3:
			pieceColor = Color.green;
			break;
		case 4:
			pieceColor = Color.orange;
			break;
		}
		
		setPreferredSize(new Dimension(60,60));
	}
	
	
	/**
	 *  Getters and setters
	 */

	
	/**
	 *  Creates the image of the piece
	 */
	@Override
	public void paint(Graphics g)  // TODO figure out proper method for double-buffering
	{
		Piece p = model.getPiece();  // TODO use correct method
		
		g.setColor(pieceColor);  // Set the color
		
		// For each Point in model, draw a square at that location
		for(Point pt : p.getPiece())
		{
			Point square = pt;  // TODO use correct method
			g.fillRoundRect(10 * square.getRow(), 10 * square.getCol(),
					10, 10, 3, 3);
		}
	}
}
