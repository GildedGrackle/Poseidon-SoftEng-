package poseidon.builder.entity;

public interface IMove {
	Boolean isValid();
	Boolean doMove();
	Boolean undoMove();
}
