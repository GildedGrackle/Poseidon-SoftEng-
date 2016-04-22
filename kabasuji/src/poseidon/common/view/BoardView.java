package poseidon.common.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import poseidon.entities.Board;
import poseidon.entities.LevelModel;
import poseidon.entities.Piece;
import poseidon.entities.Point;
import poseidon.entities.ReleaseNumber;
import poseidon.entities.Square;

/**
 *  Renders the Board of a Kabasuji game.
 *  
 *  @author Alex Titus
 */
public class BoardView extends JPanel
{
	/** The model of the Board */
	Board board;
	/** The Pieces currently on the Board */
	ArrayList<PieceView> pieces;
	/** The Piece currently being dragged (if applicable) */
	PieceView activeDragging;
	/** Location of the top-left corner (anchor) of activeDragging */
	java.awt.Point activeLocation;
	/** The size (height or width) of a Square when displayed on the Board */
	public static final int SQUARE_SIZE = 30;
	public static final int PIECE_SIZE = 180;

	/**
	 * Create the panel.
	 */
	public BoardView(Board board)
	{
		this.board = board;
		pieces = new ArrayList<PieceView>();
		activeDragging = null;
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics drawer = g.create();
		
		// Draw playable Squares
		Square[][] playArea = board.getPlayArea();
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 12; j++)
			{
				// If the Square if playable
				if(playArea[i][j].getType() > 0)
				{
					// Then at least draw a square for it
					drawer.drawRect(SQUARE_SIZE * i, SQUARE_SIZE * j, SQUARE_SIZE, SQUARE_SIZE);
					
					// If the Square is a ReleaseSquare
					if(playArea[i][j].getType() == LevelModel.RELEASE)
					{
						// Then draw the number in its color
						drawNumber(drawer, playArea[i][j].getReleaseNumber(), i, j);
					}
				}
			}
		}
		
		// Now draw Pieces
		for(PieceView pv : pieces)
		{
			Piece p = pv.getModel().getPiece();
			Point location = pv.getModel().getLocation();
			if(location == null)
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
				int pieceOffsetX = SQUARE_SIZE * pt.getRow();
				int pieceOffsetY = SQUARE_SIZE * pt.getCol();
				drawer.setColor(pv.getPieceColor());
				drawer.fillRoundRect(pieceOffsetX + row * SQUARE_SIZE,
						pieceOffsetY + col * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
				drawer.setColor(pv.getPieceBorder());
				drawer.drawRoundRect(pieceOffsetX + row * SQUARE_SIZE,
						pieceOffsetY + col * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			}
		}
		
		// Now draw active dragging Piece
		if(activeDragging != null)  // If there is one
		{
			Piece p = activeDragging.getModel().getPiece();
			int boardOffsetX = activeLocation.x;
			int boardOffsetY = activeLocation.y;
			for(Point pt : p.getPiece())
			{
				int pieceOffsetX = 2 + SQUARE_SIZE * pt.getRow();
				int pieceOffsetY = 2 + SQUARE_SIZE * pt.getCol();
				drawer.setColor(activeDragging.getPieceColor());
				drawer.fillRoundRect(pieceOffsetX + boardOffsetX, pieceOffsetY + boardOffsetY,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
				drawer.setColor(activeDragging.getPieceBorder());
				drawer.drawRoundRect(pieceOffsetX + boardOffsetX, pieceOffsetY + boardOffsetY,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			}
		}
		
		// Now draw hints
		drawer.setColor(Color.yellow);
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				// If the Square is a hint space
				if(playArea[i][j].getIsHint())
				{
					drawer.drawRoundRect(SQUARE_SIZE * i + 1, SQUARE_SIZE * j + 1,
							SQUARE_SIZE - 3, SQUARE_SIZE - 3, 3, 3);
					drawer.drawRoundRect(SQUARE_SIZE * i + 2, SQUARE_SIZE * j + 2,
							SQUARE_SIZE - 5, SQUARE_SIZE - 5, 3, 3);
				}
			}
		}
	}
	
	
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
		drawer.drawString("" + toDraw.getNumber(), SQUARE_SIZE * row + 10, SQUARE_SIZE * col + 10);
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public ArrayList<PieceView> getPieces()
	{
		return pieces;
	}
	public PieceView getActiveDragging()
	{
		return activeDragging;
	}
	public java.awt.Point getActiveLocation()
	{
		return activeLocation;
	}
	public void setPieces(ArrayList<PieceView> pieces)
	{
		this.pieces = pieces;
	}
	public void addPiece(PieceView piece)
	{
		pieces.add(piece);
	}
	public void setActiveDragging(PieceView activeDragging)
	{
		this.activeDragging = activeDragging;
	}
	public void setActiveLocation(java.awt.Point activeLocation)
	{
		this.activeLocation = activeLocation;
	}
}
