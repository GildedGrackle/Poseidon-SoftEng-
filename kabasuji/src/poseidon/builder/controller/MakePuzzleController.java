package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.Board;
import poseidon.entities.BuilderBoardLogic;
import poseidon.entities.BuilderBullpenLogic;
import poseidon.entities.Bullpen;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.PieceContainer;
import poseidon.entities.PuzzleLevel;
import poseidon.entities.PuzzleSquare;
import poseidon.entities.Square;

/**
 *  Creates a new Puzzle Level and displays it on the screen.
 *  
 *  The new Level has a 12x12 Board consisting entirely of PuzzleSquares. None
 *  of the squares are hints. Initial allotted moves are 10.
 *  The LevelContainer for the Level has no file name. Max score is set to zero.
 *  "inGame" indicator is set to indicate unadded saved game. No level number is assigned.
 *  
 * @author Alex Titus
 */
public class MakePuzzleController implements ActionListener
{
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level object of the application's state
	 *  @param view  the top-level GUI object
	 */
	public MakePuzzleController(LevelBuilderModel model, LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Level Builder screen, with a new Puzzle Level ready to build.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toPuzzleLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Level Builder screen, with a new Puzzle Level ready to build.
	 */
	public Boolean toPuzzleLevel()
	{
		// Create new Level and LevelContainer
		Square[][] newPlayArea = new Square[12][12];
		for(int i = 0; i < Board.MAXROWS; i++)  // Fill newPlayArea with empty PuzzleSquares
		{
			for(int j = 0; j < Board.MAXCOLS; j++)
			{
				newPlayArea[i][j] = new PuzzleSquare(false);
			}
		}
		BuilderBullpenLogic newBullpenLogic = new BuilderBullpenLogic();
		BuilderBoardLogic newBoardLogic = new BuilderBoardLogic();
		PuzzleLevel newLevel = new PuzzleLevel(10, "New Puzzle Level",
				new Bullpen(new ArrayList<PieceContainer>(), newBullpenLogic),
				new Bullpen(newBullpenLogic), new Board(newPlayArea, newBoardLogic), true);
		LevelContainer newContainer = new LevelContainer("", 3, 0, newLevel, 0);  // TODO use correct "inGame" input
		model.setBuildingLevel(newContainer);
		
		// Set new screen
		BuilderView newScreen = new BuilderView(model, application);  // The new screen to display
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}
}
