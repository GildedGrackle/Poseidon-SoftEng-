package poseidon.entities;

/**
 * Manages squares in the puzzle level.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class PuzzleSquare extends Square {
	/** Whether this is a hint space or not. */
	Boolean isHint;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param isFilled  indicator whether square should be initialized as filled
	 *  @param isHint  indicator whether square should be initialized as a hint space
	 */
	public PuzzleSquare(Boolean isFilled, Boolean isHint) {
		super(isFilled);
		this.isHint = isHint;					//Set to false as default
	}
	
	
	void makeHint() {
		this.isHint = true;
	}

	
	/**
	 *  Used to indicate the type of square this is.
	 *  
	 *  @return one of the game-type constants in LevelModel
	 */
	@Override
	public int getType()
	{
		return LevelModel.PUZZLE;
	}

	
	/**
	 *  Because this is not a ReleaseSquare, nothing to do here.
	 *  
	 *  @return null
	 */
	@Override
	public ReleaseNumber getReleaseNumber()
	{
		return null;
	}

	
	/**
	 *  Indicates if this square has been marked as a hint.
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
}
