package poseidon.entities;

import poseidon.common.view.ILevelView;

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
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the Level
	 *  @param view  the GUI of the Level
	 *  @param piece  Piece to place on Board
	 *  @param location  location Piece is intended to be moved to
	 */
	public BullpenToBoardMove(ILevelView view, PieceContainer piece, Point location) {
		this.game = view.getModel();
		this.piece = piece;
		this.location = location;
		this.view = view;
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
	 *  
	 *  @return  Indication of operation completion.
	 */
	public Boolean doMove() {
		
		if(isValid())
		{
			// Inform piece of new location
			piece.setLocation(location);
			
			// Add piece to board
			game.getBoard().addPiece(piece);
			
			// Remove piece from bullpen
			// TODO fix this when we fix Bullpen remove piece
			boolean successRemove = view.getBullpen().getModel().removePiece(piece);
			
			// If remove was unsuccessful
			if(!(successRemove))
			{
				// Then something bad happened, deselect everything in bullpen
				// and hope for the best
				view.getBullpen().getModel().setPieceSelected(null);
				
				return false;
			}
			else  // Remove successful
			{
				view.getBullpen().getModel().setPieceSelected(null);
				view.getModel().updateScore();
				// Decrease moves remaining by 1 (if applicable)
				game.decrementLimit();

				return true;
			}
		}
		
		return false;
	}
	
	/**
	 *  Returns the piece to the bullpen.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	public Boolean undoMove() {
		if (game.getBoard().canEdit()) {
			game.getBoard().removePiece(piece);
			piece.setLocation(new Point(-1, -1));
			// TODO just select the new one in the bullpen
//			view.getBullpen().getModel().addPiece(piece);
//			view.getBullpen().addPiece(draggedPiece);
//			view.getBullpen().setSelectedPiece(draggedPiece);
			piece.setIsSelected(true);
			game.incrementLimit();
			return true;
		}
		return false;
	}
}
