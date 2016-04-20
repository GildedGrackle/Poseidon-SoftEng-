package poseidon.entities;

/**
 * Interface for the board logic.
 * Includes Builder, puzzle, lightning and release boards.
 * @author Natalia
 *
 */
public interface IBoardLogic {
	Boolean addPiece (Board board, PieceContainer piece, int row, int col);
	Boolean removePiece (Board board, PieceContainer piece);
	Boolean selectPiece (Board board, PieceContainer piece);
}
