package poseidon.entities;

/**
 * Interface for the possible moves.
 * Includes moving pieces from bullpen to board, from board to bullpen, bullpen to bullpen, marking squares as playable or unplayable,
 * as well as resizing the board, flipping horizontally, flipping vertically, rotating clockwise and counterclockwise.
 * @author Natalia
 *
 */
public interface IMove {
	/**
	 *  Move is validated according to game mode.
	 * 
	 *  @return  Whether the move is valid.
	 */
	Boolean isValid();
	
	
	/**
	 *  Performs the move.
	 *  
	 *  @return  Whether the move completed successfully.
	 */
	Boolean doMove();
	
	
	/** 
	 *  Reverses the effects of the move.
	 * 
	 *  @return  Whether the undo operation completed successfully.
	 */
	Boolean undoMove();
}
