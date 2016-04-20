package poseidon.entities;

/**
 * Manages unplayable squares.
 * @author Natalia
 *
 */
public class NonplayableSquare extends Square{

	public NonplayableSquare() {
		super(false);				//returns false as "isFilled" to Square
	}
}
