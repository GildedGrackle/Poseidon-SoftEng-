package poseidon.tests;

import java.awt.Component;
import java.awt.event.ActionEvent;

import junit.framework.TestCase;
import poseidon.common.view.BullpenView;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.player.view.LevelPlayerView;

public class TestCommonControllers extends TestCase{

	LevelPlayerView view;
	LevelPlayerModel model;
	Piece piece;
	PieceContainer pieceCont;
	Point location;
	BullpenView bullpenView;
	Bullpen bullpen;
	
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, getName());
	}
	
	public void setUp(){
		
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
		pieceCont = new PieceContainer (piece, location, true);
		//bullpenView = new BullpenView(bullpen);
		
		
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
	
}
