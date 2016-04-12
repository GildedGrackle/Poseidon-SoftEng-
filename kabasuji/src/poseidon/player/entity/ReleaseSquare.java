package player;

public class ReleaseSquare extends Square{
	ReleaseNumber isNumber;
	Boolean isHint;
	
	ReleaseSquare(Boolean isFilled, ReleaseNumber isNumber) {
		super(isFilled);
		this.isNumber = isNumber;
		isHint = false;						//Set to false as default
	}
}
