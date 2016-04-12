package poseidon.builder.entity;

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
}
