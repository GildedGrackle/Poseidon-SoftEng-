package poseidon.entities;

/**
 * Interface for the bullpen logic.
 * Includes Builder, Puzzle, Lightning and Release bullpens.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public interface IBullpenLogic {
	/**
	 *  Performs game-type-specific operations on the bullpen after a piece is removed.
	 *  
	 *  @param bullpen  the bullpen to operate on
	 *  @param piece  the piece that was removed
	 */
	public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece);
}
