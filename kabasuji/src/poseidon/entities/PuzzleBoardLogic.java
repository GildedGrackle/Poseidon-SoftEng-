package poseidon.entities;

/**
 * Handles the actions on the board in the puzzle game mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class PuzzleBoardLogic implements IBoardLogic{
	
	/**
	 *  Constructor.
	 */
	public PuzzleBoardLogic() {
	}
	

	/**
	 *  TODO PuzzleBoardLogic.shouldAddPiece documentation; see IBoardLogic
	 */
	@Override
	public Boolean shouldAddPiece(Board board, PieceContainer piece) {
		return true;
	}
	
	
	/**
	 * Removes a piece from the board.
	 * 
	 * @param board - The board the piece is removed from
	 * @param piece - The container of the piece that needs to be removed
	 * @return Boolean - Indicates whether the removal was successful.
	 */
	@Override
	public Boolean shouldRemovePiece(Board board, PieceContainer piece){
		Square [] [] playArea = board.getPlayArea();
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + piece.getLocation().getRow();		
			int pointCol = pt.getCol() + piece.getLocation().getCol();
			if (!(playArea[pointRow][pointCol].isFilled())) {
				return false;									//the square isn't filled. Piece isn't on the board
			}
			
		}
		
		//emptying the squares that the piece used to be on
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + piece.getLocation().getRow();		
			int pointCol = pt.getCol() + piece.getLocation().getCol();
			playArea[pointRow][pointCol].empty();
		}
		
		// Set location as nowhere
		piece.setLocation(new Point(-1, -1));
	
		return true;
	}
	
	
	/**
	 * Indicates whether it's possible to select a piece on the board.
	 * Since this is puzzle, we can always select pieces.
	 * 
	 * @return  True - can always select piece.
	 */
	@Override
	public Boolean canSelectPieces (){
		return true;
	}
	
	
	/**
	 *  Indicates if this move is valid given game logic.
	 *  
	 *  Piece must be only on playable Squares and must not overlap other Pieces.
	 *  
	 *  @param board  the board being checked
	 *  @param piece  the piece prospectively being placed
	 *  @param location  the (row, col) coordinates of the anchor point of piece
	 *  @return  Indicator of whether the prospective move is valid.
	 */
	public Boolean isValid(Board board, PieceContainer piece, Point location)
	{
		Square[][] playArea = board.getPlayArea();
		
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();		//finds the theoretical row of the square
			int pointCol = pt.getCol() + location.getCol();		//finds the theoretical col of the square
			if (pointRow >= board.getRows() || pointCol>=board.getCols() || pointRow < 0 || pointCol < 0 ||
					playArea[pointRow][pointCol].isFilled() || (board.getSquare(pointRow, pointCol).getType() < 0)) {
								//Checks that the piece isn't covering an existing one, isn't outside the border
								//and isn't on top of a non-playable square.
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 *  Determines if a piece with part at (row, col) can be selected.
	 *  
	 *  Checks that the square is playable and contains a piece.
	 *  
	 *  @param board  the board being searched
	 *  @param row  the row of the selected square
	 *  @param col  the column of the selected square
	 *  @return  Indicator of whether there is a selectable piece at (row, col)
	 */
	@Override
	public Boolean selectablePieceAt(Board board, int row, int col)
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
		
		
		return false;
	}


	/** @return  False - cannot edit Puzzle board during gameplay. */
	@Override
	public Boolean canEdit(Board board) {
		return false;
	}

}
