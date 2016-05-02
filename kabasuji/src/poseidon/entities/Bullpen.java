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
	 *  Fills pieces with one of every type of Piece.
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
	 *  Removes the given piece from the bullpen.
	 *  
	 *  Performs game-mode-specific operations in addition to removing
	 *  the piece.
	 *  
	 * @param piece  the piece to remove
	 * @return  An indicator of whether the operation modified the bullpen.
	 */
	public Boolean removePiece (PieceContainer piece) {	
		if(pieces.remove(piece))
		{
			logic.afterPieceRemoved(this, piece);
			return true;
		}
		return false;
	}
	
	
	/**
	 *  Adds given piece to the bullpen.
	 *  
	 * @param piece  the piece to add
	 * @return  An indicator of whether the operation modified the bullpen.
	 */
	public Boolean addPiece (PieceContainer piece) {		 
		 return pieces.add(piece);
	}
	
	
	/**
	 *  Returns a given piece to the bullpen at given index.
	 *  
	 *  Should only be called by the bullpen logic.
	 *  
	 * @param piece  the piece to add
	 * @param index  the index of this piece, must be within bounds
	 * @return  Indicator of whether the bullpen now contains that piece.
	 */
	Boolean addPieceAt(PieceContainer piece, int index) {
		pieces.add(index, piece);
		
		return pieces.contains(piece);
	}
	

	/** @return The number of pieces in the bullpen. */
	public int getSize()
	{
		return pieces.size();
	}
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	/** @return The list of pieces in the bullpen. */
	public ArrayList<PieceContainer> getPieces()
	{
		return pieces;
	}
	/** @return The piece that is currently selected in the bullpen. */
	public PieceContainer getPieceSelected()
	{
		return pieceSelected;
	}
	
	/**
	 *  Returns the piece at index in the bullpen.
	 *  
	 *  @param index  the index of the piece, must be within bounds
	 *  @return The piece contained at index. */
	public PieceContainer getPiece(int index) {
		return pieces.get(index);
	}
	
	/** 
	 *  Returns the index in the bullpen of the given piece.
	 *  
	 *  @param piece  the piece to return the index of
	 *  @return The index of the given Piece. */
	public int getLocation(PieceContainer piece){
		return pieces.indexOf(piece);
	}
	
	/** 
	 *  Sets the given piece as the currently selected piece.
	 *  
	 *  Sets the given piece as selected, and will replace any
	 *  currently selected piece with the given one.
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
