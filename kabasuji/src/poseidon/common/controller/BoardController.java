package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.BoardToBoardMove;
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
		int col = me.getX() / BoardView.SQUARE_SIZE;
		int row = me.getY() / BoardView.SQUARE_SIZE;
		
		// If nothing is selected
		if(piece == null || pv == null)
		{
			// Then try to set a Playable Square
			
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
			
			view.modelUpdated();
			return ;
		}
		
		// Else attempt to add Piece to Board
		BullpenToBoardMove move = new BullpenToBoardMove(game, view, piece, new Point(row, col));
		if(move.doMove())  // If move is successful
		{
			// Then record it and reset Board and Bullpen's active/selection
			boardView.setActiveDragging(null);
			boardView.setActiveLocation(null);
			bullpenView.setSelectedPiece(null);
			UndoManager.instance().recordMove(move);
			view.modelUpdated();
		}
	}
	
	
	/**
	 *  Used to select a Piece for moving.
	 *  
	 *  Only used in Puzzle Levels and sometimes in the Builder.
	 *  
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mousePressed(MouseEvent me)
	{
		// Get row and column coordinates
		int col = me.getX() / BoardView.SQUARE_SIZE;
		int row = me.getY() / BoardView.SQUARE_SIZE;
		
		// Selects Piece at Square (row, col) if possible, else false
		if(boardModel.canSelect(row, col))
		{
			boardView.selectPiece(row, col);
			boardView.setActiveSource(new Point(row, col));
			PieceView pv = boardView.getActiveDragging();
			PieceContainer piece = pv.getModel();
			boardModel.setActiveDragged(piece);
			
			// If nothing is selected, release any dragged objects and leave
			if(piece == null || pv == null)
			{
				boardView.setActiveSource(null);
				boardView.setActiveDragging(null);
				boardModel.setActiveDragged(null);
				return ;
			}
			
			// Notify Board that there is a Piece on it
			boardView.setActiveLocation(me.getPoint());
			
			boardView.repaint();
		}
		// Else nothing
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
		PieceContainer piece = boardModel.getActiveDragged();
		
		// Determine row and column of click
		int col = me.getX() / BoardView.SQUARE_SIZE;
		int row = me.getY() / BoardView.SQUARE_SIZE;
		
		// If there is no actively dragged piece
		if(piece == null)
		{
			// Then bail
			return ;
		}
		
		// Attempt to add Piece to Board
		BoardToBoardMove move = new BoardToBoardMove(view, piece,
				boardView.getActiveSource(), new Point(row, col));
		if(move.doMove())  // If move is successful
		{
			// Then record it and reset Board and Bullpen's active/selection
			boardView.setActiveDragging(null);
			boardView.setActiveLocation(null);
			bullpenView.setSelectedPiece(null);
			UndoManager.instance().recordMove(move);
			view.modelUpdated();
		}
	}
	
	
	/**
	 *  Used to move a Piece selected from the Board.
	 * 
	 *  @param me  the initiating MouseEvent
	 */
	@Override
	public void mouseDragged(MouseEvent me)
	{
		PieceView pv = boardView.getActiveDragging();
		
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
}
