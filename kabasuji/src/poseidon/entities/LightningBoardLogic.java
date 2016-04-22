package poseidon.entities;

import poseidon.entities.PieceContainer;

/**
 * Handles the actions on the board in lightning game mode.
 * @author Natalia
 *
 */
public class LightningBoardLogic implements IBoardLogic {
	
	LightningBoardLogic(){
		//TODO: Change return value
	}
	
	/**
	 * Adds given piece to the board and returns whether the addition was successful.
	 * 
	 * @param board - The board the addition is performed on.
	 * @param piece - The piece container of the piece that needs to be added.
	 * @param row, col - The location on the board where the pivot of the piece should be.
	 * @return Boolean - Indicates whether the addition was successful
	 */
	public Boolean addPiece(Board board, PieceContainer piece) {
		Point location = piece.getLocation();  // TODO need to check if this returns null
		Point [] pieceArray = piece.getPiece().getPiece();
		Square [] [] playArea = board.getPlayArea();
		int i;
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + location.getRow();		//finds the theoretical row of the square
			int pointCol = pieceArray[i].getCol() + location.getCol();		//finds the theoretical col of the square
			if (pointRow >=board.getRows() || pointCol>=board.getCols()|| (board.getSquare(pointRow, pointCol) instanceof NonplayableSquare)) {
								//Checks that the piece isn't outside the border and is put on playable squares
				return false;
			}
		}
					//if we got this far, the placement is valid
		board.addPieceToList(piece);
		
		for (i=0; i<pieceArray.length; i++) {
			int pointRow = pieceArray[i].getRow() + location.getRow();
			int pointCol = pieceArray[i].getCol() + location.getCol();
			playArea[pointRow][pointCol].fill();				//fills the squares with the piece points
		}
		return true;
	}
	
	/**
	 * Meant to remove piece from the board, however there is no way to remove pieces from a lightning board.
	 * In addition, the undo option doesn't apply to lightning level, so there is no need to create the function
	 * for that purpose.
	 * 
	 * @return Boolean - Always false.
	 */
	public Boolean removePiece(Board board, PieceContainer piece) {
		return false;							
	}
	
	
	/**
	 * Meant to check whether a piece can be selected, but there is no way to select a piece in lightning mode.
	 * 
	 * @return Boolean - Always false.
	 */
	public Boolean selectPiece (Board board, PieceContainer piece){
		return false;
	}

}
