package poseidon.entities;

public class LevelBuilderModel {
	LevelContainer buildingLevel;
	
	public LevelBuilderModel (LevelContainer level) {
		this.buildingLevel = level;
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
