package poseidon.tests;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.IBullpenLogic;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.LightningSquare;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.Point;
import poseidon.entities.PuzzleBullpenLogic;
import poseidon.entities.PuzzleSquare;
import poseidon.entities.ReleaseNumber;
import poseidon.entities.ReleaseSquare;
import poseidon.entities.Square;
import poseidon.entities.XMLHandler;
import poseidon.player.view.LevelPlayerView;

public class TestEntities extends TestCase{
	
	LevelPlayerView view;
	LevelPlayerModel model;
	Piece piece; 
	PieceContainer pieceCont; 
	Piece squigglePiece;
	Piece linePiece;
	PieceContainer squiggleCont;
	PieceContainer lineCont;
	Point location1; 
	Point location2; 
	ArrayList<PieceContainer> pieces;
	Bullpen bullpen; 
	IBullpenLogic logic; 
	
	public void setUp(){
		view = new LevelPlayerView(model);
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
		
		pieceCont = new PieceContainer(piece, location);
		
		assertEquals(new PieceContainer((new Piece(points)), location), pieceCont);
		
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
	
	public void testBullpen(){
		Point squiggleLoc = new Point(0,0);
		Point lineLoc = new Point(0,6);
		squiggleCont = new PieceContainer(squigglePiece, squiggleLoc);
		lineCont = new PieceContainer(linePiece, lineLoc);
		
		
		pieces = new ArrayList<PieceContainer>();
		pieces.add(squiggleCont);
		pieces.add(lineCont);
		
		bullpen = new Bullpen(pieces, new TestBullpenLogic());
		
		assertEquals(bullpen.getLocation(lineCont), 1);
		assertEquals(bullpen.getSize(), 2);
		assertEquals(bullpen.getPieces(), pieces);
		
		
		bullpen.removePiece(squiggleCont);
		
		assertEquals(bullpen.getSize(), 1);
		assertEquals(bullpen.getPiece(0), lineCont);
		
		bullpen.addPiece(squiggleCont);
		
		assertEquals(bullpen.getSize(), 2);
		assertEquals(new HashSet<>(bullpen.getPieces()), 
					 new HashSet<>(pieces));
		
	}
	
	public void testSelectBullpenPeice(){
		Point squiggleLoc = new Point(0,0);
		Point lineLoc = new Point(0,6);
		squiggleCont = new PieceContainer(squigglePiece, squiggleLoc );
		lineCont = new PieceContainer(linePiece, lineLoc);
		
		
		pieces = new ArrayList<PieceContainer>();
		pieces.add(squiggleCont);
		pieces.add(lineCont);
		
		bullpen = new Bullpen(pieces, new TestBullpenLogic());
		
		bullpen.setPieceSelected(squiggleCont);
		
		assertEquals(squiggleCont, bullpen.getPieceSelected());
		
		bullpen.setPieceSelected(lineCont);
		
		assertEquals(lineCont, bullpen.getPieceSelected());
		
		bullpen.setPieceSelected(lineCont);
		
		assertEquals(null, bullpen.getPieceSelected());
		
	}

	public void testXMLHandler() {
		XMLHandler testXML = new XMLHandler();
		testXML.makeExampleLevels();
		
		LevelModel[] testLevelsWrite = testXML.getTestLevels();
		LevelModel[] testLevelsRead = new LevelModel[3];
		
		// Ensure that the levels save successfully
		assertTrue(testXML.saveXML(testLevelsWrite[0], "puzzle0.xml"));
		assertTrue(testXML.saveXML(testLevelsWrite[1], "lightning0.xml"));
		assertTrue(testXML.saveXML(testLevelsWrite[2], "release0.xml"));
		
		// Load the levels back in for comparison
		testLevelsRead[0] = testXML.loadXML("puzzle0.xml", false);
		testLevelsRead[1] = testXML.loadXML("lightning0.xml", false);
		testLevelsRead[2] = testXML.loadXML("release0.xml", false);
		
		// Dig into the first level of each type and compare all of the values
		for (int i=0; i<3; i++) {
			assertTrue(testLevelsWrite[i].getLevelName().equals(testLevelsRead[i].getLevelName()));
			assertEquals(testLevelsWrite[i].getGameMode(), testLevelsRead[i].getGameMode());
			
			Bullpen testBullpenWrite = testLevelsWrite[i].getPlayableBullpen();
			Bullpen testBullpenRead = testLevelsRead[i].getPlayableBullpen();
			assertEquals(testBullpenWrite.getSize(), testBullpenRead.getSize());
			for (int j=0; j<testBullpenWrite.getSize(); j++) {
				Point[] testPieceWrite = testBullpenWrite.getPiece(j).getPiece().getPiece();
				Point[] testPieceRead = testBullpenRead.getPiece(j).getPiece().getPiece();
				for (int k=0; k<6; k++) {
					assertEquals(testPieceWrite[k].getRow(), testPieceRead[k].getRow());
					assertEquals(testPieceWrite[k].getCol(), testPieceRead[k].getCol());
				}
			}
			
			Square[][] testBoardWrite = testLevelsWrite[i].getBoard().getPlayArea();
			Square[][] testBoardRead = testLevelsRead[i].getBoard().getPlayArea();
			for (int j=0; j<12; j++) {
				for (int k=0; k<12; k++) {
					assertEquals(testBoardWrite[j][k].isFilled(), testBoardRead[j][k].isFilled());
					assertEquals(testBoardWrite[j][k].getType(), testBoardRead[j][k].getType());
					if (i == 2) {
						assertEquals(testBoardWrite[j][k].getReleaseNumber().getColor(), testBoardRead[j][k].getReleaseNumber().getColor());
						assertEquals(testBoardWrite[j][k].getReleaseNumber().getNumber(), testBoardRead[j][k].getReleaseNumber().getNumber());
					}
				}
			}
		}
		
		// Test progress saving as well
		int[] testProgressWrite = new int[]{0,1,2};
		assertTrue(testXML.saveProgressXML(testProgressWrite, "testProgress.xml"));
		int[] testProgressRead = testXML.loadProgressXML("testProgress.xml");
		assertEquals(testProgressWrite[0], testProgressRead[0]);
		assertEquals(testProgressWrite[1], testProgressRead[1]);
		assertEquals(testProgressWrite[2], testProgressRead[2]);
	}
	
	

}
