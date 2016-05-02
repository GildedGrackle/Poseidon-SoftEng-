package poseidon.entities;


/**
 *  Changing the size of the Board.
 *  
 *  @author Natalia
 *  @author Alex Titus
 */
public class ResizeBoardMove implements IMove{
	/** The GUI of the Board being modified. */
	Board board;
	/** Old number of rows. */
	int oldHeight;
	/** Old number of columns. */
	int oldWidth;
	/** New number of rows. */
	int newHeight;
	/** New number of columns. */
	int newWidth;
	/** Backup board for undoing */
	Board backupBoard;
	/** To allow the proper squares to be built. */
	int gamemode;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI of the Board being modified
	 *  @param gamemode  to allow the proper squares to be built
	 *  @param oldHeight  the previous number of rows of the Board
	 *  @param oldWidth  the previous number of columns of the Board
	 *  @param newHeight  the new number of rows of the Board
	 *  @param newWidth  the new number of columns of the Board
	 */
	public ResizeBoardMove(Board board, int gamemode, int oldHeight, int oldWidth, int newHeight, int newWidth) {
		this.board = board;
		this.gamemode = gamemode;
		this.oldHeight = oldHeight;
		this.oldWidth = oldWidth;
		this.newHeight = newHeight;
		this.newWidth = newWidth;
		this.backupBoard = null;
	}
	
	
	/**
	 * Checks if the resize is valid.
	 * 
	 * Should be only valid in builder mode, and the board size has to be between 6*6 and 12*12.
	 */
	public Boolean isValid() {
		if (board.getLogic() instanceof BuilderBoardLogic) {
			if(newHeight<=12 && newWidth<=12 && newHeight>=6 && newWidth>=6) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 *  Sets all Squares outside of newHeight and newWidth to unplayable.
	 *  
	 *  @return  indicator whether operation was successful
	 */
	public Boolean doMove() {
		if(isValid())
		{
			backupBoard = board; 			// Backup in case we want to undo the move
			board.resizeBoard(newHeight, newWidth, gamemode);
			return true;
		}
		// Else failure
		return false;
	}
	
	/**
	 * Reverts the performed move.
	 */
	public Boolean undoMove() {
		if(backupBoard !=null){				// Backup board would be null if the move wasn't performed
			board.setBoard(backupBoard.getPlayArea()); 
			return true;
		}
		return false;
	}
}
