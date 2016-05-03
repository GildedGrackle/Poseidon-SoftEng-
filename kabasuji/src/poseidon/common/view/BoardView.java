package poseidon.common.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import poseidon.entities.Board;
import poseidon.entities.LevelModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.ReleaseNumber;
import poseidon.entities.Square;

/**
 *  Renders the Board of a Kabasuji game.
 *  
 *  @author Alex Titus
 */
public class BoardView extends JPanel implements IModelUpdated
{
	/** The model of the Board. */
	Board board;
	/** Location of the top-left corner (anchor) of activeDragging. */
	java.awt.Point activeLocation;
	/** Whether in hint selection mode or not. */
	Boolean hintSelectionMode;
	/** Whether in release number placement mode or not. */
	Boolean releaseNumberMode;
	/** The size (height or width) of a Square, in pixels. */
	public static final int SQUARE_SIZE = 30;
	/** The size (height or width) of a Piece when, in pixels. */
	public static final int PIECE_SIZE = 180;

	
	/**
	 *  Constructor.
	 *  
	 *  @param board  the Board to render
	 */
	public BoardView(Board board)
	{
		this.board = board;
		this.hintSelectionMode = false;
		this.releaseNumberMode = false;
	}
	
	
	/**
	 *  Overrides JPanel's paintComponent() method, drawing the Board.
	 *  
	 *  @param g  Graphics object used to render this object
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Draw playable Squares
		drawBoard(g);
		
		// Now draw Pieces
		drawPieces(g);
		
		// Now draw active dragging Piece
		if(board.getActiveDragged() != null)  // If there is one
		{
			drawActiveDragging(g);
		}
		
		// Now draw hints
		drawHints(g);
	}
	
	
	/**
	 *  Draws the Squares of the Board.
	 *  
	 *  This method shouldn't be called directly. It is called during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawBoard(Graphics g)
	{
		Graphics drawer = g.create();
		
		Square[][] playArea = board.getPlayArea();
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				// If the Square if playable
				if(playArea[i][j].getType() > 0)
				{
					// Then at least draw a square for it
					drawer.setColor(Color.white);
					drawer.fillRect(SQUARE_SIZE * j, SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
					drawer.setColor(Color.black);
					drawer.drawRect(SQUARE_SIZE * j, SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
					
					// If the Square is a ReleaseSquare and it has a number to draw
					if(playArea[i][j].getType() == LevelModel.RELEASE &&
							playArea[i][j].getReleaseNumber() != null)
					{
						// Then draw the number in its color
						drawNumber(drawer, playArea[i][j].getReleaseNumber(), i, j);
					}
					
					// If the Square is filled
					if(playArea[i][j].isFilled())
					{
						drawer.fillRect(SQUARE_SIZE * j, SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
					}
				}
				// Else don't draw anything for unplayable squares
			}
		}
	}
	
	
	/**
	 *  Draws the Pieces that have been placed on the Board.
	 *  
	 *  This method shouldn't be called directly. It is called during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawPieces(Graphics g)
	{
		Graphics drawer = g.create();
		
		for(PieceContainer pc : board.getPieces())
		{
			Piece p = pc.getPiece();
			Point location = pc.getLocation();
			if(location == null || location == new Point(-1, -1))
			{
				// Then we have a problem
				System.err.println("BoardView: Unexpectedly encountered Piece with no location while painting.");
				return ;
			}
			// Else everything is fine (it should be)
			int row = location.getRow();
			int col = location.getCol();
			for(Point pt : p.getPiece())
			{
				int pieceOffsetY = SQUARE_SIZE * pt.getRow();
				int pieceOffsetX = SQUARE_SIZE * pt.getCol();
				drawer.setColor(pc.getPiece().getPieceColor());
				drawer.fillRoundRect(pieceOffsetX + col * SQUARE_SIZE,
						pieceOffsetY + row * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
				drawer.setColor(pc.getPiece().getPieceBorder());
				drawer.drawRoundRect(pieceOffsetX + col * SQUARE_SIZE,
						pieceOffsetY + row * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			}
		}
	}
	
	
	/**
	 *  Draws the Piece currently being moved on the Board.
	 *  
	 *  This method shouldn't be called directly. It is called during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawActiveDragging(Graphics g)
	{
		Graphics drawer = g.create();

		Piece p = board.getActiveDragged().getPiece();
		int boardOffsetX = activeLocation.x;
		int boardOffsetY = activeLocation.y;
		for(Point pt : p.getPiece())
		{
			int pieceOffsetY = 2 + SQUARE_SIZE * pt.getRow();
			int pieceOffsetX = 2 + SQUARE_SIZE * pt.getCol();
			drawer.setColor(p.getPieceColor());
			drawer.fillRoundRect(pieceOffsetX + boardOffsetX, pieceOffsetY + boardOffsetY,
					SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			drawer.setColor(p.getPieceBorder());
			drawer.drawRoundRect(pieceOffsetX + boardOffsetX, pieceOffsetY + boardOffsetY,
					SQUARE_SIZE, SQUARE_SIZE, 3, 3);
		}
	}
	
	
	/**
	 *  Draws the ReleaseNumber on the indicated Square.
	 *  
	 *  This method shouldn't be called directly. It is called during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 *  @param toDraw  the ReleaseNumber to draw
	 *  @param row  the row of the Square containing this ReleaseNumber
	 *  @param col  the column of the Square containing this ReleaseNumber
	 */
	void drawNumber(Graphics g, ReleaseNumber toDraw, int row, int col)
	{
		Graphics drawer = g.create();
		
		// Set graphics properties
		drawer.setFont(new Font("Lucida Handwriting", Font.PLAIN, 14));
		switch(toDraw.getColor())
		{
		case ReleaseNumber.RED:
			drawer.setColor(Color.red);
			break;
		case ReleaseNumber.GREEN:
			drawer.setColor(Color.green);
			break;
		case ReleaseNumber.YELLOW:
			drawer.setColor(Color.yellow);
			break;
		}
		
		// Draw number
		drawer.drawString("" + toDraw.getNumber(), SQUARE_SIZE * col + 10, SQUARE_SIZE * row + 22);
	}
	
	
	/**
	 *  Draws the hint indicated on the Board.
	 *  
	 *  This method shouldn't be called directly. It is called during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawHints(Graphics g)
	{
		Graphics drawer = g.create();
		Square[][] playArea = board.getPlayArea();
		
		drawer.setColor(Color.yellow);
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				// If the Square is a hint space
				if(playArea[i][j].getIsHint())
				{
					drawer.drawRoundRect(SQUARE_SIZE * j + 1, SQUARE_SIZE * i + 1,
							SQUARE_SIZE - 3, SQUARE_SIZE - 3, 3, 3);
					drawer.drawRoundRect(SQUARE_SIZE * j + 2, SQUARE_SIZE * i + 2,
							SQUARE_SIZE - 5, SQUARE_SIZE - 5, 3, 3);
				}
			}
		}
	}
	
	
	/**
	 *  Updates the display when the underlying model changes.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	@Override
	public Boolean modelUpdated()
	{
		// Update display
		repaint();
		
		return true;
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return The Board associated with this object. */
	public Board getBoard()
	{
		return board;
	}
	
	
	/** @return  Indication of whether in hint selection mode. */
	public Boolean getHintSelectionMode()
	{
		return hintSelectionMode;
	}
	
	
	/** @return  Indication of whether in release number placement mode. */
	public Boolean getReleaseNumberMode()
	{
		return releaseNumberMode;
	}
	
	
	/** @return The location of the anchor point of the piece being dragged. */
	public java.awt.Point getActiveLocation()
	{
		return activeLocation;
	}
	
	
	/**
	 *  Sets the location of the anchor point of the piece being dragged.
	 *  
	 *  @param activeLocation  the new location of the piece
	 */
	public void setActiveLocation(java.awt.Point activeLocation)
	{
		this.activeLocation = activeLocation;
	}
	
	/**
	 *  Sets the hint selection mode.
	 *  
	 *  @param newMode  the new mode
	 */
	public void setHintSelectionMode(Boolean newMode)
	{
		hintSelectionMode = newMode;
	}
	
	/**
	 *  Sets the release number placement mode.
	 *  
	 *  @param newMode  the new mode
	 */
	public void setReleaseNumberMode(Boolean newMode)
	{
		releaseNumberMode = newMode;
	}
}
