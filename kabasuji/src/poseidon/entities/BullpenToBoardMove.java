package poseidon.entities;

import poseidon.common.view.ILevelView;
import poseidon.common.view.PieceView;

/**
 * Moving a piece from bullpen to board.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class BullpenToBoardMove implements IMove{
	/** The level, containing the score, bullpen, and board. */
	LevelModel game;
	/** The piece being moved. */
	PieceContainer piece;
	/** The location on the board the piece's anchor point is being moved to. */
	Point location;
	/** The GUI representation of the Level. */
	ILevelView view;
	/** The color represenation of the piece being moved. */
	PieceView draggedPiece;
	/** The index of the piece in the bullpen. */
	int index;
	
	
	/**
	 *  Constructor.
	 * @param game  the Level
	 * @param view  the GUI of the Level
	 * @param piece  Piece to place on Board
	 * @param location  location Piece is intended to be moved to
	 */
	public BullpenToBoardMove(ILevelView view, PieceContainer piece, Point location) {
		this.game = view.getModel();
		this.piece = piece;
		this.location = location;
		this.view = view;
		this.draggedPiece = view.getBullpen().getSelectedPiece();
		this.index = view.getBullpen().getModel().getPieces().indexOf(piece);
	}
	
	
	/**
	 *  Move is validated according to game mode.
	 *  
	 *  Valid if Piece is entirely on playable Squares and any game-type-specific logic.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	public Boolean isValid() {
		
		// Check that limit has not been met
		if(game.getLimit() <= 0)
		{
			return false;
		}
		
		// Check game-type-specific logic
		if(game.getBoard().isValid(piece, location))
		{
			// Then valid move
			return true;
		}
		
		// Else invalid move
		return false;
	}
	
	
	/**
	 *  Places the Piece from the Bullpen to Board at location.
	 */
	public Boolean doMove() {
		
		if(isValid())
		{
			// Inform piece of new location
			piece.setLocation(location);
			
			// Add piece to board
			game.getBoard().addPiece(piece);
			view.getBoard().addPiece(draggedPiece);
			
			// Remove piece from bullpen
			// TODO fix this when we fix Bullpen remove piece
			boolean successRemove = view.getBullpen().getModel().removePiece(piece);  
			
			// If remove was unsuccessful
			if(!(successRemove))
			{
				// Then nothing selected in the bullpen
				view.getBullpen().setSelectedPiece(null);
				
				return true;
			}
			else
			{
			view.getBullpen().removePiece(draggedPiece);
			view.getBullpen().setSelectedPiece(null);

			// Decrease moves remaining by 1 (if applicable)
			game.decrementLimit();
			
			return true;
		}}
		
		return false;
	}
	
	/**
	 *  Returns the piece to the bullpen.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	public Boolean undoMove() {
		if (draggedPiece != null && game.getBoard().canEdit()) {
			game.getBoard().removePiece(piece);
			view.getBoard().removePiece(draggedPiece);
			piece.setLocation(new Point(-1, -1));
			view.getBullpen().getModel().addPieceAt(piece, index);
			view.getBullpen().addPieceAt(draggedPiece, index);
			view.getBullpen().setSelectedPiece(draggedPiece);
			piece.setIsSelected(true);
			game.incrementLimit();
			return true;
		}
		return false;
	}
}
