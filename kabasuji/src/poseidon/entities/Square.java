package poseidon.entities;

/**
 * Manages general squares. Includes puzzle, lightning, release and builder.
 * @author Natalia
 * @author Alex Titus
 */
public abstract class Square {
	Boolean isFilled;
	
	Square (Boolean isFilled) {
		this.isFilled = isFilled;
	}
	
	void fill() {
		this.isFilled = true;
	}
	
	void empty () {
		this.isFilled = false;
	}
	
	public boolean isFilled () {
		return this.isFilled;
	}
	
	/** Used to indicate the type of Square this is. */
	public abstract int getType();
	
	public abstract ReleaseNumber getReleaseNumber();
	
	public abstract boolean getIsHint();
}
