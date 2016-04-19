package poseidon.entities;

public class BuilderBoardLogic implements IBoardLogic {

	BuilderBoardLogic() {
		
	}
	
	public Boolean addPiece(Board board, PieceContainer piece, int row, int col) {
		Point [] pieceArray = piece.getPiece().getPiece();
		Square [] [] playArea = board.getPlayArea();
		int i;
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + row;		//finds the theoretical row of the square
			int pointCol = pieceArray[i].getCol() + col;		//finds the theoretical col of the square
			if (pointRow>=board.getMaxRows() || pointCol>=board.getMaxCols()) {					//Compares it to 11, since it's the largest number		
								//Checks that the piece isn't outside the border
				return false;
			}
		}
					//if we got this far, the placement is valid
		Point newPoint = new Point(row, col);					//Setting pivot point
		piece.setLocation(newPoint);
		board.addPieceToList(piece);
		
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + row;
			int pointCol = pieceArray[i].getCol() + col;
			playArea[pointRow][pointCol].fill();				//fills the squares with the piece points
		}
		return true;
}
	
	public Boolean removePiece (Board board, PieceContainer piece){
		return false;											//Cannot remove pieces in builder
	}
}
