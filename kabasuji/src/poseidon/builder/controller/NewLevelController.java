package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.builder.view.NewLevelView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Handles the creation and display of the new level creation screen.
 *  
 *  @author Alex Titus
 */
public class NewLevelController implements ActionListener
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
	public NewLevelController(LevelBuilderModel model,  LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  New Level screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toNewLevel();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  New Level screen.
	 *  
	 *  @return  Indicator of operation's exit status.
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
