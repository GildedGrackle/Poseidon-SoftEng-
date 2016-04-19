package poseidon.entities;

public class LevelContainer {
	String levelFileName;  // For file reading
	int inGame;  // TODO If it is one of the original 15, custom, or not in the game but saved
	int levelNumber;  // Level for placement in level select screen and for game progress
	LevelModel level;  // The level associated with this container
	Boolean isChanged;  // If the level has been modified (for Level Builder)
	int score;  // Added here to simplify LevelSelect display
	
	public LevelContainer (String levelFileName, int inGame, int lvlnum, LevelModel level, int score) {
		this.levelFileName = levelFileName;
		this.inGame = inGame;
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
	public int getInGame()
	{
		return inGame;
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
	public void setInGame(int inGame)
	{
		this.inGame = inGame;
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
