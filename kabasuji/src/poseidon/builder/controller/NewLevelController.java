package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.entities.LevelBuilderModel;

public class NewLevelController implements ActionListener
{
	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object
	
	
	/**
	 *  Constructor
	 * @param view
	 */
	public NewLevelController(LevelBuilderModel model,  LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  New Level screen
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toNewLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  New Level screen
	 */
	public Boolean toNewLevel()
	{
		NewLevelView newScreen = new NewLevelView(model, application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}

}
