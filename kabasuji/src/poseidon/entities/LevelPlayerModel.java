package poseidon.entities;

import java.util.ArrayList;

public class LevelPlayerModel {
	// Where the player is on each mode: 1 - Puzzle, 2 - Lightning, 3 - Release
	// Zero-based (0 means level 1 is unlocked but not others)
	int[] currentLevel = new int[3];
	LevelModel playingLevel;  // The level being currently played
	LevelContainer[][] levels;  // The containers for the levels
	
	
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
		levels = new LevelContainer[3][5];
		
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
		}
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
	public LevelContainer[][] getLevels()
	{
		return levels;
	}
	public void setPlayingLevel(LevelModel newLevel)
	{
		playingLevel = newLevel;
	}

}
