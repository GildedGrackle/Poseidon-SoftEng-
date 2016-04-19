package poseidon.entities;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;

// Class to contain the save and load methods for level <-> XML
public class XMLHandler {

	XMLHandler () {}

	// Generate a new XML doc representing the given LevelModel at the specified location
	// !!!  Should this use a boolean return confirmation OR exception(s)?
	// !!! Input File or just a String path, either way will work
	boolean saveXML (LevelModel level, File file) {

		// --- level (root) ---
		Element levelElement = new Element("level");
		levelElement.setAttribute(new Attribute("gameMode", String.valueOf(level.gameMode)));

		// --- level.name ---
		Element nameElement = new Element("name");
		nameElement.setText(level.levelName);
		levelElement.addContent(nameElement);

		// --- level.countdown ---
		Element countdownElement = new Element("countdown");
		// Have to know which kind of countdown attribute to get, specify which level subclass it is (sketchy casting?)
		if (level.gameMode == 1) { // Puzzle
			countdownElement.setText(String.valueOf(((PuzzleLevel) level).allottedMoves));
		} else if (level.gameMode == 2) { // Lightning
			countdownElement.setText(String.valueOf(((LightningLevel) level).allottedTime));
		} else if (level.gameMode == 3) { // Release
			countdownElement.setText(String.valueOf(((ReleaseLevel) level).allottedPieces));
		} else { // Invalid level, can't save
			return false;
		}
		levelElement.addContent(countdownElement);

		// --- level.playableBullpen ---
		Element playableBullpenElement = new Element("playableBullpen");
		levelElement.addContent(playableBullpenElement);

		// --- level.playableBullpen.piece ---
		// Have to create as many elements as there are pieces in the playable bullpen
		Element [] pieceElement = new Element[level.playableBullpen.pieces.size()];
		// Now have to iterate through the list and look at the corresponding piece
		int index = 0;
		for (Element pceE : pieceElement) { // Could use a normal indexed for loop, but I really like these for-each loops
			Piece pieceTemp = level.playableBullpen.pieces.get(index).piece; // For convenient referencing
			pceE = new Element("piece" + String.valueOf(index)); // Set incremented names based on index
			Element [] pointElement = new Element[6]; // Need six points in each piece
			// We can go deeper!
			int subIndex = 0;
			for (Element pntE : pointElement) {
				pntE = new Element("point" + String.valueOf(subIndex)); // Incremented naming again
				// Simple to add row and column elements, just text contents
				pntE.addContent(new Element("row").setText(String.valueOf(pieceTemp.piece[subIndex].row)));
				pntE.addContent(new Element("column").setText(String.valueOf(pieceTemp.piece[subIndex].col)));
				pceE.addContent(pntE); // Add this point to the current piece (aren't double-nested for loops fun?)
				subIndex++;
			}
			playableBullpenElement.addContent(pceE); // Add this piece to the bullpen element
			index++;
		}

		// --- level.board ---
		Element boardElement = new Element("board");
		levelElement.addContent(boardElement);

		// --- level.board.square ---
		// Will have to create and iterate 144 (12*12) squares, same style as above
		Element [] squareElement = new Element[144];
		index = 0;
		for (Element sqrE : squareElement) {
			Square squareTemp = level.board.playArea[index];
			sqrE = new Element("square" + String.valueOf(index));
			if (squareTemp instanceof NonplayableSquare) {
				sqrE.setAttribute("type", "nonplayable");
			} else if (squareTemp instanceof PuzzleSquare) {
				sqrE.setAttribute("type", "puzzle");
				sqrE.addContent(new Element("isHint").setText(String.valueOf(((PuzzleSquare) squareTemp).isHint)));
			} else if (squareTemp instanceof LightningSquare) {
				sqrE.setAttribute("type", "lightning");
			} else if (squareTemp instanceof ReleaseSquare) {
				sqrE.setAttribute("type", "release");
				sqrE.addContent(new Element("isHint").setText(String.valueOf(((ReleaseSquare) squareTemp).isHint)));
				sqrE.addContent(new Element("isNumber").setText(String.valueOf(((ReleaseSquare) squareTemp).isNumber.number)));
				sqrE.addContent(new Element("isColor").setText(String.valueOf(((ReleaseSquare) squareTemp).isNumber.color)));
			} else {
				return false; // Invalid square, can't save
			}
			boardElement.addContent(sqrE);
			index++;
		}

		// --- level.isCustom ---
		Element isCustomElement = new Element("isCustom");
		isCustomElement.setText(String.valueOf(level.isCustom));
		levelElement.addContent(isCustomElement);

		// Generate new XML file at specified location
		Document doc = new Document(levelElement);
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter(file));
			return true; // Success (probably)
		} catch (Exception e) {
			return false;
		}
	}

	// Return a LevelModel based on the XML document at the specified location
	LevelModel loadXML (File file, boolean inBuilder) {
		if (!file.exists()) {
			return null; // Can't load a level that doesn't exist
		}

		// Turn file into a JDOM tree thing
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc;
		try {
			doc = saxBuilder.build(file);
		} catch (Exception e) {
			return null; // Can't load level as a valid XML JDOM tree
		}

		// Get all of the elements out of the tree
		Element levelElement = doc.getRootElement();
		int loadGameMode = Integer.parseInt(levelElement.getAttributeValue("gameMode"));

		Element nameElement = levelElement.getChild("name");

		Element countdownElement = levelElement.getChild("countdown");

		Element playableBullpenElement = levelElement.getChild("playableBullpen");
		Element [] pieceElement = new Element[(playableBullpenElement.getContentSize() - 1) / 2]; // Compensate for reading '/n' as element
		ArrayList<PieceContainer> loadPieces = new ArrayList<PieceContainer>();
		int index = 0;
		for (Element pceE : pieceElement) {
			pceE = playableBullpenElement.getChild("piece" + String.valueOf(index));
			Element [] pointElement = new Element[6];
			Point [] loadPoints = new Point[6];
			int subIndex = 0;
			for (Element pntE : pointElement) {
				pntE = pceE.getChild("point" + String.valueOf(subIndex));
				loadPoints[subIndex] = new Point(Integer.parseInt(pntE.getChildText("row")),
												 Integer.parseInt(pntE.getChildText("column")));
				subIndex++;
			}
			loadPieces.add(index, new PieceContainer(new Piece(loadPoints), null, false)); // !!! Location?
			index++;
		}
		Bullpen loadBullpen;
		if (inBuilder) {
			loadBullpen = new Bullpen(loadPieces, new BuilderBullpenLogic());
		} else if (loadGameMode == 1) {
			loadBullpen = new Bullpen(loadPieces, new PuzzleBullpenLogic());
		} else if (loadGameMode == 2) {
			loadBullpen = new Bullpen(loadPieces, new LightningBullpenLogic());
		} else if (loadGameMode == 2) {
			loadBullpen = new Bullpen(loadPieces, new ReleaseBullpenLogic());
		} else {
			return null; // Invalid gameMode
		}

		Element boardElement = levelElement.getChild("board");
		Element [] squareElement = new Element[(boardElement.getContentSize() - 1) / 2]; // Compensate for reading '/n' as element
		Square [] loadSquares = new Square [144];
		index = 0;
		for (Element sqrE : squareElement) {
			sqrE = boardElement.getChild("square" + String.valueOf(index));
			String type = sqrE.getAttributeValue("type");
			if (type.equals("nonplayable")) {
				loadSquares[index] = new NonplayableSquare();
			} else if (type.equals("puzzle")) {
				loadSquares[index] = new PuzzleSquare(false); // !!! isHint?
			} else if (type.equals("lightning")) {
				loadSquares[index] = new LightningSquare(false);
			} else if (type.equals("release")) {
				loadSquares[index] = new ReleaseSquare(false,
													   new ReleaseNumber(Integer.parseInt(sqrE.getChildText("isNumber")),
													   Integer.parseInt(sqrE.getChildText("isColor"))));
			} else {
				return null; // Invalid square type, can't load
			}
			index++;
		}
		Board loadBoard;
		if (inBuilder) {
			loadBoard = new Board(loadSquares, new BuilderBoardLogic());
		} else if (loadGameMode == 1) {
			loadBoard = new Board(loadSquares, new PuzzleBoardLogic());
		} else if (loadGameMode == 2) {
			loadBoard = new Board(loadSquares, new LightningBoardLogic());
		} else if (loadGameMode == 3) {
			loadBoard = new Board(loadSquares, new ReleaseBoardLogic());
		} else {
			return null; // Invalid gameMode
		}

		Element isCustomElement = levelElement.getChild("isCustom");

		// Construct level object
		LevelModel loadLevel;
		if (loadGameMode == 1) {
			loadLevel =  new PuzzleLevel(Integer.parseInt(countdownElement.getText()),
										nameElement.getText(),
										loadBullpen,
										loadBoard,
										Boolean.parseBoolean(isCustomElement.getText()));
		} else if (loadGameMode == 2) {
			loadLevel =  new LightningLevel(Integer.parseInt(countdownElement.getText()),
										   nameElement.getText(),  // TODO I switched this with loadBoard to meet new constructor param order
										   loadBullpen,
										   loadBoard,  // TODO hope that didn't break anything
										   Boolean.parseBoolean(isCustomElement.getText()));
		} else if (loadGameMode == 3) {
			loadLevel =  new ReleaseLevel(Integer.parseInt(countdownElement.getText()),
										 nameElement.getText(),  // TODO I switched this with loadBoard to meet new constructor param order
										 loadBullpen,
										 loadBoard,  // TODO hope that didn't break anything
										 Boolean.parseBoolean(isCustomElement.getText()));
		} else {
			return null; // Invalid gameMode, can't load
		}
		return loadLevel;
	}
	
	void test() {
		File testFile = new File("testXML.xml"); // !!! Relative path, need to figure out how final exe will work
		
		Point [] testPoints0 = new Point[6]; // Should be a 'T' looking thing?
		testPoints0[0] = new Point(0,0);
		testPoints0[1] = new Point(1,0);
		testPoints0[2] = new Point(2,0);
		testPoints0[3] = new Point(3,0);
		testPoints0[4] = new Point(0,-1);
		testPoints0[5] = new Point(0,1);
		Piece testPiece0 = new Piece (testPoints0);
		PieceContainer testPieceC0 = new PieceContainer(testPiece0, null, false);
		
		Point [] testPoints1 = new Point[6]; // Should be a 'c' looking thing?
		testPoints1[0] = new Point(0,0);
		testPoints1[1] = new Point(0,1);
		testPoints1[2] = new Point(0,2);
		testPoints1[3] = new Point(1,0);
		testPoints1[4] = new Point(2,0);
		testPoints1[5] = new Point(2,1);
		Piece testPiece1 = new Piece (testPoints1);
		PieceContainer testPieceC1 = new PieceContainer(testPiece1, null, false);
		
		ArrayList<PieceContainer> testPieces = new ArrayList<PieceContainer>();
		testPieces.add(testPieceC0);
		testPieces.add(testPieceC1);
		
		Bullpen testBullpen = new Bullpen(testPieces, null);
		
		Square [] testSquares = new Square[144];
		// Start with base of non-playable, create the playable ones on top
		for (int i=0; i < 144; i++) {
			testSquares[i] = new NonplayableSquare();
		}
		// Multiple square types ONLY for testing
		testSquares[1] = new PuzzleSquare(false);
		testSquares[3] = new LightningSquare(false);
		testSquares[5] = new ReleaseSquare(false, new ReleaseNumber(3,2));
		
		Board testBoard = new Board(testSquares, new PuzzleBoardLogic());
		
		PuzzleLevel testLevel = new PuzzleLevel(42, "testLevel", testBullpen, testBoard, true);
		
		
		
		// Test saveXML() and loadXML()
		XMLHandler testXMLHandler = new XMLHandler();
		testXMLHandler.saveXML(testLevel, testFile);
		testXMLHandler.loadXML(testFile, false);
	}
}
