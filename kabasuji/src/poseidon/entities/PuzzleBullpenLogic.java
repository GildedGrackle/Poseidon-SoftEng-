package poseidon.entities;

/**
 * Handles the actions on the bullpen in the puzzle game mode.
 * @author Natalia
 *
 */
public class PuzzleBullpenLogic extends IBullpenLogic {
	
	/**
	 *  Constructor.
	 */
	public PuzzleBullpenLogic(){
		//TODO: Change return value
	}
	
	/**
	 * Adds a piece to the bullpen. 
	 * 
	 * Note: would add the piece to the end of the list of pieces on bullpen. 
	 * Since there is no undo in puzzle, the function would be called when moving a piece from 
	 * the board to the bullpen
	 * 
	 * @param bullpen - The bullpen that the piece gets added to.
	 * @param piece - The container of the piece that needs to be added to the bullpen.
	 * @return Boolean - Returns false in any case.
	 */
	public Boolean shouldAddPiece(Bullpen bullpen, PieceContainer piece) {
		return true;
	}
	
	/**
	 * Removes a piece from the bullpen.
	 * 
	 * Note: currently shifts all the pieces after removing the piece
	 * 
	 * @param bullpen - the bullpen that the piece needs to get removed from.
	 * @param piece - The PieceContainer of the piece that needs to be removed
	 * 
	 * @return Boolean - Indicates whether the removal was successful
	 */
	public Boolean shouldRemovePiece(Bullpen bullpen, PieceContainer piece) {
		return true;
	}


}
