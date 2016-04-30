package poseidon.entities;

import java.util.ArrayList;

/**
 *  The top-level model for the Level Builder.
 *  
 *  Contains the Level currently being played, and all Levels currently saved
 *  (excluding built-in ones).
 *  
 *  @author Natalia Kononenko
 *  @author Alex Titus
 */
public class LevelBuilderModel {
	/** The number of game modes */
	public static final int NUM_GAMEMODES = 3;
	/** Saved levels [gamemode][level] */
	ArrayList<ArrayList<LevelContainer>> savedLevels;
	/** Level currently under construction. */
	LevelContainer buildingLevel;
	
	
	/**
	 *  Constructor.
	 */
	public LevelBuilderModel () {
		initialize();
	}

	
	/**
	 *  Loads all custom levels into savedLevels.
	 */
	void initialize()
	{
		savedLevels = new ArrayList<ArrayList<LevelContainer>>(NUM_GAMEMODES);  // Gamemodes
		for(int i = 0; i < NUM_GAMEMODES; i++)  // Levels
		{
			savedLevels.add(new ArrayList<LevelContainer>());  // Default size of 10
		}
		
		// Assuming naming convention of gamemode1, gamemode2, ... gamemode5
		
		// Puzzle levels
		for (int i=0; i<5; i++) {
			String filePath = "puzzle"+String.valueOf(i)+".xml";
			PuzzleLevel levelTemp = (PuzzleLevel) XMLHandler.loadXML(filePath, true, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				savedLevels.get(0).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				savedLevels.get(0).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Lightning Levels, same process
		for (int i=0; i<5; i++) {
			String filePath = "lightning"+String.valueOf(i)+".xml";
			LightningLevel levelTemp = (LightningLevel) XMLHandler.loadXML(filePath, true, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				savedLevels.get(1).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				savedLevels.get(1).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Release Levels, same process again
		for (int i=0; i<5; i++) {
			String filePath = "release"+String.valueOf(i)+".xml";
			ReleaseLevel levelTemp = (ReleaseLevel) XMLHandler.loadXML(filePath, true, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				savedLevels.get(2).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				savedLevels.get(2).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
	}
				/*********************
				 *  Getter & Setter  *
				 *********************/
	/** @return  The level currently being built. */
	public LevelContainer getBuildingLevel()
	{
		return buildingLevel;
	}
	/**
	 *  Sets the level currently being built.
	 *  
	 *  @param buildingLevel  the new level
	 */
	public void setBuildingLevel(LevelContainer buildingLevel)
	{
		this.buildingLevel = buildingLevel;
	}
	/** @return  The LevelContainer structure, containing data about all of the loaded levels. */
	public ArrayList<ArrayList<LevelContainer>> getSavedLevels()
	{
		return savedLevels;
	}

	
}
