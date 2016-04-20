package poseidon.entities;

/**
 * Manages squares in the lightning level.
 * @author Natalia
 *
 */
public class LightningSquare extends Square{

	LightningSquare(Boolean isFilled) {
		super(isFilled);
	}
	void fillSquare() {
		this.isFilled = true;
	}

}
