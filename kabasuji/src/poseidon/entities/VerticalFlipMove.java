package poseidon.entities;

/**
 * Flipping piece vertically.
 * @author Natalia
 *
 */
public class VerticalFlipMove implements IMove{
	PieceContainer piece;
	
	VerticalFlipMove(PieceContainer piece) {
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
