package poseidon.entities;

/**
 * Manages general squares. Includes puzzle, lightning, release and builder.
 * @author Natalia
 * @author Alex Titus
 */
public abstract class Square {
	/** Whether this square has a piece on it. */
	Boolean isFilled;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param isFilled  whether this square should be initialized as filled
	 */
	Square (Boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	
	/**
	 *  Indicates that this square has a piece on it.
	 */
	void fill() {
		this.isFilled = true;
	}
	
	
	/**
	 *  Indicates that this square no longer has a piece on it.
	 */
	void empty () {
		this.isFilled = false;
	}
	
	
	/** @return  Whether this square has a piece on it. */
	public boolean isFilled () {
		return this.isFilled;
	}
	
	
	/** 
	 *  Used to indicate the type of Square this is.
	 *  
	 *  @return  One of the gamemode constants in LevelModel.
	 */
	public abstract int getType();
	
	
	
	/** @return  The ReleaseNumber on this square (if any).
	 *  Null if no number on this square.*/
	public abstract ReleaseNumber getReleaseNumber();
	
	
	/** @return  Indicator of whether this square is a hint space. */
	public abstract boolean getIsHint();
}
