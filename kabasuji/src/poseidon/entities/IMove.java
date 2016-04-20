package poseidon.entities;

/**
 * Interface for the possible moves.
 * Includes moving pieces from bullpen to board, from board to bullpen, bullpen to bullpen, marking squares as playable or unplayable,
 * as well as resizing the board, flipping horizontally, flipping vertically, rotating clockwise and counterclockwise.
 * @author Natalia
 *
 */
public interface IMove {
	Boolean isValid();
	Boolean doMove();
	Boolean undoMove();
}
