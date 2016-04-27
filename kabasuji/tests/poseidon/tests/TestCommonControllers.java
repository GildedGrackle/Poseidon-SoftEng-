package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import junit.framework.TestCase;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.BullpenController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.IBullpenLogic;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.XMLHandler;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelView;
import poseidon.tests.TestMouseEvents;
import poseidon.tests.TestEntities.TestBullpenLogic;

public class TestCommonControllers extends TestMouseEvents{

	LevelPlayerView view;
	LevelPlayerModel model;
	Piece squigglePiece;
	Piece linePiece;
	PieceContainer squiggleCont;
	PieceContainer lineCont;
	Point location;
	BullpenView bullpenView;
	Bullpen testBullpen;
	LevelModel testLevel;
	Board testBoard;
	Point testLocation;
	LevelBuilderModel builderModel;
	BoardView board;
	ArrayList<PieceContainer> pieces;

	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public class TestBullpenLogic extends IBullpenLogic{

		@Override
		public Boolean shouldAddPiece(Bullpen bullpen, PieceContainer piece) {
			return true;
		}

		@Override
		public Boolean shouldRemovePiece(Bullpen bullpen, PieceContainer piece) {
			return true;
		}
		
	}
	
	public void setUp(){
		testLevel = XMLHandler.getTestLevels()[0];
		int[] currentLvl = new int[]{
				1, 1, 1
		};
		
		testBoard = testLevel.getBoard();
		testLocation = new Point(1,1);
		model = new LevelPlayerModel(currentLvl, testLevel);
		builderModel = new LevelBuilderModel();
		
		Point[] piece1Points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3)
		};
		squigglePiece = new Piece(piece1Points);
		
		Point[] piece2Points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(0, 3),
				new Point(0, 4),
				new Point(0, 5)
		};
		linePiece = new Piece(piece2Points);	
		location = new Point(0,0);
		
		Point squiggleLoc = new Point(0,0);
		Point lineLoc = new Point(0,6);
		squiggleCont = new PieceContainer(squigglePiece, squiggleLoc);
		lineCont = new PieceContainer(linePiece, lineLoc);
		
		testBullpen = testLevel.getPlayableBullpen();
		
		testBullpen.addPiece(lineCont);
		testBullpen.addPiece(squiggleCont);
		
		view = new LevelPlayerView(model);
	
		}
	
	public void tearDown(){
		view.getfrmKabasuji().dispose();
	}
	
	public void testHFlip(){
		
	}
	
	public void testVFlip(){
		
	}
	
	public void testRedoManger(){
		
	}
	
	public void testCCWController(){
		
	}
	
	public void testRotateCW(){
		
	}
	
	/**
	 * Tests the board controllers using a puzzle board. 
	 * Does not attempt to do mouse double clicked as that is only available in builder
	 */
	public void testPuzzleBoardCont(){

		squiggleCont.setIsSelected(true);
		testBullpen.setPieceSelected(squiggleCont);
		LevelView lvlView = new LevelView(model, view); 
		PieceView squigglePieceView = new PieceView(squiggleCont, lvlView.getBullpen());
		PieceView linePieceView = new PieceView(lineCont, lvlView.getBullpen());
		lvlView.getBullpen().setSelectedPiece(squigglePieceView);
		
		BoardController controller = new BoardController(testLevel, lvlView);
		
		MouseEvent enterBoard = createEntered(lvlView, lvlView.getBoard(), 0, 0);
		controller.mouseEntered(enterBoard); 
		
		assertEquals(squigglePieceView.getModel(), lvlView.getBoard().getActiveDragging().getModel());
		
		java.awt.Point oldLocation = new java.awt.Point(lvlView.getBoard().getActiveDragging().getY(), lvlView.getBoard().getActiveDragging().getX());
		
		MouseEvent movePiece = createMoved(lvlView, lvlView.getBoard(), 4, 5);
		controller.mouseMoved(movePiece);
		
		assertNotSame(lvlView.getBoard().getActiveLocation(), oldLocation);
		
		MouseEvent pressed = createBoardPressed(lvlView, lvlView.getBoard(), 4, 5);
		controller.mousePressed(pressed);
		
		assertFalse(testBullpen.getPieces().contains(squiggleCont));
		assertFalse(lvlView.getBoard().getPieces().isEmpty());
		assertTrue(testBoard.getPieces().contains(squiggleCont));
		assertEquals(lvlView.getBoard().getActiveDragging(), null);
		
		java.awt.Point location2 =  new java.awt.Point(squigglePieceView.getY(), squigglePieceView.getX());
		
		controller.mousePressed(pressed);
		assertEquals(squigglePieceView.getModel(), lvlView.getBoard().getActiveDragging().getModel());
		assertTrue(lvlView.getBoard().getPieces().isEmpty());
		
		MouseEvent dragPiece = createDragged(lvlView, lvlView.getBoard(), 2, 2);
		controller.mouseDragged(dragPiece);
		
		assertNotSame(location2, lvlView.getBoard().getActiveLocation());
		
		MouseEvent released = createReleased(lvlView, lvlView.getBoard(), 2, 2);
		controller.mouseReleased(released);
		
		assertTrue(testBoard.getPieces().contains(squiggleCont));
		assertEquals(lvlView.getBoard().getActiveDragging(), null);
		assertNotSame(location2, squigglePieceView.getLocation());
		
		pressed = createBoardPressed(lvlView, lvlView.getBoard(), 2, 2);
		controller.mousePressed(pressed);
		dragPiece = createDragged(lvlView, lvlView.getBoard(), -300, -300);
		controller.mouseDragged(dragPiece);
		released = createReleased(lvlView, lvlView.getBoard(), -300, -300);
		controller.mouseReleased(released);
		
		assertTrue(lvlView.getBoard().getPieces().isEmpty());
		assertTrue(testBullpen.getPieces().contains(squiggleCont));
		
		lineCont.setIsSelected(true);
		testBullpen.setPieceSelected(lineCont);
		lvlView.getBullpen().setSelectedPiece(linePieceView);
		
		enterBoard = createEntered(lvlView, lvlView.getBoard(), 0, 0);
		controller.mouseEntered(enterBoard);
		
		assertEquals(linePieceView.getModel(), lvlView.getBoard().getActiveDragging().getModel());
		
		MouseEvent removePiece = createExited(lvlView, lvlView.getBoard(), 0, 0);
		controller.mouseExited(removePiece);
		
		assertNull(lvlView.getBoard().getActiveDragging());	
	}
		
	public void testBullpenControllerPuzzle(){
		LevelView lvlView = new LevelView(model, view); 
		PieceView squigglePieceView = new PieceView(squiggleCont, lvlView.getBullpen());
		PieceView linePieceView = new PieceView(lineCont, lvlView.getBullpen());
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		
		BullpenController bullpenController = new BullpenController(testBullpen, bullpenView, board);
		MouseEvent selectPiece = createBullpenPressed(lvlView, bullpenView, 0, 0);
		
		bullpenController.mousePressed(selectPiece);
		
		assertEquals(testBullpen.getPieceSelected(), testBullpen.getPiece(0));
		
		selectPiece = createBullpenPressed(lvlView, bullpenView, 200, 0);
		bullpenController.mousePressed(selectPiece);
		
		assertFalse(testBullpen.getPieceSelected() == testBullpen.getPiece(0));
		assertEquals(testBullpen.getPieceSelected(), testBullpen.getPiece(4));
		
		
	}
	
	
}
