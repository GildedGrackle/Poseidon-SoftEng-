package poseidon.entities;

public class MarkPlayableSquareMove implements IMove{
	Square square;
	
	MarkPlayableSquareMove(Square square) {
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