package poseidon.entities;

import poseidon.player.view.LevelView;

/**
 *  The base class of a Kabasuji Level.
 *  
 *  Actual Levels are implemented as subclasses of this class.
 *  
 * @author Natalia
 * @author Alex Titus
 */
public abstract class LevelModel {
	
	/** The number used to represent Puzzle levels. */
	public static final int PUZZLE = 1;
	/** The number used to represent Lightning levels. */
	public static final int LIGHTNING = 2;
	/** The number used to represent Release levels. */
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
	 *  
	 *  @param bullpen  the model of this Level's Bullpen
	 *  @param board  the model of this Level's Board
	 *  @param gameMode  the type of Level this is
	 *  @param levelName  the name of this Level
	 *  @param isCustom  true if the Level is user-created
	 */
	public LevelModel (Bullpen bullpen, Bullpen infinite, Board board, int gameMode, String levelName, Boolean isCustom) {
		this.levelName = levelName;
		this.playableBullpen = bullpen;
		this.infiniteBullpen = infinite;
		this.gameMode = gameMode;
		this.board = board;
		this.isCustom = isCustom;
	}
	
	
	void saveLevel () {
		//TODO: change return value
	}
	
	void addToGame () {
		//TODO: change return value
	}
	
	/** 
	 *  Start the level in a game-type-specific way.
	 *  
	 *  Given the rendering object so that if the implementing level needs
	 *  to create game-type-specific controllers that can update the display
	 *  they (the controllers) can do so.
	 * 
	 *  @param view  the rendering object
	 */
	public abstract void initialize(LevelView view);
	
	/** 
	 *  Start the level in the builder.
	 *  
	 *  Should set the moves in such a way that moves can always be made.
	 * 
	 *  @param view  the rendering object
	 */
	public abstract void builderInitialize();
	
	/** @return  Whether the player reached 3 stars. */
	abstract Boolean hasWon();
	
	/** @return  Whether the player can move to the next level. */
	abstract Boolean hasPassed();
	
	/** @return  The current score on this level. */
	abstract int calculateScore();
	

	/** 
	 *  Provides the game-type-specific limit for play.
	 *  
	 *  Override this in level subclasses to provide the current remaining move or time limit.
	 */
	public abstract int getLimit();
	
	
	/** 
	 *  Provides the game-type-specific allocated limit for play.
	 *  
	 *  Override this in level subclasses to provide the allocated move or time limit.
	 */
	public abstract int getMaxLimit();
	
	
	/**
	 *  Decreases the moves remaining by 1, if applicable.
	 *  
	 *  Override this in level subclasses to decrease the remaining move limit, if it exists.
	 */
	public abstract void decrementLimit();
	
	
	/**
	 *  Increase the moves remaining by 1, if applicable.
	 *  
	 *  Override this in level subclasses to increase the remaing move limit, if it exists.
	 */
	public abstract void incrementLimit();
	
	
	/**
	 *  Sets the game-type-specific allotted limit.
	 *  
	 *  Override this in level subclasses to set the maximum move or time limit.
	 *  @param newLimit  the new limit
	 */
	public abstract void setMaxLimit(int newLimit);
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The name of the level. */
	public String getLevelName()
	{
		return levelName;
	}
	/** @return  The state of the level's infinite bullpen. */
	public Bullpen getInfiniteBullpen()
	{
		return infiniteBullpen;
	}
	/** @return  The state of the level's playable bullpen. */
	public Bullpen getPlayableBullpen()
	{
		return playableBullpen;
	}
	/** @return  The current score on this level (not all-time score). */
	public int getScore()
	{
		return score;
	}
	/** @return  Number indicating what kind of level this is. */
	public int getGameMode()
	{
		return gameMode;
	}
	/** @return  The state of the level's board. */
	public Board getBoard()
	{
		return board;
	}
	/** @return  Indicator of whether this level is custom-made. */
	public Boolean getIsCustom() {
		return isCustom;
	}

}
