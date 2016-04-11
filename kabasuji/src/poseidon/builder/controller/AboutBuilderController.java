package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.AboutBuilderView;
import poseidon.builder.view.LevelBuilderView;

public class AboutBuilderController implements ActionListener
{
//	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object
	
	
	public AboutBuilderController(/* LevelBuilderModel model, */ LevelBuilderView view)
	{
//		this.model = model;
		this.application = view;
	}


	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  About screen
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		toAbout();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelPlayerView to the
	 *  About screen
	 */
	public Boolean toAbout()
	{
		AboutBuilderView newScreen = new AboutBuilderView(application);  // The new screen to display
		
		// Set new screen
		application.setCurrentScreen(newScreen);
		application.getBuilder().setContentPane(newScreen);
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}

}
