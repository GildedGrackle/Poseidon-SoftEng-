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

	
	void initialize()
	{
		// TODO LevelBuilderModel initialize
	}
				/*********************
				 *  Getter & Setter  *
				 *********************/
	public LevelContainer getBuildingLevel()
	{
		return buildingLevel;
	}
	public void setBuildingLevel(LevelContainer buildingLevel)
	{
		this.buildingLevel = buildingLevel;
	}

	
}
