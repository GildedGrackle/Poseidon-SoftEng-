package poseidon.entities;

public class PuzzleSquare extends Square {
	Boolean isHint;
	
	PuzzleSquare(Boolean isFilled) {
		super(isFilled);
		this.isHint = false;					//Set to false as default
	}

}
