package poseidon.entities;

import java.util.Stack;

/**
 *  Stores and handles moves made during a session.
 *  
 *  getLastMove should only be called if in a Builder session.
 *  
 *  @author 
 *  @author Alex Titus
 */
public class UndoManager {
	Stack<IMove> movesMade;
	static UndoManager undoManagerInstance = null;
	
	
	/**
	 *  Constructor.
	 */
	UndoManager() {
		movesMade = new Stack<IMove>();
	}
	
	
	/**
	 *  Access control for singleton.
	 * @return  the single instance of this class
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
	 * @param move
	 * @return  indication if operation completed successfully
	 */
	public Boolean recordMove(IMove move) {
		movesMade.push(move);
		return true;
	}
	
	
	/**
	 *  Gets the most recently made Move from the undo stack.
	 *  
	 *  This removes the Move from the undo stack.
	 * @return  the most recently made Move
	 */
	public IMove getLastMove() {
		return movesMade.pop();
	}

	
}
