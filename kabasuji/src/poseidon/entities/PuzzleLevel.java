package poseidon.entities;

/**
 *  Implementation of LevelModel for Puzzle Levels in Kabasuji.
 *  
 * @author Alex Titus
 */
public class PuzzleLevel extends LevelModel {
	int allottedMoves, remainingMoves;

	PuzzleLevel(int allottedMoves, String levelName, Bullpen bullpen, Board board, Boolean isCustom) {
		super(bullpen, board, PUZZLE, levelName, isCustom);
		this.allottedMoves = allottedMoves;
		initialize();
	}

	void resetMoves() {
		//TODO: Change return value
	}
	
	
	/**
	 *  Decreases the number of moves remaining by 1.
	 */
	@Override
	public void decrementLimit() {
		remainingMoves--;
	}
	
	void initialize() {
		//TODO: Change return value
		remainingMoves = allottedMoves;
	}
	
	Boolean hasWon() {
		return false;							//TODO: Change return value
	}
	
	void reset() {
		//TODO: Change return value
	}


	@Override
	public int getLimit()
	{
		return remainingMoves;
	}
}
