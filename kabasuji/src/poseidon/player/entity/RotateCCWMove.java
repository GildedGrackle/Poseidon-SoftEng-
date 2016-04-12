package player;

public class RotateCCWMove implements IMove{
	PieceContainer piece;
	
	RotateCCWMove(PieceContainer piece) {
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
