package poseidon.builder.entity;

public class LightningLevel extends LevelModel{
	int allotedTime, usedTime;

	LightningLevel(int alottedTime, String levelName, Bullpen bullpen, Board board) {
		super(levelName, bullpen, LIGHTNING, board);
		this.allotedTime = alottedTime;			
	}

}
