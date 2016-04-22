package poseidon.entities;

import java.util.Set;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelModel;
import poseidon.entities.Square;

/**
 *  Implementation of LevelModel for Release Levels in Kabasuji.
 *  
 *  
 * @author Alex Titus
 */
public class ReleaseLevel extends LevelModel{
	int allottedPieces, movesRemaining;
	Set<Integer> redNumbers, greenNumbers, yellownumbers;
	ReleaseLevel(int allottedPieces, String name, Bullpen bullpen, Board board, Boolean isCustom){
		super(bullpen, board, RELEASE, name, isCustom);
		this.allottedPieces = allottedPieces;
	}
	
	void markNumber(int color, int number, Square square) {	 //Not sure
		//TODO: Change return value
	}
	
	void resetCollected () {
		//TODO: Change return value
	}
	
	
	/**
	 *  Decreases the number of moves remaining by 1.
	 */
	public void decrementLimit() {
		//TODO: Change return value
		movesRemaining--;
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
		return allottedPieces;
	}
}
