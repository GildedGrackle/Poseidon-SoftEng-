package poseidon.entities;

import poseidon.common.view.PieceView;
import poseidon.player.view.LevelView;

/**
 * Moving a piece from bullpen to board.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class BullpenToBoardMove implements IMove{
	LevelModel game;
	PieceContainer piece;
	Point location;
	LevelView view;
	PieceView draggedPiece;
	
	
	/**
	 *  Constructor.
	 * @param game  the Level
	 * @param view  the GUI of the Level
	 * @param piece  Piece to place on Board
	 * @param location  location Piece is intended to be moved to
	 */
	public BullpenToBoardMove(LevelModel game, LevelView view, PieceContainer piece, Point location) {
		this.game = game;
		this.piece = piece;
		this.location = location;
		this.view = view;
	}
	
	
	/**
	 *  Move is validated according to game mode.
	 *  
	 *  Valid if Piece is entirely on playable Squares and any game-type-specific logic.
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
			piece.setLocation(location);
			game.getBoard().addPiece(piece);
			
			draggedPiece = view.getBullpen().getSelectedPiece();
			view.getBoard().addPiece(draggedPiece);
			game.getPlayableBullpen().removePiece(piece);
			view.getBullpen().removePiece(draggedPiece);
			view.getBullpen().setSelectedPiece(null);

			// Decrease moves remaining by 1 (if applicable)
			game.decrementLimit();
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * returns the piece to it's original location.
	 */
	public Boolean undoMove() {
		if (draggedPiece!=null && game.getBoard().canEdit()) {
			piece.setLocation(new Point(-1, -1));
			game.getBoard().removePiece(piece);
			view.getBoard().removePiece(draggedPiece);
			game.getPlayableBullpen().addPiece(piece);
			view.getBullpen().addPiece(draggedPiece);
			view.getBullpen().setSelectedPiece(draggedPiece);
			return true;
		}
		return false;
	}
}
