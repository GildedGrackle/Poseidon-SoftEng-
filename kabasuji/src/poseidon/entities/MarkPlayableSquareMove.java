package poseidon.entities;

/**
 *  Handles performing, recording, and undoing of making a square playable.
 *  
 *  @author Natalia
 *  @author Alex Titus
 */
public class MarkPlayableSquareMove implements IMove{
	/**	The Board being modified. */
	Board board;
	/** The (row, col) coordinates of the square in question. */
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
	public MarkPlayableSquareMove(Board board, Point location, int gameMode) {
		this.board = board;
		this.location = location;
		this.gameMode = gameMode;
	}
	
	
	/**
	 *  Move is valid if the square is unplayable and in Builder.
	 *  
	 *  @return  Indicator of whether move is valid.
	 */
	public Boolean isValid() {
		return board.getSquare(location.getRow(), location.getCol()).getType() < 0 && board.canEdit();
	}
	
	
	/**
	 *  Performs the move, after it is complete the square at location will be a
	 *  playable square of game-specific type.
	 *  
	 *  @return  Indicator of whether the move was successful.
	 */
	public Boolean doMove() {
		if(isValid())
		{
			// Create new playable square
			Square newSquare = new PuzzleSquare(false, false);  // Default is PuzzleSquare
			switch(gameMode)
			{
			case LevelModel.LIGHTNING:
				newSquare = new LightningSquare(false);
				break;
			case LevelModel.RELEASE:
				newSquare = new ReleaseSquare(false, null);
				break;
			default:
				break;
			}
			
			// Set the new square
			replaced = board.setSquare(location, newSquare);
			
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