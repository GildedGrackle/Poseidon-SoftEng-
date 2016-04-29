package poseidon.entities;

/**
 *  Handles performing, recording, and undoing of making a Square unplayable.
 *  
 *  @author Natalia?
 *  @author Alex Titus
 */
public class MarkUnplayableSquareMove implements IMove{
	/**	The Board. */
	Board board;
	/** The (row, col) coordinates of the Square in question. */
	Point location;
	/** The type of level being built. */
	int gameMode;
	/** The square that was replaced. */
	Square replaced;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param board  the board being modified
	 *  @param location  the (row, col) coordinates of the square being modified
	 *  @param gameMode  the type of level being built
	 */
	public MarkUnplayableSquareMove(Board board, Point location, int gameMode) {
		this.board = board;
		this.location = location;
	}
	
	
	/**
	 *  Move is valid if the square in question is an playable square and in Builder.
	 *  
	 *  @return  Indicator of whether move is valid.
	 */
	public Boolean isValid() {
		return board.getSquare(location.getRow(), location.getCol()).getType() > 0 && board.canEdit();
	}
	
	
	/**
	 *  Performs the move, after it is complete the square at location will be a
	 *  unplayable square.
	 *  
	 *  @return  Indicator of whether the move was successful.
	 */
	public Boolean doMove() {
		if(isValid())
		{
			// Set square
			replaced = board.setSquare(location, new NonplayableSquare());
			return true;
		}
		return false;
	}
	
	
	/**
	 *  Reverses the action of this move, returning the previously replaced square back to the play area.
	 *  
	 *  @return  Indicator of whether the move was successful.
	 */
	public Boolean undoMove() {
		board.setSquare(location, replaced);
		return true;
	}
}
