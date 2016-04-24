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
	
	/**
	 *  Constructor.
	 *  
	 * @param pieces  the intial Pieces in the Bullpen
	 * @param logic  the game-mode-specific logic associated with the Bullpen
	 */
	public Bullpen(ArrayList <PieceContainer> pieces, IBullpenLogic logic) {
		this.pieces = new ArrayList<>(pieces);
		this.logic = logic;
		this.pieceSelected = null;
	}
	
	
	/**
	 *  Removes the given Piece from the Bullpen.
	 *  
	 *  Performs game-mode-specific operations in addition to removing
	 *  the Piece.
	 *  
	 * @param piece  the Piece to remove
	 * @return an indicator of whether the operation completed successfully
	 */
	public boolean removePiece (PieceContainer piece) {	
		boolean shouldRemove = logic.shouldRemovePiece(this, piece) 
				&& pieces.contains(piece);
		if (shouldRemove) {
			pieces.remove(piece);
			logic.afterPiece(this);
		}
		return shouldRemove;
	}
	
	/**
	 *  Adds given Piece to the Bullpen.
	 *  
	 * @param piece  the Piece to add
	 * @return an indicator of whether the operation completed successfully
	 */
	public boolean addPiece (PieceContainer piece) {
		 boolean shouldAdd = logic.shouldAddPiece(this, piece);
		 if (shouldAdd) {
			 pieces.add(piece);
		 }
		 return shouldAdd;
	}
	

	/** Returns the number of Pieces in the Bullpen. */
	public int getSize()
	{
		return pieces.size();
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** Returns the list of Pieces in the Bullpen. */
	public ArrayList<PieceContainer> getPieces()
	{
		return pieces;
	}
	/** Returns the Piece that is currently selected in the Bullpen. */
	public PieceContainer getPieceSelected()
	{
		return pieceSelected;
	}
	
	/** Returns the Piece contained at index. */
	public PieceContainer getPiece(int index) {
		return pieces.get(index);
	}
	/** Returns the index of the given Piece. */
	public int getLocation(PieceContainer piece){
		return pieces.indexOf(piece);
	}
	
	/** 
	 *  Sets the given Piece as the currently selected Piece.
	 *  
	 *  Sets the given Piece as selected, and will replace any
	 *  currently selected Piece with the given one.
	 */
	public void setPieceSelected(PieceContainer pieceSelected)
	{
		if (this.pieceSelected != null && this.pieceSelected.equals(pieceSelected)) {
			this.pieceSelected = null;
		} else {
			this.pieceSelected = pieceSelected;
		}
	}
	
}
