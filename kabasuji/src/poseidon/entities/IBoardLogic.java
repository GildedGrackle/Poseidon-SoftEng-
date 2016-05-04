package poseidon.entities;

/**
 * Interface for the board logic.
 * Includes Builder, Puzzle, Lightning and Release boards.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public interface IBoardLogic {
	
	/** @return  Whether the piece should be added to list of pieces on the
	 *  board as well as used to filled the squares. */
	Boolean shouldAddList();
	
	
	/**
	 *  Removes a piece from the board, if possible.
	 * 
	 *  @param board  board to remove the piece from
	 *  @param piece  piece to remove
	 *  @return  Indicator whether operation could be completed.
	 */
	Boolean shouldRemovePiece (Board board, PieceContainer piece);
	
	
	/** @return  Indicator of whether it is possible to select a piece on the board.	 */
	Boolean canSelectPieces();
	
	
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
	Boolean isValid(Board board, PieceContainer piece, Point location);
	
	
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
	Boolean selectablePieceAt(Board board, int row, int col);
	
	
	/** @return  Indicator of whether the board can be modified. */
	Boolean canEdit();
	
	
	/**
	 *  Fills the squares covered by the given piece.
	 *  
	 *  @param board  the board to modify
	 *  @param piece  The piece used to fill squares
	 */
	void placePiece(Board board, PieceContainer piece);
	
	
	/** 
	 *  Toggles the square at (row, col) to be a hint, if possible.
	 *  
	 *  @param board  the board to modify
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 */
	void toggleHint(Board board, int row, int col);
	
	
	/** 
	 *  Sets the square at (row, col) to have given ReleaseNumber, if possible.
	 *  
	 *  @param board  the board to modify
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 *  @param rn  the ReleaseNumber to set
	 */
	void setReleaseNumber(Board board, int row, int col, ReleaseNumber rn);
}
