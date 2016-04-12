package player;

public class LevelContainer {
	String levelFileName;
	int inGame, levelNumber;
	
	LevelContainer (String name, int inGame, int number) {
		this.levelFileName = name;
		this.inGame = inGame;
		this.levelNumber = number;
	}
}
