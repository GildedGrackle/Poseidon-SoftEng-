package poseidon.player.entity;

public interface IMove {
	Boolean isValid();
	Boolean doMove();
	Boolean undoMove();
}
