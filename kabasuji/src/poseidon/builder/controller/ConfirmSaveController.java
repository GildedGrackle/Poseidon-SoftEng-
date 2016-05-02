package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import poseidon.builder.view.BuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.SaveLevelView;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.LevelModel;
import poseidon.entities.XMLHandler;

/**
 * Saves the level currently being built and returns to the build level screen (BuilderView).
 * 
 * @author Jacob
 */
public class ConfirmSaveController implements ActionListener {
	
	/** The top-level entity. */
	LevelBuilderModel topModel;
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
	public ConfirmSaveController(LevelBuilderModel topModel, LevelBuilderView view, SaveLevelView saveView, LevelModel level) {
		this.topModel = topModel;
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
		
		// Add the level to the model list and re-save the list file
		ArrayList<ArrayList<LevelContainer>> levelList = topModel.getSavedLevels();
		levelList.get(level.getGameMode()-1).add(
				new LevelContainer(level.getLevelName() + ".xml", 0, level.getGameMode(), level, 0));
		ArrayList<String> namesAL = new ArrayList<String>();
		for (ArrayList<LevelContainer> sublist : levelList) {
			for (LevelContainer level : sublist) {
				namesAL.add(level.getLevelFileName());
			}
		}
		String[] names = namesAL.toArray(new String[namesAL.size()]);
		XMLHandler.saveFilenames(names, "customFilenames.xml", true);
		
		// Set screen
		BuilderView currentScreen = (BuilderView) view.getCurrentScreen();
		view.getFrame().setContentPane(currentScreen);

		// Display new screen
		view.getFrame().setVisible(true);
	}
}
