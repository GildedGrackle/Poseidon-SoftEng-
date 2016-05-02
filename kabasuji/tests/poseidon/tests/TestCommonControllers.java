package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import junit.framework.TestCase;
import poseidon.builder.controller.AboutBuilderController;
import poseidon.common.controller.BoardController;
import poseidon.common.controller.BullpenController;
import poseidon.common.controller.HorizontalFlipController;
import poseidon.common.controller.RotateCCWController;
import poseidon.common.controller.RotateCWController;
import poseidon.common.controller.VerticalFlipController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.IBullpenLogic;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.XMLHandler;
import poseidon.player.controller.ResetController;
import poseidon.player.view.LevelPlayerView;
import poseidon.player.view.LevelSelectView;
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
	LevelContainer testLevelContainer;
	LevelModel testLevel;
	Board testBoard;
	Point testLocation;
	LevelBuilderModel builderModel;
	BoardView board;
	ArrayList<PieceContainer> pieces;
	JButton button;


	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public class TestBullpenLogic implements IBullpenLogic{

		@Override
		public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece)
		{
			return ;  // TODO  change this to suit purpose
		}
		
	}
	
	public void setUp(){
		testLevel = XMLHandler.getTestLevels()[0];
		int[] currentLvl = new int[]{
				1, 1, 1
		};
		testLevelContainer = new LevelContainer("Test level X", 0, 0, testLevel, 0);  // TODO set this to whatever
		testBoard = testLevel.getBoard();
		testLocation = new Point(1,1);
		model = new LevelPlayerModel(currentLvl, testLevelContainer);
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
		view.getFrame().dispose();
	}

	
	/**
	 * Tests the board controllers using a puzzle board. 
	 * Does not attempt to do mouse double clicked as that is only available in builder
	 */
	public void testPuzzleBoardCont(){

		testBullpen.setPieceSelected(squiggleCont);
		LevelView lvlView = new LevelView(model, view); 
		testLevel.incrementLimit();
		testLevel.incrementLimit();
		
		BoardController controller = new BoardController(view, testLevel, lvlView);
		
		MouseEvent movePiece = createMoved(lvlView, lvlView.getBoard(), 4, 5);
		controller.mouseMoved(movePiece);
		
		assertEquals(model.getPlayingLevel().getLevel().getBoard().getActiveDragged(), squiggleCont);
		
		MouseEvent pressed = createBoardPressed(lvlView, lvlView.getBoard(), 4, 5);
		controller.mousePressed(pressed);
		
		assertFalse(testBullpen.getPieces().contains(squiggleCont));
		assertTrue(testBoard.getPieces().contains(squiggleCont));
		assertEquals(model.getPlayingLevel().getLevel().getBoard().getActiveDragged(), null);

		testLevel.incrementLimit();
		testLevel.incrementLimit();
		controller.mousePressed(pressed);
		assertEquals(squiggleCont, model.getPlayingLevel().getLevel().getBoard().getActiveDragged());
		assertTrue(model.getPlayingLevel().getLevel().getBoard().getPieces().isEmpty());
		
		MouseEvent dragPiece = createDragged(lvlView, lvlView.getBoard(), 30, 2);
		controller.mouseDragged(dragPiece);
		
		MouseEvent released = createReleased(lvlView, lvlView.getBoard(), 30, 2);
		controller.mouseReleased(released);
		
		assertTrue(testBoard.getPieces().contains(squiggleCont));
		assertEquals(model.getPlayingLevel().getLevel().getBoard().getActiveDragged(), null);
		
		pressed = createBoardPressed(lvlView, lvlView.getBoard(), 30, 2);
		controller.mousePressed(pressed);
		dragPiece = createDragged(lvlView, lvlView.getBoard(), -1000, -1000);
		controller.mouseDragged(dragPiece);
		released = createReleased(lvlView, lvlView.getBoard(), -1000, -1000);
		controller.mouseReleased(released);
		
		testLevel.incrementLimit();
		testLevel.incrementLimit();
		testLevel.incrementLimit();
		
		assertTrue(model.getPlayingLevel().getLevel().getBoard().getPieces().isEmpty());
		assertTrue(testBullpen.getPieces().contains(squiggleCont));
		
		testBullpen.setPieceSelected(lineCont);
		
		assertTrue(lineCont.getIsSelected());
		assertEquals(testBullpen.getPieceSelected(), lineCont);
		
		movePiece = createMoved(lvlView, lvlView.getBoard(), 4, 5);
		controller.mouseMoved(movePiece);;
		
		assertEquals(lineCont,
				model.getPlayingLevel().getLevel().getBoard().getActiveDragged());
		
		MouseEvent removePiece = createExited(lvlView, lvlView.getBoard(), 0, 0);
		controller.mouseExited(removePiece);
		
		assertNull(model.getPlayingLevel().getLevel().getBoard().getActiveDragged());	
	}
		
	public void testBullpenControllerPuzzle(){
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		
		BullpenController bullpenController = new BullpenController(testBullpen, bullpenView, board);
		MouseEvent selectPiece = createBullpenPressed(lvlView, bullpenView, 10, 0);
		
		bullpenController.mousePressed(selectPiece);
		
		assertEquals(testBullpen.getPieceSelected(), testBullpen.getPiece(0));
		
		selectPiece = createBullpenPressed(lvlView, bullpenView, 200, 0);
		bullpenController.mousePressed(selectPiece);
		
		assertFalse(testBullpen.getPieceSelected() == testBullpen.getPiece(0));
		assertEquals(testBullpen.getPieceSelected(), testBullpen.getPiece(4));
			
	}
	
	public void testRotateCWController(){
		
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		testBullpen.setPieceSelected(squiggleCont);
		
		RotateCWController rotateCWController = new RotateCWController(bullpenView);
		
		button = lvlView.getCW();
		ActionEvent cwPress = buttonPress(button);
		rotateCWController.actionPerformed(cwPress);
		
		Point[] rotatePoints = new Point[] {
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 1),
				new Point(2, 0),
				new Point(3, 0)
		};
		
		
		assertEquals(new Piece(rotatePoints), squiggleCont.getPiece());
		
	}
	

	public void testRotateCCWController(){
		
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		testBullpen.setPieceSelected(squiggleCont);
		
		RotateCCWController rotateCCWController = new RotateCCWController(bullpenView);
		
		button = lvlView.getCCW();
		ActionEvent ccwPress = buttonPress(button);
		rotateCCWController.actionPerformed(ccwPress);
		
		Point[] originalPoints = new Point[] {
				new Point(0, 2),
				new Point(1, 2),
				new Point(1, 1),
				new Point(1, 0),
				new Point(2, 0),
				new Point(3, 0) 
				};
		
		assertEquals(new Piece(originalPoints), squiggleCont.getPiece());

		
	}
	public void testFlipHController(){
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		testBullpen.setPieceSelected(squiggleCont);
		
		HorizontalFlipController flipHController = new HorizontalFlipController(bullpenView);
		
		button = lvlView.getHFlip();
		ActionEvent hFlip = buttonPress(button);
		flipHController.actionPerformed(hFlip);	
		
		Point[] flipHPoints = new Point[] {
				new Point(2, 0),
				new Point(2, 1),
				new Point(1, 1),
				new Point(0, 1),
				new Point(0, 2),
				new Point(0, 3)
		};
		
		assertEquals(new Piece(flipHPoints), squiggleCont.getPiece());
	}

	public void testFlipVController(){
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		testBullpen.setPieceSelected(squiggleCont);
		
		VerticalFlipController flipVController = new VerticalFlipController(bullpenView);
		
		button = lvlView.getVFlip();
		ActionEvent vFlip = buttonPress(button);
		flipVController.actionPerformed(vFlip);	
		
		Point[] flipVPoints = new Point[] {
				new Point(2, 0),
				new Point(2, 1),
				new Point(2, 2),
				new Point(1, 2),
				new Point(0, 2),
				new Point(0, 3)
		};
		
		assertEquals(new Piece(flipVPoints), squiggleCont.getPiece());
	}
	
	public void testResetController(){
		LevelView lvlView = new LevelView(model, view); 
		bullpenView = lvlView.getBullpen();
		board = lvlView.getBoard();
		testBullpen.setPieceSelected(squiggleCont);
		view.setCurrentView(new LevelSelectView(model, view));
		testLevel.incrementLimit();
		testLevel.incrementLimit();
		
		ResetController resetController = new ResetController(model, view);
		
		button = lvlView.getReset();
		ActionEvent reset = buttonPress(button);
		
		
		BoardController controller = new BoardController(view, testLevel, lvlView);
		
		MouseEvent movePiece = createMoved(lvlView, lvlView.getBoard(), 4, 5);
		controller.mouseMoved(movePiece);
		
		assertEquals(model.getPlayingLevel().getLevel().getBoard().getActiveDragged(), squiggleCont);
		
		MouseEvent pressed = createBoardPressed(lvlView, lvlView.getBoard(), 4, 5);
		controller.mousePressed(pressed);
		
		assertFalse(testBullpen.getPieces().contains(squiggleCont));
		assertFalse(model.getPlayingLevel().getLevel().getBoard().getPieces().isEmpty());
		assertTrue(testBoard.getPieces().contains(squiggleCont));
		assertEquals(model.getPlayingLevel().getLevel().getBoard().getActiveDragged(), null);
		
		resetController.actionPerformed(reset);	

		assertTrue(model.getPlayingLevel().getLevel().getBoard().getPieces().isEmpty());
		assertNull(model.getPlayingLevel().getLevel().getBoard().getActiveDragged());

	}

	
}
