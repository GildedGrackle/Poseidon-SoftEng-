package poseidon.entities;
/**
 * Handles the actions on the bullpen in Builder mode.
 * @author Natalia
 *
 */
public class BuilderBullpenLogic extends IBullpenLogic {

	BuilderBullpenLogic() {
	
	}

	/**
	 * Returns false because the bullpen is a set of fixed 35 pieces in builder mode. 
	 */
	public Boolean shouldAddPiece(Bullpen bullpen, PieceContainer piece) {
		return false;		
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
	public Boolean shouldRemovePiece(Bullpen bullpen, PieceContainer piece) {
		return false;																									
	}
	

}
