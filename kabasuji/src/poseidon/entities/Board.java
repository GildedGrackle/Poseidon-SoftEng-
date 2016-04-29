package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

/**
 * Playable board in all variations. 
 * 
 * @author Natalia
 * @author Alex Titus
 * @author Jacob Wennersten
 */
public class Board {
	/** Largest amount of rows, set for convenience and optional future modification. */
	public static final int MAXROWS = 12;
	/** Largest amount of columns, set for convenience and optional future modification. */
	public static final int MAXCOLS = 12;
	
	/** Initial play area. Changes when constructor is called. */
	Square [][] playArea = new Square [MAXROWS][MAXCOLS];	
	
	/** A list of all the pieces that are currently positioned on the board. */
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	
	/** The piece that is currently selected. */ 
	PieceContainer activeDragged;
	
	/** The coordinates of the origin of the active dragging piece. */
	Point activeSource;
	
	/** The functioning of the board, game mode/builder. */
	IBoardLogic logic;	
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param playArea  the total board area, of dimensions MAXROW x MAXCOLS
	 *  @param logic  the game-type-specific logic to associate with the Board
	 */
	public Board (Square [][] playArea, IBoardLogic logic) {
		this.playArea = playArea;
		pieces = new ArrayList<PieceContainer>();
		this.logic = logic;
	}
	
	
	/**
	 * Finds the PieceContainer that is located on a selected square and returns it.
	 * 
	 * @param row  row part of selected square's coordinates
	 * @param col  column part of selected square's coordinates
	 * @return  PieceContainer found at (row, col)
	 */
	PieceContainer findPiece (int row, int col) {
		for (int i=0; i<pieces.size(); i++) {							//Iterates through all the pieces on a board
			Point pivot = pieces.get(i).getLocation();
			for (Point p : pieces.get(i).getPiece().getPiece() ) {
				if (p.getRow() + pivot.getRow() == row && p.getCol() + pivot.getCol() == col){
																		//checks if a point on the piece is located at 
																		// given point
					return pieces.get(i);
				}
			}
		}
		return null;													//No piece found
	}
	
	
	/*
	 *  Fills the squares covered by the given piece
	 */
	private void placePiece(PieceContainer piece){
		Point location = piece.getLocation();
		Square[][] playArea = this.getPlayArea();
		
		//fills the squares with the piece points
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();
			int pointCol = pt.getCol() + location.getCol();
			playArea[pointRow][pointCol].fill();
		}

		}
	
	
	/**
	 * Adds piece to a specific pivot point on the board depending on the type of board.
	 * 
	 * @param piece - The piece that needs to be placed on the board.
	 * @return  Boolean - Indicates whether the addition was successful.
	 */
	public Boolean addPiece (PieceContainer piece) {
		boolean shouldAdd = logic.shouldAddPiece(this, piece);
		if (shouldAdd){
		pieces.add(piece);	
		placePiece(piece);
		}
		
		return shouldAdd;
	}
	

	/**
	 * Removes a piece from the board depending on the type of board.
	 * 
	 * @param piece - The piece that needs to be removed from the board. 
	 * @return  Boolean - Indicates whether the removal was successful.
	 */
	public Boolean removePiece (PieceContainer piece) {
		boolean shouldRemove = logic.shouldRemovePiece(this, piece) 
				&& pieces.contains(piece);
		if (shouldRemove) {
			pieces.remove(piece);
		
		}
		return shouldRemove;
		
	}
		
	
	/**
	 * Deals with selected squares depending on the type.
	 * 
	 * Note:	If builder, selects/deselects the square.
	 * 			If puzzle, selects the piece that is on the square.
	 * 			If lightning/release, doesn't perform any action.
	 * 
	 * @param row
	 * @param col - parameters that indicate the square on the board that was selected
	 * @return
	 */
	public Boolean selectSquare (int row, int col) {
		if (logic instanceof BuilderBoardLogic) {
			//TODO toggle squares on/off
			return true;
		}
		PieceContainer piece = findPiece(row,col);
		if(piece != null) {
			if(logic.canSelectPieces()) {
				activeDragged.setIsSelected(false);
				activeDragged = piece;
				piece.setIsSelected(true);
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 *  Determines if the given piece can be played at the given location.
	 *  
	 *  @param piece  the piece being placed
	 *  @param location  the (row, col) location of the piece's anchor point
	 *  @return  Indicator whether the placement is valid.
	 */
	public boolean isValid(PieceContainer piece, Point location)
	{
		return logic.isValid(this, piece, location);
	}
	
	
	/**
	 *  @return  Indicates whether the board's play area can be modified.
	 */
	public boolean canEdit(){
		Boolean editBoard = logic.canEdit(this);
		return editBoard;
	}
	
	
	/**
	 *  Determines if a piece with part at (row, col) can be selected.
	 *  
	 * @param row  the row of the square the piece would contain
	 * @param col  the column of the square the piece would contain
	 * @return  Indicator of whether there is a piece that can be selected at (row, col).
	 */
	public boolean canSelect(int row, int col)
	{
		return logic.selectablePieceAt(this, row, col);
	}
	
	
	/**
	 *  Returns the active dragging piece to its source.
	 */
	public void returnPiece()
	{
		activeDragged.setLocation(activeSource);
		addPiece(activeDragged);
	}
	
	
	/** @return  The entire play area (both playable and unplayable squares). */
	public Square[][] getPlayArea (){
		return this.playArea;
	}
	
	
	/** @return  The list of pieces on the board. */
	public ArrayList<PieceContainer> getPieces()
	{
		return pieces;
	}
	
	
	/** @return  The number of rows in the play area. */
	int getRows () {
		return this.playArea.length;
	}
	
	
	/** @return  The number of columns in the play area. */
	int getCols () {
		return this.playArea[0].length;
	}

	
	/** @return  The square at location (row, col). */
	public Square getSquare(int row, int col) {
		return playArea[row][col];
	}
	
	
	/** @return  The PieceContainer currently being moved on the board. */
	public PieceContainer getActiveDragged()
	{
		return activeDragged;
	}
	
	
	/** @return  The (row, col) starting point of the active dragged PieceContainer. */
	public Point getActiveSource()
	{
		return activeSource;
	}
	
	
	/**
	 *  Sets the specified Piece as the active dragging Piece.
	 *  
	 *  Empties the Squares occupied by the Piece.
	 *  
	 *  @param piece  a Piece that is on the Board
	 */
	public void setActiveDragged(PieceContainer piece)
	{
		
		// If not resetting activeDragged
		if(piece != null && activeSource.getCol() != -1 && activeSource.getRow() != -1)
		{
			// Then remove new active dragging Piece from Board
			logic.shouldRemovePiece(this, piece);
		}
		activeDragged = piece;
	}
	
	
	/**
	 *  Sets the starting point of the active dragged PieceContainer.
	 *  
	 *  @param activeSource  the starting Point
	 */
	public void setActiveSource(Point activeSource)
	{
		this.activeSource = activeSource;
	}
	
	
	/**
	 *  Sets the square at location to newSquare and returns the replaced square.
	 *  
	 * @param location  the (row, col) coordinates of the square to modify
	 * @param newSquare  the square to place at location
	 * @return  The square that was replaced.
	 */
	public Square setSquare(Point location, Square newSquare)
	{
		Square oldSquare = playArea[location.getRow()][location.getCol()];
		playArea[location.getRow()][location.getCol()]	= newSquare;
		
		return oldSquare;
	}
}
