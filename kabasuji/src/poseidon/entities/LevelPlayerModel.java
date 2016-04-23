package poseidon.entities;

import java.util.ArrayList;

/**
 *  The top-level model for the Level Player.
 *  
 *  Contains the player's progress on unlocking Levels, the Level currently
 *  being played, and all Levels currently part of the game (including
 *  player-made ones).
 *  
 *  @author Natalia Kononenko
 *  @author Alex Titus
 */
public class LevelPlayerModel {
	/** The number of game modes */
	public static final int NUM_GAMEMODES = 3;
	/** The containers for the levels ([gamemode][levelNumber]). */
	ArrayList<ArrayList<LevelContainer>> levels;
	/** Where the player is on each mode: 1 - Puzzle, 2 - Lightning, 3 - Release .
	 *  Zero-based (0 means level 1 is unlocked but not others) */
	int[] currentLevel = new int[NUM_GAMEMODES];
	/** The level being currently played. */
	LevelModel playingLevel;
	
	
	
	/**
	 *  Constructor
	 * @param current  designates player progress in each mode: 1 - Puzzle, 2 - Lightning, 3 - Release
	 * @param playing  reference to level currently being played; can be null if no level is being played
	 */
	public LevelPlayerModel (int[] current, LevelModel playing) {
		this.currentLevel = current;
		this.playingLevel = playing;
		
		initializeContainers();
	}
	
	
	/**
	 *  Gets the information about each Level to construct LevelContainer
	 */
	public void initializeContainers()
	{
		levels = new ArrayList<ArrayList<LevelContainer>>(NUM_GAMEMODES);  // Gamemodes
		for(int i = 0; i < NUM_GAMEMODES; i++)  // Levels
		{
			levels.add(new ArrayList<LevelContainer>());  // Default size of 10
		}
		XMLHandler xmlHandler = new XMLHandler();
		
		// TODO Probably change the file path, like level folder instead of just root
		
		// Assuming naming convention of gamemode1, gamemode2, ... gamemode5
		
		// Puzzle levels
		for (int i=0; i<5; i++) {
			String filePath = "puzzle"+String.valueOf(i)+".xml";
			PuzzleLevel levelTemp = (PuzzleLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(0).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(0).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Lightning Levels, same process
		for (int i=0; i<5; i++) {
			String filePath = "lightning"+String.valueOf(i)+".xml";
			LightningLevel levelTemp = (LightningLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(1).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(1).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		// Release Levels, same process again
		for (int i=0; i<5; i++) {
			String filePath = "release"+String.valueOf(i)+".xml";
			ReleaseLevel levelTemp = (ReleaseLevel) xmlHandler.loadXML(filePath, false);
			if (levelTemp == null) {
				// TODO Need to account for this properly, what if the level file doesn't exist?
				levels.get(2).add(new LevelContainer(null, 0, i, null, 0));
			} else {
				levels.get(2).add(new LevelContainer(filePath, 0, i, levelTemp, 0));
			}
		}
		
		/* TODO Copied into XMLHandler method, delete here?
		// This stuff is for demonstration purposes only, it should be read in
		Point[] piecePoints = {new Point(0,0), new Point(1,0), new Point(2,0), 
				new Point(0,1), new Point(0,2), new Point(1,1)};
		Piece p = new Piece(piecePoints);
		ArrayList<PieceContainer> pieces = new ArrayList<PieceContainer>();
		for(int i = 0; i < 10; i++)
		{
			pieces.add(new PieceContainer(p, new Point(-1, -1), false));
		}
		PuzzleBullpenLogic pbullLog = new PuzzleBullpenLogic();
		LightningBullpenLogic lbullLog = new LightningBullpenLogic();
		ReleaseBullpenLogic rbullLog = new ReleaseBullpenLogic();
		Square[] pPlayArea = new Square[144];
		Square[] lPlayArea = new Square[144];
		Square[] rPlayArea = new Square[144];
		for(int i = 0; i < 144; i++)
		{
			pPlayArea[i] = new PuzzleSquare(true);
			lPlayArea[i] = new LightningSquare(true);
			rPlayArea[i] = new ReleaseSquare(true, new ReleaseNumber(1, ReleaseNumber.GREEN));
		}
		PuzzleBoardLogic pborLog = new PuzzleBoardLogic();
		LightningBoardLogic lborLog = new LightningBoardLogic();
		ReleaseBoardLogic rborLog = new ReleaseBoardLogic();
		
		// TODO read in the information, rather than create it here
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				switch(i)
				{
				case 0:  // Puzzle levels
					levels[i][j] = new LevelContainer("Somewhere" + i + "-" + j, 0, j,
							new PuzzleLevel(10, "Level" + j, new Bullpen(pieces, pbullLog), 
									new Board(pPlayArea, pborLog), false), 0);
					break;
				case 1:  // Lightning levels
					levels[i][j] = new LevelContainer("Somewhere" + i + "-" + j, 0, j,
							new LightningLevel(10, "Level" + j, new Bullpen(pieces, lbullLog), 
									new Board(lPlayArea, lborLog), false), 0);
					break;
				case 2:  // Release levels
					levels[i][j] = new LevelContainer("Somewhere" + i + "-" + j, 0, j,
							new ReleaseLevel(10, "Level" + j, new Bullpen(pieces, pbullLog), 
									new Board(pPlayArea, pborLog), false), 0);
					break;
				}
			}
		}*/
	}
	
				/***********************
				 *  Getters & Setters  *
				 ***********************/
	public LevelModel getPlayingLevel()
	{
		return playingLevel;
	}
	public int[] getCurrentLevel()
	{
		return currentLevel;
	}
	public ArrayList<ArrayList<LevelContainer>> getLevels()
	{
		return levels;
	}
	public void setPlayingLevel(LevelModel newLevel)
	{
		playingLevel = newLevel;
	}

}
