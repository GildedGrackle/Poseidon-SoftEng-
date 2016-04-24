package poseidon.entities;

/**
 * Interface for the bullpen logic.
 * Includes Builder, puzzle, lightning and release bullpens.
 * @author Natalia
 *
 */
public abstract class IBullpenLogic {
	abstract Boolean shouldAddPiece (Bullpen bullpen, PieceContainer piece);
	abstract Boolean shouldRemovePiece (Bullpen bullpen, PieceContainer piece);
	void afterPiece(Bullpen bullpen) {};
}
