package poseidon.entities;

import java.util.Deque;
import java.util.Stack;

/**
 *  Stores and handles moves undone during a session.
 *  
 *  getLastMove() should only be called if in a Builder session. Moves are lost if 
 *  
 *  @author Natalia?
 *  @author Alex Titus
 *
 */
public class RedoManager {

	/** Store of moves that have been made and can be undone. */
	Stack<IMove> movesMade;
	
	/** Instance for singleton. */
	static RedoManager redoManagerInstance = null;
	
	
	/**
	 *  Constructor.
	 */
	RedoManager() {
		movesMade = new Stack<IMove>();
	}
	
	
	/**
	 *  Access control for singleton.
	 *  
	 *  @return  The single instance of this class.
	 */
	public static RedoManager instance () {
		// If instance hasn't been created yet
		if(redoManagerInstance == null)
		{
			// Then create it
			redoManagerInstance = new RedoManager();
		}
		return redoManagerInstance;
	}
	
	
	/**
	 *  Pushes the move onto the redo stack.
	 *  
	 *  @param move  move to record
	 *  @return  Indicator of whether the operation completed successfully.
	 */
	public Boolean recordMove(IMove move) {
		movesMade.push(move);
		return true;
	}
	
	
	/**
	 *  Gets the most recently made move from the redo stack.
	 *  
	 *  This removes the move from the redo stack. Redo stack
	 *  must be checked for emptiness before calling this method.
	 *  
	 * @return  The most recently made move.
	 */
	public IMove getLastMove() {
		return movesMade.pop();
	}
	
	
	/**
	 *  Clears redo stack if a new move is made.
	 *  
	 *  @return  Indicator whether operation completed successfully.
	 */
	public Boolean clearMove() {
		movesMade.clear();
		return true;
	}
	
	
	/** @return  Indicator of whether the redo stack is empty. */
	public Boolean isEmpty()
	{
		return movesMade.empty();
	}
}
