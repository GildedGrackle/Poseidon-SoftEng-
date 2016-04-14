package poseidon.entities;

import java.util.ArrayList;

public class RedoManager {
	ArrayList<IMove> movesMade = new ArrayList<IMove>();
	UndoManager undoManagerInstance;
	
	RedoManager() {
		//TODO: change return value
	}
	
	RedoManager instance () {
		return null;						//TODO: change return value
	}
	
	Boolean recordMove(IMove Move) {
		return false;						//TODO: change return value
	}
	
	IMove getLastMove() {
		return null;						//TODO: change return value
	}
	
	Boolean clearMove() {
		return false;						//TODO: change return value
	}
}
