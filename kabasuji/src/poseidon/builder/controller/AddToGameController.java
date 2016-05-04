package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.EditLevelView;
import poseidon.entities.LevelContainer;
import poseidon.entities.XMLHandler;

/**
 * Re-saves the specified level as being added to game.
 * 
 * @author Jacob
 * @author Alex Titus
 */
public class AddToGameController implements ActionListener {
	
	/** The edit level screen, with information about the selected level. */
	EditLevelView view;
	
	
	/**
	 * Constructor.
	 * 
	 * @param view The edit level screen, with information about the selected level.
	 */
	public AddToGameController(EditLevelView view) {
		this.view = view;
	}
	
	
	/**
	 *  Saves the specified level as being added to game (presumed to overwrite old version).
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
		selected.getLevel().setIsAddedToPlayer(true);
		XMLHandler.saveXML(selected.getLevel(), selected.getLevelFileName());
		
		view.setSelectedLevel(null);
		view.disableButtons();
		view.modelUpdated();
	}
}
