package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.EditLevelView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Creates and displays the edit level screen.
 *  
 *  @author Alex Titus
 */
public class ToEditLevelController implements ActionListener
{
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object
	 *  @param view  the top-level GUI object
	 */
	public ToEditLevelController(LevelBuilderModel model,  LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  edit level screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toEditLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  edit level screen.
	 *  
	 *  @return  Indication of whether operation completed successfully.
	 */
	public Boolean toEditLevel()
	{
		EditLevelView newScreen = new EditLevelView(model, application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}
}
