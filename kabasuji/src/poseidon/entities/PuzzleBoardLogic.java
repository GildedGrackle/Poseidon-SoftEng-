package poseidon.entities;

/**
 * Handles the actions on the board in the puzzle game mode.
 * @author Natalia
 *
 */
public class PuzzleBoardLogic implements IBoardLogic{
	PuzzleBoardLogic() {
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
			if (playArea[pointRow][pointCol].isFilled() || pointRow>=board.getRows() || pointCol>=board.getCols()|| (board.getSquare(pointRow, pointCol) instanceof NonplayableSquare)) {
								//Checks that the piece isn't covering an existing one, isn't outside the boarder
								//and isn't on top of a non-playable square.
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
	
	/**
	 * Removes a piece from the board.
	 * 
	 * @param board - The board the piece is removed from
	 * @param piece - The container of the piece that needs to be removed
	 * @return Boolean - Indicates whether the removal was successful
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
	
	/**
	 * Indicates whether it's possible to select a piece on the board.
	 * Since this is puzzle, we can always select pieces.
	 */
	public Boolean selectPiece (Board board, PieceContainer piece){
		return true;
	}
}
