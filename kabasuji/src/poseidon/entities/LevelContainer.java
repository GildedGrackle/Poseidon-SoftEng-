package poseidon.entities;

/**
 *  Stores the file-related and persistant game-related information about a level.
 *  
 *  @author Alex Titus
 */
public class LevelContainer {
	/** For file reading. */
	String levelFileName;
	/** Level for placement in level select screen and for game progress. */
	int levelNumber;
	/** The level associated with this container. */
	LevelModel level;
	/** If the level has been modified (for Level Builder). */
	Boolean isChanged;
	/** The high score for the level associated with this container. */
	int score;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param levelFileName  name of the file containing the Level's data
	 *  @param lvlnum  Level number, for display on the Level Select screen
	 *  @param level  the Level associated with this container
	 *  @param score  the highest score attained on the Level
	 */
	public LevelContainer (String levelFileName, int lvlnum, LevelModel level, int score) {
		this.levelFileName = levelFileName;
		this.levelNumber = lvlnum;
		this.level = level;
		this.isChanged = false;				//Set to false by default
		this.score = score;
	}

	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return  The file name for this level. */
	public String getLevelFileName()
	{
		return levelFileName;
	}
	/** @return  The number of this level. */
	public int getLevelNumber()
	{
		return levelNumber;
	}
	/** @return  The level contained by this object. */
	public LevelModel getLevel()
	{
		return level;
	}
	/** @return  Whether the level has been modified (in the Builder). */
	public Boolean getIsChanged()
	{
		return isChanged;
	}
	/** @return  The high score for this level. */
	public int getScore()
	{
		return score;
	}
	/**
	 *  Sets the file name for the level contained by this object.
	 *  
	 *  @param levelFileName  the new filename
	 */
	public void setLevelFileName(String levelFileName)
	{
		this.levelFileName = levelFileName;
	}
	/**
	 *  Sets the level number for the level contained by this object.
	 *  
	 *  @param levelNumber  the new level number
	 */
	public void setLevelNumber(int levelNumber)
	{
		this.levelNumber = levelNumber;
	}
	/**
	 *  Sets the level model to be contained by this object.
	 *  
	 *  @param level  the new level model
	 */
	public void setLevel(LevelModel level)
	{
		this.level = level;
		this.levelFileName = level.getLevelName() + ".xml";
	}
	/**
	 *  Sets whether the level has been modified.
	 *  
	 *  @param isChanged  the new modified status
	 */
	public void setIsChanged(Boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	/**
	 *  Sets the high score for this level.
	 *  
	 *  @param score  the new high score
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
}
