package poseidon.entities;

public class PuzzleLevel extends LevelModel {
	int allottedMoves, remainingMoves;

	PuzzleLevel(int allottedMoves, String levelName, Bullpen bullpen, Board board) {
		super(levelName, bullpen, PUZZLE, board);
		this.allottedMoves = allottedMoves;			
	}

}
