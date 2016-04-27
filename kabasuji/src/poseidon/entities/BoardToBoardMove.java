package poseidon.entities;

import poseidon.common.view.PieceView;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.player.view.LevelView;

/**
 * Moving piece from one part of the board to another.
 * Only applicable for puzzle mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class BoardToBoardMove implements IMove{
	/** The representation of the Board being changed. */
	LevelView view;
	/** Original location of the Piece. */
	Point from;
	/** Possible new location of the Piece. */
	Point to;
	/** Piece being moved. */
	PieceContainer piece;
	
	PieceView draggedPiece;
	
	
	public BoardToBoardMove(LevelView view, PieceContainer piece, Point from, Point to) {
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
