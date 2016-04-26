package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import poseidon.common.controller.BoardController;
import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.common.view.PieceView;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
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

public class TestCommonControllers extends TestMouseEvents{

	LevelPlayerView view;
	LevelPlayerModel model;
	Piece piece;
	PieceContainer pieceCont;
	Point location;
	BullpenView bullpenView;
	Bullpen bullpen;
	XMLHandler testXML;
	LevelModel testLevel;
	Board testBoard;
	PieceContainer testPiece;
	Point testLocation;
	LevelBuilderModel builderModel;
	BoardView board;
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public void setUp(){
		
		testXML = new XMLHandler();
		testLevel = testXML.getTestLevels()[0];

		testBoard = testLevel.getBoard();
		testPiece = testLevel.getPlayableBullpen().getPiece(0);
		testLocation = new Point(1,1);
		model = new LevelPlayerModel(null, testLevel);
		builderModel = new LevelBuilderModel();
		board = new BoardView(testBoard);
		
		view = new LevelPlayerView(model);
		Point[] points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3)
		};
		piece = new Piece(points);
		location = new Point(0,0);
		pieceCont = new PieceContainer (piece, location);
		bullpenView = new BullpenView(bullpen);
		
		
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
	
	public void testBoardCont(){

		testPiece.setIsSelected(true);
		LevelView lvlView = new LevelView(model, view); 
		PieceView testPieceView = new PieceView(pieceCont, bullpenView);
		
		MouseEvent enterBoard = createEntered(lvlView, board, 0, 0);
		board.getMouseManager().handleMouseEvents();
		
		
		
	}
	
}
