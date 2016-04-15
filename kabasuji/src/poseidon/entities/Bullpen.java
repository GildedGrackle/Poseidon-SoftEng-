package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

public class Bullpen {
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	PieceContainer pieceSelected;
	IBullpenLogic logic;
	
	public Bullpen(ArrayList <PieceContainer> pieces, IBullpenLogic logic) {
		this.pieces = pieces;
		this.logic = logic;
	}
	
	Boolean removePiece (Point location) {		//UML says "int location". Point?
		return false;							//TODO: change return value
	}
	
	Boolean addPiece (PieceContainer piece) {
		return false;							//TODO: Change return value
	}

	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public ArrayList<PieceContainer> getPieces()
	{
		return pieces;
	}
	public PieceContainer getPieceSelected()
	{
		return pieceSelected;
	}
	public IBullpenLogic getLogic()
	{
		return logic;
	}
	public void setPieces(ArrayList<PieceContainer> pieces)
	{
		this.pieces = pieces;
	}
	public void setPieceSelected(PieceContainer pieceSelected)
	{
		this.pieceSelected = pieceSelected;
	}
	public void setLogic(IBullpenLogic logic)
	{
		this.logic = logic;
	}
}
