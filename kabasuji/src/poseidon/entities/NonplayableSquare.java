package poseidon.entities;

/**
 * Manages unplayable squares.
 * @author Natalia
 *
 */
public class NonplayableSquare extends Square{

	/**
	 *  Constructor.
	 */
	public NonplayableSquare() {
		super(false);				//returns false as "isFilled" to Square
	}

	
	/**
	 *  Used to indicate the type of Square this is.
	 *  
	 *  @return -1 to indicate not a real Square
	 */
	@Override
	public int getType()
	{
		return -1;
	}

	
	/**
	 * Because this is not a ReleaseSquare, nothing to do here.
	 * 
	 * @return null
	 */
	@Override
	public ReleaseNumber getReleaseNumber()
	{
		return null;
	}

	
	/**
	 *  Because NonplayableSquares don't exist.
	 *  
	 *  @return false
	 */
	@Override
	public boolean getIsHint()
	{
		return false;
	}
	
	
}
