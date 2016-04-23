package poseidon.entities;

/**
 * Handles the actions on the bullpen in the lightning mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class LightningBullpenLogic implements IBullpenLogic {
	LightningBullpenLogic() {

	}

	/**
	 * Should add a piece to the bullpen, however there is no way to add pieces to the bullpen after the game has started.
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @param piece - The container of the piece that needs to be added to the bullpen.
	 * @return Boolean - Returns false in any case.
	 */
	public Boolean addPiece(Bullpen bullpen, PieceContainer piece) {
		if(bullpen.getPieces().add(piece))
		{
			return true;
		}
		else  // Operation failed
		{
			return false;
		}
	}
	
	
	/**
	 * Unique to lightning, generates a random piece and adds it to the board.
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @param piece - The container of the piece that needs to be added to the bullpen.
	 */
	Boolean addRandomPiece(Bullpen bullpen, PieceContainer piece) {
		PieceContainer pc = new PieceContainer(new Point(-1, -1), false);
		if(bullpen.getPieces().add(pc))
		{
			return true;
		}
		else  // Operation failed
		{
			return false;
		}
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
	public Boolean removePiece(Bullpen bullpen, PieceContainer piece) {
		if(!bullpen.getPieces().remove(piece)) {return false;}				//A case where the piece isn't found on the bullpen
		addRandomPiece(bullpen, piece);
		return true; 	
	}
}
