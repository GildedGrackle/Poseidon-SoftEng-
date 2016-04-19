package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

public class Board {
	static final int MAXROWS = 12;
	static final int MAXCOLS = 12;
	Square [][] playArea = new Square [MAXROWS][MAXCOLS];			
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer activeDragged;
	IBoardLogic logic;						
	
	
	Board (Square [][] playArea, IBoardLogic logic) {
		this.playArea = playArea;
		pieces = new ArrayList<PieceContainer>();
		this.logic = logic;
	}
	
	/*
	 * finds the piece container that is located on a selected point
	 */
	PieceContainer findPiece (int row, int col) {
		for (int i=0; i<pieces.size(); i++) {							//Iterates through all the pieces on a board
			Point pivot = pieces.get(i).getLocation();
			for (Point p : pieces.get(i).getPiece().getPiece() ) {
				if (p.getRow() + pivot.getRow() == row && p.getCol() + pivot.getCol() == col){
																		//checks if a point on the piece is located at 
																		// given point
					return pieces.get(i);
				}
			}
		}
		return null;													//No piece found
	}
	
	Boolean addPiece (int row, int col, Piece piece) {
		return logic.addPiece(this, piece.getContainer(), row, col);
	}
	
	Boolean removePiece (Piece piece) {
		return logic.removePiece(this, piece.getContainer());							
	}
	
	void showHint () {
		//TODO: Change return value
	}
	
	Square [] [] getPlayArea (){
		return this.playArea;
	}
	
	int getRows () {
		return this.playArea.length;
	}
	
	int getCols () {
		return this.playArea[0].length;
	}
	
	int getMaxRows() {
		return MAXROWS;
	}
	
	int getMaxCols() {
		return MAXCOLS;
	}
	
	void addPieceToList (PieceContainer piece) {
		pieces.add(piece);
	}
	
	void removePieceFromList (PieceContainer piece) {
		pieces.remove(piece);
	}
}
