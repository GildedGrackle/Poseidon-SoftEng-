package poseidon.entities;

public interface IMove {
	Boolean isValid();
	Boolean doMove();
	Boolean undoMove();
}
