package poseidon.entities;

/**
 * Handles the actions on the bullpen in the release game mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class ReleaseBullpenLogic implements IBullpenLogic{
	
	/**
	 *  Constructor.
	 */
	ReleaseBullpenLogic() {
		
	}
	

	/**
	 *  No action is taken after piece is removed from a Release bullpen.
	 *  
	 *  @param bullpen  the bullpen to modify,  unused
	 *  @param piece  the piece that was removed,  unused
	 */
	@Override
	public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece)
	{
		return ;
	}
}
