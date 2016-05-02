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
	/** For easy resetting of the prior board state */
	Square[][] before;
	
	
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
		this.before = new Square[Board.MAXROWS][Board.MAXCOLS];
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				before[i][j] = game.getBoard().getSquare(i, j);
			}
		}
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
			view.getBullpen().getModel().removePiece(piece);
			view.getBullpen().getModel().setPieceSelected(null);
			view.getModel().updateScore();
			view.getModel().checkIfWon();

			// Decrease moves remaining by 1 (if applicable)
			game.decrementLimit();

			return true;
		}
		
		return false;
	}
	
	/**
	 *  Returns the piece to the bullpen.
	 *  
	 *  @return  Indicator of whether operation completed successfully.
	 */
	public Boolean undoMove() {
		
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				game.getBoard().setSquare(new Point(i, j), before[i][j]);
			}
		}
		piece.setLocation(new Point(-1, -1));
		for(int i = 0; i < view.getBullpen().getModel().getSize(); i++)
		{
			if(piece.equals(view.getBullpen().getModel().getPiece(i)))
			{
				PieceContainer newSelected = view.getBullpen().getModel().getPiece(i);
				view.getBullpen().getModel().setPieceSelected(newSelected);
				newSelected.setIsSelected(true);
				break;
			}
		}

		game.incrementLimit();
		return true;
	}
}
