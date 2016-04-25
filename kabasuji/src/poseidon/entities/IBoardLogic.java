package poseidon.entities;

/**
 * Interface for the board logic.
 * Includes Builder, puzzle, lightning and release boards.
 * @author Natalia
 * @author Alex Titus
 */
public interface IBoardLogic {
	/**
	 *  Adds Piece to Board.
	 * @param board  Board being added to
	 * @param piece  Piece being added, must have valid location set
	 * @return
	 */
	Boolean shouldAddPiece (Board board, PieceContainer piece);
	Boolean shouldRemovePiece (Board board, PieceContainer piece);
	Boolean selectPiece (Board board, PieceContainer piece);
	Boolean isValid(Board board, PieceContainer piece, Point location);
	Boolean canSelect(Board board, int row, int col);
}
