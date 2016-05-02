package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.entities.LevelContainer;
import poseidon.entities.XMLHandler;

/**
 * Re-saves the specified level as being added to game.
 * 
 * @author Jacob
 */
public class AddToGameController implements ActionListener {
	
	/** The level model being deleted. */
	LevelContainer level;
	
	
	/**
	 * Constructor.
	 * 
	 * @param level The level to be modified.
	 */
	public AddToGameController(LevelContainer level) {
		this.level = level;
	}
	
	
	/**
	 *  Saves the specified level as being added to game (presumed to overwrite old version).
	 *  
	 *  @param ae  The initiating event (unused).
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		level.getLevel().setIsAddedToPlayer(true);
		XMLHandler.saveXML(level.getLevel(), level.getLevelFileName());
	}
}
