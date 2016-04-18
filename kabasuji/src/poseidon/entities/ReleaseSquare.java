package poseidon.entities;

public class ReleaseSquare extends Square{
	ReleaseNumber isNumber;
	Boolean isHint;
	
	
	// TODO need a way to indicate no number
	ReleaseSquare(Boolean isFilled, ReleaseNumber isNumber) {
		super(isFilled);
		this.isNumber = isNumber;
		isHint = false;						//Set to false as default
	}
}
