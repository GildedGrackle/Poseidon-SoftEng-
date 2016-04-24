package poseidon.entities;
/**
 * Handles PieceContainers that are placed on the board/bullpen.
 * @author Natalia
 * @author Alex Titus
 */
public class PieceContainer {
	
	/** The piece that is contained */
	Piece piece;
	
	/** Where the piece is located on the board (if not on board is Point(-1, -1))*/
	Point location;
	
	/** Indicates whether the piece is the selected piece on the board.bullpen*/
	Boolean isSelected;
	
	
	/**
	 *  Constructor.
	 * @param piece  Piece being contained
	 * @param location  initial location of this Piece, usually Point(-1, -1)
	 * @param isSelected  indicates whether the Piece is the selected Piece in the Bullpen/Board
	 */
	public PieceContainer (Piece piece, Point location) {
		this.piece = piece;
		this.location = location;
	
	}
	
	
	/**
	 *  Constructor for random Piece.
	 *  
	 *  The contained Piece is randomly chosen from among the 35 Pieces.
	 *  
	 *  @param location  initial location of this Piece, usually Point(-1, -1)
	 *  @param isSelected  indicates whether the Piece is the selected Piece in the Bullpen/Board
	 */
	PieceContainer(Point location)
	{
		this.piece = new Piece();
		this.location = location;
		
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

		if(this.location == null || newPiece.getLocation() == null)
		{
			return false;
		}
		if(this.piece.equals(newPiece.getPiece()) && this.location.equals(newPiece.getLocation()))
		{ return true; }
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = piece.hashCode();
		if (location != null) {
			hash += location.getCol() + 31 * location.getRow();
		}
		return hash;
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
