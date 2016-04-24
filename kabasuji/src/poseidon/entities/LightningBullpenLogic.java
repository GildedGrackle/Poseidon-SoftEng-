package poseidon.entities;

/**
 * Handles the actions on the bullpen in the lightning mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class LightningBullpenLogic extends IBullpenLogic {
	LightningBullpenLogic() {

	}

	/**
	 * Returns False because you should not be able to add a piece once the level has started
	 */
	public Boolean shouldAddPiece(Bullpen bullpen, PieceContainer piece) {
		return false;
	}
	
	
	/**
	 * Unique to lightning, generates a random piece and adds it to the board.
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @param piece - The container of the piece that needs to be added to the bullpen.
	 */
	Boolean addRandomPiece(Bullpen bullpen) {
		PieceContainer pc = new PieceContainer(new Point(-1, -1), false);
		return bullpen.getPieces().add(pc);
	}

	/**
	 * Removes a piece from the bullpen and replaces it with a random piece
	 * 
	 * 
	 * @param bullpen - the bullpen that the piece needs to get removed from.
	 * @param piece - The PieceContainer of the piece that needs to be removed
	 * 
	 * @return Boolean - true if piece is removed and replaced, false if piece isn't found
	 */
	public Boolean shouldRemovePiece(Bullpen bullpen, PieceContainer piece) {
		return true;
	}
	
	public void afterPieceRemoved(Bullpen bullpen) {
		addRandomPiece(bullpen);
	}
}
