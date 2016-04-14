package poseidon.entities;

import java.util.Set;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelModel;
import poseidon.entities.Square;

public class ReleaseLevel extends LevelModel{
	int allottedPieces, piecesUsed;
	Set redNumbers, greenNumbers, yellownumbers;
	ReleaseLevel(int allottedPieces, Board board, Bullpen bullpen, String name, Boolean isCustom){
		super(bullpen, board, RELEASE, name, isCustom);
		this.allottedPieces = allottedPieces;
	}
	
	void markNumber(int color, int number, Square square) {	 //Not sure
		//TODO: Change return value
	}
	
	void resetCollected () {
		//TODO: Change return value
	}
	
	void decrementPieces() {
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
