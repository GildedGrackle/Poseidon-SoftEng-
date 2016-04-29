package poseidon.entities;

import java.util.Stack;

/**
 *  Stores and handles moves made during a session.
 *  
 *  getLastMove() should only be called if in a Builder session.
 *  
 *  @author Natalia?
 *  @author Alex Titus
 */
public class UndoManager {
	
	/** Store of moves that have been made and can be undone. */
	Stack<IMove> movesMade;
	
	/** Instance for singleton. */
	static UndoManager undoManagerInstance = null;
	
	
	/**
	 *  Constructor.
	 */
	UndoManager() {
		movesMade = new Stack<IMove>();
	}
	
	
	/**
	 *  Access control for singleton.
	 *  
	 *  @return  The single instance of this class.
	 */
	public static UndoManager instance () {
		// If instance hasn't been created yet
		if(undoManagerInstance == null)
		{
			// Then create it
			undoManagerInstance = new UndoManager();
		}
		return undoManagerInstance;
	}
	
	
	/**
	 *  Pushes the move onto the undo stack.
	 *  
	 *  @param move  move to record
	 *  @return  Indicator of whether the operation completed successfully.
	 */
	public Boolean recordMove(IMove move) {
		movesMade.push(move);
		return true;
	}
	
	
	/**
	 *  Gets the most recently made move from the undo stack.
	 *  
	 *  This removes the move from the undo stack. Undo stack
	 *  must be checked for emptiness before calling this method.
	 *  
	 * @return  The most recently made move.
	 */
	public IMove getLastMove() {
		return movesMade.pop();
	}

	
	/** @return  Indicator of whether the undo stack is empty. */
	public Boolean isEmpty()
	{
		return movesMade.empty();
	}
}
