package poseidon.entities;

import poseidon.entities.PieceContainer;

/**
 * Handles the actions on the board in lightning game mode.
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class LightningBoardLogic implements IBoardLogic {
	
	/**
	 * Constructor.
	 */
	LightningBoardLogic(){
		
	}
	
	/** @return  False - pieces shouldn't be added to the list of pieces on the board. */
	@Override
	public Boolean shouldAddList() {
		return false;
	}
	
	/**
	 * Meant to remove piece from the board, however there is no way to remove pieces from a lightning board.
	 * In addition, the undo option doesn't apply to lightning level, so there is no need to create the function
	 * for that purpose. TODO see IBoardLogic
	 * 
	 * @return Boolean - Always false.
	 */
	@Override
	public Boolean shouldRemovePiece(Board board, PieceContainer piece) {
		return false;
	}
	
	
	/**
	 * Meant to check whether a piece can be selected, but there is no way to select a piece in Lightning mode.
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
	 *  Piece must be only on playable Squares.
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
			if (pointRow >= board.getRows() || pointCol >= board.getCols() ||
					pointRow < 0 || pointCol < 0 ||
					(board.getSquare(pointRow, pointCol).getType() < 0)) {
								//Checks that the piece isn't outside the border and is put on playable squares
				return false;
			}
		}
		
		return true;
	}

	
	/**
	 *  Determines if a piece with part at (row, col) can be selected.
	 *  
	 *  @return  False - pieces aren't allowed to be selected in Lightning levels.
	 */
	@Override
	public Boolean selectablePieceAt(Board board, int row, int col)
	{
		return false;
	}

	
	/** @return  False - cannot edit Lightning board during gameplay. */
	@Override
	public Boolean canEdit(Board board) {
		return false;
	}
}
