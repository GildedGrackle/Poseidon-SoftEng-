package poseidon.builder.entity;

public class ReleaseLevel extends LevelModel {
	int allottedPieces, piecesRemaining, redNumberCaptive, greenNumberCaptive, yellownumberCaptive;

	ReleaseLevel(int allottedPieces, String levelName, Bullpen bullpen, Board board) {
		super(levelName, bullpen, RELEASE, board);
		this.allottedPieces = allottedPieces;			
	}
}
