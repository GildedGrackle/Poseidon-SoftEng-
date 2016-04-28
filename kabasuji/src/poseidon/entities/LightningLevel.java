package poseidon.entities;

import javax.swing.Timer;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;

/**
 *  Implementation of LevelModel for Lightning Levels in Kabasuji.
 *  
 * @author Alex Titus
 * @author Natalia
 */
public class LightningLevel extends LevelModel{
	int allottedTime, usedTime;
	Timer timer;

	LightningLevel(int allottedTime, String name, Bullpen bullpen, Bullpen infinite, Board board, Boolean isCustom){
		super(bullpen, infinite, board, LIGHTNING, name, isCustom);
		this.allottedTime = allottedTime;
		this.usedTime = 0;  // TODO does this count up to allotted time or down to 0 from allotted time?
	}
	
	void startTimer() {
		//TODO: Change return value
	}
	
	void stopTimer () {
		//TODO: Change return value
	}
	
	void resetTimer() {
		//TODO: Change return value
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
	void reset() {
		//TODO: Change return value
	}
	
	
	/**
	 *  Doesn't do anything; no moves to decrement.
	 */
	public void decrementLimit()
	{
		return;
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	@Override
	public int getLimit()
	{
		return allottedTime;
	}
	public int getAllottedTime()
	{
		return allottedTime;
	}
	public int getUsedTime()
	{
		return usedTime;
	}
	public void setAllottedTime(int newTime)
	{
		allottedTime = newTime;
	}
	public void setUsedTime(int newTime)
	{
		usedTime = newTime;
	}


}
