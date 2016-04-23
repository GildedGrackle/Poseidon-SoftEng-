package poseidon.entities;


/**
 * Handles the actions on the board in the release game mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class ReleaseBoardLogic implements IBoardLogic{
	ReleaseBoardLogic() {
		
	}
	/**
	 * Adds given piece to the board and returns whether the addition was successful.
	 * 
	 * @param board - The board the addition is performed on.
	 * @param piece - The piece container of the piece that needs to be added, location must be set.
	 * @param row, col - The location on the board where the pivot of the piece should be.
	 * @return Boolean - Indicates whether the addition was successful
	 */
	public Boolean addPiece(Board board, PieceContainer piece) {
		Point location = piece.getLocation();
		Square[][] playArea = board.getPlayArea();
		
		// Add to Board's list of Pieces
		board.addPieceToList(piece);
		
		//fills the squares with the piece points
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();
			int pointCol = pt.getCol() + location.getCol();
			playArea[pointRow][pointCol].fill();
		}
		
		// Indicate success
		return true;
	}
	
	/**
	 * Meant to remove piece from the board, however there is no way to remove pieces from a release board.
	 * In addition, the undo option doesn't apply to release level, so there is no need to create the function
	 * for that purpose.
	 * 
	 * @return Boolean - Always false.
	 */
	public Boolean removePiece(Board board, PieceContainer piece) {
		return false;											//Cannot remove pieces from release boards
	}
	
	/**
	 * Meant to check whether a piece can be selected, but there is no way to select a piece in lightning mode.
	 * 
	 * @return Boolean - Always false.
	 */
	@Override
	public Boolean selectPiece (Board board, PieceContainer piece){
		return false;
	}
	
	
	/**
	 *  Indicates if this move is valid given game logic.
	 *  
	 *  Piece must be only on playable Squares and must not overlap other Pieces.
	 */
	@Override
	public Boolean isValid(Board board, PieceContainer piece, Point location)
	{
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();		//finds the theoretical row of the square
			int pointCol = pt.getCol() + location.getCol();		//finds the theoretical col of the square
			if (pointRow>=board.getRows() || pointCol>=board.getCols() || pointRow < 0 || pointCol < 0
					|| board.getSquare(pointRow, pointCol).getType() < 0) {
								//Checks that the piece isn't outside the border or on nonplayble Squares
				return false;
			}
		}
		
		// Else valid
		return true;
	}
	
	
	/**
	 *  Determines if a Piece with part at (row, col) can be selected.
	 *  
	 *  Always returns false, as Pieces aren't allowed to be selected in Release Levels.
	 */
	@Override
	public Boolean canSelect(Board board, int row, int col)
	{
		return false;
	}
}
