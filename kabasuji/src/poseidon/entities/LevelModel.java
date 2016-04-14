package poseidon.entities;

public class LevelModel {
	static final int PUZZLE = 1;
	static final int LIGHTNING = 2;
	static final int RELEASE = 3;
	
	
	String levelName;
	Bullpen infiniteBullpen, playableBullpen;
	int gameMode, score; 
	Board board;
	Boolean isCustom;
	
	
	LevelModel (Bullpen bullpen, Board board, int gameMode, String levelName, Boolean isCustom) {
		this.levelName = levelName;
		this.playableBullpen = bullpen; //Not sure, should playable/infinite to be set as the passed bullpen?
		this.gameMode = gameMode;
		this.board = board;
		this.isCustom = isCustom;
	}
	
	void Initialize() {
		//TODO: Change return value
	}
	
	void SaveLevel () {
		//TODO: change return value
	}
	
	void addToGame () {
		//TODO: change return value
	}
	
	Boolean hasWon(){
		return false;							//TODO: Change return value
	}
	
	void reset() {
		//TODO: Change return value
	}
}
