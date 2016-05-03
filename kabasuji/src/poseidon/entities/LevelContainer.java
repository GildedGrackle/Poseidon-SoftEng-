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
	/** Added here to simplify LevelSelect display. */
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
	public String getLevelFileName()
	{
		return levelFileName;
	}
	public int getLevelNumber()
	{
		return levelNumber;
	}
	public LevelModel getLevel()
	{
		return level;
	}
	public Boolean getIsChanged()
	{
		return isChanged;
	}
	public int getScore()
	{
		return score;
	}
	public void setLevelFileName(String levelFileName)
	{
		this.levelFileName = levelFileName;
	}
	public void setLevelNumber(int levelNumber)
	{
		this.levelNumber = levelNumber;
	}
	public void setLevel(LevelModel level)
	{
		this.level = level;
	}
	public void setIsChanged(Boolean isChanged)
	{
		this.isChanged = isChanged;
	}
	public void setScore(int score)
	{
		this.score = score;
	}
}
