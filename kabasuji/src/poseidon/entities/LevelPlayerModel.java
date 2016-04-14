package poseidon.entities;

public class LevelPlayerModel {
	int[] currentLevel = new int[3];	//Where the player is on each mode
	LevelModel playingLevel;
	LevelPlayerModel instance;
	
	LevelPlayerModel (int[] current, LevelModel playing) {
		this.currentLevel = current;
		this.playingLevel = playing;
	}
	
	LevelPlayerModel instance() {
		return null;							//TODO: Change return value
	}
}
