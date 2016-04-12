package builder;

public class MarkUnplayableSquareMove implements IMove{
	Square square;
	
	MarkUnplayableSquareMove(Square square) {
		this.square = square; 
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
