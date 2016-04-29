package poseidon.common.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.ILevelView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.BoardToBoardMove;
import poseidon.entities.BoardToBullpenMove;
import poseidon.entities.Bullpen;
import poseidon.entities.BullpenToBoardMove;
import poseidon.entities.IMove;
import poseidon.entities.LevelModel;
import poseidon.entities.MarkPlayableSquareMove;
import poseidon.entities.MarkUnplayableSquareMove;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.RedoManager;
import poseidon.entities.UndoManager;

/**
 *  Controls mouse events on the board.
 *  
 *  Handles drawing pieces on the board on mouseover, placing pieces on click,
 *  and allowing for pieces to be moved once placed on the board by drag & drop.
 *  
 *  @author Alex Titus
 *  @author Morgan Hopeman
 */
public class BoardController extends MouseAdapter
{
	/** The level (Kabasuji game) state. */
	LevelModel game;
	/** The visual representation of the level. */
	ILevelView view;
	/** The board state. */
	Board boardModel;
	/** The visual representation of the board. */
	BoardView boardView;
	/** The bullpen state. */
	Bullpen bullpenModel;
	/** The visual representation of the bullpen. */
	BullpenView bullpenView;

	
	/**
	 *  Constructor.
	 *  
	 * @param model  the level that contains the board
	 * @param view  the representation of the board
	 */
	public BoardController(LevelModel game, ILevelView view)
	{
		this.game = game;
		this.view = view;
		this.boardModel = game.getBoard();
		this.boardView = view.getBoard();
		this.bullpenModel = game.getPlayableBullpen();
		this.bullpenView = view.getBullpen();
	}
	
	/**
	 *  Removes the active dragging piece from the board.
	 *  
	 *  @param me  the initiating mouse event
	 */
	@Override
	public void mouseExited(MouseEvent me)
	{
		PieceView pv = bullpenView.getSelectedPiece();
		
		// If nothing is selected, leave
		if(pv == null)
		{
			return ;
		}
		
		// Notify Board that there is a Piece on it
		boardView.setActiveDragging(null);
		boardView.setActiveLocation(null);
		
		boardView.repaint();
	}
	
	
	/**
	 *  Notifies board of cursor's new location to render the active dragging piece.
	 *  
	 *  @param me  the initiating move event
	 */
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
		boardView.setActiveDragging(pv);
		boardView.setActiveLocation(newLocation);
		
