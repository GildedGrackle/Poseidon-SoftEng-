package poseidon.entities;

public class ReleaseSquare extends Square{
	ReleaseNumber number;					//type releasenumber. consists of number and color
	Boolean isHint;
	
	ReleaseSquare(Boolean isFilled, ReleaseNumber number) {
		super(isFilled);
		this.number = number;
		isHint = false;						//Set to false as default
	}
	
	void fillSquare() {
		this.isFilled = true;
		
	}
	
	void makeHint() {
		this.isHint = true;
	}

}
