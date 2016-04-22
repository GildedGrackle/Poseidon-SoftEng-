package poseidon.common.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

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
	/** The model of the Board */
	Board board;
	/** The Pieces currently on the Board */
	ArrayList<PieceView> pieces;
	/** The Piece currently being dragged (if applicable) */
	PieceView activeDragging;
	/** Location of the top-left corner (anchor) of activeDragging */
	java.awt.Point activeLocation;
	/** The coordinates of the originating Square of a drag event. */
	Point activeSource;
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
		
		// Draw playable Squares
		drawBoard(g);
		
		// Now draw Pieces
		drawPieces(g);
		
		// Now draw active dragging Piece
		drawActiveDragging(g);
		
		// Now draw hints
		drawHints(g);
	}
	
	
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
					drawer.drawRect(SQUARE_SIZE * j, SQUARE_SIZE * i, SQUARE_SIZE, SQUARE_SIZE);
					
					// If the Square is a ReleaseSquare
					if(playArea[i][j].getType() == LevelModel.RELEASE)
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
			}
		}
	}
	
	
	void drawPieces(Graphics g)
	{
		Graphics drawer = g.create();
		
		for(PieceView pv : pieces)
		{
			Piece p = pv.getModel().getPiece();
			Point location = pv.getModel().getLocation();
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
				drawer.setColor(pv.getPieceColor());
				drawer.fillRoundRect(pieceOffsetX + col * SQUARE_SIZE,
						pieceOffsetY + row * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
				drawer.setColor(pv.getPieceBorder());
				drawer.drawRoundRect(pieceOffsetX + col * SQUARE_SIZE,
						pieceOffsetY + row * SQUARE_SIZE,
						SQUARE_SIZE, SQUARE_SIZE, 3, 3);
			}
		}
	}
	
	
	void drawActiveDragging(Graphics g)
	{
		Graphics drawer = g.create();
		
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
		drawer.drawString("" + toDraw.getNumber(), SQUARE_SIZE * col + 10, SQUARE_SIZE * row + 10);
	}
	
	
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
	
	
	@Override
	public Boolean modelUpdated()
	{
		// Update display
		repaint();
		
		return true;
	}
	
	
	/**
	 *  Adds the given PieceView to the list of PieceViews.
	 *  
	 * @param piece  PieceView to add
	 */
	public void addPiece(PieceView piece)
	{
		pieces.add(piece);
	}
	
	
	/**
	 *  Sets the Piece at (row, col) as the active dragging Piece.
	 *  
	 * @param row  row on Board that Piece should cover
	 * @param col  col on Board that Piece should cover
	 */
	public void selectPiece(int row, int col)
	{
		// Search through all Pieces on Board
		for(PieceView pv : pieces)
		{
			PieceContainer pc = pv.getModel();
			for(Point pt : pc.getPiece().getPiece())
			{
				// If this Piece is present at (row, col)
				if((pc.getLocation().getRow() + pt.getRow()) == row &&
						(pc.getLocation().getCol() + pt.getCol()) == col)
				{
					// Then set it as the active dragging
					activeDragging = pv;
					return ;
				}
			}
		}
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public Board getBoard()
	{
		return board;
	}
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
	public Point getActiveSource()
	{
		return activeSource;
	}
	public void setPieces(ArrayList<PieceView> pieces)
	{
		this.pieces = pieces;
	}
	public void setActiveDragging(PieceView activeDragging)
	{
		this.activeDragging = activeDragging;
	}
	public void setActiveLocation(java.awt.Point activeLocation)
	{
		this.activeLocation = activeLocation;
	}
	public void setActiveSource(Point activeSource)
	{
		this.activeSource = activeSource;
	}
}
