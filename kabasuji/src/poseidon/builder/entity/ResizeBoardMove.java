package builder;

public class ResizeBoardMove implements IMove{
	int oldHeight, oldWidth, newHeight, newWidth;
	
	ResizeBoardMove(int oldHeight, int oldWidth, int newHeight, int newWidth) {
		this.oldHeight = oldHeight;
		this.oldWidth = oldWidth;
		this.newHeight = newHeight;
		this.newWidth = newWidth;
	}
	
	public Boolean isValid() {
		return false;						//TODO: change return value
	}
	
	public Boolean doMove() {
		return false;						//TODO: change return value
	}
	
	public Boolean undoMove() {
		return false;						//TODO: change return value
	}
}
