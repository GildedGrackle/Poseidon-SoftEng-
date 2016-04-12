package poseidon.entities;

import javax.swing.Timer;

import poseidon.entities.Board;
import poseidon.entities.Bullpen;

public class LightningLevel extends LevelModel{
	int allottedTime, usedTime;
	Timer timer;

	LightningLevel(int allottedTime, Board board, Bullpen bullpen, String name, Boolean isCustom){
		super(bullpen, board, LIGHTNING, name, isCustom);
		this.allottedTime = allottedTime;
		this.usedTime = usedTime;
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
}
