package poseidon.entities;

import poseidon.common.view.ILevelView;
import poseidon.common.view.ITopView;
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
	/** The top-level GUI object, for if/when a game is won. */
	ITopView application;
	/** The representation of the board being changed. */
	ILevelView view;
	/** Original location of the piece. */
	Point from;
	/** Possible new location of the piece. */
	Point to;
	/** The piece being moved. */
	PieceContainer piece;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param application  the top-level GUI object, for if/when a game is won
	 *  @param view  the GUI of the board being modified
	 *  @param piece  the piece being moved
	 *  @param from  original location of the piece
	 *  @param to  possible new location of the piece
	 */
	public BoardToBoardMove(ITopView application, ILevelView view, PieceContainer piece, Point from, Point to) {
		this.application = application;
		this.from = from;
		this.to = to;
		this.piece = piece;
		this.view = view;
	}
	
	
	/**
	 *  Move is valid if the limit still allows for moves, the location isn't the same,
	 *  and the game-type-specific logic allows for it.
	 *  
	 *  @return  Indicator of whether move is valid.
	 */
	public Boolean isValid() {
		// Check that limit has not been met
		if(view.getModel().getLimit() <= 0)
		{
			return false;
		}

		// Check if moving to same spot
		// This is invalid so that manually returning a piece
		// doesn't cost a move
		if(from.equals(to))
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
	 *  Places the piece onto the board at point to.
	 *  
	 *  @return  Indicator of whether move was successful.
	 */
	public Boolean doMove() {

		if(isValid())
		{
			piece.setLocation(to);
			view.getBoard().getBoard().addPiece(piece);

			// Decrease moves remaining by 1 (if applicable)
			view.getModel().decrementLimit();
			view.getModel().updateScore();
			view.getModel().checkIfWon(application);
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * @return  False - Board to board moves are only possible in Puzzle, where moves are un-undoable.
	 */
	public Boolean undoMove() {
		return false;
	}
}
