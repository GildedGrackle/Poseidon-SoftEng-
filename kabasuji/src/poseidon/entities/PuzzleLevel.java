package poseidon.entities;

import poseidon.player.view.LevelView;

/**
 *  Implementation of LevelModel for Puzzle levels in Kabasuji.
 *  
 * @author Alex Titus
 * @author Natalia
 */
public class PuzzleLevel extends LevelModel {
	/** The number of moves to start the level with. */
	int allottedMoves;
	/** The number of moves remaining in this level. */
	int remainingMoves;

	
	/**
	 *  Constructor.
	 *  
	 *  @param allottedMoves  the maximum number of moves allowed
	 *  @param levelName  the displayed name of this level
	 *  @param bullpen  the bullpen used in this level
	 *  @param infinite  the infinite bullpen
	 *  @param board  the board used in this level
	 *  @param isCustom  indicator whether level is custom-made by user
	 */
	public PuzzleLevel(int allottedMoves, String levelName, Bullpen bullpen, Bullpen infinite, Board board, Boolean isCustom, Boolean isAddedToPlayer) {
		super(bullpen, infinite, board, PUZZLE, levelName, isCustom, isAddedToPlayer);
		this.allottedMoves = allottedMoves;
	}

	
	/**
	 *  TODO documentation PuzzleLevel.resetMoves
	 */
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
	
	
	/**
	 *  Decreases the number of moves remaining by 1.
	 */
	@Override
	public void incrementLimit() {
		remainingMoves++;
	}
	
	
	/**
	 *  Sets the remaining moves, which signals the start of the game.
	 * 
	 *  @param view  the GUI representation of this, unused
	 */
	public void initialize(LevelView view) {
		remainingMoves = allottedMoves;
	}
	
	/**
	 * Checks whether the player has achieved a perfect score.
	 */
	Boolean hasWon() {
		if (calculateScore() == 3) {return true;}
		return false;
	}
	
	/**
	 * Checks whether the player is eligible to move to the next level.
	 */
	Boolean hasPassed() {
		if(calculateScore() > 0) { return true; }
		return false;
	}
	
	void reset() {
		//TODO: Change return value
	}

	/**
	 * Checks the amount of stars the player has reached in the game.
	 * 
	 * The check is performed according to amount of pieces on bullpen, 
	 * and also based on whether there is a piece being dragged.
	 */
	int calculateScore() {
		int stars = 0;
		if (board.getActiveDragged()!=null) {stars-=1;}
		if(playableBullpen.getSize() == 2) { stars+=1; }
		else if (playableBullpen.getSize() == 1) { stars+=2; }
		else if(playableBullpen.getSize() == 0) { stars+=3; }
		
		if (stars <=0) {return 0;}
		else return stars;
	}
	
	
	/** @return  the number of moves remaining */
	@Override
	public int getLimit()
	{
		return remainingMoves;
	}
	
	
	/**
	 *  Sets the allotted move limit.
	 *  
	 *  @param newLimit  the new limit
	 */
	@Override
	public void setLimit(int newLimit)
	{
		allottedMoves = newLimit;
	}
}
