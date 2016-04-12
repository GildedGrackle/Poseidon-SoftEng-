package poseidon.builder.entity;
import java.util.ArrayList;

public class Bullpen {
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer pieceSelected;
	IBullpenLogic logic;
	
	Bullpen ( ArrayList <PieceContainer> pieces, IBullpenLogic logic) {
		this.pieces = pieces;
		this.logic = logic;
	}
	
	Boolean removePiece (Point location) {		//UML says "int location". Point?
		return false;							//TODO: change return value
	}
}
