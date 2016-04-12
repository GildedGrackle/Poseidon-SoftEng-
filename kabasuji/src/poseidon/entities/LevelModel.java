package poseidon.entities;

public class LevelModel {
	static final int PUZZLE = 1;
	static final int LIGHTNING = 2;
	static final int RELEASE = 3;
	
	
	String levelName;
	Bullpen infiniteBullpen, playableBullpen;
	int gameMode, score; 
	Board board;
	
	
	LevelModel (String levelName, Bullpen bullpen, int gameMode, Board board ) {
		this.levelName = levelName;
		this.playableBullpen = bullpen; //Not sure, should playable/infinite to be set as the passed bullpen?
		this.gameMode = gameMode;
		this.board = board;
	}
	
	void SaveLevel () {
		//TODO: change return value
	}
	
	void addToGame () {
		//TODO: change return value
	}
}
