package poseidon.entities;

/**
 *  Handles performing, recording, and undoing of making a Square playable.
 *  
 *  @author 
 *  @author Alex Titus
 */
public class MarkPlayableSquareMove implements IMove{
	/**	The Board. */
	Board board;
	/** The (row, col) coordinates of the Square in question. */
	Point location;
	
	public MarkPlayableSquareMove(Board board, Point location) {
		this.board = board;
		this.location = location;
	}
	
	
	/**
	 *  Move is valid if the Square is unplayable and in Builder.
	 */
	public Boolean isValid() {
		boolean valid = board.getSquare(location.getRow(), location.getCol()).getType() < 0;
//		valid = valid && isBuilder
		
		return valid;
	}
	
	
	
	public Boolean doMove() {
		if(isValid())
		{
			// TODO figure out how to replace Square with correct class of Square
//			board.setSquare(location, new __Square(false)); ??
			return true;
		}
		return false;
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}