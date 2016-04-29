package poseidon.entities;

/**
 * Interface for the board logic.
 * Includes Builder, Puzzle, Lightning and Release boards.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public interface IBoardLogic {
	/**
	 *  TODO figure out what shouldAddPiece does and probably rename it.
	 * @param board  Board being added to
	 * @param piece  Piece being added, must have valid location set
	 * @return
	 */
	Boolean shouldAddPiece (Board board, PieceContainer piece);
	
	
	/**
	 *  Removes a piece from the board, if possible.
	 *  TODO figure out what shouldRemovePiece does and probably rename it
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
	Boolean canEdit(Board board);
}
