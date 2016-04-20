package poseidon.entities;
/**
 * Handles PieceContainers that are placed on the board/bullpen.
 * @author Natalia
 */
public class PieceContainer {
	
	/** The piece that is contained */
	Piece piece;
	
	/** Where the piece is located on the board (if is on board)*/
	Point location;
	
	/** Indicates whether the piece is the selected piece on the board.bullpen*/
	Boolean isSelected;
	
	PieceContainer (Piece piece, Point location, Boolean isSelected) {
		this.piece = piece;
		this.location = location;
		this.isSelected = isSelected;
	}

	@Override
	/**
	 * Overrides the standard equals() method for PiecesContainers.
	 * 
	 * Note: Doesn't only compare the way the pieces "look".
	 * For Board: would need to be the exact same piece (since location would be different otherwise)
	 * For Bullpen: Since location on bullpen would be equal to null, would produce true even those are two different pieces that just "look" the same
	 */
	public boolean equals(Object o) {
		if (!(o instanceof PieceContainer)) { return false; }
		PieceContainer newPiece = (PieceContainer) o;
		if(this.piece.equals(newPiece.getPiece())&&this.location.equals(newPiece.getLocation())) { return true; }
		return false;
	}
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public Piece getPiece()
	{
		return piece;
	}

	public Point getLocation()
	{
		return location;
	}

	public Boolean getIsSelected()
	{
		return isSelected;
	}

	public void setPiece(Piece piece)
	{
		this.piece = piece;
	}

	public void setLocation(Point location)
	{
		this.location = location;
	}

	public void setIsSelected(Boolean isSelected)
	{
		this.isSelected = isSelected;
	}
}
