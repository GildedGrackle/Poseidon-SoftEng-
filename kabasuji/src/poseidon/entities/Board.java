package poseidon.entities;
import java.util.ArrayList;

public class Board {
	Square [] playArea = new Square [144];			
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	IBoardLogic logic;						
	
	
	Board (Square [] playArea, ArrayList<PieceContainer> pieces, BuilderBoardLogic logic) {
		this.playArea = playArea;
		this.pieces = pieces;
		this.logic = logic;
	}
	
	PieceContainer findPiece (int row, int col) {
		return null;							//TODO: Change return value
	}
	
	Boolean addPiece (int row, int col, PieceContainer piece) {
		return false;							//TODO: Change return value
	}
	
	Boolean removePiece (Piece piece) {
		return false;							//TODO: Change return value
	}
	
	Boolean intersects (PieceContainer piece, Point location) {
		return false;							//TODO: Change return value
	}
}
