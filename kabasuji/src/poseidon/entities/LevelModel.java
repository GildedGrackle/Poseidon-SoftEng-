package poseidon.entities;

/**
 *  The base class of a Kabasuji Level.
 *  
 *  Actual Levels are implemented as subclasses of this class.
 *  
 * @author Natasha Kononenko
 * @author Alex Titus
 */
public abstract class LevelModel {
	public static final int PUZZLE = 1;
	public static final int LIGHTNING = 2;
	public static final int RELEASE = 3;
	
	/** The name of the Level. */
	String levelName;
	/** The container for Pieces used in the Kabasuji Level Builder application. */
	Bullpen infiniteBullpen;
	/** The container for Pieces not on the Board. */
	Bullpen playableBullpen;
	/** Indicates the type of game. */
	int gameMode;
	/** The player's current score on the Level, from 0 to 3, represented as stars. */
	int score; 
	/** The Kabasuji game board. */
	Board board;
	/** Indicates if this Level is built-in or user-created. */
	Boolean isCustom;
	
	
	/**
	 *  Constructor.
	 * @param bullpen  the model of this Level's Bullpen
	 * @param board  the model of this Level's Board
	 * @param gameMode  the type of Level this is
	 * @param levelName  the name of this Level
	 * @param isCustom  true if the Level is user-created
	 */
	public LevelModel (Bullpen bullpen, Board board, int gameMode, String levelName, Boolean isCustom) {
		this.levelName = levelName;
		this.playableBullpen = bullpen;
		this.gameMode = gameMode;
		this.board = board;
		this.isCustom = isCustom;
	}
	
	void SaveLevel () {
		//TODO: change return value
	}
	
	void addToGame () {
		//TODO: change return value
	}
	
	abstract void initialize();
	
	abstract Boolean hasWon();
	
	abstract void reset();
	
	/** 
	 *  Provides the game-type-specific limit for play.
	 *  
	 *  Override this in level subclasses to provide the maximum move or time limit. */
	public abstract int getLimit();
	
	/**
	 *  Decreases the moves remaining by 1, if applicable.
	 *  
	 *  Override this in level subclasses to decrease the move limit, if it exists.
	 */
	public abstract void decrementMoves();
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public String getLevelName()
	{
		return levelName;
	}
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

}
