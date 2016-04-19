package poseidon.entities;

public interface IBoardLogic {
	Boolean addPiece (Board board, PieceContainer piece, int row, int col);
	Boolean removePiece (Board board, PieceContainer piece);
}
