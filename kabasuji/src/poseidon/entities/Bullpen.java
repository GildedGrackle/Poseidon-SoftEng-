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
		this.pieces = new ArrayList<PieceContainer>(pieces);
		this.logic = logic;
		this.pieceSelected = null;
	}
	
	
	/**
	 *  Constructor for infinite Bullpen.
	 *  
	 *  Creates a Bullpen with one of every type of Piece in it.
	 */
	public Bullpen(IBullpenLogic logic)
	{
		this.pieceSelected = null;
		this.logic = logic;
		this.pieces = new ArrayList<PieceContainer>(35);
		
		initializeInfinite();
	}
	
	
	/**
	 *  Fills pieces with one of every type of Piece
	 */
	void initializeInfinite()
	{
		PieceFactory factory = new PieceFactory();  // For creating the pieces
		
		for(int i = 1; i <= 35; i++)  // Fill pieces
		{
			PieceContainer nextPiece = new PieceContainer(factory.getPiece(i), new Point(-1, -1));
			pieces.add(nextPiece);
		}
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
	 *  
	 *  @param pieceSelected  the new piece to select, or null to deselect all
	 */
	public void setPieceSelected(PieceContainer pieceSelected)
	{
		// If there is no piece currently selected
		if(this.pieceSelected == null)
		{
			// Then set current selection to incoming piece immediately
			// If the incoming piece indicates a deselect operation
			if(pieceSelected != null)
			{
				this.pieceSelected = pieceSelected;
				this.pieceSelected.setIsSelected(true);
			}
			// Else selection was already null, so no change
		}
		else  // There is a selected piece
		{
			// Then determine state of incoming piece
			// If incoming piece indicates a deselect operation
			if(pieceSelected == null)
			{
				// Then inform current piece that it is no longer selected
				// and clear selection (setting to null)
				this.pieceSelected.setIsSelected(false);
				this.pieceSelected = pieceSelected;
			}
			
			// If the incoming piece is already selected then it is the currently
			// selected piece because it is assumed that only one piece will be
			// selected at a time
			else if(pieceSelected.getIsSelected())
			{
				// Then inform current piece that it is no longer selected
				// and clear selection
				this.pieceSelected.setIsSelected(false);
				this.pieceSelected = null;
			}
			
			// Otherwise the incoming piece is a different piece from the
			// current selection
			else
			{
				// Inform current piece that it is no longer selected,
				// set new selection, and inform new selection that it is selected
				this.pieceSelected.setIsSelected(false);
				this.pieceSelected = pieceSelected;
				this.pieceSelected.setIsSelected(true);
			}
		}
	}


	/**
	 *  Sets the list of pieces in the bullpen.
	 *  
	 *  @param pieces  the new list of pieces
	 */
	public void setPieces(ArrayList<PieceContainer> pieces)
	{
		this.pieces = pieces;
	}
	
}
