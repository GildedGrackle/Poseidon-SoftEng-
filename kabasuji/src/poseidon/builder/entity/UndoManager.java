package builder;

import java.util.ArrayList;

public class UndoManager {
	ArrayList<IMove> movesMade = new ArrayList<IMove>();
	UndoManager undoManagerInstance;
	
	UndoManager() {
		//TODO: change return value
	}
	
	UndoManager instance () {
		return null;						//TODO: change return value
	}
	
	Boolean recordMove(IMove Move) {
		return false;						//TODO: change return value
	}
	
	IMove getLastMove() {
		return null;						//TODO: change return value
	}
	
}
