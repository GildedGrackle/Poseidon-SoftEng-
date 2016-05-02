package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Creates and displays the "About" screen.
 *  
 *  @author Alex Titus
 */
public class AboutBuilderController implements ActionListener
{
	/** The top-level entity object, representing the application's state. */
	LevelBuilderModel model;
	/** The top-level GUI object. */
	LevelBuilderView application;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object, representing the application's state
	 *  @param view  the top-level GUI object
	 */
	public AboutBuilderController(LevelBuilderModel model, LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  About screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toAbout();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen.
	 *  
	 *  @return  Indication of whether operation completed successfully.
	 */
	public Boolean toAbout()
	{
		AboutBuilderView newScreen = new AboutBuilderView(model, application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}

}
