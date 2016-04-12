package poseidon.builder.entity;

public class BuilderModel {
	BuilderModel instance;
	LevelContainer buildingLevel;
	
	BuilderModel (LevelContainer level) {
		this.buildingLevel = level;
	}
	
	BuilderModel instance () {
		return null;							//TODO: change return value
	}
}
