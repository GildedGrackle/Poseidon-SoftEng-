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
	 * Unique to lightning, generates a random piece and adds it to the board.
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @return  An indicator of whether the operation modified the bullpen.
	 */
	Boolean addRandomPiece(Bullpen bullpen) {
		PieceContainer pc = new PieceContainer(new Point(-1, -1));
		return bullpen.addPiece(pc);
	}
	
	
	/**
	 *  After a piece is removed from the Lightning bullpen, add another random one.
	 *  
	 *  @param bullpen  the bullpen to modify
	 *  @param piece  the piece that was removed,  unused
	 */
	@Override
	public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece) {
		addRandomPiece(bullpen);
	}
}
