package poseidon.entities;

public class PuzzleBoardLogic implements IBoardLogic{
	PuzzleBoardLogic() {
	}
	
	/*
	 * Adds piece to the board
	 */
	public Boolean addPiece(Board board, PieceContainer piece, int row, int col) {
		Point [] pieceArray = piece.getPiece().getPiece();
		Square [] [] playArea = board.getPlayArea();
		int i;
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + row;		//finds the theoretical row of the square
			int pointCol = pieceArray[i].getCol() + col;		//finds the theoretical col of the square
			if (playArea[pointRow][pointCol].isFilled() || pointRow>=board.getRows() || pointCol>=board.getCols()) {
								//Checks that the piece isn't covering an existing one and isn't outside the boarder
				return false;
			}
		}
					//if we got this far, all spaces are free to use
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
	
	/*
	 * Removes piece from the board
	 */
	public Boolean removePiece(Board board, PieceContainer piece){
		Point [] pieceArray = piece.getPiece().getPiece();
		Square [] [] playArea = board.getPlayArea();
		int i;
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + piece.getLocation().getRow();		
			int pointCol = pieceArray[i].getCol() + piece.getLocation().getCol();
			if (!(playArea[pointRow][pointCol].isFilled())) {
				return false;									//the square isn't filled. Piece isn't on the board
			}
		}
		
		piece.location = null;									//setting the location of the piece to null
		board.removePieceFromList(piece);
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + piece.getLocation().getRow();		
			int pointCol = pieceArray[i].getCol() + piece.getLocation().getCol();
			playArea[pointRow][pointCol].empty();				//emptying the squares that the piece used to be on
		}
		return true;
	}
}
