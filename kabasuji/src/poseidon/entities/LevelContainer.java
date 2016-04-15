package poseidon.entities;

public class LevelContainer {
	String levelFileName;
	int inGame, levelNumber;
	LevelModel level;
	Boolean isChanged;
	
	LevelContainer (String levelFileName, int inGame, int lvlnum, LevelModel level) {
		this.levelFileName = levelFileName;
		this.inGame = inGame;
		this.levelNumber = lvlnum;
		this.level = level;
		this.isChanged = false;				//Set to false by default
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
}
