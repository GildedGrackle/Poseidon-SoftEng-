package poseidon.entities;

/**
 * Manages the numbers and colors withing a release level squares.
 * @author Natalia
 *
 */
public class ReleaseNumber {
	public static final int RED = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	
	
	int number;
	int color;							
	
	ReleaseNumber(int number, int color) {
		this.number = number;
		this.color = color;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getColor() {
		return color;
	}
	
	boolean setNumber(int num) throws IllegalArgumentException{
		if(num >= 1 && num <=6) {
			number = num;
			return true;
		}
		return false;
	}
	
	boolean setColor(int col) {
		if(col >= 1 && col <=3) {
			color = col;
			return true;
		}
		return false;
	}

}
