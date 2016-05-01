package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import poseidon.entities.LevelContainer;
import poseidon.entities.XMLHandler;

/**
 * Attempts to delete the file location associated with the specified level.
 * 
 * @author Jacob
 */
public class DeleteLevelController implements ActionListener {
	
	/** The level model being deleted. */
	LevelContainer level;
	
	/**
	 * Constructor.
	 * 
	 * @param level The level to be deleted.
	 */
	public DeleteLevelController(LevelContainer level) {
		this.level = level;
	}
	
	/**
	 *  Attempts to delete the file location associated with the specified level.
	 *  
	 *  @param ae  The initiating event (unused).
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		File file = new File(XMLHandler.customDirectory + level.getLevelFileName());
		
		if (file.exists()) {
			file.delete();
		}
	}
}
