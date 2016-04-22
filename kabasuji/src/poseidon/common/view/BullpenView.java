package poseidon.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import poseidon.entities.Bullpen;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;

/**
 *  Renders the Bullpen, drawing the Pieces it contains.
 *  
 *  
 *  @author Alex Titus
 */
public class BullpenView extends JPanel implements Scrollable, IModelUpdated
{
	/** The state of the Bullpen. */
	Bullpen model;
	/** The Pieces in the Bullpen. */
	ArrayList<PieceView> pieces;
	/** The representation of the selected Piece */
	PieceView selectedPiece;
	/** How large the Pieces are, in pixels. */
	public static final int PIECE_SIZE = 60;
	/** How large the constituent squares of a Piece are, in pixels. */
	public static final int SQUARE_SIZE = 10;
	

	/**
	 * Create the panel.
	 * 
	 * @param model  the model of the Bullpen
	 */
	public BullpenView(Bullpen model)
	{
		this.model = model;
		this.selectedPiece = null;

		setLayout(null);
		createPieces();
		setPreferredSize(new Dimension(PIECE_SIZE * pieces.size(), PIECE_SIZE));
	}

	
	/*************************
	 *  Getters and setters  *
	 *************************/

	
	
	/**
	 *  Creates the PieceView objects based on the model's Bullpen.
	 */
	public Boolean createPieces()
	{
		pieces = new ArrayList<PieceView>(model.getPieces().size());
		
		for(PieceContainer p : model.getPieces())
		{
			pieces.add(new PieceView(p, this));  // TODO use proper method
		}
		
		return true;
	}
	
	
	/**
	 *  Updates display when the model changes.
	 *  
	 *  @return whether operation completed successfully
	 */
	public Boolean modelUpdated()
	{
		// Refresh pieces
		createPieces();

		// Resize so that if the number of Pieces changed the
		// panel will extend or contract to accomodate them
		setPreferredSize(new Dimension(BullpenView.PIECE_SIZE * pieces.size(),
				BullpenView.PIECE_SIZE));

		repaint();
		
		return true;
	}
	
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawPieces(g);
	}
	
	
	void drawPieces(Graphics g)
	{
		Graphics drawer = g.create();
		
		// Draw Pieces
		for(int i = 0; i < pieces.size(); i++)
		{
			PieceView pv = pieces.get(i);
			Piece p = pv.getModel().getPiece();
			int offsetX = PIECE_SIZE * i;
			for(Point pt : p.getPiece())
			{
				int pieceOffsetX = 2 + SQUARE_SIZE * pt.getRow();
				int pieceOffsetY = 2 + SQUARE_SIZE * pt.getCol();
				drawer.setColor(pv.getPieceColor());
				drawer.fillRoundRect(pieceOffsetX + offsetX, pieceOffsetY,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
				drawer.setColor(pv.getPieceBorder());
				drawer.drawRoundRect(pieceOffsetX + offsetX, pieceOffsetY,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			}
			
			// If the Piece Container is currently selected in the Bullpen
			if(pv.getModel().getIsSelected())
			{
				// Then indicate that it is selected (with a border right now)
				drawer.setColor(Color.CYAN);
				drawer.drawRect(offsetX + 0, 0, PIECE_SIZE - 1, PIECE_SIZE - 1);
				drawer.drawRect(offsetX + 1, 1, PIECE_SIZE - 3, PIECE_SIZE - 3);
			}
		}
	}
	

	/**
	 *  Determines how much of this panel will be displayed in the containing scroll pane.
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return new Dimension(6 * BullpenView.PIECE_SIZE + 6 * 4, BullpenView.PIECE_SIZE + 4);
	}


	/**
	 *  Called whenever the user clicks the track.
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Scroll enough to move two Pieces in the Bullpen, snapping to next Piece
		int maximum = 2 * BullpenView.PIECE_SIZE;
		
		return maximum - visibleRect.x % BullpenView.PIECE_SIZE;
	}


	/**
	 *  Whether the panel should be constrained by the height of the viewing window.
	 */
	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		// Don't want to be constrained
		return false;
	}


	/**
	 *  Whether the panel should be constrained by the width of the viewing window.
	 */
	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		// Don't want to be constrained
		return false;
	}


	/**
	 *  Called whenever the user clicks on one of the arrow buttons.
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Scroll the width of one Piece in the Bullpen, snapping to next Piece
		int maximum = BullpenView.PIECE_SIZE;
		return maximum - visibleRect.x % BullpenView.PIECE_SIZE;
	}
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public PieceView getSelectedPiece()
	{
		return selectedPiece;
	}
	public PieceView getPieceView(int index)
	{
		return pieces.get(index);
	}
	public void setSelectedPiece(PieceView piece)
	{
		selectedPiece = piece;
	}
}
