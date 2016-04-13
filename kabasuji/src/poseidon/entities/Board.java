package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

public class Board {
	Square [] playArea = new Square [144];			
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer activeDragged;
	IBoardLogic logic;						
	
	
	Board (Square [] playArea, BuilderBoardLogic logic) {
		this.playArea = playArea;
		pieces = new ArrayList<PieceContainer>();
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
	
	void showHint () {
		//TODO: Change return value
	}
}
