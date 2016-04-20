package poseidon.entities;

public class PuzzleLevel extends LevelModel {
	int allottedMoves, remainingMoves;

	PuzzleLevel(int allottedMoves, String levelName, Bullpen bullpen, Board board, Boolean isCustom) {
		super(bullpen, board, PUZZLE, levelName, isCustom);
		this.allottedMoves = allottedMoves;			
	}

	void resetMoves() {
		//TODO: Change return value
	}
	
	void decrementMoves () {
		//TODO: Change return value
	}
	
	void initialize() {
		//TODO: Change return value
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
		return allottedMoves;
	}
}
