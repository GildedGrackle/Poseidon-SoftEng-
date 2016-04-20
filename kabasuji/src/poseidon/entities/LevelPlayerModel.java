package poseidon.entities;

import java.util.ArrayList;

public class LevelPlayerModel {
	// Where the player is on each mode: 1 - Puzzle, 2 - Lightning, 3 - Release
	// Zero-based (0 means level 1 is unlocked but not others)
	int[] currentLevel = new int[3];
	LevelModel playingLevel;  // The level being currently played
	LevelContainer[][] levels;  // The containers for the levels ([gamemode][levelNumber])
	
	
	/**
	 *  Constructor
	 * @param current  designates player progress in each mode: 1 - Puzzle, 2 - Lightning, 3 - Release
	 * @param playing  reference to level currently being played; can be null if no level is being played
	 */
	public LevelPlayerModel (int[] current, LevelModel playing) {
		this.currentLevel = current;
		this.playingLevel = playing;
		
		initializeContainers();
	}
	
	
	/**
	 *  Gets the information about each Level to construct LevelContainer
	 */
	public void initializeContainers()
	{ 
		levels = new LevelContainer[3][5];
		XMLHandler xmlHandler = new XMLHandler();
		
		// TODO Probably change the file path, like level folder instead of just root
		
		// Assuming naming convention of gamemode1, gamemode2, ... gamemode5
		
		// Puzzle levels
		for (int i=0; i<5; i++) {
			String filePath = "puzzle"+String.valueOf(i)+".xml";
			PuzzleLevel levelTemp = (PuzzleLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels[0][i] = new LevelContainer(null, 0, i, null, 0);
			} else {
				levels[0][i] = new LevelContainer(filePath, 0, i, levelTemp, 0);
			}
		}
		
		// Lightning Levels, same process
		for (int i=0; i<5; i++) {
			String filePath = "lightning"+String.valueOf(i)+".xml";
			LightningLevel levelTemp = (LightningLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels[1][i] = new LevelContainer(null, 0, i, null, 0);
			} else {
				levels[1][i] = new LevelContainer(filePath, 0, i, levelTemp, 0);
			}
		}
		
		// Release Levels, same process again
		for (int i=0; i<5; i++) {
			String filePath = "release"+String.valueOf(i)+".xml";
			ReleaseLevel levelTemp = (ReleaseLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels[2][i] = new LevelContainer(null, 0, i, null, 0);
			} else {
				levels[2][i] = new LevelContainer(filePath, 0, i, levelTemp, 0);
			}
		}
	
	}
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public LevelModel getPlayingLevel()
	{
		return playingLevel;
	}
	public int[] getCurrentLevel()
	{
		return currentLevel;
	}
	public LevelContainer[][] getLevels()
	{
		return levels;
	}
	public void setPlayingLevel(LevelModel newLevel)
	{
		playingLevel = newLevel;
	}

}
