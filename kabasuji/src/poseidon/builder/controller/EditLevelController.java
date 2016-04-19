package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.EditLevelView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

public class EditLevelController implements ActionListener
{
	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object

	
	/**
	 *  Constructor
	 * @param view
	 */
	public EditLevelController(LevelBuilderModel model,  LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Edit Level screen
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toEditLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  Edit Level screen
	 */
	public Boolean toEditLevel()
	{
		EditLevelView newScreen = new EditLevelView(application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}
}
