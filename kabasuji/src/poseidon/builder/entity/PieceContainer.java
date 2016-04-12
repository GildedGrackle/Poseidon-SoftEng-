package builder;

public class PieceContainer {
	Piece piece;
	Point location;
	Boolean isSelected;
	
	PieceContainer (Piece piece, Point location, Boolean isSelected) {
		this.piece = piece;
		this.location = location;
		this.isSelected = isSelected;
	}
}
