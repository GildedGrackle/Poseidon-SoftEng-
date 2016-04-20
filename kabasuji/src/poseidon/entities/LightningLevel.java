package poseidon.entities;

import javax.swing.Timer;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;

public class LightningLevel extends LevelModel{
	int allottedTime, usedTime;
	Timer timer;

	LightningLevel(int allottedTime, String name, Bullpen bullpen, Board board, Boolean isCustom){
		super(bullpen, board, LIGHTNING, name, isCustom);
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
	
	Boolean hasWon() {
		return false;							//TODO: Change return value
	}
	
	void reset() {
		//TODO: Change return value
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
