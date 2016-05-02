package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

/**
 *  Sets the display back to the main menu.
 *  
 *  @author Alex Titus
 */
public class BackBuilderController implements ActionListener
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
	public BackBuilderController(LevelBuilderModel model, LevelBuilderView view)
	{
		this.model = model;
		this.application = view;
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  main menu screen.
	 *  
	 *  @param ae  the initiating event
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// Call testable method
		goBack();
	}
	
	
	/**
	 *  Switches the panel currently being displayed in LevelBuilderView to the
	 *  main menu screen.
	 *  
	 *  @return  Indication of whether operation completed successfully.
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
