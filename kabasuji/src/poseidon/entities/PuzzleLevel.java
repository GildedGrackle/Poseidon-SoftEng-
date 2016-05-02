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
	 *  Start the level in the builder.
	 *  
	 *  Should set the moves in such a way that moves can always be made.
	 *  This is achieved by setting movesRemaining to Integer.MAX_VALUE, which
	 *  should provide enough moves for any single level-building session.
	 * 
	 *  @param view  the rendering object
	 */
	public void builderInitialize()
	{
		remainingMoves = Integer.MAX_VALUE;
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

	/**
	 * Checks the amount of stars the player has reached in the game.
	 * 
	 * The check is performed according to amount of pieces on bullpen, 
	 * and also based on whether there is a piece being dragged.
	 */
	int calculateScore() {
		int nonFilledSquares = 0;
		int stars = 0;
		Square [] [] playArea = new Square [board.getRows()] [board.getCols()];
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getCols();j++) {
				if (!(playArea[i][j].isFilled()) && playArea[i][j] instanceof PuzzleSquare) {
					nonFilledSquares+=1;
				}
			}
		}
		
		if (board.getActiveDragged()!=null) {stars-=1;}
		if (nonFilledSquares <= 12) { stars+=1; }
		if (nonFilledSquares <= 6) { stars+=1; }
		if (nonFilledSquares == 0) { stars+=1; }
		
		if(stars<=0) {return 0;}
		else return stars;
	}
	
	
	/** @return  the number of moves remaining */
	@Override
	public int getLimit()
	{
		return remainingMoves;
	}
	
	
	/** @return  The allotted moves for this level. */
	@Override
	public int getMaxLimit()
	{
		return allottedMoves;
	}
	
	
	/**
	 *  Sets the allotted move limit.
	 *  
	 *  @param newLimit  the new limit
	 */
	@Override
	public void setMaxLimit(int newLimit)
	{
		allottedMoves = newLimit;
	}
}
