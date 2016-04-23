package poseidon.entities;
import java.util.ArrayList;

import poseidon.entities.PieceContainer;

/**
 * Playable board in all variations. 
 * 
 * @author Natalia
 * @author Alex Titus
 */
public class Board {
	/**Largest amount of rows and columns, set for convinience and optional future modification.*/
	public static final int MAXROWS = 12;
	public static final int MAXCOLS = 12;
	
	/**Initial playArea. Changes when constructor is called. */
	Square [][] playArea = new Square [MAXROWS][MAXCOLS];	
	
	/**A list of all the pieces that are currently positioned on the board*/
	ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
	
	/**Piece that is currently selected*/ 
	PieceContainer activeDragged;
	
	/** The coordinates of the origin of the active dragging Piece. */
	Point activeSource;
	
	/**The functioning of the board, game mode/builder*/
	IBoardLogic logic;	
	
	
	Board (Square [][] playArea, IBoardLogic logic) {
		this.playArea = playArea;
		pieces = new ArrayList<PieceContainer>();
		this.logic = logic;
	}
	
	/**
	 * Finds the piece container that is located on a selected square and returns it.
	 * 
	 * @param row
	 * @param col
	 * @return PieceContainer 
	 */
	PieceContainer findPiece (int row, int col) {
		for (int i=0; i<pieces.size(); i++) {							//Iterates through all the pieces on a board
			Point pivot = pieces.get(i).getLocation();  // TODO need to check if this returns null
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
	
	/**
	 * Adds piece to a specific pivot point on the board depending on the type of board.
	 * 
	 * @param row, col - The location on the board where the pivot of the piece should be. 
	 * @param piece - The piece that needs to be placed on the board.
	 * @return Boolean - Indicates whether the addition was successful.
	 */
	public Boolean addPiece (PieceContainer piece) {
		return logic.addPiece(this, piece);
	}
	
	/**
	 * Removes a Piece from the board depending on the type of board.
	 * 
	 * @param piece - The Piece that needs to be removed from the board. 
	 * @return Boolean - Indicates whether the removal was successful.
	 */
	public Boolean removePiece (PieceContainer piece) {
		return logic.removePiece(this, piece);
	}
	
	/**
	 * Displays the hint that was chosen for the board.
	 */
	void showHint () {
		//TODO: Change return value
	}
	
	/**
	 * Adds piece to the ArrayList of pieces.
	 * 
	 * @param piece - The piece container of the piece that needs to be added.
	 */
	void addPieceToList (PieceContainer piece) {
		pieces.add(piece);
	}
	
	
	/**
	 * Deals with selected squares depending on the type.
	 * 
	 * Note:	If builder, selects/deselcts the square.
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
			if(logic.selectPiece(this, piece)) {
				activeDragged.setIsSelected(false);
				activeDragged = piece;
				piece.setIsSelected(true);
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 *  Determines if the given Piece can be played at the given location.
	 *  
	 * @return  indicator if the placement is valid
	 */
	public boolean isValid(PieceContainer piece, Point location)
	{
		return logic.isValid(this, piece, location);
	}
	
	
	/**
	 *  Determines if a Piece with part at (row, col) can be selected.
	 *  
	 * @param row  the row of the Square the Piece would contain
	 * @param col  the column of the Square the Piece would contain
	 * @return  indicator if there is a Piece that can be selected at (row, col)
	 */
	public boolean canSelect(int row, int col)
	{
		return logic.canSelect(this, row, col);
	}
	
	
	/**
	 *  Returns the active dragging Piece to its source.
	 */
	public void returnPiece()
	{
		activeDragged.setLocation(activeSource);
		addPiece(activeDragged);
	}
	
	public Square [] [] getPlayArea (){
		return this.playArea;
	}
	
	public ArrayList<PieceContainer> getPieces()
	{
		return pieces;
	}
	
	int getRows () {
		return this.playArea.length;
	}
	
	int getCols () {
		return this.playArea[0].length;
	}
	
	int getMaxRows() {
		return MAXROWS;
	}
	
	int getMaxCols() {
		return MAXCOLS;
	}
	
	public Square getSquare(int row, int col) {
		return playArea[row][col];
	}
	
	public PieceContainer getActiveDragged()
	{
		return activeDragged;
	}
	
	public Point getActiveSource()
	{
		return activeSource;
	}
	
	
	/**
	 *  Sets the specified Piece as the active dragging Piece.
	 *  
	 *  Empties the Squares occupied by the Piece.
	 * @param piece  a Piece that is on the Board
	 */
	public void setActiveDragged(PieceContainer piece)
	{
		// If not resetting activeDragged
		if(piece != null && activeSource.getCol() != -1 && activeSource.getRow() != -1)
		{
			// Then remove new active dragging Piece from Board
			logic.removePiece(this, piece);
		}
		activeDragged = piece;
	}
	
	public void setActiveSource(Point activeSource)
	{
		this.activeSource = activeSource;
	}
	

	
	/**
	 * Removes piece from the ArrayList of pieces.
	 * 
	 * @param piece - The piece container of the piece that needs to be removed.
	 */
	void removePieceFromList (PieceContainer piece) {
		pieces.remove(piece);
	}
}
