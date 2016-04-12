package poseidon.player.entity;
import java.util.ArrayList;

public class Bullpen {
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer pieceSelected;
	IBullpenLogic logic;
	
	Bullpen(ArrayList<PieceContainer> pieces) {
		this.pieces = pieces;
	}
	
	Boolean removePiece (Point location) {
		return false;							//TODO: Change return value
	}
	
	Boolean addPiece (PieceContainer piece) {
		return false;							//TODO: Change return value
	}
}
