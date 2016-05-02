package poseidon.entities;
/**
 * Handles the actions on the bullpen in Builder mode.
 * 
 * @author Natalia
 */
public class BuilderBullpenLogic implements IBullpenLogic {

	/**
	 *  Constructor.
	 */
	public BuilderBullpenLogic() {
	
	}

	
	/**
	 *  Replaces the piece that was removed with an identical copy.
	 *  
	 *  @param bullpen  the bullpen to modify
	 *  @param piece  the piece that was removed
	 */
	@Override
	public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece)
	{
		// Find where the removed piece was in the bullpen
		PieceFactory fac = new PieceFactory();
		int index = 0;
		for(int i = 1; i <= 35; i++)
		{
			if(! bullpen.getPiece(i - 1).equals(fac.getPiece(i)))
			{
				index = i;
				break;
			}
		}
		
		PieceContainer freshCopy = new PieceContainer(fac.getPiece(index), new Point(-1, -1));
		
		// Add the new copy
		bullpen.addPieceAt(freshCopy, index - 1);
	}
}
