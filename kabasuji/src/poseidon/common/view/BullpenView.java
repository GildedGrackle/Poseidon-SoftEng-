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
 *  @author Alex Titus
 */
public class BullpenView extends JPanel implements Scrollable, IModelUpdated{
	/** The state of the Bullpen. */
	Bullpen model;
	/** The Pieces in the Bullpen. */
	ArrayList<PieceView> pieces;
	/** The representation of the selected Piece */
	PieceView selectedPiece;
	/** How size (height or width) of the constituent squares of a Piece, in pixels. */
	public static final int SQUARE_SIZE = 10;
	/** The size (height or width) of a Piece, in pixels. */
	public static final int PIECE_SIZE = 60;
	
	

	/**
	 *  Constructor.
	 * 
	 *  @param model  the Bullpen
	 */
	public BullpenView(Bullpen model)
	{
		this.model = model;
		this.selectedPiece = null;

		createPieces();
		
		setLayout(null);
		setMinimumSize(new Dimension(PIECE_SIZE * 6, PIECE_SIZE));
		setPreferredSize(new Dimension(PIECE_SIZE * pieces.size(), PIECE_SIZE));
	}

	
	/**
	 *  Creates the PieceView objects based on the model's playable bullpen.
	 *  
	 *  @return  Indicator whether operation completed successfully.
	 */
	public Boolean createPieces()
	{
		pieces = new ArrayList<PieceView>(model.getPieces().size());
		
		for(PieceContainer p : model.getPieces())
		{
			pieces.add(new PieceView(p));
		}
		
		return true;
	}
	
	
	/**
	 *  Updates display when the model changes.
	 *  
	 *  @return  Indicator whether operation completed successfully.
	 */
	public Boolean modelUpdated()
	{
		// If resizing won't make this panel smaller than its minimum size
		if(PIECE_SIZE * pieces.size() >= getMinimumSize().width)
		{
			// Then resize so that if the number of Pieces changed the
			// panel will extend or contract to accomodate them
			setPreferredSize(new Dimension(BullpenView.PIECE_SIZE * pieces.size(),
					BullpenView.PIECE_SIZE));
		}
		
		repaint();
		revalidate();
		
		return true;
	}
	
	
	/**
	 *  Overrides JPanel's paintComponent() method, drawing the board.
	 *  
	 *  @param g  Graphics object used to render this object
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawPieces(g);
	}
	
	
	/** 
	 *  Draws pieces currently in the bullpen.
	 *  
	 *  This method shouldn't be called directly. It is automatically called
	 *  during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawPieces(Graphics g)
	{
		Graphics drawer = g.create();
		
		// Draw Pieces
		int i = 0;
		for(PieceView pv : pieces)
		{
			Piece p = pv.getModel().getPiece();
			int offsetX = PIECE_SIZE * i;
			for(Point pt : p.getPiece())
			{
				int pieceOffsetY = 2 + SQUARE_SIZE * pt.getRow();
				int pieceOffsetX = 2 + SQUARE_SIZE * pt.getCol();
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
			
			i++;
		}
	}
	
	
	/**
	 *  Adds given PieceView to the list of PieceViews.
	 *  
	 * @param piece  PieceView to add
	 * @return  Indicator whether operation completed successfully.
	 */
	public boolean addPiece(PieceView piece)
	{
		return pieces.add(piece);
	}
	
	
	/**
	 *  Returns a given PieceView to the list of PieceViews at given index.
	 *  
	 *  @param piece  PieceView to add
	 *  @param index  the index of this piece, must be within bounds
	 *  @return  Indicator whether operation completed successfully.
	 */
	public boolean addPieceAt(PieceView piece, int index)
	{
		pieces.add(index, piece);
		return true;
	}
	
	
	/**
	 *  Removes given PieceView from the list of PieceViews.
	 *  
	 * @param piece  the PieceView to remove
	 * @return  Indicator whether operation changed list.
	 */
	public boolean removePiece(PieceView piece)
	{
		return pieces.remove(piece);
	}
	

	/**
	 *  Determines how much of this panel will be displayed in the containing scroll pane.
	 *  
	 *  @return  Size to fully display 6 pieces arranged left-to-right.
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return new Dimension(6 * BullpenView.PIECE_SIZE + 6 * 4, BullpenView.PIECE_SIZE + 4);
	}


	/**
	 *  Whenever the user clicks the track, move four pieces over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to location four pieces over.
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Enough to move four Pieces in the bullpen
		int maximum = 4 * BullpenView.PIECE_SIZE;
		//                          to snap to next piece
		return maximum - visibleRect.x % BullpenView.PIECE_SIZE;
	}


	/**
	 *  Whether the panel should be constrained by the height of the viewing window.
	 *  
	 *  @return  False - don't want to be constrained.
	 */
	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}


	/**
	 *  Whether the panel should be constrained by the width of the viewing window.
	 *  
	 *  @return  False - don't want to be constrained.
	 */
	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return false;
	}


	/**
	 *  Whenever the user clicks on one of the arrow buttons, move one piece over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to the next piece.
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
	/** @return  The Bullpen associated with this object. */
	public Bullpen getModel()
	{
		return model;
	}
	/** @return  The selected PieceView. */
	public PieceView getSelectedPiece()
	{
		return selectedPiece;
	}
	/**
	 *  @param index  location in the list of pieces, must be within list bounds
	 *  @return  The PieceView at index. 
	 */
	public PieceView getPieceView(int index)
	{
		return pieces.get(index);
	}
	/** 
	 * Sets the selected PieceView, or deselects the PieceView is already selected.
	 * 
	 * @param piece  the piece to select
   */
	public void setSelectedPiece(PieceView piece)
	{
		// If incoming piece is already selected
		if(piece == selectedPiece){
			// Then deselect it
			selectedPiece = null;
		}
		else {  // Different piece
			selectedPiece = piece;
		}
	}
}
