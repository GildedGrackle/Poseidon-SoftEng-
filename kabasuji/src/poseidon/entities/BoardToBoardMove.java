package poseidon.entities;

import poseidon.common.view.ILevelView;
import poseidon.common.view.PieceView;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;

/**
 * Moving piece from one part of the board to another.
 * Only applicable for puzzle mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class BoardToBoardMove implements IMove{
	/** The representation of the board being changed. */
	ILevelView view;
	/** Original location of the piece. */
	Point from;
	/** Possible new location of the piece. */
	Point to;
	/** The piece being moved. */
	PieceContainer piece;
	/** Color container for piece being moved. */
	PieceView draggedPiece;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI of the board being modified
	 *  @param piece  the piece being moved
	 *  @param from  original location of the piece
	 *  @param to  possible new location of the piece
	 */
	public BoardToBoardMove(ILevelView view, PieceContainer piece, Point from, Point to) {
		this.from = from;
		this.to = to;
		this.piece = piece;
		this.view = view;
	}
	
	public Boolean isValid() {
		// Check that limit has not been met
		if(view.getModel().getLimit() <= 0)
		{
			return false;
		}

		// Check game-type-specific logic
		if(view.getModel().getBoard().isValid(piece, to))
		{
			// Then valid move
			return true;
		}
		
		// Check if moving to same spot
		// This is invalid so that manually returning a piece
		// doesn't cost a move
		if(from.equals(to))
		{
			return false;
		}

		// Else invalid move
		return false;
	}
	
	
	/**
	 *  Places the Piece onto the Board at Point to.
	 */
	public Boolean doMove() {

		if(isValid())
		{
			piece.setLocation(to);
			view.getBoard().getBoard().addPiece(piece);
			draggedPiece = view.getBoard().getActiveDragging();
			view.getBoard().addPiece(draggedPiece);

			// Decrease moves remaining by 1 (if applicable)
			view.getModel().decrementLimit();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns false since board to board moves are only possible in puzzle, where moves are un-undoable.
	 * 
	 */
	public Boolean undoMove() {
		return false;
	}
}
