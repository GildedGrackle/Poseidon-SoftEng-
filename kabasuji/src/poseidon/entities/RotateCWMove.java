package poseidon.entities;

public class RotateCWMove implements IMove{
	PieceContainer piece;
	
	RotateCWMove(PieceContainer piece) {
		this.piece = piece; 
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
