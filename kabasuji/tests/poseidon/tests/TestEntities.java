package poseidon.tests;

import junit.framework.TestCase;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.Point;
import poseidon.player.view.LevelPlayerView;

public class TestEntities extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	Piece piece; 
	
	
	public void setUp(){
		view = new LevelPlayerView(model);
		Point[] points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(0, 3),
				new Point(0, 4),
				new Point(0, 5)
		};
		piece = new Piece(points);
	}
	
	public void tearDown(){
		view.getfrmKabasuji().dispose();
	}
	
	public void testMakeHint(){
		
	}
	
	public void testMakePLayableSquare(){
		
	}
	
	public void testUndoManager(){
		
	}
	
	public void testSetReleaseNumber(){
		
	}
	
	public void testGetBoard(){
		
	}
	
	public void testGetLevel(){
		
	}
	
	public void testGetScore(){
		
	}
	
	public void testGetBestScore(){
		
	}
	
	public void testPiece(){
		
	}
	
	
}
