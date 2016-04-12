package poseidon.player.entity;

public class LevelPlayerModel {
	int[] currentLevel = new int[3];	//Where the player is on each mode
	Level playingLevel;
	LevelPlayerModel instance;
	
	LevelPlayerModel (int[] current, Level playing) {
		this.currentLevel = current;
		this.playingLevel = playing;
	}
	
	LevelPlayerModel instance() {
		return null;							//TODO: Change return value
	}
}
