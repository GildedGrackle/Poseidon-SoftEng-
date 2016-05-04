package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import poseidon.builder.view.EditLevelView;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.entities.XMLHandler;

/**
 * Attempts to delete the file location associated with the specified level, remove from level list.
 * 
 * @author Jacob
 * @author Alex Titus
 */
public class DeleteLevelController implements ActionListener {
	
	/** The top-level entity. */
	LevelBuilderModel topModel;
	/** The edit level screen, with information about the selected level. */
	EditLevelView view;
	
	
	/**
	 * Constructor.
	 * 
	 * @param topModel  The top-level entity.
	 * @param level  The edit level screen, with information about the selected level.
	 */
	public DeleteLevelController(LevelBuilderModel topModel, EditLevelView view) {
		this.topModel = topModel;
		this.view = view;
	}
	
	
	/**
	 *  Attempts to delete the file location associated with the specified level, remove from level list.
	 *  
	 *  @param ae  The initiating event (unused).
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		LevelContainer selected = view.getSelectedLevel();
		if(selected == null)
		{
			// Bail
			return ;
		}
		File file = new File(XMLHandler.customDirectory + selected.getLevelFileName());
		
		if (file.exists()) {
			file.delete();
			
			// Remove the level from the model list and re-save the list file
			ArrayList<ArrayList<LevelContainer>> levelList = topModel.getSavedLevels();
			levelList.get(selected.getLevel().getGameMode()-1).remove(selected);
			ArrayList<String> namesAL = new ArrayList<String>();
			for (ArrayList<LevelContainer> sublist : levelList) {
				for (LevelContainer level : sublist) {
					namesAL.add(level.getLevelFileName());
				}
			}
			String[] names = namesAL.toArray(new String[namesAL.size()]);
			XMLHandler.saveFilenames(names, "customFilenames.xml", true);
		}
		
		view.setSelectedLevel(null);
		view.disableButtons();
		view.modelUpdated();
	}
}
