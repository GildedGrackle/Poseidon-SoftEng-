package poseidon.player.entity;

public class NonplayableSquare extends Square{

	public NonplayableSquare() {
		super(false);				//returns false as "isFilled" to Square
	}
}
