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
		// Create new PieceContainer copy
		PieceContainer freshCopy = createNewPiece(piece);
		
		// Find where the removed piece was in the bullpen
		PieceFactory fac = new PieceFactory();
		int index = 0;
		for(int i = 1; i <= 35; i++)
		{
			if(freshCopy.equals(fac.getPiece(i)))
			{
				index = i;
			}
		}
		
		// Add the new copy
		bullpen.addPieceAt(freshCopy, index - 1);
	}
	

	/**
	 *  Creates a new PieceContainer that contains the same Piece but is different.
	 *  
	 *  @param piece  the PieceContainer to duplicate
	 *  @return  The newly created PieceContainer.
	 */
	PieceContainer createNewPiece(PieceContainer piece)
	{
		Point[] points = new Point[6];
		for(Point newPt : points)
		{
			for(Point pt : piece.getPiece().getPiece())
			{
				newPt = new Point(pt.getRow(), pt.getCol());
			}
		}
		Piece pieceCopy = new Piece(points);
		PieceContainer newPC = new PieceContainer(pieceCopy, new Point(-1, -1));
		
		return newPC;
	}
}
