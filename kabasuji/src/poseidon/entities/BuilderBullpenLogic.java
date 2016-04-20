package poseidon.entities;
/**
 * Handles the actions on the bullpen in Builder mode.
 * @author Natalia
 *
 */
public class BuilderBullpenLogic implements IBullpenLogic {

	BuilderBullpenLogic() {
	
	}

	/**
	 * Looks for the piece on the bullpen, and adds it if it doesn't exist there previously
	 * 
	 * Note: The builder bullpen shouldn't have pieces added to it while using it within the interface, since the pieces
	 * don't "go away" after they are picked. In a situation that a player is trying to add the piece to the bullpen 
	 * within builder, the function would return false. The check is made mostly for a case where pieces need to be added
	 * to the bullpen as a part of the initialization process.
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @param piece - The container of the piece that needs to be added to the bullpen.
	 * @return Boolean - Returns true if a piece was added to the bullpen, false if it already exists in it.
	 */
	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		Piece pieceToAdd = piece.getPiece();
		Boolean isFound = false;
		for (int i=0; i<bullpen.getPieces().size(); i++) {
			if (bullpen.getPieces().get(i).equals(pieceToAdd)) { 
				isFound = true;
				break;
			}
		}
		if(!isFound) {
			bullpen.addPieceToList(piece);
		}
		
		return !isFound;			
	}
	
	/**
	 * Supposed to remove a piece from the bullpen, however the action is not possible in builder mode.
	 * 
	 * Note: The action isn't required for the undo operation, since there is no need to add pieces to the bullpen
	 * while inside builder.
	 * 
	 * @param bullpen - the bullpen that the piece needs to get removed from.
	 * @param piece - The PieceContainer of the piece that needs to be removed
	 * 
	 * @return Boolean - Always false, since pieces cannot be removed from buillpen in builder mode
	 */
	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		return false;																									
	}
	

}
