package poseidon.entities;


/**
 * Flipping piece horizontally.
 * @author Natalia
 *
 */
public class HorizontalFlipMove implements IMove{
	PieceContainer piece;
	
	HorizontalFlipMove(PieceContainer piece) {
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
