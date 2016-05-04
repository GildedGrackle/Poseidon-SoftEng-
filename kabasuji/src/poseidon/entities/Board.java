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
	/**type of board. 1-Puzzle, 2-Lightning, 3-bullpen. Regardless of builder/player*/
	int type;
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
		activeSource = new Point(-1, -1);
		
		//Might be a little complicated, but determines the "type" of board. Mostly needed to deal with builder boards
		if (logic instanceof PuzzleBoardLogic) { type = 1; }
		else if (logic instanceof LightningBoardLogic) { type = 2;}
		else if (logic instanceof ReleaseBoardLogic) {type = 3;}
		else if (logic instanceof BuilderBoardLogic) {
			if (playArea[0][0] instanceof PuzzleSquare) { type = 1; }
			else if (playArea[0][0] instanceof LightningSquare) { type = 2; }
			else if (playArea[0][0] instanceof ReleaseSquare) { type = 3; }
		}
		
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
	
	
	/**
	 * Adds piece to a specific pivot point on the board depending on the type of board.
	 * 
	 * @param piece - The piece that needs to be placed on the board.
	 * @return  Boolean - Indicates whether the addition was successful.
	 */
	public Boolean addPiece (PieceContainer piece) {
		// Add piece
		logic.placePiece(this, piece);
		
		// If should add to list
		if (logic.shouldAddList()){
			// Then add it
			pieces.add(piece);
		}
		
		return true;
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
	 * Resizes the board to the requested size.
	 * 
	 * NOTE: Checks for validity while making the move itself. Nothing should be calling this function
	 * except ResizeBoardMove.
	 * @param newRow  The new number of rows that the board needs to be resized to
	 * @param newCol  The new number of columns that the board needs to be resized to
	 * @param gamemode  to allow the proper squares to be built
	 */
	void resizeBoard (int newRow, int newCol, int gamemode){
		Square[][] newBoard = new Square [Board.MAXROWS] [Board.MAXCOLS];
		
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				// If the square is outside of the resizing boundary
				if(i >= newRow || j >= newCol)
				{
					// Then make it unplayable
					newBoard[i][j] = new NonplayableSquare();
				}
				else
				{
					// Else make it playable
					switch(gamemode)
					{
					case LevelModel.PUZZLE:
						newBoard[i][j] = new PuzzleSquare(false, false);
						break;
					case LevelModel.LIGHTNING:
						newBoard[i][j] = new LightningSquare(false);
						break;
					case LevelModel.RELEASE:
						newBoard[i][j] = new ReleaseSquare(false, new ReleaseNumber(-1, -1));
						break;
					default:
						newBoard[i][j] = playArea[i][j];
					}
					
				}
			}
		}
		
		playArea = newBoard;
	}
	
	
	/**
	 *  Sets the board's play area.
	 *  
	 *  @param newBoard  the new play area
	 */
	void setBoard(Square [] [] newBoard){		
		this.playArea = newBoard;
	}
	
	
	/**
	 *  Sets the piece at (row, col) to the active dragged piece.
	 *  
	 *  @param row  the row of the selected square
	 *  @param col  the column of the selected square
	 *  @return  Indicator of whether the piece was found.
	 */
	public Boolean selectPiece(int row, int col)
	{
		for(PieceContainer pc : pieces)
		{
			int mainRow = pc.getLocation().getRow();
			int mainCol = pc.getLocation().getCol();
			for(Point pt : pc.getPiece().getPiece())
			{
				int actualRow = mainRow + pt.getRow();
				int actualCol = mainCol + pt.getCol();
				
				// If this piece has been clicked on
				if(actualRow == row && actualCol == col)
				{
					// Then set this piece as the active dragged piece
					activeDragged = pc;
					return true;  // No point in continuing, found what we were looking for
				}
			}
		}
		
		// If we get here, somehow didn't find it; most likely won't happen
		return false;
	}
	
	
	/** 
	 *  Sets the square at (row, col) to be a hint, if possible.
	 *  
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 */
	public void setHint(int row, int col)
	{
		// Just in case
		if(row >= MAXROWS || col >= MAXCOLS || row < 0 || col < 0)
		{
			return ;
		}
		
		logic.setHint(this, row, col);
	}
	
	
	/** 
	 *  Sets the square at (row, col) to have given ReleaseNumber, if possible.
	 *  
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 *  @param rn  the ReleaseNumber to set
	 */
	public void setReleaseNumber(int row, int col, ReleaseNumber rn)
	{
		// Just in case
		if(row >= MAXROWS || col >= MAXCOLS || row < 0 || col < 0 || type != LevelModel.RELEASE)
		{
			return ;
		}
		
		logic.setReleaseNumber(this, row, col, rn);
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
		Boolean editBoard = logic.canEdit();
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
	
	/**@return the logic of the board.*/
	public IBoardLogic getLogic() {
		return logic;
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

	
	/**
	 *  @param row  the row half of the location
	 *  @param col  the column half of the location
	 *  @return  The square at location (row, col).
	 */
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
			removePiece(piece);
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
