package poseidon.entities;

import java.util.ArrayList;

/**
 *  The top-level model for the Level Player.
 *  
 *  Contains the player's progress on unlocking Levels, the Level currently
 *  being played, and all Levels currently part of the game (including
 *  player-made ones).
 *  
 *  @author Natalia Kononenko
 *  @author Alex Titus
 */
public class LevelPlayerModel {
	/** The number of game modes */
	public static final int NUM_GAMEMODES = 3;
	/** The containers for the levels ([gamemode][levelNumber]). */
	ArrayList<ArrayList<LevelContainer>> levels;
	/** Where the player is on each mode: 1 - Puzzle, 2 - Lightning, 3 - Release .
	 *  Zero-based (0 means level 1 is unlocked but not others) */
	int[] currentLevel = new int[NUM_GAMEMODES];
	/** The level being currently played. */
	LevelModel playingLevel;
	XMLHandler xmlHandler;
	
	
	
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
		levels = new ArrayList<ArrayList<LevelContainer>>(NUM_GAMEMODES);  // Gamemodes
		for(int i = 0; i < NUM_GAMEMODES; i++)  // Levels
		{
			levels.add(new ArrayList<LevelContainer>());  // Default size of 10
		}
		xmlHandler = new XMLHandler();
		
		// TODO Probably change the file path, like level folder instead of just root
		
		// Assuming naming convention of gamemode1, gamemode2, ... gamemode5
		
		// Puzzle levels
		for (int i=0; i<5; i++) {
			String filePath = "puzzle"+String.valueOf(i)+".xml";
			PuzzleLevel levelTemp = (PuzzleLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(0).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(0).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Lightning Levels, same process
		for (int i=0; i<5; i++) {
			String filePath = "lightning"+String.valueOf(i)+".xml";
			LightningLevel levelTemp = (LightningLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(1).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(1).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Release Levels, same process again
		for (int i=0; i<5; i++) {
			String filePath = "release"+String.valueOf(i)+".xml";
			ReleaseLevel levelTemp = (ReleaseLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(2).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(2).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
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
	public ArrayList<ArrayList<LevelContainer>> getLevels()
	{
		return levels;
	}
	public XMLHandler getXMLHandler(){
		return xmlHandler;
	}
	
	public void setPlayingLevel(LevelModel newLevel)
	{
		playingLevel = newLevel;
	}
	


}
