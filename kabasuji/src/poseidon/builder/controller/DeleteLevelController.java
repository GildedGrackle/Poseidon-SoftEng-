package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.XMLHandler;

/**
 * Attempts to delete the file location associated with the specified level, remove from level list.
 * 
 * @author Jacob
 */
public class DeleteLevelController implements ActionListener {
	
	/** The top-level entity. */
	LevelBuilderModel topModel;
	/** The level model being deleted. */
	LevelContainer level;
	
	
	/**
	 * Constructor.
	 * 
	 * @param level The level to be deleted.
	 */
	public DeleteLevelController(LevelBuilderModel topModel, LevelContainer level) {
		this.topModel = topModel;
		this.level = level;
	}
	
	
	/**
	 *  Attempts to delete the file location associated with the specified level, remove from level list.
	 *  
	 *  @param ae  The initiating event (unused).
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		File file = new File(XMLHandler.customDirectory + level.getLevelFileName());
		
		if (file.exists()) {
			file.delete();
			
			// Remove the level from the model list and re-save the list file
			ArrayList<ArrayList<LevelContainer>> levelList = topModel.getSavedLevels();
			levelList.get(level.getLevel().getGameMode()-1).remove(level);
			ArrayList<String> namesAL = new ArrayList<String>();
			for (ArrayList<LevelContainer> sublist : levelList) {
				for (LevelContainer level : sublist) {
					namesAL.add(level.getLevelFileName());
				}
			}
			String[] names = namesAL.toArray(new String[namesAL.size()]);
			XMLHandler.saveFilenames(names, "customFilenames.xml", true);
		}
	}
}
