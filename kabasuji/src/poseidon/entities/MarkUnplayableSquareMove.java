package poseidon.entities;

/**
 *  Handles performing, recording, and undoing of making a Square playable.
 *  
 *  @author
 * @author Alex Titus
 */
public class MarkUnplayableSquareMove implements IMove{
	/**	The Board. */
	Board board;
	/** The (row, col) coordinates of the Square in question. */
	Point location;
	
	public MarkUnplayableSquareMove(Board board, Point location) {
		this.board = board;
		this.location = location;
	}
	
	
	/**
	 *  Move is valid if the Square in question is an UnplayableSquare.
	 */
	public Boolean isValid() {
		return board.getSquare(location.getRow(), location.getCol()).getType() > 0;
	}
	
	
	
	public Boolean doMove() {
		if(isValid())
		{
			// TODO figure out how to replace Square with correct class of Square
//			board.setSquare(location, new NonplayableSquare()); ??
			return true;
		}
		return false;
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}
