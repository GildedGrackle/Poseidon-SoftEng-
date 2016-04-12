package player;

import java.util.ArrayList;

public class Board {
	Square[] playArea = new Square[144];
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer activeDragged;
	IBoardLogic logic;
	
	Board(Square[] playArea, ArrayList<PieceContainer> pieces) {
		this.playArea = playArea;
		this.pieces = pieces;
	}
	
	PieceContainer findPiece (int row, int col) {
		return null;							//TODO: Change return value
	}
	
	void showHint () {
		//TODO: Change return value
	}
	
	Boolean removePiece (Piece piece) {
		return false;							//TODO: Change return value
	}
}
