package poseidon.entities;

/**
 * Moving a piece from bullpen to board.
 * 
 * @author Natalia
 *
 */
public class BullpenToBoardMove implements IMove{
	PieceContainer piece;
	Point location;
	
	public BullpenToBoardMove(PieceContainer piece, Point location) {
		this.piece = piece;
		this.location = location;
	}
	
	public Boolean isValid() {
		return false;						//TODO: change return value
	}
	
	public Boolean doMove() {
		
		/** Should do all of these things */
//		boardModel.addPiece(piece);
//		bullpenModel.removePiece(piece);
//		boardView.setActiveDragging(null);
//		boardView.setActiveLocation(null);
//		bullpenView.setSelectedPiece(null);
//		pv.setOnBoard(true);
//		piece.setLocation(location);
//		
//		// Decrease moves remaining by 1 (if applicable)
//		game.decrementMoves();
		return false;						//TODO: change return value
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}
