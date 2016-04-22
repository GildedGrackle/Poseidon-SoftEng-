package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.BullpenToBoardMove;
import poseidon.entities.LevelModel;
import poseidon.entities.MarkPlayableSquareMove;
import poseidon.entities.MarkUnplayableSquareMove;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.UndoManager;
import poseidon.player.view.LevelView;

/**
 *  Controls mouse events on the Board.
 *  
 *  Handles drawing Pieces on the Board on mouseover, placing Pieces on click,
 *  and allowing for Pieces to be moved once placed on the Board by drag & drop.
 *  
 * @author Alex Titus
 */
public class BoardController extends MouseAdapter
{
	/** The Level (Kabasuji game) state. */
	LevelModel game;
	/** The visual representation of the Level. */
	LevelView view;
	/** The Board state. */
	Board boardModel;
	/** The visual representation of the Board. */
	BoardView boardView;
	/** The Bullpen state. */
	Bullpen bullpenModel;
	/** The visual representation of the Bullpen. */
	BullpenView bullpenView;

	
	/**
	 *  Constructor.
	 * @param model  the Level that contains the Board
	 * @param view  the represenation of the Board
	 */
	public BoardController(LevelModel game, LevelView view)
	{
		this.game = game;
		this.view = view;
		this.boardModel = game.getBoard();
		this.boardView = view.getBoard();
		this.bullpenModel = game.getPlayableBullpen();
		this.bullpenView = view.getBullpen();
	}
	
	
	/**
	 *  Draws the Piece selected in the Bullpen onto the Board.
	 *  
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseEntered(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(piece == null || pv == null)
		{
			return ;
		}
		
		// Notify Board that there is a Piece on it
		boardView.setActiveDragging(pv);
		boardView.setActiveLocation(me.getPoint());
		
		boardView.repaint();
	}
	
	
	/**
	 *  Removes the active dragging Piece from the Board.
	 *  
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseExited(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(piece == null || pv == null)
		{
			return ;
		}
		
		// Notify Board that there is a Piece on it
		boardView.setActiveDragging(null);
		boardView.setActiveLocation(null);
		
		boardView.repaint();
	}
	
	
	@Override
	public void mouseMoved(MouseEvent me)
	{
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(pv == null)
		{
			return ;
		}
		
		// Notify Board that the Piece has changed locations
		java.awt.Point newLocation = me.getPoint();
		newLocation.x = newLocation.x - 14;
		newLocation.y = newLocation.y - 14;
		boardView.setActiveLocation(newLocation);
		
		boardView.repaint();
	}
	
	
	/**
	 *  Places a Piece on the Board.
	 *  
	 *  Places the selected Piece in the Bullpen, removing it from the Bullpen,
	 *  adding it to the Board, and updating the PieceContainer's location with
	 *  its (row, column) coordinates on the Board.
	 *  
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		PieceContainer piece = bullpenModel.getPieceSelected();
		PieceView pv = bullpenView.getSelectedPiece();
		
		// Determine row and column of click
		int row = me.getX() / BoardView.SQUARE_SIZE;
		int col = me.getY() / BoardView.SQUARE_SIZE;
		
		// If nothing is selected
		if(piece == null || pv == null)
		{
			// Then try to set a Playable Squarw
			
			// Figure out whether to try a Mark Playable or Unplayable Move
			if(boardModel.getSquare(row, col).getType() < 0)
			{
				// Is UnplayableSquare, try to mark as Playable
				MarkPlayableSquareMove move =
						new MarkPlayableSquareMove(boardModel, new Point(row, col));
				
				// Attempt move
				if(move.doMove())  // If successful
				{
					// Then record the move
					UndoManager.instance().recordMove(move);
				}
				
				// Now finished
			}
			else  // Is Playable Square
			{
				// Try to mark as Unplayable
				MarkUnplayableSquareMove move =
						new MarkUnplayableSquareMove(boardModel, new Point(row, col));
				
				// Attempt move
				if(move.doMove())  // If successful
				{
					// Then record the move
					UndoManager.instance().recordMove(move);
				}
			}
			
			return ;
		}
		
		// Else attempt to add Piece to Board
		
		// Add Piece to Board
		BullpenToBoardMove move = new BullpenToBoardMove(piece, new Point(row, col));
		if(move.doMove())  // If move is successful
		{
			// Then record it
			UndoManager.instance().recordMove(move);
			boardView.repaint();
			bullpenView.repaint();
		}
		else  // Move was unsuccessful
		{
			// Reset Piece's location
			piece.setLocation(null);
		}
	}
	
	
	/**
	 *  Used to select a Piece for moving.
	 *  
	 *  Only used in Puzzle Levels.
	 *  
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent me)
	{
		// Get row and column coordinates
		int row = me.getX() / BoardView.SQUARE_SIZE;
		int col = me.getY() / BoardView.SQUARE_SIZE;
		
		// Selects Piece at Square (row, col) if possible, else false
		if(boardModel.selectSquare(row, col))
		{
			PieceView pv = boardView.getActiveDragging();
			PieceContainer pc = pv.getModel();
			
			// If either dragging objects don't exist
			if(pv == null || pc == null)
			{
				// Then bail now, can't do anything here
				return ;
			}
			
			
		}
	}
	
	
	/**
	 *  Used to place a Piece from the Board.
	 *  
	 *  Handles either a Board-to-Board move or a Board-to-Bullpen move
	 *  TODO determine if Board2Bullpen is actually on mouseExited
	 * 
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseReleased(MouseEvent me)
	{
		
	}
	
	
	/**
	 *  Used to move a Piece selected from the Board.
	 * 
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent me)
	{
		
	}
}
