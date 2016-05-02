package poseidon.entities;

import java.util.Set;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelModel;
import poseidon.entities.Square;
import poseidon.player.view.LevelView;

/**
 *  Implementation of LevelModel for Release levels in Kabasuji.
 *  
 *  
 * @author Alex Titus
 * @author Natalia
 */
public class ReleaseLevel extends LevelModel{
	/** The number of moves to start the level with. */
	int allottedMoves;
	/** The number of moves remaining in this level. */
	int remainingMoves;
	/** Stores of numbers collected during gameplay. */
	Set<Integer> redNumbers, greenNumbers, yellownumbers;
	
	
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
	public ReleaseLevel(int allottedMoves, String levelName, Bullpen bullpen,
			Bullpen infinite, Board board, Boolean isCustom, Boolean isAddedToPlayer){
		
		super(bullpen, infinite, board, RELEASE, levelName, isCustom, isAddedToPlayer);
		this.allottedMoves = allottedMoves;
	}
	
	/**
	 * Sets the color and number on a release square
	 * @param color   the wished color for the square
	 * @param number  the wished number for the square
	 * @param row
	 * @param col     the position of the square
	 */
	void markNumber(int color, int number, int row, int col) {	 
		Square [] [] area = board.getPlayArea();
		area[row][col].getReleaseNumber().setColor(color);
		area[row][col].getReleaseNumber().setNumber(number);
	}
	
	void resetCollected () {
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
	 *  Sets the allotted move limit.
	 *  
	 *  @param newLimit  the new limit
	 */
	@Override
	public void setMaxLimit(int newLimit)
	{
		allottedMoves = newLimit;
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
	 *  This is achieved by setting remainingMoves to Integer.MAX_VALUE, which
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
	 * 
	 * @return  indicator whether game has been completed
	 */
	Boolean hasWon() {
		if (calculateScore() == 3) {return true;}
		return false;
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
		Square [] [] playArea = super.getBoard().getPlayArea();
		for (int i=0; i<board.getRows();i++) {
			for (int j=0; j<board.getCols();j++) {
				if (playArea[i][j].isFilled() && playArea[i][j] instanceof ReleaseSquare ) {
					if(playArea[i][j].getReleaseNumber() != null){
						if(playArea[i][j].getReleaseNumber().getColor() == color) {
							checkSet[playArea[i][j].getReleaseNumber().getNumber()] = true;
						}
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
