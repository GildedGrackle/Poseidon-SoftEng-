package poseidon.entities;
/**
 * Handles the actions on the board in Builder mode.
 * @author Natalia
 * @author Alex Titus
 */
public class BuilderBoardLogic implements IBoardLogic {

	
	/**
	 *  Constructor.
	 */
	public BuilderBoardLogic() {
		
	}
	
	
	/**
	 * Adds given piece to the board and returns whether the addition was successful.
	 * 
	 * @param board - The board the addition is performed on.
	 * @param piece - The piece container of the piece that needs to be added, location must be set.
	 * @param row, col - The location on the board where the pivot of the piece should be.
	 * @return Boolean - Indicates whether the addition was successful
	 */
	public Boolean shouldAddPiece(Board board, PieceContainer piece) {
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
	public Boolean shouldRemovePiece (Board board, PieceContainer piece){
		return false;											//TODO change return value

	}
	public Boolean selectPiece (Board board, PieceContainer piece){
		return false;
	}
	
	
	/**
	 *  Indicates if this move is valid given game logic.
	 *  
	 *  Piece must be only be on Board.
	 */
	public Boolean isValid(Board board, PieceContainer piece, Point location)
	{
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();		//finds the theoretical row of the square
			int pointCol = pt.getCol() + location.getCol();		//finds the theoretical col of the square
			if (pointRow >= board.getMaxRows() || pointCol >= board.getMaxCols()
					|| pointRow < 0 || pointCol < 0) {
				//Compares it to 11, since it's the largest number
				//Checks that the piece isn't outside the border
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 *  Determines if a Piece with part at (row, col) can be selected.
	 *  
	 *  Checks that the Square is playable and contains a Piece.
	 */
	@Override
	public Boolean canSelect(Board board, int row, int col)
	{
		// If location is unplayable or unfilled
		if(board.getSquare(row, col).getType() < 0 || !board.getSquare(row, col).isFilled())
		{
			// Then can't select any Piece on it
			return false;
		}
		
		// Search for the Piece covering this Square
		for(PieceContainer pc : board.getPieces())
		{
			for(Point pt : pc.getPiece().getPiece())
			{
				// If Piece contains the Square
				if(pt.getCol() + pc.getLocation().getCol() == col &&
						pt.getRow() + pc.getLocation().getRow() == row)
				{
					// Then can select it
					return true;
				}
			}
		}
		
		// Probably won't get down here, but it keeps the compiler happy.
		return false;
	}

	@Override
	public Boolean canEdit(Board board) {
		return true;
	}
}
