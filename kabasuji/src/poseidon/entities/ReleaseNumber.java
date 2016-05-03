package poseidon.entities;

/**
 * Manages the numbers and colors withing a release level squares.
 * @author Natalia
 *
 */
public class ReleaseNumber {
	/** Constant for red numbers. */
	public static final int RED = 1;
	/** Constant for green numbers. */
	public static final int GREEN = 2;
	/** Constant for yellow numbers. */
	public static final int YELLOW = 3;
	
	/** The number stored in the ReleaseSquare. */
	int number;
	/** The color of the number stored in the ReleaseSquare. */
	int color;							
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param number  the number to store
	 *  @param color  the color value to store
	 */
	public ReleaseNumber(int number, int color) {
		this.number = number;
		this.color = color;
	}
	
	/** @return  The number value. */
	public int getNumber() {
		return number;
	}
	
	/** @return  The color value. */
	public int getColor() {
		return color;
	}
	
	/**
	 *  Sets the number value.
	 *  
	 *  @param num  the number to set
	 *  @return  Indication of whether the number was set.
	 */
	boolean setNumber(int num){
		if(num >= 1 && num <=6) {
			number = num;
			return true;
		}
		return false;
	}
	
	
	/**
	 *  Sets the color value.
	 *  
	 *  @param num  the color value to set
	 *  @return  Indication of whether the color was set.
	 */
	boolean setColor(int col) {
		if(col >= 1 && col <=3) {
			color = col;
			return true;
		}
		return false;
	}

}
