package poseidon.entities;

import javax.swing.Timer;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.player.controller.TimeController;
import poseidon.player.view.LevelView;

/**
 *  Implementation of LevelModel for Lightning levels in Kabasuji.
 *  
 * @author Alex Titus
 * @author Natalia
 */
public class LightningLevel extends LevelModel{
	/** The amount of time to start the level with. */
	int allottedTime;
	/** The amount of time remaining in the level. */
	int remainingTime;
	/** Timer for tracking time limit. */
	Timer timer;

	
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
	public LightningLevel(int allottedTime, String levelName, Bullpen bullpen, Bullpen infinite, Board board, Boolean isCustom, Boolean isAddedToPlayer){
		super(bullpen, infinite, board, LIGHTNING, levelName, isCustom, isAddedToPlayer);
		this.allottedTime = allottedTime;
	}
	
	
	/**
	 *  Starts the timer.
	 */
	void startTimer() {
		timer.start();
	}
	
	
	/**
	 *  Stops the timer.
	 */
	public void stopTimer () {
		timer.stop();
	}
	
	
	/**
	 *  Stops the timer and resets remaining time.
	 */
	void resetTimer() {
		timer.stop();
		remainingTime = allottedTime;
	}
	
	
	/**
	 *  Begins the timer that signals the start of the level.
	 *  
	 *  @param view  the GUI representation of this
	 */
	public void initialize(LevelView view) {
		this.remainingTime = allottedTime;
		timer = new Timer(1000, new TimeController(this, view));
		
		startTimer();
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
	 * The check is performed according to amount of unfilled squares, 
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
	
	
	/**
	 *  TODO LightningLevel.reset
	 */
	public void reset() {
		//TODO: Change return value
		resetTimer();
		startTimer();
	}
	
	
	/**
	 *  Decreases the remaining time by one.
	 */
	public void decrementTime()
	{
		remainingTime--;
	}
	
	
	/**
	 *  Doesn't do anything; no moves to decrement.
	 */
	public void decrementLimit()
	{
		return ;
	}
	
	
	/**
	 *  Doesn't do anything; no moves to increment.
	 */
	public void incrementLimit()
	{
		return ;
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The amount of time remaining. */
	@Override
	public int getLimit()
	{
		return remainingTime;
	}
	
	/** @return  The allotted time for this level. */
	int getAllottedTime()
	{
		return allottedTime;
	}
	
	/** @return  Time remaining in this level */
	int getRemainingTime()
	{
		return remainingTime;
	}
	
	/**
	 *  Sets the allotted time.
	 *  
	 *  @param newTime  time to set
	 */
	void setAllottedTime(int newTime)
	{
		allottedTime = newTime;
	}
	
	/**
	 *  Sets the allotted time limit.
	 *  
	 *  @param newLimit  the new limit
	 */
	@Override
	public void setLimit(int newLimit)
	{
		allottedTime = newLimit;
	}


}
