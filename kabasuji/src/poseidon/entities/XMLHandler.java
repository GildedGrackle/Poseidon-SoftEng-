package poseidon.entities;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to contain the methods for saving and loading XML files
 * 
 * @author Jacob
 *
 */
public class XMLHandler {
	
	/**
	 * This is what the filenames are appended onto for saving and loading.
	 */
	String directory = "StockLevels/";

	/**
	 * Constructor, no parameters, just a method holder.
	 * 
	 */
	public XMLHandler() {};

	/**
	 * Generate a new XML doc representing the given LevelModel at the specified location.
	 * 
	 * @param 	level 		LevelModel representing the level to be saved.
	 * @param 	filePath 	String specifying where to save the file to.
	 * @return 				True if saved, false is failed.
	 */
	// TODO Should this use a boolean return confirmation or exception(s)?
	public boolean saveXML(LevelModel level, String filePath) {
		// Turn filePath into an actual File object and create directory
		File file = new File(directory + filePath);
		file.getParentFile().mkdirs();

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
			Square squareTemp = level.board.playArea[(int)index/12][index%12];
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
				sqrE.addContent(new Element("isNumber").setText(String.valueOf(((ReleaseSquare) squareTemp).number.number)));
				sqrE.addContent(new Element("isColor").setText(String.valueOf(((ReleaseSquare) squareTemp).number.color)));
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

	/**
	 * Create a LevelModel from the specified XML file
	 * 
	 * @param 	filePath 	String specifying where to load the file from.
	 * @param 	inBuilder 	Boolean to indicate if loading into the builder.
	 * @return 				LevelModel if successful, null if file doesn't exist or isn't valid.
	 */
	// TODO Should this use a null return or exception(s)?
	public LevelModel loadXML(String filePath, boolean inBuilder) {
		// Turn filePath into an actual File object
		File file = new File(directory + directory + filePath);
		
		// Check if the file actually exists
		if (!file.exists()) {
			return null;
		}

		// Turn file into a JDOM tree
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc;
		try {
			doc = saxBuilder.build(file);
		} catch (Exception e) {
			return null; // Can't load level as a valid XML JDOM tree
		}

		// Get all of the elements out of the tree, sort of the reverse of creating the XML
		Element levelElement = doc.getRootElement();
		int loadGameMode = Integer.parseInt(levelElement.getAttributeValue("gameMode"));

		Element nameElement = levelElement.getChild("name");

		int countdown = Integer.parseInt(levelElement.getChild("countdown").getText());

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
			loadPieces.add(index, new PieceContainer(new Piece(loadPoints), new Point(-1, -1), false));
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
		Square [][] loadSquares = new Square [12][12];
		index = 0;
		for (Element sqrE : squareElement) {
			sqrE = boardElement.getChild("square" + String.valueOf(index));
			String type = sqrE.getAttributeValue("type");
			if (type.equals("nonplayable")) {
				loadSquares[(int)(index/12)][index%12] = new NonplayableSquare();
			} else if (type.equals("puzzle")) {
				loadSquares[(int)(index/12)][index%12] = new PuzzleSquare(false);
			} else if (type.equals("lightning")) {
				loadSquares[(int)(index/12)][index%12] = new LightningSquare(false);
			} else if (type.equals("release")) {
				loadSquares[(int)(index/12)][index%12] = new ReleaseSquare(false,
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

		Boolean isCustom = Boolean.parseBoolean(levelElement.getChild("isCustom").getText());

		// Construct level object
		LevelModel loadLevel;
		if (loadGameMode == 1) {
			loadLevel =  new PuzzleLevel(countdown,
										 nameElement.getText(),
										 loadBullpen,
										 loadBoard,
										 isCustom);
		} else if (loadGameMode == 2) {
			loadLevel =  new LightningLevel(countdown,
										    nameElement.getText(),
										    loadBullpen,
										    loadBoard,
										    isCustom);
		} else if (loadGameMode == 3) {
			loadLevel =  new ReleaseLevel(countdown,
										  nameElement.getText(),
										  loadBullpen,
										  loadBoard,
										  isCustom);
		} else {
			return null; // Invalid gameMode, can't load
		}
		
		// All done!
		return loadLevel;
	}

	/**
	 * Generate fifteen example level xml files for loading.
	 * 
	 * WARNING: Watch out for overwriting, these use the standard naming convention.
	 */
	public void makeExampleLevels() {
		LevelModel[][] levels = new LevelModel[3][5];
		
		Point[] piecePoints = {new Point(0,0), new Point(1,0), new Point(2,0), 
							   new Point(0,1), new Point(0,2), new Point(1,1)};
		Piece p = new Piece(piecePoints);
		
		ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
		for(int i = 0; i < 10; i++) {
			pieces.add(new PieceContainer(p, new Point(-1, -1), false));
		}
		
		PuzzleBullpenLogic pbullLog = new PuzzleBullpenLogic();
		LightningBullpenLogic lbullLog = new LightningBullpenLogic();
		ReleaseBullpenLogic rbullLog = new ReleaseBullpenLogic();
		
		Bullpen pbull = new Bullpen(pieces, pbullLog);
		Bullpen lbull = new Bullpen(pieces, lbullLog);
		Bullpen rbull = new Bullpen(pieces, rbullLog);
		
		Square[][] pPlayArea = new Square[12][12];
		Square[][] lPlayArea = new Square[12][12];
		Square[][] rPlayArea = new Square[12][12];
		for(int i = 0; i < 144; i++) {
			pPlayArea[(int)(i/12)][i%12] = new PuzzleSquare(true);
			lPlayArea[(int)(i/12)][i%12] = new LightningSquare(true);
			rPlayArea[(int)(i/12)][i%12] = new ReleaseSquare(true, new ReleaseNumber(1, ReleaseNumber.GREEN));
		}
		
		PuzzleBoardLogic pborLog = new PuzzleBoardLogic();
		LightningBoardLogic lborLog = new LightningBoardLogic();
		ReleaseBoardLogic rborLog = new ReleaseBoardLogic();
		
		Board pbor = new Board(pPlayArea, pborLog);
		Board lbor = new Board(lPlayArea, lborLog);
		Board rbor = new Board(rPlayArea, rborLog);

		// Fill the levels matrix with identical levels of each gamemode
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 5; j++) {
				switch(i) {
				case 0:  // Puzzle levels
					levels[i][j] = new PuzzleLevel(15,
												   "puzzle" + String.valueOf(j),
												   pbull,
												   pbor,
												   false);
					break;
				case 1:  // Lightning levels
					levels[i][j] = new LightningLevel(60,
												   "lightning" + String.valueOf(j),
												   lbull,
												   lbor,
												   false);
					break;
				case 2:  // Release levels
					levels[i][j] = new ReleaseLevel(10,
												   "release" + String.valueOf(j),
												   rbull,
												   rbor,
												   false);
					break;
				}
				
				// Save the new level as xml
				saveXML(levels[i][j], directory + levels[i][j].levelName + ".xml");
			}
		}
	}
	
	/**
	 * Save a simple xml file for storing the progress variables
	 * 
	 * @param 	progress 	int[3] of the progress variables, from the LevelPlayerModel.
	 * @param 	filePath 	String specifying where to save the file to.
	 * @return 				True if saved, false is failed.
	 */
	public boolean saveProgressXML(int[] progress, String filePath) {
		// Turn filePath into an actual File object and create directory
		File file = new File(directory + filePath);
		file.getParentFile().mkdirs();

		// Root element
		Element progressElement = new Element("progress");

		// The three values, zero-based (should be 0-4)
		Element puzzleElement = new Element("puzzle");
		puzzleElement.setText(String.valueOf(progress[0]));
		progressElement.addContent(puzzleElement);
		
		Element lightningElement = new Element("lightning");
		lightningElement.setText(String.valueOf(progress[1]));
		progressElement.addContent(lightningElement);
		
		Element releaseElement = new Element("release");
		releaseElement.setText(String.valueOf(progress[2]));
		progressElement.addContent(releaseElement);
		
		// Generate new XML file at specified location
		Document doc = new Document(progressElement);
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, new FileWriter(file));
			return true; // Success (probably)
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Create an int[3] from the specified xml file.
	 * 
	 * @param 	filePath 	String specifying where to load the file from.
	 * @return				int[3] if successful, null if file doesn't exist or isn't valid.
	 */
	public int[] loadProgressXML(String filePath) {
		// Turn filePath into an actual File object
		File file = new File(directory + directory + filePath);

		// Check if the file actually exists
		if (!file.exists()) {
			return null;
		}

		// Turn file into a JDOM tree
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc;
		try {
			doc = saxBuilder.build(file);
		} catch (Exception e) {
			return null; // Can't load level as a valid XML JDOM tree
		}

		// Get all of the elements out of the tree, sort of the reverse of creating the XML
		Element progressElement = doc.getRootElement();
		int puzzle = Integer.parseInt(progressElement.getChildText("puzzle"));
		int lightning = Integer.parseInt(progressElement.getChildText("lightning"));
		int release = Integer.parseInt(progressElement.getChildText("release"));
		
		// Prevent impossible progress
		puzzle = (puzzle > 4)? 4 : puzzle;
		lightning = (lightning > 4)? 4 : lightning;
		release = (release > 4)? 4 : release;
		
		int[] progress = new int[]{puzzle, lightning, release};
		
		return progress;
	}
}
