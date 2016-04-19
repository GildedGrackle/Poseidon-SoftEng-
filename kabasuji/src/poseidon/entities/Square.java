package poseidon.entities;

public class Square {
	Boolean isFilled;
	
	Square (Boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	void fill() {
		this.isFilled = true;
	}
	
	void empty () {
		this.isFilled = false;
	}
	
	boolean isFilled () {
		return this.isFilled;
	}
}
