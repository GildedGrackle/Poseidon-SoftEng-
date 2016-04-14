package poseidon.entities;

public class PieceContainer {
	Piece piece;
	Point location;
	Boolean isSelected;
	
	PieceContainer (Piece piece, Point location, Boolean isSelected) {
		this.piece = piece;
		this.location = location;
		this.isSelected = isSelected;
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
