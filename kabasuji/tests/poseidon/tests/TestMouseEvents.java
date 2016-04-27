package poseidon.tests;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.player.view.LevelView;

public class TestMouseEvents extends TestCase{
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createBoardPressed (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createEntered (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_ENTERED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createExited (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_EXITED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createRightClick (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, true);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createReleased (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_RELEASED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createClicked (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 1, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createDoubleClicked (LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_CLICKED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 2, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createDragged(LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_DRAGGED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	/** (dx,dy) are offsets into the widget space. Feel Free to Use as Is. */
	public MouseEvent createMoved(LevelView lvlView, BoardView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_MOVED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	public MouseEvent createBullpenPressed (LevelView lvlView, BullpenView view, int dx, int dy) {
		MouseEvent me = new MouseEvent(lvlView, MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	public void testEmpty(){
		
	}
}
