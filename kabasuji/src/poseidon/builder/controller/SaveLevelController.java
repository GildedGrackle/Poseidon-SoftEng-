package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.EditPlayableBullpenView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.SaveLevelView;
import poseidon.entities.LevelModel;

/**
 * Open up a screen for the saving of a custom level in the builder.
 * 
 * @author Jacob
 */
public class SaveLevelController implements ActionListener {

	/** The state of the level under construction. */
	LevelModel model;
	/** The top-level GUI object. */
	LevelBuilderView view;
	
	/**
	 *  Constructor.
	 *  
	 *  @param view  The top-level GUI object.
	 */
	public SaveLevelController (LevelModel model, LevelBuilderView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 *  Creates and displays the save level screen.
	 *  
	 *  @param ae  The initiating event.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// Switch to new screen but don't set view.currentScreen, to allow for easier return
		SaveLevelView saveScreen = new SaveLevelView(model, view);
		view.getBuilder().setContentPane(saveScreen);
		
		// Display the new screen
		view.getBuilder().setVisible(true);
	}

}
