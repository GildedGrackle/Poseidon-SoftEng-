package builder;

public interface IMove {
	Boolean isValid();
	Boolean doMove();
	Boolean undoMove();
}
