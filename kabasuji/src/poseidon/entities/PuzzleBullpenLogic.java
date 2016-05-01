package poseidon.entities;

/**
 * Handles the actions on the bullpen in the puzzle game mode.
 * @author Natalia
 * @author Alex Titus
 */
public class PuzzleBullpenLogic implements IBullpenLogic {
	
	/**
	 *  Constructor.
	 */
	public PuzzleBullpenLogic(){
		
	}
	

	/**
	 *  No action is taken after piece is removed from a Puzzle bullpen.
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
