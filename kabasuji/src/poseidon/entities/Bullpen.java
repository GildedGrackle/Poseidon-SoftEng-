package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

/**
 * Bullpen in all variations.
 * 
 * Note: In builder, the bullpen refers to the bullpen the user is accessing, not the playable bullpen that 
 * he constructs on his own.
 * 
 * @author Natalia
 * @author Alex Titus
 *
 */
public class Bullpen {
	/** List of all the pieces that are located on the bullpen at the moment.*/
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	
	/** The piece that is currently selected to perform actions on */
	PieceContainer pieceSelected;
	
	/**The logic of the bullpen - game mode/builder*/
	IBullpenLogic logic;
	
	public Bullpen(ArrayList <PieceContainer> pieces, IBullpenLogic logic) {
		this.pieces = pieces;
		this.logic = logic;
		this.pieceSelected = null;
	}
	
	public boolean removePiece (PieceContainer piece) {	
		return logic.removePiece(this, piece);
	}
	
	/**
	 *  Adds given Piece to the Bullpen.
	 * @param piece  the Piece to add
	 * @return indication if the operation completed successfully
	 */
	public boolean addPiece (PieceContainer piece) {
		return logic.addPiece(this, piece);
	}
	
	void addPieceToList (PieceContainer piece) {
		pieces.add(piece);
	}
	
	boolean removePieceFromList (PieceContainer piece) {
		if(pieces.indexOf(piece) == -1) { return false; }
		pieces.remove(piece);
		return true;
	}

	/** Returns the size of ArrayList pieces */
	public int getSize()
	{
		return pieces.size();
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
	public PieceContainer getPiece(int location) {
		return pieces.get(location);
	}
	public int getLocation(PieceContainer piece){
		return pieces.indexOf(piece);
	}
	public void setPieces(ArrayList<PieceContainer> pieces)
	{
		this.pieces = pieces;
	}
	public void setPieceSelected(PieceContainer pieceSelected)
	{
		// If selecting Piece
		if(pieceSelected != null)
		{
			// If have a selected Piece already
			if(this.pieceSelected != null)
			{
				// Then deselect that first and select the new one
				this.pieceSelected.setIsSelected(false);
				pieceSelected.setIsSelected(true);
			}
			else  // No prior selected Piece
			{
				// Then select new one
				pieceSelected.setIsSelected(true);
			}
		}
		else // Deselecting Piece
		{
			// If have a selected Piece already
			if(this.pieceSelected != null)
			{
				// Then deselect it
				this.pieceSelected.setIsSelected(false);
			}
			// Else nothing
		}
		
		// Set selected Piece as such
		this.pieceSelected = pieceSelected;
	}
	public void setPiece(int location, PieceContainer piece){
		pieces.set(location, piece);
	}
	public void setLogic(IBullpenLogic logic)
	{
		this.logic = logic;
	}
}
