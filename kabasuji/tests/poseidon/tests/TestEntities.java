package poseidon.tests;

import junit.framework.TestCase;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.player.view.LevelPlayerView;

public class TestEntities extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	Piece piece; 
	PieceContainer pieceCont; 
	
	
	public void setUp(){
		view = new LevelPlayerView(model);

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
	
	
	public void testGetScore(){
		
	}
	
	public void testGetBestScore(){
		
	}
	/**
	 * Test of the piece entity. 
	 * First Checks that we can make a piece and that the new equals works
	 * Second tests that we can rotate the piece Clock Wise 
	 * Third tests that we can rotate that piece back to the original position using Counter clockwise  
	 */
	public void testPiece(){
		Point[] points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3)
		};
		piece = new Piece(points);
		assertTrue(piece.equals(new Piece(points)));
		
		piece.rotateCW();
		
		Point[] rotatePoints = new Point[] {
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 1),
				new Point(2, 0),
				new Point(3, 0)
		};
		
		assertEquals(new Piece(rotatePoints), piece);
		
		piece.rotateCCW();
		
		Point[] originalPoints = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3) 
				};
		
		assertEquals(new Piece(originalPoints), piece);
		
	}
	
	public void testPieceFlips(){
		Point[] points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3)
		};
		piece = new Piece(points);
		
		Point[] flipHPoints = new Point[] {
				new Point(2, 0),
				new Point(2, 1),
				new Point(1, 1),
				new Point(0, 1),
				new Point(0, 2),
				new Point(0, 3)
		};
		
		piece.flipHorizontal();
		
		assertEquals(new Piece(flipHPoints), piece);
		
		piece.flipHorizontal();
		
		Point[] flipVPoints = new Point[] {
				new Point(2, 0),
				new Point(2, 1),
				new Point(2, 2),
				new Point(1, 2),
				new Point(0, 2),
				new Point(0, 3)
		};
		
		piece.flipVertical();
		
		assertEquals(new Piece(flipVPoints), piece);
		
	}
	
	public void testIllegalPiece(){
		try {
			Point[] points = new Point[] {
					new Point(0, 0),
					new Point(0, 1),
					new Point(0, 2),
					new Point(1, 2),
					new Point(2, 2),
					new Point(2, 3),
					new Point(0, 3)
			};
			
			piece = new Piece(points);
			
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Expected IllegalArgumentException");
		}
	}
	
	public void testPieceContainer(){
		Point[] points = new Point[] {
				new Point(0, 0),
				new Point(0, 1),
				new Point(0, 2),
				new Point(1, 2),
				new Point(2, 2),
				new Point(2, 3)
		};
		
		piece = new Piece(points);
		
		Point location = new Point(0,0);
		
		pieceCont = new PieceContainer(piece, location, false);
		
		assertEquals(new PieceContainer((new Piece(points)), location, false), pieceCont);
		
	}
	
	
}
