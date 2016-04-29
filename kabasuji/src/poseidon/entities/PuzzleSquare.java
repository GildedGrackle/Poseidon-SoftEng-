package poseidon.entities;

/**
 * Manages squares in the puzzle level.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class PuzzleSquare extends Square {
	Boolean isHint;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param isFilled  indicator whether Square should be initialized as filled
	 *  TODO add isHint to PuzzleSquare constructor
	 */
	public PuzzleSquare(Boolean isFilled) {
		super(isFilled);
		this.isHint = false;					//Set to false as default
	}
	
	
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
