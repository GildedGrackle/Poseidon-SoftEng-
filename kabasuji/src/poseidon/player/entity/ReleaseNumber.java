package poseidon.player.entity;

public class ReleaseNumber {
	static final int RED = 1;
	static final int GREEN = 2;
	static final int YELLOW = 3;
	
	int number;
	int color;							
	
	ReleaseNumber(int number, int color) {
		this.number = number;
		this.color = color;
	}

}
