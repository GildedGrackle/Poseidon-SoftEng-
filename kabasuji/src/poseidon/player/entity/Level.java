package player;

public class Level {
	static final int PUZZLE = 1;
	static final int LIGHTNING = 2;
	static final int RELEASE = 3;
	
	Bullpen bullpen;
	Board board;
	int gameMode, score;
	String fileName;
	Boolean isCustom;
	
	Level (Bullpen bullpen, Board board, int gameMode, String name, Boolean isCustom) {
		this.bullpen = bullpen;
		this.board = board;
		this.gameMode = gameMode;
		this.fileName = name;
		this.isCustom = isCustom;
	}
	
	void Initialize() {
		//TODO: Change return value
	}
	
	Boolean hasWon(){
		return false;							//TODO: Change return value
	}
	
	void reset() {
		//TODO: Change return value
	}
}
