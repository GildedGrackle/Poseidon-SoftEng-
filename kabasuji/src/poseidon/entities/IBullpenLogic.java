package poseidon.entities;

/**
 * Interface for the bullpen logic.
 * Includes Builder, puzzle, lightning and release bullpens.
 * @author Natalia
 *
 */
public abstract class IBullpenLogic {
	public abstract Boolean shouldAddPiece (Bullpen bullpen, PieceContainer piece);
	public abstract Boolean shouldRemovePiece (Bullpen bullpen, PieceContainer piece);
	public void afterPiece(Bullpen bullpen) {};
}
