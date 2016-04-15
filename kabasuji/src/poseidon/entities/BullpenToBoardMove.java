package poseidon.entities;

public class BullpenToBoardMove implements IMove{
	PieceContainer piece;
	Point location;
	
	BullpenToBoardMove(PieceContainer piece, Point location) {
		this.piece = piece;
		this.location = location;
	}
	
	public Boolean isValid() {
		return false;						//TODO: change return value
	}
	
	public Boolean doMove() {
		return false;						//TODO: change return value
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}
