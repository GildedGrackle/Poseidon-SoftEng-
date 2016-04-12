package builder;

public class MakeHintMove implements IMove{
	Square [] locations = new Square[6];
	
	MakeHintMove(Square [] locations) {
		this.locations = locations;
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
