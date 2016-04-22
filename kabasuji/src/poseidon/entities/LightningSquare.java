package poseidon.entities;

/**
 * Manages squares in the lightning level.
 * @author Natalia
 * @author Alex Titus
 */
public class LightningSquare extends Square{

	LightningSquare(Boolean isFilled) {
		super(isFilled);
	}
	
	
	/**
	 *  Used to indicate the type of Square this is.
	 *  
	 *  @return one of the game type constants in LevelModel
	 */
	@Override
	public int getType()
	{
		return LevelModel.LIGHTNING;
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
	 *  Because LightningSquares don't need to be hints.
	 *  
	 *  @return false
	 */
	@Override
	public boolean getIsHint()
	{
		return false;
	}

}
