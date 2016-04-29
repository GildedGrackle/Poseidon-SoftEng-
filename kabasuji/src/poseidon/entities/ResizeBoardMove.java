package poseidon.entities;

import poseidon.common.view.BoardView;

/**
 *  Changing the size of the Board.
 *  
 *  @author Natalia
 *  @author Alex Titus
 */
public class ResizeBoardMove implements IMove{
	/** The GUI of the Board being modified. */
	BoardView view;
	/** Old number of rows. */
	int oldHeight;
	/** Old number of columns. */
	int oldWidth;
	/** New number of rows. */
	int newHeight;
	/** New number of columns. */
	int newWidth;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  the GUI of the Board being modified
	 *  @param oldHeight  the previous number of rows of the Board
	 *  @param oldWidth  the previous number of columns of the Board
	 *  @param newHeight  the new number of rows of the Board
	 *  @param newWidth  the new number of columns of the Board
	 */
	public ResizeBoardMove(BoardView view, int oldHeight, int oldWidth, int newHeight, int newWidth) {
		this.view = view;
		this.oldHeight = oldHeight;
		this.oldWidth = oldWidth;
		this.newHeight = newHeight;
		this.newWidth = newWidth;
	}
	
	
	public Boolean isValid() {
		return false;						//TODO: change return value
	}
	
	
	/**
	 *  Sets all Squares outside of newHeight and newWidth to unplayable.
	 *  
	 *  @return  indicator whether operation was successful
	 */
	public Boolean doMove() {
		if(isValid())
		{
			// TODO resizeBoardMove
			return true;
		}
		// Else failure
		return false;
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}
