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
 * @author Natalia
 */
public class ReleaseLevel extends LevelModel{
	int allottedPieces, movesRemaining;
	Set<Integer> redNumbers, greenNumbers, yellownumbers;
	
	
	/**
	 *  Constructor.
	 *  
	 *  TODO ReleaseLevel constructor documentation
	 *  @param allottedPieces
	 *  @param name
	 *  @param bullpen
	 *  @param infinite
	 *  @param board
	 *  @param isCustom
	 */
	ReleaseLevel(int allottedPieces, String name, Bullpen bullpen, Bullpen infinite, Board board, Boolean isCustom){
		super(bullpen, infinite, board, RELEASE, name, isCustom);
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
	
	/**
	 * Checks whether the player has achieved a perfect score.
	 */
	Boolean hasWon() {
		if (calculateScore() == 3) {return true;}
		return false;
	}
	
	void reset() {
		//TODO: Change return value
	}

	@Override
	public int getLimit()
	{
		return allottedPieces;
	}

	/**
	 * Checks whether the player is eligible to move to the next level.
	 */
	Boolean hasPassed() {
		if(calculateScore() > 0) { return true; }
		return false;
	}

	/**
	 * Checks the amount of stars the player has reached in the game.
	 * 
	 */
	int calculateScore() {
		int stars = 0;
		boolean red, yellow, green;
		red = trackRelease(1);
		yellow = trackRelease(2);
		green = trackRelease(3);
		
		if(red) {stars+=1;}
		if(yellow) {stars+=1;}
		if(green) {stars+=1;}
		
		return stars;
	}
	
	
	/**
	 * Helper function that determines whether an entire set of a specific color was covered.
	 * @param color
	 * @return
	 */
	private boolean trackRelease (int color) {
		boolean [] checkSet = new boolean[6];
		boolean passed = true;
		Square [] [] playArea = new Square [board.getRows()] [board.getCols()];
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getCols();j++) {
				if (playArea[i][j].isFilled() && playArea[i][j] instanceof ReleaseSquare ) {
					if(playArea[i][j].getReleaseNumber().getColor() == color) {
						checkSet[playArea[i][j].getReleaseNumber().getNumber()] = true;
					}
					
				}
			}
		}
		for (int k=0;k<checkSet.length;k++) {
			if (checkSet[k] == false) { passed = false; }
		}
		
		return passed;
	}
}
