package player;

public class PuzzleLevel extends Level {
	int allottedMoves, remainingMoves;
	
	PuzzleLevel(int allottedMoves, Board board, Bullpen bullpen, String name, Boolean isCustom){
		super(bullpen, board, PUZZLE, name, isCustom);
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
}
