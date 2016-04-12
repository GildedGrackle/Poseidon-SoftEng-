package player;

public class BoardToBullpenMove implements IMove{
	Point from, to;
	PieceContainer piece;
	
	BoardToBullpenMove(PieceContainer piece, Point from, Point to) {
		this.from = from;
		this.to = to;
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
