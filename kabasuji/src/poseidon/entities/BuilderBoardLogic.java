package poseidon.entities;
/**
 * Handles the actions on the board in Builder mode.
 * @author Natalia
 *
 */
public class BuilderBoardLogic implements IBoardLogic {

	BuilderBoardLogic() {
		
	}
	
	/**
	 * Adds given piece to the board and returns whether the addition was successful.
	 * 
	 * @param board - The board the addition is performed on.
	 * @param piece - The piece container of the piece that needs to be added.
	 * @param row, col - The location on the board where the pivot of the piece should be.
	 * @return Boolean - Indicates whether the addition was successful
	 */
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
	
	/**
	 * Removes a piece from the board.
	 * 
	 * Note: Removing pieces from the board in builder shouldn't be possible, however this function is created
	 * in order to make the Undo action possible. Shouldn't be called in any case except undo.
	 * 
	 * @param board - The board the piece is removed from
	 * @param piece - The container of the piece that needs to be removed
	 */
	public Boolean removePiece (Board board, PieceContainer piece){
		return false;											//TODO change return value

	}
	public Boolean selectPiece (Board board, PieceContainer piece){
		return false;
	}
}
