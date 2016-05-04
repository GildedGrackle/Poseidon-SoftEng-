package poseidon.entities;

/**
 * Manages squares in the release level.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class ReleaseSquare extends Square{
	/** Number to this square contains. Consists of number and color. */
	ReleaseNumber number;
	/** Whether this square is a hint space. */
	Boolean isHint;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param isFilled  indicator whether square should be initialized as filled
	 *  @param number  the ReleaseNumber in this square, (-1, -1) if none
	 */
	public ReleaseSquare(Boolean isFilled, Boolean isHint, ReleaseNumber number) {
		super(isFilled);
		this.number = number;
		this.isHint = isHint;
		isHint = false;				// Set to false as default
	}
	
	
	/**
	 *  Marks this ReleaseSquare as a hint space.
	 */
	void makeHint() {
		this.isHint = true;
	}

	
	/**
	 *  Used to indicate the type of Square this is.
	 *  
	 *  @return one of the game-type constants in LevelModel
	 */
	@Override
	public int getType()
	{
		return LevelModel.RELEASE;
	}

	
	/**
	 *  Returns the ReleaseNumber associated with this Square.
	 *  
	 *  @return the ReleaseNumber associated with this ReleaseSquare, or null if none exists
	 */
	@Override
	public ReleaseNumber getReleaseNumber()
	{
		return number;
	}

	
	/**
	 *  Indicates if this Square has been marked as a hint.
	 *  
	 *  @return whether this is a hint space or not
	 */
	@Override
	public boolean getIsHint()
	{
		return isHint;
	}
	
	
	/**
	 *  Sets isHint.
	 *  
	 *  @param isHint  the new indication
	 */
	public void setIsHint(Boolean isHint)
	{
		this.isHint = isHint;
	}
	
	
	/**
	 *  Sets number.
	 *  
	 *  @param number  the new number
	 */
	public void setNumber(ReleaseNumber number)
	{
		this.number = number;
	}

}
