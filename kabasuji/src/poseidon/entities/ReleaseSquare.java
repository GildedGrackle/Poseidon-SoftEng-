package poseidon.entities;

/**
 * Manages squares in the release level.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class ReleaseSquare extends Square{
	ReleaseNumber number;					//type release number. consists of number and color
	Boolean isHint;
	
	
	// TODO need a way to indicate no number  // Just use null
	ReleaseSquare(Boolean isFilled, ReleaseNumber number) {
		super(isFilled);
		this.number = number;
		isHint = false;						//Set to false as default
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

}
