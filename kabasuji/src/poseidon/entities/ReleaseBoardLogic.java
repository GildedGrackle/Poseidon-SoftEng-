package poseidon.entities;


/**
 * Handles the actions on the board in the release game mode.
 * 
 * @author Natalia
 * @author Alex Titus
 * @author Morgan Hopeman
 */
public class ReleaseBoardLogic implements IBoardLogic{
	
	/**
	 *  Constructor.
	 */
	ReleaseBoardLogic() {
		
	}
	
	
	/** @return  False - pieces shouldn't be added to the list of pieces on the board. */
	@Override
	public Boolean shouldAddList() {
		return false;
	}
	
	
	/**
	 * Meant to remove piece from the board, however there is no way to remove pieces from a release board.
	 * In addition, the undo option doesn't apply to release level, so there is no need to create the function
	 * for that purpose.
	 * 
	 * @param board  the board to remove from
	 * @param piece  the piece to remove
	 * @return Boolean - Always false.
	 */
	public Boolean shouldRemovePiece(Board board, PieceContainer piece) {
		return false;
	}
	
	
	/**
	 * Meant to check whether a piece can be selected, but there is no way to select a piece in lightning mode.
	 * 
	 * @return Boolean - Always false.
	 */
	@Override
	public Boolean canSelectPieces (){
		return false;
	}
	
	
	/**
	 *  Indicates if this move is valid given game logic.
	 *  
	 *  Piece must be only on playable Squares and must not overlap other Pieces.
	 *  
	 *  @param board  the board being checked
	 *  @param piece  the piece prospectively being placed
	 *  @param location  the (row, col) coordinates of the anchor point of piece
	 *  @return  Indicator of whether prospective move is valid.
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
	 *  @param board  the board being searched
	 *  @param row  the row of the selected square
	 *  @param col  the column of the selected square
	 *  @return  False - pieces can't be selected in Release.
	 */
	@Override
	public Boolean selectablePieceAt(Board board, int row, int col)
	{
		return false;
	}
	
	
	/** @return  False - cannot edit Release board during gameplay. */
	@Override
	public Boolean canEdit(Board board) {
		return false;
	}
	
	
	/**
	 *  Fills the squares covered by the given piece.
	 *  
	 *  @param piece  The piece used to fill squares
	 */
	@Override
	public void placePiece(Board board, PieceContainer piece){
		Point location = piece.getLocation();
		Square[][] playArea = board.getPlayArea();
		
		//fills the squares with the piece points
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();
			int pointCol = pt.getCol() + location.getCol();
			playArea[pointRow][pointCol].fill();
		}

	}
}
