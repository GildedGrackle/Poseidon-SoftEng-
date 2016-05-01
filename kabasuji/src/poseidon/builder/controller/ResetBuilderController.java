package poseidon.builder.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.XMLHandler;


/**
 *  Handles resetting a Level back to its initial state.
 *  
 *  @author Morgan Hopeman 
 */

public class ResetBuilderController implements ActionListener {

	/** The top-level model. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView game;
	
	MakePuzzleController makePuzCont;
	MakeLightningController makeLightCont;
	MakeReleaseController makeReleaseCont;
	JButton button;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the model of the level to reset
	 *  @param view  the representation of the level
	 */
	public ResetBuilderController(LevelBuilderModel model, LevelBuilderView game)
	{
		this.model = model;
		this.game = game;
	}
	
	
	private ActionEvent buttonPress(Component button) {
		return new ActionEvent(button, 0, "");
		}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		resetBuilder();

	}

	/**
	 * Resets the Level back to its initial state.
	 *  
	 * Current score earned on the Level will be lost.
	 * 
	 * @return true if successful
	 */
	boolean resetBuilder() {
		
		// Reload the current level
		LevelModel currentLevel = model.getBuildingLevel();
		
		if(currentLevel.getGameMode() == 1){
			makePuzCont = new MakePuzzleController(model, game);
			makePuzCont.toPuzzleLevel();
		}
		
		else if(currentLevel.getGameMode() == 2){
			makeLightCont = new MakeLightningController(model, game);
			makeLightCont.toLightningLevel();
		}
		
		else if(currentLevel.getGameMode() == 3){
			makeReleaseCont = new MakeReleaseController(model, game);
			makeReleaseCont.toReleaseLevel();
		}
		
		return true;
	}
}
