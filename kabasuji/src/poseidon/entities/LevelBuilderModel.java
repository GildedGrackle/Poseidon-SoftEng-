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
 *  @author Jacob
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
	void initialize() {
		// First tier of lists for number of gamemodes
		savedLevels = new ArrayList<ArrayList<LevelContainer>>(NUM_GAMEMODES);
		for(int i = 0; i < NUM_GAMEMODES; i++) {
			savedLevels.add(new ArrayList<LevelContainer>());  // Default size of 10
		}
		
		// Load in the list of filenames, if any, and load each named level file
		String[] listOfNames = XMLHandler.loadFilenames("customFilenames.xml", true);
		if (!(listOfNames == null)) {
			int levelNumber = 0;
			for (String name : listOfNames) {
				LevelModel loadedLevel = XMLHandler.loadXML(name, true, true);
				if(!(loadedLevel == null)) {
					int mode = loadedLevel.getGameMode();
					savedLevels.get(mode-1).add(new LevelContainer(name, levelNumber, loadedLevel, 0));
					levelNumber++;
				}
				
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
