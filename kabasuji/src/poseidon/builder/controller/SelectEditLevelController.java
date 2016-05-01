package poseidon.builder.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.EditLevelIcon;
import poseidon.builder.view.EditLevelView;

/**
 *  Handles selecting a level to edit.
 *  
 *  @author Alex Titus
 *
 */
public class SelectEditLevelController implements ActionListener
{
	/** The GUI object to modify. */
	EditLevelView view;


	/**
	 *  Constructor.
	 *  
	 *  @param view  The GUI object to modify
	 */
	public SelectEditLevelController(EditLevelView view)
	{
		this.view = view;
	}

	
	/**
	 *  Sets the selectedLevel LevelContainer in LevelSelectView to the
	 *  LevelContainer associated with the StarView that produced this
	 *  ActionEvent
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Set new selected level
		EditLevelIcon source = (EditLevelIcon) ae.getSource();
		view.setSelectedLevel(source.getLevelContainer());

		// Update display
		view.resetSelectColors();
		source.setBackground(Color.yellow);
		view.enableButtons();
		view.repaint();
	}

}
