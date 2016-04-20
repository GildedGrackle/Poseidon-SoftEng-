package poseidon.entities;

/**
 * Interface for the bullpen logic.
 * Includes Builder, puzzle, lightning and release bullpens.
 * @author Natalia
 *
 */
public interface IBullpenLogic {
	Boolean addPiece (Bullpen bullpen, PieceContainer piece);
	Boolean removePiece (Bullpen bullpen, PieceContainer piece);
}
