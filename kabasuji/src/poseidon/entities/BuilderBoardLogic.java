package poseidon.entities;
/**
 * Handles the actions on the board in Builder mode.
 * @author Natalia
 * @author Alex Titus
 */
public class BuilderBoardLogic implements IBoardLogic {

	
	/**
	 *  Constructor.
	 */
	public BuilderBoardLogic() {
		
	}
	
	
	/** @return  False - pieces shouldn't be added to the list of pieces on the board. */
	public Boolean shouldAddList() {
		return false;
	}
	
	
	/**
	 *  Removes a piece from the board.
	 * 
	 *  Note: Removing pieces from the board in builder shouldn't be possible, however this function is created
	 *  in order to make the Undo action possible. Shouldn't be called in any case except undo.
	 * 
	 *  @param board - The board the piece is removed from
	 *  @param piece - The container of the piece that needs to be removed
	 *  @return  Indicator whether operation could be completed.
	 */
	public Boolean shouldRemovePiece (Board board, PieceContainer piece){
		return true;											

	}
	
	
	/** @return  False - cannot select pieces on the board in Builder. */
	public Boolean canSelectPieces(){
		return false;
	}
	
	
	/**
	 *  Indicates if this move is valid given game logic.
	 *  
	 *  Piece must only be on board.
	 *  
	 *  @param board  the board being checked
	 *  @param piece  the piece prospectively being placed
	 *  @param location  the (row, col) coordinates of the anchor point of piece
	 *  @return  Indicator of whether prospective move is valid.
	 */
	public Boolean isValid(Board board, PieceContainer piece, Point location)
	{
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();		//finds the theoretical row of the square
			int pointCol = pt.getCol() + location.getCol();		//finds the theoretical col of the square
			if (pointRow >= Board.MAXROWS || pointCol >= Board.MAXCOLS
					|| pointRow < 0 || pointCol < 0) {
				//Compares it to 11, since it's the largest number
				//Checks that the piece isn't outside the border
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 *  Determines if a Piece with part at (row, col) can be selected.
	 *
	 *  @param board  the board being searched
	 *  @param row  the row of the selected square
	 *  @param col  the column of the selected square
	 *  @return  False - pieces can't be selected in Release.
	 */
	@Override
	public Boolean selectablePieceAt(Board board, int row, int col)
	{
		return false;
	}

	
	/**
	 *  @return  True - can edit board during building. */
	@Override
	public Boolean canEdit() {
		return true;
	}
	
	
	/**
	 *  Fills the squares covered by the given piece.
	 *  
	 *  @param board  the board to modify
	 *  @param piece  The piece used to fill squares
	 */
	@Override
	public void placePiece(Board board, PieceContainer piece){
		Point location = piece.getLocation();
		Square[][] playArea = board.getPlayArea();
		int gamemode = 0;
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				if(playArea[i][j].getType() > 0)
				{
					gamemode = playArea[i][j].getType();
					break;  // Sometimes I wish there would be a double-break
				}
			}
			if(gamemode > 0)  // Then I wouldn't have to do this
			{
				break;
			}
		}
		
		//fills the squares with the piece points
		for (Point pt : piece.getPiece().getPiece()) {
			int pointRow = pt.getRow() + location.getRow();
			int pointCol = pt.getCol() + location.getCol();
			switch(gamemode)
			{
			case LevelModel.PUZZLE:
				playArea[pointRow][pointCol] = new PuzzleSquare(false, false);
				break;
			case LevelModel.LIGHTNING:
				playArea[pointRow][pointCol] = new LightningSquare(false);
				break;
			case LevelModel.RELEASE:
				playArea[pointRow][pointCol] = new ReleaseSquare(false, false, new ReleaseNumber(-1, -1));
				break;
			}
		}
	}
	
	
	/** 
	 *  Sets the square at (row, col) to be a hint, if possible.
	 *  
	 *  @param board  the board to modify
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 */
	public void toggleHint(Board board, int row, int col)
	{
		Square[][] playArea = board.getPlayArea();
		
		// Figure out the gamemode
		int gamemode = 0;
		for(int i = 0; i < Board.MAXROWS; i++)
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				if(playArea[i][j].getType() > 0)
				{
					gamemode = playArea[i][j].getType();
					break;  // Sometimes I wish there would be a double-break
				}
			}
			if(gamemode > 0)  // Then I wouldn't have to do this
			{
				break;
			}
		}
		
		// If the gamemode is Lightning or the square is unplayable
		if(gamemode == LevelModel.LIGHTNING || playArea[row][col].getType() < 0)
		{
			// Then bail, can't make a hint
			return ;
		}
		
		// Else make a hint
		if(gamemode == LevelModel.PUZZLE)
		{
			PuzzleSquare newHintSquare = (PuzzleSquare) playArea[row][col];
			// Toggle hint
			if(newHintSquare.getIsHint())
			{
				newHintSquare.setIsHint(false);
			}
			else
			{
				newHintSquare.setIsHint(true);
			}
		}
		else
		{
			ReleaseSquare newHintSquare = (ReleaseSquare) playArea[row][col];
			// Toggle hint
			if(newHintSquare.getIsHint())
			{
				newHintSquare.setIsHint(false);
			}
			else
			{
				newHintSquare.setIsHint(true);
			}
		}
	}
	
	
	/** 
	 *  Does nothing, can't set ReleaseNumbers during gameplay.
	 *  
	 *  @param board  the board to modify
	 *  @param row  the row of the square to make a hint
	 *  @param col  the column of the square to make a hint
	 *  @param rn  the ReleaseNumber to set
	 */
	public void setReleaseNumber(Board board, int row, int col, ReleaseNumber rn)
	{
		Square[][] playArea = board.getPlayArea();
		ReleaseSquare newRNSquare = (ReleaseSquare) playArea[row][col];
		newRNSquare.setNumber(rn);
	}
}
