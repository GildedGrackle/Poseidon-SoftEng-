package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;

public class BackBuilderController implements ActionListener
{
//	LevelBuilderModel model;  // The top-level entity object, representing the application's state
	LevelBuilderView application;  // The top-level GUI object
	
	
	public BackBuilderController(/* LevelBuilderModel model, */ LevelBuilderView view)
	{
		//this.model = model;
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
		goBack();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  About screen
	 */
	public Boolean goBack()
	{	
		// Reset LBV to original view
		application.setCurrentScreen(null);
		application.initialize();
		
		// Display the new screen
		application.getBuilder().setVisible(true);
		
		return true;
	}

}
