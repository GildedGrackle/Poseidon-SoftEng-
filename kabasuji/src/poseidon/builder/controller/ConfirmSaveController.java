package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.SaveLevelView;
import poseidon.entities.LevelModel;
import poseidon.entities.XMLHandler;

/**
 * Saves the level currently being built and returns to the build level screen (BuilderView).
 * 
 * @author Jacob
 */
public class ConfirmSaveController implements ActionListener {
	
	/** The top-level GUI object. */
	LevelBuilderView view;
	/** The save screen, with the new level name and flag checkboxes. */
	SaveLevelView saveView;
	/** The level model being built. */
	LevelModel level;
	
	/**
	 * Constructor.
	 * 
	 * @param view		The top-level GUI object.
	 * @param saveView	The save screen, with the new level name and flag checkboxes.
	 * @param level		The level model being built.
	 */
	public ConfirmSaveController(LevelBuilderView view, SaveLevelView saveView, LevelModel level) {
		this.view = view;
		this.saveView = saveView;
		this.level = level;
	}
	
	/**
	 *  Saves the level using the new name and boolean flags, returns to the build level screen (BuilderView).
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Modify the level attributes
		level.setLevelName(saveView.getSaveName());
		level.setIsCustom(true); // Just in case
		level.setIsAddedToPlayer(saveView.getAddToGame());
		
		// Save the level in the appropriate location.
		XMLHandler.saveXML(level, level.getLevelName() + ".xml");
		
		// Set screen
		BuilderView currentScreen = (BuilderView) view.getCurrentScreen();
		view.getBuilder().setContentPane(currentScreen);

		// Display new screen
		view.getBuilder().setVisible(true);
	}
}
