package poseidon.entities;

public class LevelPlayerModel {
	int[] currentLevel = new int[3];	//Where the player is on each mode
	LevelModel playingLevel;
	
	public LevelPlayerModel (int[] current, LevelModel playing) {
		this.currentLevel = current;
		this.playingLevel = playing;
	}
	
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public LevelModel getPlayingLevel()
	{
		return playingLevel;
	}

}
