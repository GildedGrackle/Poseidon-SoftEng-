package poseidon.tests;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.TestCase;
import poseidon.entities.Board;
import poseidon.entities.Bullpen;
import poseidon.entities.IBoardLogic;
import poseidon.entities.IBullpenLogic;
import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.LevelPlayerModel;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.PieceFactory;
import poseidon.entities.Point;
import poseidon.entities.ReleaseNumber;
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
	Board board;
	Square[][] playArea;
	
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
		view.getFrame().dispose();
	}
	
	public void testMakeHint(){
		
	}
	
	public void testMakePLayableSquare(){
		
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
		
		try {
			Point[] points = new Point[] {
					new Point(0, 0),
					new Point(0, 1),
					new Point(0, 2),
					new Point(1, 2),
					new Point(2, 2),
					new Point(0, 2)
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
	
	public class TestBullpenLogic implements IBullpenLogic{

		@Override
		public void afterPieceRemoved(Bullpen bullpen, PieceContainer piece)
		{
			return ;  // TODO  change this to suit purpose
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

	public class TestBoardLogic implements IBoardLogic{

		public Boolean shouldAddList (Board board, PieceContainer piece){
			return true;
		}
		
		public Boolean shouldRemovePiece (Board board, PieceContainer piece){
			return true;
		}
		public Boolean canSelectPieces(){
			return true;
		}
		
		public Boolean isValid(Board board, PieceContainer piece, Point location){
			return true;
		}
		public Boolean selectablePieceAt(Board board, int row, int col){
			return true;
		}

		@Override
		public Boolean canEdit() {
			return false;
		}

		@Override
		public Boolean shouldAddList() {
			return true;
		}

		@Override
		public void placePiece(Board board, PieceContainer piece) {
			return;
		}

		@Override
		public void setHint(Board board, int row, int col) {
			return;
		}
		
		@Override
		public void setReleaseNumber(Board board, int row, int col, ReleaseNumber rn) {
			return;
		}
	}
	
	public void testBoard(){
		// Just pulling one test level, easier than making a new one from scratch
		LevelModel testLevel = XMLHandler.getTestLevels()[0];

		Board testBoard = testLevel.getBoard();
		PieceContainer testPiece = testLevel.getPlayableBullpen().getPiece(0);
		Point testLocation = new Point(1,1);
		
		assertTrue(testBoard.isValid(testPiece, testLocation));
		testPiece.setLocation(testLocation);
		assertTrue(testBoard.addPiece(testPiece));

		testBoard.setActiveSource(testLocation); 
		testBoard.setActiveDragged(testPiece);
		testBoard.returnPiece();

		assertTrue(testBoard.canSelect(1,1));
		assertTrue(testBoard.selectPiece(1,1));
		assertEquals(testBoard.getActiveDragged(), testPiece);
		assertEquals(testBoard.getActiveSource(), testLocation);

		assertTrue(testBoard.removePiece(testPiece));
	}

	public void testXMLHandler() {
		XMLHandler.makeExampleLevels();
		
		LevelModel[] testLevels = XMLHandler.getTestLevels();
		LevelModel testLevelCustom = XMLHandler.getTestLevels()[1];
		testLevelCustom.setIsCustom(true);
		LevelModel[] testLevelsWrite = new LevelModel[]{testLevels[0],
														testLevels[1],
														testLevels[2],
														testLevelCustom};
		LevelModel[] testLevelsRead = new LevelModel[4];
		
		// Test filename list saving and loading
		String[] testStockNamesWrite = new String[]{"testPuzzle0.xml",
											   		"testLightning0.xml",
											   		"testRelease0.xml"};
		String[] testCustomNamesWrite = new String[]{"testCustom0.xml"};
		
		assertTrue(XMLHandler.saveFilenames(testStockNamesWrite, "testStockNames.xml", false));
		assertTrue(XMLHandler.saveFilenames(testCustomNamesWrite, "testCustomNames.xml", true));
		
		String[] testStockNamesRead = XMLHandler.loadFilenames("testStockNames.xml", false);
		String[] testCustomNamesRead = XMLHandler.loadFilenames("testCustomNames.xml", true);
		
		assertEquals(testStockNamesWrite[0], testStockNamesRead[0]);
		assertEquals(testStockNamesWrite[1], testStockNamesRead[1]);
		assertEquals(testStockNamesWrite[2], testStockNamesRead[2]);
		assertEquals(testCustomNamesWrite[0], testCustomNamesRead[0]);
		
		// Ensure that the levels save successfully
		assertTrue(XMLHandler.saveXML(testLevelsWrite[0], testStockNamesRead[0]));
		assertTrue(XMLHandler.saveXML(testLevelsWrite[1], testStockNamesRead[1]));
		assertTrue(XMLHandler.saveXML(testLevelsWrite[2], testStockNamesRead[2]));
		assertTrue(XMLHandler.saveXML(testLevelsWrite[3], testCustomNamesRead[0]));
		
		// Load the levels back in for comparison
		testLevelsRead[0] = XMLHandler.loadXML("testPuzzle0.xml", false, false);
		testLevelsRead[1] = XMLHandler.loadXML("testLightning0.xml", false, false);
		testLevelsRead[2] = XMLHandler.loadXML("testRelease0.xml", false, false);
		testLevelsRead[3] = XMLHandler.loadXML("testCustom0.xml", false, true);
		
		// Dig into the first level of each type and compare all of the values
		for (int i=0; i<4; i++) {
			assertTrue(testLevelsWrite[i].getLevelName().equals(testLevelsRead[i].getLevelName()));
			assertEquals(testLevelsWrite[i].getGameMode(), testLevelsRead[i].getGameMode());
			assertEquals(testLevelsWrite[i].getIsCustom(), testLevelsRead[i].getIsCustom());
			assertEquals(testLevelsWrite[i].getIsAddedToPlayer(), testLevelsRead[i].getIsAddedToPlayer());
			
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
		
		// Test progress saving and loading as well
		int[] testProgressWrite = new int[]{0,1,2};
		assertTrue(XMLHandler.saveProgressXML(testProgressWrite, "testProgress.xml"));
		int[] testProgressRead = XMLHandler.loadProgressXML("testProgress.xml"); 
		assertEquals(testProgressWrite[0], testProgressRead[0]);
		assertEquals(testProgressWrite[1], testProgressRead[1]);
		assertEquals(testProgressWrite[2], testProgressRead[2]);
	}

	public void testLevelPlayerModel() {
		int[] testProgress = new int[]{0,1,2};
		LevelPlayerModel testPlayer = new LevelPlayerModel(testProgress, null);
		
		assertEquals(testPlayer.getCurrentLevel(), testProgress);
		
		ArrayList<ArrayList<LevelContainer>> testLevels = testPlayer.getLevels();
		LevelContainer testLevel = testLevels.get(0).get(0);
		
		testPlayer.setPlayingLevel(testLevel);
		assertEquals(testLevel.getLevel(), testPlayer.getPlayingLevel().getLevel());
	}

	public void testPieceFactory(){
		PieceFactory pieceFactory = new PieceFactory();
		
		assertEquals(pieceFactory.getPiece(19), squigglePiece);
		assertEquals(pieceFactory.getRandomPiece().getClass(), squigglePiece.getClass());
		
	}
}
