package poseidon.entities;

public abstract class LevelModel {
	public static final int PUZZLE = 1;
	public static final int LIGHTNING = 2;
	public static final int RELEASE = 3;
	
	
	String levelName;
	Bullpen infiniteBullpen, playableBullpen;
	int gameMode, score; 
	Board board;
	Boolean isCustom;
	
	
	public LevelModel (Bullpen bullpen, Board board, int gameMode, String levelName, Boolean isCustom) {
		this.levelName = levelName;
		this.playableBullpen = bullpen; // Correct, should be playable bullpen, infinite can be easily constructed separately
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
	
	/***********************
	 *  Getters & Setters  *
	 ***********************/
	public Bullpen getInfiniteBullpen()
	{
		return infiniteBullpen;
	}
	public Bullpen getPlayableBullpen()
	{
		return playableBullpen;
	}
	public int getScore()
	{
		return score;
	}
	public int getGameMode()
	{
		return gameMode;
	}
	public Board getBoard()
	{
		return board;
	}

	
	// Override this in level subclasses to provided the maximum move or time limit
	public abstract int getLimit();
}