		boardView.repaint();
	}
	
	
	/**
	 *  Toggles square from playable to nonplayable on double click (or higher).
	 *  
	 *  @param me  the initiating mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		if(me.getClickCount() > 1)
		{
			// Then try to set a Playable Square
			int col = me.getX() / BoardView.SQUARE_SIZE;
			int row = me.getY() / BoardView.SQUARE_SIZE;
			
			// Just in case
			if(col > 11 || row > 11 || col < 0 || row < 0)
			{
				return ;
			}

			// Figure out whether to try a Mark Playable or Unplayable Move
			if(boardModel.getSquare(row, col).getType() < 0)
			{
				// Is UnplayableSquare, try to mark as Playable
				MarkPlayableSquareMove move =
						new MarkPlayableSquareMove(boardModel, new Point(row, col), game.getGameMode());

				// Attempt move
				if(move.doMove())  // If successful
				{
					// Then record the move
					UndoManager.instance().recordMove(move);
					
					// Clear redo stack
					RedoManager.instance().clearMove();
				}

				// Now finished
			}
			else  // Is Playable Square
			{
				// Try to mark as Unplayable
				MarkUnplayableSquareMove move =
						new MarkUnplayableSquareMove(boardModel, new Point(row, col), game.getGameMode());

				// Attempt move
				if(move.doMove())  // If successful
				{
					// Then record the move
					UndoManager.instance().recordMove(move);
					
					// Clear redo stack
					RedoManager.instance().clearMove();
				}
			}

			// Update display
			view.modelUpdated();
		}
		
		// No double-click, do nothing
	}
	
	
	/**
	 *  Used to select a piece for moving or place a piece from the bullpen.
	 *  
	 *  Only used in Puzzle levels and sometimes in the Builder.
	 *  
	 *  @param me  the initiating mouse press
	 */
	@Override
	public void mousePressed(MouseEvent me)
	{
		PieceView pv = bullpenView.getSelectedPiece();

		// Determine row and column of click
		int col = me.getX() / BoardView.SQUARE_SIZE;
		int row = me.getY() / BoardView.SQUARE_SIZE;

		// If nothing is selected in the bullpen
		if(pv == null)
		{
			// Then must be trying to select piece on the board
			// If it is possible to select piece at square (row, col)
			if(boardModel.canSelect(row, col))
			{
				boardView.selectPiece(row, col);
				boardModel.setActiveSource(boardView.getActiveDragging().getModel().getLocation());
				pv = boardView.getActiveDragging();
				PieceContainer piece = pv.getModel();
				boardModel.setActiveDragged(piece);
				
				// If nothing is selected, release any dragged objects and leave
				if(piece == null || pv == null)
				{
					boardModel.setActiveSource(null);
					boardView.setActiveDragging(null);
					boardModel.setActiveDragged(null);
					return ;
				}
				
				// Notify Board that there is a Piece on it
				boardView.setActiveLocation(me.getPoint());
				
				boardView.repaint();
			}
			// Else nothing
			return ;
		}

		// Else attempt to add Piece to Board
		BullpenToBoardMove move = new BullpenToBoardMove(view, pv.getModel(), new Point(row, col));
		if(move.doMove())  // If move is successful
		{
			// Then record it and reset Board and Bullpen's active/selection
			boardView.setActiveDragging(null);
			boardView.setActiveLocation(null);
			bullpenView.setSelectedPiece(null);
			pv.getModel().setIsSelected(false);
			UndoManager.instance().recordMove(move);
			view.modelUpdated();
		}
	}
	
	
	/**
	 *  Used to place a piece on the board.
	 *  
	 *  Handles either a board-to-board move or a board-to-bullpen move
	 * 
	 *  @param me  the initiating mouse release
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
		
		// Attempt to add Piece to Board or remove it from the Board, depending on
		// if the release event occurred outside of the Board
		IMove move;
		if(col < 0 || row < 0)
		{
			// Then remove it
			move = new BoardToBullpenMove(game, view, piece);
		}
		else
		{
			// Add it
			move = new BoardToBoardMove(view, piece,
					boardModel.getActiveSource(), new Point(row, col));
		}
		
		// Attempt move
		if(move.doMove())  // If move is successful
		{
			// Then record it and reset Board and Bullpen's active/selection
			boardView.setActiveDragging(null);
			boardView.setActiveLocation(null);
			UndoManager.instance().recordMove(move);
			
			// Clear redo stack
			RedoManager.instance().clearMove();
		}
		else
		{
			// Send the Piece back
			boardModel.returnPiece();
			boardView.returnPiece();
			boardView.setActiveDragging(null);
			boardView.setActiveLocation(null);
			boardModel.setActiveDragged(null);
			boardModel.setActiveSource(null);
		}
		
		// Update display
		view.modelUpdated();
	}
	
	
	/**
	 *  Used to move a piece selected from the board.
	 * 
	 *  @param me  the initiating drag event
	 */
	@Override
	public void mouseDragged(MouseEvent me)
	{
		PieceView pv = boardView.getActiveDragging();
		if (pv == null){
			PieceContainer piece = bullpenModel.getPieceSelected(); 
			PieceView selectedPV = bullpenView.getSelectedPiece();
			
			// If nothing is selected, leave
			if(piece == null)
			{
				return ;
			}
			
			// Notify Board that there is a Piece on it
			boardView.setActiveDragging(selectedPV);
			java.awt.Point newLocation = me.getPoint();
			newLocation.x = newLocation.x - 14;
			newLocation.y = newLocation.y - 14;
			boardView.setActiveLocation(newLocation);
			
			boardView.repaint();
		}
		
		else {
			// Notify Board that the Piece has changed locations
			java.awt.Point newLocation = me.getPoint();
			newLocation.x = newLocation.x - 14;
			newLocation.y = newLocation.y - 14;
			boardView.setActiveLocation(newLocation);
			
			boardView.repaint();	
		}
	}
}
